package com.utn.parcial.model;

public enum TypeCurrency {
    DOLAR("DOLAR", 150),
    EURO("EURO", 100);


    private String descripcion;
    private Integer cotizacion;

    TypeCurrency (String descripcion,Integer cotizacion){
        this.descripcion=descripcion;
        this.cotizacion=cotizacion;
    }
    public static TypeCurrency find(String descripcion){
        for (TypeCurrency v: values()){
            if(values().toString().equalsIgnoreCase(descripcion)){
                return v;
            }
        }
        throw new IllegalArgumentException(String.format("Invalid TypeCurrency: %s", descripcion));
    }
    public String getDescripcion(){
        return this.descripcion;
    }
    public Integer getCotizacion(){
        return this.cotizacion;
    }

}
