package com.taw.grupo5.entity;

public class PersonaDeAlta {
    private ClienteEntity cliente;
    private CuentaEntity cuenta;

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
