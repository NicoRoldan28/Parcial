package com.utn.parcial.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Jugador extends Persona {

    private Integer peso;
    private Integer altura;
    private Integer goles;
    private Integer minutosJugados;
    private Currency currency;
    private String fechaDeNacimiento;


}
