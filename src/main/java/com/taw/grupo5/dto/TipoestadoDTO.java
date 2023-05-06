package com.taw.grupo5.dto;/*
Created by Pedro Ankersmit Carrión
*/

import java.io.Serializable;

public class TipoestadoDTO implements Serializable {

    private Integer idTipoestado;

    private String nombre;

    public Integer getIdTipoestado() {
        return idTipoestado;
    }

    public void setIdTipoestado(Integer idTipoestado) {
        this.idTipoestado = idTipoestado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
