package com.taw.grupo5.dto;/*
Created by Pedro Ankersmit Carrión
*/

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class TransferenciaDTO implements Serializable {
    private Integer idTransferencia;

    private BigDecimal cantidad;

    private Date fechaInstruccion;

    private Date fechaEjecucion;

    private OperacionDTO operacion;

    public Integer getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(Integer idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaInstruccion() {
        return fechaInstruccion;
    }

    public void setFechaInstruccion(Date fechaInstruccion) {
        this.fechaInstruccion = fechaInstruccion;
    }

    public Date getFechaEjecucion() {
        return fechaEjecucion;
    }

    public void setFechaEjecucion(Date fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }

    public OperacionDTO getOperacion() {
        return operacion;
    }

    public void setOperacion(OperacionDTO operacion) {
        this.operacion = operacion;
    }
}
