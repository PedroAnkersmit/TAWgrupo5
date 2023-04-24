package com.taw.grupo5.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "sacardinero", schema = "grupo5", catalog = "")
public class SacardineroEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_Operacion", nullable = false)
    private Integer idoperacion;
    @Basic
    @Column(name = "cantidad", nullable = true, precision = 2)
    private BigDecimal cantidad;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SacardineroEntity that = (SacardineroEntity) o;

        if (idoperacion != null ? !idoperacion.equals(that.idoperacion) : that.idoperacion != null) return false;
        if (cantidad != null ? !cantidad.equals(that.cantidad) : that.cantidad != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idoperacion != null ? idoperacion.hashCode() : 0;
        result = 31 * result + (cantidad != null ? cantidad.hashCode() : 0);
        return result;
    }

    public OperacionEntity getOperacionbyidoperacion() {
        return operacionbyidoperacion;
    }

    public void setOperacionbyidoperacion(OperacionEntity operacionByIdOperacion) {
        this.operacionbyidoperacion = operacionByIdOperacion;
    }
}
