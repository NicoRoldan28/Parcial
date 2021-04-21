package com.utn.parcial.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import org.springframework.data.annotation.AccessType;

@Data
@NoArgsConstructor
@Entity
@JsonTypeInfo(use= JsonTypeInfo.Id.NAME, property = "typePersona", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value=Jugador.class, name = "JUGADOR"),
        @JsonSubTypes.Type(value=Representante.class, name = "REPRESENTANTE")
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public abstract class Persona {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String lastName;

    @AccessType(AccessType.Type.PROPERTY)
    public abstract TypePersona typePersona();
}