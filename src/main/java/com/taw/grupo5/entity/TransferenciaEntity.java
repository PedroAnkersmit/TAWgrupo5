package com.taw.grupo5.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "transferencia", schema = "grupo5", catalog = "")
public class TransferenciaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_Operacion", nullable = false)
    private Integer idoperacion;
    @Basic
    @Column(name = "cantidad", nullable = true, precision = 2)
    private BigDecimal cantidad;
    @Basic
    @Column(name = "fechaInstruccion", nullable = true)
    private Date fechainstruccion;
    @Basic
    @Column(name = "fechaEjecucion", nullable = true)
    private Date fechaejecucion;
    @OneToOne
    @JoinColumn(name = "ID_Operacion", referencedColumnName = "ID_Operacion", nullable = false)
    private OperacionEntity operacionbyidoperacion;

    public Integer getIdoperacion() {
        return idoperacion;
    }

    public void setIdoperacion(Integer idOperacion) {
        this.idoperacion = idOperacion;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechainstruccion() {
        return fechainstruccion;
    }

    public void setFechainstruccion(Date fechaInstruccion) {
        this.fechainstruccion = fechaInstruccion;
    }

    public Date getFechaejecucion() {
        return fechaejecucion;
    }

    public void setFechaejecucion(Date fechaEjecucion) {
        this.fechaejecucion = fechaEjecucion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransferenciaEntity that = (TransferenciaEntity) o;

        if (idoperacion != null ? !idoperacion.equals(that.idoperacion) : that.idoperacion != null) return false;
        if (cantidad != null ? !cantidad.equals(that.cantidad) : that.cantidad != null) return false;
        if (fechainstruccion != null ? !fechainstruccion.equals(that.fechainstruccion) : that.fechainstruccion != null)
            return false;
        if (fechaejecucion != null ? !fechaejecucion.equals(that.fechaejecucion) : that.fechaejecucion != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idoperacion != null ? idoperacion.hashCode() : 0;
        result = 31 * result + (cantidad != null ? cantidad.hashCode() : 0);
        result = 31 * result + (fechainstruccion != null ? fechainstruccion.hashCode() : 0);
        result = 31 * result + (fechaejecucion != null ? fechaejecucion.hashCode() : 0);
        return result;
    }

    public OperacionEntity getOperacionbyidoperacion() {
        return operacionbyidoperacion;
    }

    public void setOperacionbyidoperacion(OperacionEntity operacionByIdOperacion) {
        this.operacionbyidoperacion = operacionByIdOperacion;
    }
}
