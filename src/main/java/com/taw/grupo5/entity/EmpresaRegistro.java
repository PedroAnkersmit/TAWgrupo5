package com.taw.grupo5.entity;

/**
 * @author Jesús Ariza
 */

public class EmpresaRegistro {
    private EmpresaEntity empresa;
    private ClienteEntity cliente;
    private CuentaEntity cuenta;

    public EmpresaEntity getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaEntity empresa) {
        this.empresa = empresa;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public CuentaEntity getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaEntity cuenta) {
        this.cuenta = cuenta;
    }
}
