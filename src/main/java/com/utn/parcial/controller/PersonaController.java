package com.utn.parcial.controller;

import com.utn.parcial.model.Persona;
import com.utn.parcial.service.PersonaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    private PersonaService personaService;

    @PostMapping("/")
    public void addPerson(@RequestBody Persona persona){
        personaService.addPerson(persona);
    }

    @GetMapping()
    public List<Persona> getAll(){
        return personaService.getAll();
    }

    @GetMapping("/{id}")
    public Optional getById(@PathVariable Integer id){
        return personaService.getById(id);
    }

    @PutMapping("/{id}/jugador/{idJugador}")
    public void addJugadorToPerson(@PathVariable Integer id, Integer idJugador){
        personaService.addJugadorToPerson(id,idJugador);
    }

    /*@GetMapping()
    public Persona getById(@PathVariable Integer id){
        return personaService.getById(id);
    }*/


}
