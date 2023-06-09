package com.taw.grupo5.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "transferencia", schema = "grupo5", catalog = "")
public class TransferenciaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idTransferencia", nullable = false)
    private Integer idTransferencia;
    @Basic
    @Column(name = "cantidad", nullable = true, precision = 2)
    private BigDecimal cantidad;
    @Basic
    @Column(name = "fechainstruccion", nullable = true)
    private Date fechainstruccion;
    @Basic
    @Column(name = "fechaejecucion", nullable = true)
    private Date fechaejecucion;
    @Basic
    @Column(name = "idcuentadestino", nullable = false)
    private Integer idcuentadestino;
    @ManyToOne
    @JoinColumn(name = "idoperacion", referencedColumnName = "idoperacion", nullable = false)
    private OperacionEntity operacionByIdoperacion;

    public Integer getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(Integer idTransferencia) {
        this.idTransferencia = idTransferencia;
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

    public void setFechainstruccion(Date fechainstruccion) {
        this.fechainstruccion = fechainstruccion;
    }

    public Date getFechaejecucion() {
        return fechaejecucion;
    }

    public void setFechaejecucion(Date fechaejecucion) {
        this.fechaejecucion = fechaejecucion;
    }

    public Integer getIdcuentadestino() {
        return idcuentadestino;
    }

    public void setIdcuentadestino(Integer idcuentadestino) {
        this.idcuentadestino = idcuentadestino;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransferenciaEntity that = (TransferenciaEntity) o;

        if (idTransferencia != null ? !idTransferencia.equals(that.idTransferencia) : that.idTransferencia != null)
            return false;
        if (cantidad != null ? !cantidad.equals(that.cantidad) : that.cantidad != null) return false;
        if (fechainstruccion != null ? !fechainstruccion.equals(that.fechainstruccion) : that.fechainstruccion != null)
            return false;
        if (fechaejecucion != null ? !fechaejecucion.equals(that.fechaejecucion) : that.fechaejecucion != null)
            return false;
        if (idcuentadestino != null ? !idcuentadestino.equals(that.idcuentadestino) : that.idcuentadestino != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTransferencia != null ? idTransferencia.hashCode() : 0;
        result = 31 * result + (cantidad != null ? cantidad.hashCode() : 0);
        result = 31 * result + (fechainstruccion != null ? fechainstruccion.hashCode() : 0);
        result = 31 * result + (fechaejecucion != null ? fechaejecucion.hashCode() : 0);
        result = 31 * result + (idcuentadestino != null ? idcuentadestino.hashCode() : 0);
        return result;
    }

    public OperacionEntity getOperacionByIdoperacion() {
        return operacionByIdoperacion;
    }

    public void setOperacionByIdoperacion(OperacionEntity operacionByIdoperacion) {
        this.operacionByIdoperacion = operacionByIdoperacion;
    }
}
