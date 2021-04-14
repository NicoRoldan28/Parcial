package com.utn.parcial.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@NoArgsConstructor
public class Currency {

    @Id
    private Integer id;
    private String currency;
    private Integer monto;

}
