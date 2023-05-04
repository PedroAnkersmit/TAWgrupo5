package com.taw.grupo5.dto;/*
Created by Pedro Ankersmit Carrión
*/



import java.sql.Date;
import java.util.List;

public class OperacionDTO {
    private Integer idOperacion;

    private Date fecha;

    private Integer cliente;

    private CuentaDTO cuenta;


    public Integer getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(Integer idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public CuentaDTO getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaDTO cuenta) {
        this.cuenta = cuenta;
    }


}
