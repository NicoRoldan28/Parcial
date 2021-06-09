package com.utn.parcial.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

import org.apache.tomcat.jni.User;
import org.springframework.data.annotation.AccessType;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@Entity
@JsonTypeInfo(use= JsonTypeInfo.Id.NAME, property = "typePersona", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value=Jugador.class, name = "JUGADOR"),
        @JsonSubTypes.Type(value=Representante.class, name = "REPRESENTANTE"),
        @JsonSubTypes.Type(value=Amigo.class, name = "AMIGO")
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public abstract class Persona {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "El campo name es obligatorio.")
    private String name;
    @NotNull(message = "El campo lastName es obligatorio.")
    private String lastName;

    @ManyToMany @JoinTable(name = "persona_x_cumpleanitos",
                joinColumns ={@JoinColumn(name = "persona_id")},
                inverseJoinColumns={@JoinColumn(name = "cumpleanitos_id")})
    private Set<Cumpleanitos> cumpleanitos;

    @AccessType(AccessType.Type.PROPERTY)
    public abstract TypePersona typePersona();
}