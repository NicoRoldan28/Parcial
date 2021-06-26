package com.utn.parcial.controller;

import com.utn.parcial.model.Jugador;
import com.utn.parcial.model.Persona;
import com.utn.parcial.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/persona")
public class PersonaController {

    PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService){
        this.personaService=personaService;
    }

    @PostMapping("")
    public ResponseEntity addPerson(@RequestBody Persona persona)
    {
        Persona newPerson=personaService.addPersona(persona);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPerson.getId())
                .toUri();
        return new ResponseEntity<>(location,(HttpStatus.CREATED));
    }

    @GetMapping("")
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

    @DeleteMapping("/{id}")
    public ResponseEntity deletePerson(@PathVariable Integer id) throws Exception {
            return personaService.deletePersona(id);
    }

    @PutMapping("/{id}/jugador/{idJugador}")
    public ResponseEntity<?> addJugadorToRepresentante(@PathVariable Integer id,@PathVariable Integer idJugador){
        try {
            personaService.addJugadorToRepresentante(id, idJugador);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El jugador o la currency con ese id es inexistente");
        }
    }

    @PutMapping("/{id}/currency/{id_currency}")
    public ResponseEntity<?> addCurrencyToJugador(@PathVariable("id") Integer id, @PathVariable("id_currency") Integer id_currency) {
        try {
            personaService.addCurrencyToJugador(id, id_currency);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El jugador o la currency con ese id es inexistente");
        }
    }

    @PutMapping("/{id}/amigo/{amigo_id}")
    public ResponseEntity<?> addAmigoToRepresentante(@PathVariable("id") Integer representante_id, @PathVariable("amigo_id") Integer amigo_id ){
        try {
            personaService.addAmigoToRepresentante(representante_id,amigo_id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El representante o el amigo con ese id son inexistentes");
        }
    }
}
