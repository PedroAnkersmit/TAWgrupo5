package com.taw.grupo5.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "sacardinero", schema = "grupo5", catalog = "")
public class SacardineroEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_Operacion", nullable = false)
    private Integer idOperacion;
    @Basic
    @Column(name = "cantidad", nullable = true, precision = 2)
    private BigDecimal cantidad;
    @OneToOne
    @JoinColumn(name = "ID_Operacion", referencedColumnName = "ID_Operacion", nullable = false)
    private OperacionEntity operacionByIdOperacion;

    public Integer getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(Integer idOperacion) {
        this.idOperacion = idOperacion;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SacardineroEntity that = (SacardineroEntity) o;

        if (idOperacion != null ? !idOperacion.equals(that.idOperacion) : that.idOperacion != null) return false;
        if (cantidad != null ? !cantidad.equals(that.cantidad) : that.cantidad != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOperacion != null ? idOperacion.hashCode() : 0;
        result = 31 * result + (cantidad != null ? cantidad.hashCode() : 0);
        return result;
    }

    public OperacionEntity getOperacionByIdOperacion() {
        return operacionByIdOperacion;
    }

    public void setOperacionByIdOperacion(OperacionEntity operacionByIdOperacion) {
        this.operacionByIdOperacion = operacionByIdOperacion;
    }
}
