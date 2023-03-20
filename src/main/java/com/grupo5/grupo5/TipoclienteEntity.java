package com.grupo5.grupo5;

import jakarta.persistence.*;

@Entity
@Table(name = "tipocliente", schema = "grupo5", catalog = "")
public class TipoclienteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_TipoCliente")
    private int idTipoCliente;
    @Basic
    @Column(name = "nombre")
    private String nombre;

    public int getIdTipoCliente() {
        return idTipoCliente;
    }

    public void setIdTipoCliente(int idTipoCliente) {
        this.idTipoCliente = idTipoCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipoclienteEntity that = (TipoclienteEntity) o;

        if (idTipoCliente != that.idTipoCliente) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTipoCliente;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }
}
