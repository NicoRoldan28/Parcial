package com.utn.parcial.utils;

import com.utn.parcial.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public class TestUtils {


    public static Persona aPersona() {
        Persona p = new Persona() {
            @Override
            public TypePersona typePersona() {
                return null;
            }
        };
        p.setId(10);
        p.setName("nombre");
        p.setLastName("apellido");
        p.setCumpleanitos(null);
        return p;
    }

    public static Jugador aJugador(){
        Jugador j= new Jugador();
        j.setId(1);
        j.setName("Diego");
        j.setPeso(80);
        j.setAltura(1.90);
        j.setGoles(54);
        j.setMinutosJugados(4892);
        j.setFechaDeNacimiento("1 de junio de 1967");
        return j;
    }

    public static Amigo aAmigo(){
        Amigo a= new Amigo();
        a.setId(5);
        a.setName("Roberto");
        a.setLastName("Gomez");
        a.setStatusSocial("mediano");
        a.setTypeProfesion(TypeProfesion.ABOGADO);
        return a;
    }

    public static Representante aRepresentante(){
        Representante r= new Representante();
        r.setId(3);
        r.setName("Guillermo");
        r.setLastName("Coppola");
        r.setAmigos((List<Amigo>) aAmigoPage());
        r.setMontoTotal(330.0);
        r.setPesoDeLaBobeda(500,50);
        r.setJugadores((List<Jugador>) aJugadorPage());
        return  r;
    }

    public static Cumpleanitos aCumpleanitos(){
        Cumpleanitos c=new Cumpleanitos();
        c.setCumpleaniero(aRepresentante());
        c.setId(1);
        c.setFecha(LocalDate.of(2021,07,1));
        c.setInvitados(null);
        return  c;
    }


    public static PageImpl<Jugador> aJugadorPage(){
        return new PageImpl<Jugador>(List.of(aJugador()));
    }

    public static Page<Amigo> aAmigoPage(){
        return new PageImpl<>(List.of(aAmigo()));
    }

    public static Page<Cumpleanitos> aCumpleanitosPage(){
        return new PageImpl<>(List.of(aCumpleanitos()));
    }

    public static Pageable aPageable(){
        return PageRequest.of(0,10);
    }
}
