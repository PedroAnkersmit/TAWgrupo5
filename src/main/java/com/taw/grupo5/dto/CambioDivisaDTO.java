package com.taw.grupo5.dto;/*
Created by Pedro Ankersmit Carrión
*/


import java.io.Serializable;

public class CambioDivisaDTO implements Serializable {

    private Integer idCambioDivisa;

    private String monedaventa;

    private String monedacompra;

    private String cantidadcompra;

    private String cantidadventa;

    private String comision;

    private Integer idOperacion;

    public Integer getIdCambioDivisa() {
        return idCambioDivisa;
    }

    public void setIdCambioDivisa(Integer idCambioDivisa) {
        this.idCambioDivisa = idCambioDivisa;
    }

    public String getMonedaventa() {
        return monedaventa;
    }

    public void setMonedaventa(String monedaventa) {
        this.monedaventa = monedaventa;
    }

    public String getMonedacompra() {
        return monedacompra;
    }

    public void setMonedacompra(String monedacompra) {
        this.monedacompra = monedacompra;
    }

    public String getCantidadcompra() {
        return cantidadcompra;
    }

    public void setCantidadcompra(String cantidadcompra) {
        this.cantidadcompra = cantidadcompra;
    }

    public String getCantidadventa() {
        return cantidadventa;
    }

    public void setCantidadventa(String cantidadventa) {
        this.cantidadventa = cantidadventa;
    }

    public String getComision() {
        return comision;
    }

    public void setComision(String comision) {
        this.comision = comision;
    }

    public Integer getOperacion() {
        return idOperacion;
    }

    public void setOperacion(Integer operacion) {
        this.idOperacion = operacion;
    }
}
