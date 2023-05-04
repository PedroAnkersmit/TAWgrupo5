package com.taw.grupo5.ui;
/*
Created by Pedro Ankersmit Carrión
*/

import java.math.BigDecimal;

public class FiltroOperaciones {
    private boolean transferencia;
    private boolean cambioDivisa;
    private boolean sacarDinero;

    public FiltroOperaciones() {
        this.transferencia = false;
        this.cambioDivisa = false;
        this.sacarDinero = false;
    }

    public FiltroOperaciones(boolean transferencia, boolean cambioDivisa, boolean sacarDinero) {
        this.transferencia = transferencia;
        this.cambioDivisa = cambioDivisa;
        this.sacarDinero = sacarDinero;
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


}
