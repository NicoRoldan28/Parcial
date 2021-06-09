package com.utn.parcial.controller;

import com.utn.parcial.model.Persona;
import com.utn.parcial.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Persona>> allUsers(Pageable pageable) {
        Page page = personaService.getPersona(pageable);
        return response(page);
    }

    private ResponseEntity response(Page page) {

        HttpStatus httpStatus = page.getContent().isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.
                status(httpStatus).
                header("X-Total-Count", Long.toString(page.getTotalElements()))
                .header("X-Total-Pages", Long.toString(page.getTotalPages()))
                .body(page.getContent());

    }

    @GetMapping("/{id}")
    public Persona getById(@PathVariable Integer id) {
        return personaService.getById(id);
    }
    @PutMapping("/{id}/jugador/{idJugador}")
    public void addJugadorToRepresentante(@PathVariable Integer id,@PathVariable Integer idJugador){
        personaService.addJugadorToRepresentante(id, idJugador);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Integer id) {
        personaService.deletePersona(id);
    }

    @PutMapping("/{id}/currency/{id_currency}")
    public void addCurrencyToJugador(@PathVariable Integer id, @PathVariable Integer id_currency){
        personaService.addCurrencyToJugador(id,id_currency);
    }

    @PutMapping("/{id}/currency/{id_currency}")
    public void addAmigoToRepresentante(@PathVariable Integer representante_id, @PathVariable Integer amigo_id ){
        personaService.addAmigoToRepresentante(representante_id,amigo_id);
    }






}
