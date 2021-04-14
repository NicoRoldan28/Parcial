package com.utn.parcial.controller;

import com.utn.parcial.model.Jugador;
import com.utn.parcial.service.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jugador")
public class JugadorController {

    @Autowired
    private JugadorService jugadorService;

    @PostMapping("/")
    public void addJugador(@RequestBody Jugador jugador){
        jugadorService.addJugador(jugador);
    }

}
