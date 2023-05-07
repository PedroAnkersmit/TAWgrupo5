package com.taw.grupo5.dto;/*
Created by Pedro Ankersmit Carrión
*/


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class CuentaDTO implements Serializable {

    private Integer idcuenta;

    private String numerocuenta;

    private BigDecimal saldo;

    private Date fechaapertura;

    private Date fechacierre;

    private ClienteDTO cliente;

    private TipoestadoDTO tipoEstado;

    private List<OperacionDTO> operaciones;

    public Integer getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(Integer idcuenta) {
        this.idcuenta = idcuenta;
    }

    public String getNumerocuenta() {
        return numerocuenta;
    }

    public void setNumerocuenta(String numerocuenta) {
        this.numerocuenta = numerocuenta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Date getFechaapertura() {
        return fechaapertura;
    }

    public void setFechaapertura(Date fechaapertura) {
        this.fechaapertura = fechaapertura;
    }

    public Date getFechacierre() {
        return fechacierre;
    }

    public void setFechacierre(Date fechacierre) {
        this.fechacierre = fechacierre;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public TipoestadoDTO getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(TipoestadoDTO tipoEstado) {
        this.tipoEstado = tipoEstado;
    }

    public List<OperacionDTO> getOperacionesByIdOperacion() {
        return operaciones;
    }

    public void setOperacionesByIdOperacion(List<OperacionDTO> operaciones) {
        this.operaciones = operaciones;
    }
}
