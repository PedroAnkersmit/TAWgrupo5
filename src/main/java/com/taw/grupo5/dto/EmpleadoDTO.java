package com.taw.grupo5.dto;/*
Created by Pedro Ankersmit Carrión
*/

import java.io.Serializable;

public class EmpleadoDTO implements Serializable {
    private Integer idempleado;

    private TipoempleadoDTO tipoEmpleado;

    public Integer getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Integer idempleado) {
        this.idempleado = idempleado;
    }

    public TipoempleadoDTO getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(TipoempleadoDTO tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }
}
