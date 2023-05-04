package com.taw.grupo5.dto;/*
Created by Pedro Ankersmit Carrión
*/

import java.io.Serializable;

public class TipoclienteDTO implements Serializable {
    private Integer idTipocliente;

    private String nombre;

    public Integer getIdTipocliente() {
        return idTipocliente;
    }

    public void setIdTipocliente(Integer idTipocliente) {
        this.idTipocliente = idTipocliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
