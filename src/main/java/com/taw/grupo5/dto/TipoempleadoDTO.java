package com.taw.grupo5.dto;/*
Created by Pedro Ankersmit Carrión
*/

import java.io.Serializable;

public class TipoempleadoDTO implements Serializable {
    private Integer idTipoempleado;

    private String puesto;

    public Integer getIdTipoempleado() {
        return idTipoempleado;
    }

    public void setIdTipoempleado(Integer idTipoempleado) {
        this.idTipoempleado = idTipoempleado;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public TipoempleadoDTO toDTO(){
        TipoempleadoDTO dto = new TipoempleadoDTO();
        dto.setIdTipoempleado(idTipoempleado);
        dto.setPuesto(puesto);
        return dto;
    }
}
