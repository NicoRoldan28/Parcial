package com.utn.parcial.model;

public enum TypeProfesion {
    ABOGADO("ABOGADO"),
    MEDICO("MEDICO"),
    CONTADOR("CONTADOR"),
    INGENIERO("INGENIERO");

    private String descripcion;

    TypeProfesion(String descripcion){
        this.descripcion=descripcion;
    }

    public static TypeProfesion find(String descripcion){
        for (TypeProfesion v: values()){
            if(values().toString().equalsIgnoreCase(descripcion)){
                return v;
            }
        }
        throw new IllegalArgumentException(String.format("Invalid TypeProfesion: %s", descripcion));
    }
    public String getDescripcion(){
        return this.descripcion;
    }


}
