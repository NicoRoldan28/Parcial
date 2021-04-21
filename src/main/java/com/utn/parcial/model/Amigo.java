package com.utn.parcial.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.AccessType;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
public class Amigo extends Persona{

    private String profesion;
    private String statusSocial;

    @Override
    public TypePersona typePersona() {
        return TypePersona.AMIGO;
    }

    @AccessType(AccessType.Type.PROPERTY)
    private TypeProfesion TypeProfesion;

}
