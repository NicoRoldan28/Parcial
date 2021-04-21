package com.utn.parcial.service;

import com.utn.parcial.model.*;
import com.utn.parcial.service.CurrencyService;
import com.utn.parcial.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    public void addJugadorToPerson(Integer id, Integer persona_id){
        Persona persona = getById(id);
        Persona jugador= persona;
    }

    public Persona getById(Integer id){
        return personaRepository.findById(id)
                .orElseThrow(()->new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public void addJugadorToRepresentante(Integer id, Integer persona_id) {
        Persona jugador = getById(id);
        Persona representante = getById(persona_id);

        if(!(representante instanceof Representante) || !(jugador instanceof Jugador) ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        ((Representante) representante).getJugadores().add((Jugador) jugador);
        ((Representante) representante).setPesoDeLaBobeda(((Jugador) jugador).getCurrency().getMonto());

        personaRepository.save(representante);
    }

    public void addAmigoToRepresentante(Integer representante_id, Integer amigo_id){
        Persona amigo= getById(amigo_id);
        Persona representante = getById(representante_id);

        if(!(representante instanceof Representante) || !(amigo instanceof Amigo) ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        ((Representante) representante).getAmigos().add((Amigo) amigo);
        personaRepository.save(representante);
    }


    public void deletePersona(Integer id){
        Persona persona= getById(id);
        personaRepository.delete(persona);
    }
    public void addCurrencyToJugador(Integer id,Integer id_currency){
        Persona jugador =getById(id);
        Currency currency= currencyService.getById(id_currency);
        ((Jugador) jugador).setCurrency(currency);
        personaRepository.save(jugador);
    }

}
