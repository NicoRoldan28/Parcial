package com.utn.parcial.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor

public class Cumpleanitos {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)

    private Integer id;
    private LocalDate fecha;
    @OneToOne
    @JoinColumn(name = "persona_id")
    private Persona cumpleaniero;

    @ManyToMany(mappedBy ="cumpleanitos",cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Persona> invitados;

}

