package com.utn.parcial.service;

import com.utn.parcial.model.Jugador;
import com.utn.parcial.model.Representante;
import com.utn.parcial.repository.JugadorRepository;
import com.utn.parcial.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
@Service
public class JugadorService {

    private JugadorRepository jugadorRepository;
    @Autowired
    public JugadorService(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    public void addJugador(Jugador jugador){
        jugadorRepository.save(jugador);
    }
    public List<Jugador> getAll(){
        return jugadorRepository.findAll();
    }
    public Jugador getById(Integer id)throws Throwable {
        return (Jugador) jugadorRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }
}
