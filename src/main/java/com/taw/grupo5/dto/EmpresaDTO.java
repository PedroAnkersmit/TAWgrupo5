package com.taw.grupo5.dto;/*
Created by Pedro Ankersmit Carrión
*/

import java.io.Serializable;
import java.sql.Date;

public class EmpresaDTO implements Serializable {
    private Integer idempresa;
    private String nombre;
    private Date fechacierre;

    public Integer getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechacierre() {
        return fechacierre;
    }

    public void setFechacierre(Date fechacierre) {
        this.fechacierre = fechacierre;
    }
}
