package com.taw.grupo5.ui;

/**
 * @author Jesús Ariza
 */

import java.sql.Date;
import java.time.LocalDate;

public class FiltroClientesEmpresa {

        private Integer idClienteDelPortal;
        private String tipoCliente;
        private Date fechaMinima, fechaMaxima;

        public FiltroClientesEmpresa(){
            this.idClienteDelPortal = null;
            this.tipoCliente = "";
            this.fechaMinima = Date.valueOf(LocalDate.of(1900, 1, 1));
            this.fechaMaxima = Date.valueOf(LocalDate.of(2100, 1, 1));
        }

    public Integer getIdClienteDelPortal() {
        return idClienteDelPortal;
    }

    public void setIdClienteDelPortal(Integer idClienteDelPortal) {
        this.idClienteDelPortal = idClienteDelPortal;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Date getFechaMinima() {
        return fechaMinima;
    }

    public void setFechaMinima(Date fechaMinima) {
        this.fechaMinima = fechaMinima;
    }

    public Date getFechaMaxima() {
        return fechaMaxima;
    }

    public void setFechaMaxima(Date fechaMaxima) {
        this.fechaMaxima = fechaMaxima;
    }
}
