package com.utn.parcial.service;

import com.utn.parcial.model.Persona;
import com.utn.parcial.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


import static java.util.Objects.isNull;

@Service
public class PersonaService {

    private PersonaRepository personaRepository;

    @Autowired
    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public void addPerson(Persona persona){
        personaRepository.save(persona);
    }
    public List<Persona> getAll(){
        return personaRepository.findAll();
    }

    public Optional getById(Integer id){
        return personaRepository.findById(id);
    }

    public List<Persona> getAll(Integer id) {
        if (isNull(id)){
            return personaRepository.findAll();
        }
        //return personaRepository.findById(id);
        return null;
    }
    public void addJugadorToPerson(Integer id,Integer idJugador){

    }








}
