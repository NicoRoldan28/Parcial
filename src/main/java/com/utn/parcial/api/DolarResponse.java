package com.utn.parcial.api;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class DolarResponse {
    @SerializedName("nombre")
    private String nombre;;

    @SerializedName("compra")
    private Float compra;

    @SerializedName("venta")
    private Float venta;
}
