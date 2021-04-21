package com.utn.parcial.service;

import com.utn.parcial.model.Currency;
import com.utn.parcial.model.Jugador;
import com.utn.parcial.model.Persona;
import com.utn.parcial.model.Representante;
import com.utn.parcial.service.CurrencyService;
import com.utn.parcial.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
public class PersonaService {

    PersonaRepository personaRepository;
    CurrencyService currencyService;

    @Autowired
    public PersonaService (PersonaRepository personaRepository,CurrencyService currencyService){
        this.personaRepository=personaRepository;
        this.currencyService=currencyService;
    }

    public void addPersona(Persona persona){
        personaRepository.save(persona);
    }

    public List<Persona>getPersona(){
        return personaRepository.findAll();
    }

    public void addJugadorToPerson(Integer id, Integer persona_id) throws Throwable {
        Persona persona = getById(id);
        Persona jugador= persona;
    }


    public Persona getById(Integer id) throws Throwable {
        return (Persona) personaRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

        //return personaRepository.findById(id)
          //      .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public void addJugadorToRepresentante(Integer id, Integer persona_id) throws Throwable {
        Persona jugador = getById(id);
        Persona representante = getById(persona_id);

        //check if they exist and are what they are supposed to be
        if(!(representante instanceof Representante) || !(jugador instanceof Jugador) ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }/*
        //check if player already in list
        if(((representante) Representante).getPlayers().contains(jugador)){
            throw new AlreadyInList("player with id " + idPlayer);
        }*/
        //finally add it
        ((Representante) representante).getJugadores().add((Jugador) jugador);
        ((Representante) representante).setPesoDeLaBobeda(((Jugador) jugador).getCurrency().getMonto());
        //((Representante) representante).getJugadores().add((Jugador) jugador);
        personaRepository.save(representante);

        //return
    }

    public void deletePersona(Integer id) throws Throwable {
        Persona persona= getById(id);
        personaRepository.delete(persona);
    }
    public void addCurrencyToJugador(Integer id,Integer id_currency) throws Throwable {
        Persona jugador =getById(id);
        Currency currency= currencyService.getById(id_currency);
        ((Jugador) jugador).setCurrency(currency);
        personaRepository.save(jugador);
    }

}
