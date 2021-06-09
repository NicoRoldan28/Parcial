package com.utn.parcial.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@Entity

public class Representante extends Persona {

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "jugador_id")
    private List<Jugador> jugadores;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "amigo_id")
    private List<Amigo> amigos;

    private Float pesoDeLaBobeda;
    private Double montoTotal;

    @Override
    public TypePersona typePersona(){return TypePersona.REPRESENTANTE;}

    public Double getMontoTotal(){
        double total = 0;
        for (Jugador j:jugadores) {
            total = total + (j.getCurrency().getMonto() * j.getCurrency().getTypeCurrency().getCotizacion());
        }
        return total;
    }


    /*
        public void setTotalAmount(){
        float acumulador = 0;
        for (Jugador jug : jugadoresList){
         acumulador +=  jug.getCurrency().getMonto() * jug.getCurrency().typeCurrency.getCotizacion();
     }
        this.montoTotal =  acumulador;
        this.pesoBoveda=acumulador/1000;
    }
    */

}
