package com.taw.grupo5.dto;/*
Created by Pedro Ankersmit Carrión
*/


import java.io.Serializable;

public class ConversacionDTO implements Serializable {
    private Integer idconversacion;

    private String asunto;

    private Byte abierto;

    private EmpleadoDTO empleado;

    private ClienteDTO cliente;

    public Integer getIdconversacion() {
        return idconversacion;
    }

    public void setIdconversacion(Integer idconversacion) {
        this.idconversacion = idconversacion;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Byte getAbierto() {
        return abierto;
    }

    public void setAbierto(Byte abierto) {
        this.abierto = abierto;
    }

    public EmpleadoDTO getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoDTO empleado) {
        this.empleado = empleado;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
}
