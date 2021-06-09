package com.utn.parcial.model;

public enum TypeCurrency {

    //DOLAR("DOLAR", 100,36),
    //EURO("EURO", 118,30);

    DOLAR("DOLAR", 100.36),
    EURO("EURO", 156.00);

    private String descripcion;
    private Double cotizacion;

    TypeCurrency (String descripcion,Double cotizacion){
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
    public Double getCotizacion(){
        return this.cotizacion;
    }

}
