package com.utn.parcial.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@Entity
public class Jugador extends Persona {

    private Integer peso;
    private Integer altura;
    private Integer goles;
    private Integer minutosJugados;

    @OneToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    private String fechaDeNacimiento;

    @Override
    public TypePersona typePersona(){return TypePersona.JUGADOR;}


}
