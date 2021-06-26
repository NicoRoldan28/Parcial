package com.utn.parcial.service;

import com.utn.parcial.model.*;
import com.utn.parcial.service.CurrencyService;
import com.utn.parcial.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public Persona addPersona(Persona persona){
        return personaRepository.save(persona);
    }

    public Page getPersona(Pageable pageable){
        return this.personaRepository.findAll(pageable);
    }

    public Persona getById(Integer id){
        return personaRepository.findById(id)
                .orElseThrow(()->new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<?> deletePersona(Integer id) {
        try {
            Persona persona= getById(id);
            personaRepository.delete(persona);
            return new ResponseEntity<>("Se ha eliminado a la persona con éxito.", HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>("La persona con el id " + id + " es inexistente.", HttpStatus.NOT_FOUND);
        }
    }

    public void addCurrencyToJugador(Integer id,Integer id_currency) throws Exception {
        try {
            Persona jugador = getById(id);
            Currency currency = currencyService.getById(id_currency);
            ((Jugador) jugador).setCurrency(currency);
            personaRepository.save(jugador);
        } catch (DataAccessException e) {
            throw new Exception();
        }
    }

    public void addAmigoToRepresentante(Integer representante_id, Integer amigo_id) throws Exception {
        try {
            Persona amigo= getById(amigo_id);
            Persona representante = getById(representante_id);

            if(!(representante instanceof Representante) || !(amigo instanceof Amigo) ){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            ((Representante) representante).getAmigos().add((Amigo) amigo);
            personaRepository.save(representante);
        } catch (DataAccessException e) {
            throw new Exception();
        }
    }

    public void addJugadorToRepresentante(Integer id, Integer persona_id) throws Exception {
        try {
            Persona jugador = getById(id);
            Persona representante = getById(persona_id);

            if(!(representante instanceof Representante) || !(jugador instanceof Jugador) ){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            ((Representante) representante).getJugadores().add((Jugador) jugador);
            ((Representante) representante).setPesoDeLaBobeda(((Jugador) jugador).getCurrency().getMonto());

            personaRepository.save(representante);
        } catch (DataAccessException e) {
            throw new Exception();
        }
    }
}
