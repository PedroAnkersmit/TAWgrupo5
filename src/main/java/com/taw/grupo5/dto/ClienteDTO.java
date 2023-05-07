package com.taw.grupo5.dto;/*
Created by Pedro Ankersmit Carrión
*/

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class ClienteDTO implements Serializable {

    private Integer idCliente;

    private Integer idConversacion;

    private String email;

    private String nombre;

    private String telefono;

    private Date fechainicio;

    private TipoclienteDTO tipoCliente;

    private EmpresaDTO empresa;

    private List<CuentaDTO> cuentas;

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdConversacion() {
        return idConversacion;
    }

    public void setIdConversacion(Integer idConversacion) {
        this.idConversacion = idConversacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public TipoclienteDTO getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoclienteDTO tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public EmpresaDTO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaDTO empresa) {
        this.empresa = empresa;
    }

    // Pablo
    public List<CuentaDTO> getCuentasByIdCliente(){
        return cuentas;
    }

    // Pablo
    public void setCuentasByIdCliente(List<CuentaDTO> cuentas){
        this.cuentas = cuentas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteDTO that = (ClienteDTO) o;
        return idCliente.equals(that.idCliente) && Objects.equals(idConversacion, that.idConversacion) && Objects.equals(email, that.email) && Objects.equals(telefono, that.telefono) && Objects.equals(fechainicio, that.fechainicio) && Objects.equals(tipoCliente, that.tipoCliente) && Objects.equals(empresa, that.empresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente, idConversacion, email, telefono, fechainicio, tipoCliente, empresa);
    }
}
