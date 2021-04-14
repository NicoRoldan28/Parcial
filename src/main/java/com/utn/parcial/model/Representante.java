package com.utn.parcial.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Representante extends Persona {

    private List<Jugador> jugadores;
    private Integer pesoDeLaBobeda;
    private Integer montoTotal;

}
