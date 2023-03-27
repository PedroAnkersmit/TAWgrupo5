package com.taw.grupo5.entity;

import javax.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "transferencia", schema = "grupo5", catalog = "")
public class TransferenciaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_Operacion")
    private int idOperacion;
    @Basic
    @Column(name = "cantidad")
    private BigDecimal cantidad;
    @Basic
    @Column(name = "fechaInstruccion")
    private Date fechaInstruccion;
    @Basic
    @Column(name = "fechaEjecucion")
    private Date fechaEjecucion;

    public int getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaInstruccion() {
        return fechaInstruccion;
    }

    public void setFechaInstruccion(Date fechaInstruccion) {
        this.fechaInstruccion = fechaInstruccion;
    }

    public Date getFechaEjecucion() {
        return fechaEjecucion;
    }

    public void setFechaEjecucion(Date fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransferenciaEntity that = (TransferenciaEntity) o;

        if (idOperacion != that.idOperacion) return false;
        if (cantidad != null ? !cantidad.equals(that.cantidad) : that.cantidad != null) return false;
        if (fechaInstruccion != null ? !fechaInstruccion.equals(that.fechaInstruccion) : that.fechaInstruccion != null)
            return false;
        if (fechaEjecucion != null ? !fechaEjecucion.equals(that.fechaEjecucion) : that.fechaEjecucion != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOperacion;
        result = 31 * result + (cantidad != null ? cantidad.hashCode() : 0);
        result = 31 * result + (fechaInstruccion != null ? fechaInstruccion.hashCode() : 0);
        result = 31 * result + (fechaEjecucion != null ? fechaEjecucion.hashCode() : 0);
        return result;
    }
}
