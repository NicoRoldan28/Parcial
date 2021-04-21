package com.utn.parcial.controller;

import com.utn.parcial.model.Persona;
import com.utn.parcial.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService){
        this.personaService=personaService;
    }

    @PostMapping("/")
    public void addPerson(@RequestBody Persona persona){
        personaService.addPersona(persona);
    }

    @GetMapping()
    public List<Persona> getAll(){
        return personaService.getPersona();
    }

    @GetMapping("/{id}")
    public Persona getById(@PathVariable Integer id) throws Throwable {
        return personaService.getById(id);
    }
    @PutMapping("/{id}/jugador/{idJugador}")
    public void addJugadorToRepresentante(@PathVariable Integer id,@PathVariable Integer idJugador) throws Throwable{
        personaService.addJugadorToRepresentante(id, idJugador);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Integer id) throws Throwable {
        personaService.deletePersona(id);
    }

    @PutMapping("/{id}/currency/{id_currency}")
    public void addCurrencyToJugador(@PathVariable Integer id, @PathVariable Integer id_currency) throws Throwable {
        personaService.addCurrencyToJugador(id,id_currency);
    }





}
