package com.grupo5.grupo5;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "operacion", schema = "grupo5", catalog = "")
public class OperacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_Operacion")
    private int idOperacion;
    @Basic
    @Column(name = "fecha")
    private Date fecha;
    @Basic
    @Column(name = "ID_Cliente")
    private int idCliente;

    public int getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperacionEntity that = (OperacionEntity) o;

        if (idOperacion != that.idOperacion) return false;
        if (idCliente != that.idCliente) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOperacion;
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        result = 31 * result + idCliente;
        return result;
    }
}
