package com.taw.grupo5.ui;/*
Created by Pedro Ankersmit Carrión
*/

import java.math.BigDecimal;

public class FiltroOperaciones {
    private boolean transferencia;
    private boolean cambioDivisa;
    private boolean sacarDinero;
    private BigDecimal cantidad;

    public FiltroOperaciones() {
        transferencia = false;
        cambioDivisa = false;
        sacarDinero = false;
        cantidad = new BigDecimal(0);
    }

    public FiltroOperaciones(boolean transferencia, boolean cambioDivisa, boolean sacarDinero, String fecha, BigDecimal cantidad) {
        this.transferencia = transferencia;
        this.cambioDivisa = cambioDivisa;
        this.sacarDinero = sacarDinero;
        this.cantidad = cantidad;
    }

    public boolean isTransferencia() {
        return transferencia;
    }

    public void setTransferencia(boolean transferencia) {
        this.transferencia = transferencia;
    }

    public boolean isCambioDivisa() {
        return cambioDivisa;
    }

    public void setCambioDivisa(boolean cambioDivisa) {
        this.cambioDivisa = cambioDivisa;
    }

    public boolean isSacarDinero() {
        return sacarDinero;
    }

    public void setSacarDinero(boolean sacarDinero) {
        this.sacarDinero = sacarDinero;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }
}
