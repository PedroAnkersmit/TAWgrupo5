package com.taw.grupo5.dto;/*
Created by Pedro Ankersmit Carrión
*/

import java.io.Serializable;
import java.math.BigDecimal;

public class SacardineroDTO implements Serializable {

    private Integer idSacarDinero;

    private BigDecimal cantidad;

    private OperacionDTO operacion;

    public Integer getIdSacarDinero() {
        return idSacarDinero;
    }

    public void setIdSacarDinero(Integer idSacarDinero) {
        this.idSacarDinero = idSacarDinero;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public OperacionDTO getOperacion() {
        return operacion;
    }

    public void setOperacion(OperacionDTO operacion) {
        this.operacion = operacion;
    }
}
