package com.utn.parcial.api;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
public class EuroResponse {

    @SerializedName("nombre")
    private String nombre;;

    @SerializedName("compra")
    private Float compra;

    @SerializedName("venta")
    private Float venta;
}
