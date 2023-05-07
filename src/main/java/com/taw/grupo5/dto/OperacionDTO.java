package com.taw.grupo5.dto;/*
Created by Pedro Ankersmit Carrión
*/



import com.taw.grupo5.entity.SacardineroEntity;

import java.sql.Date;
import java.util.List;

public class OperacionDTO {
    private Integer idOperacion;

    private Date fecha;

    private Integer cliente;

    private Integer idCuenta;

    private List<SacardineroDTO> sacardineros;

    private List<TransferenciaDTO> transferencias;

    private List<CambioDivisaDTO> cambiodivisas;


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

    public Integer getCuenta() {
        return idCuenta;
    }

    public void setCuenta(Integer cuenta) {
        this.idCuenta = cuenta;
    }

    public List<SacardineroDTO> getSacardineros() {
        return sacardineros;
    }

    public void setSacardineros(List<SacardineroDTO> sacardineros) {
        this.sacardineros = sacardineros;
    }

    public List<TransferenciaDTO> getTransferencias() {
        return transferencias;
    }

    public void setTransferencias(List<TransferenciaDTO> transferencias) {
        this.transferencias = transferencias;
    }

    public List<CambioDivisaDTO> getCambiodivisas() {
        return cambiodivisas;
    }

    public void setCambiodivisas(List<CambioDivisaDTO> cambiodivisas) {
        this.cambiodivisas = cambiodivisas;
    }
}
