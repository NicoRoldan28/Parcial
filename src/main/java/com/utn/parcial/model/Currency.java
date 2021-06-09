package com.utn.parcial.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.AccessType;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public class Currency {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Integer id;
    @AccessType(AccessType.Type.PROPERTY)
    private TypeCurrency typeCurrency;

    private Float monto;

}
