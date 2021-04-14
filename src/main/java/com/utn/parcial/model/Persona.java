package com.utn.parcial.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity

public class Persona {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String lastName;

}