package com.taw.grupo5.uiCajero;

import java.sql.Date;
import java.time.LocalDate;

public class FiltroOperacion {

    private String tipoOperacion;
    private Date fechaMinima, fechaMaxima;

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    private Integer idCuenta;

    public FiltroOperacion(){
        this.tipoOperacion = "";
        this.fechaMinima = Date.valueOf(LocalDate.of(1900, 1, 1));
        this.fechaMaxima = Date.valueOf(LocalDate.of(2100, 1, 1));
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public Date getFechaMinima() {
        return fechaMinima;
    }

    public Date getFechaMaxima() {
        return fechaMaxima;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public void setFechaMinima(Date fechaMinima) {
        this.fechaMinima = fechaMinima;
    }

    public void setFechaMaxima(Date fechaMaxima) {
        this.fechaMaxima = fechaMaxima;
    }
}
