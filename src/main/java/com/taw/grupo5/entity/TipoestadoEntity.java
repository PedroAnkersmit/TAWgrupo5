package com.taw.grupo5.entity;

import javax.persistence.*;

@Entity
@Table(name = "tipoestado", schema = "grupo5", catalog = "")
public class TipoestadoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_TipoEstado")
    private int idTipoEstado;
    @Basic
    @Column(name = "nombre")
    private String nombre;

    public int getIdTipoEstado() {
        return idTipoEstado;
    }

    public void setIdTipoEstado(int idTipoEstado) {
        this.idTipoEstado = idTipoEstado;
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

        TipoestadoEntity that = (TipoestadoEntity) o;

        if (idTipoEstado != that.idTipoEstado) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTipoEstado;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }
}
