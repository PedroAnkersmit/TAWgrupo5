package com.grupo5.grupo5;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "sacardinero", schema = "grupo5", catalog = "")
public class SacardineroEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_Operacion")
    private int idOperacion;
    @Basic
    @Column(name = "cantidad")
    private BigDecimal cantidad;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SacardineroEntity that = (SacardineroEntity) o;

        if (idOperacion != that.idOperacion) return false;
        if (cantidad != null ? !cantidad.equals(that.cantidad) : that.cantidad != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOperacion;
        result = 31 * result + (cantidad != null ? cantidad.hashCode() : 0);
        return result;
    }
}
