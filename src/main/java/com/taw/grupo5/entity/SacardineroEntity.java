package com.taw.grupo5.entity;

import com.taw.grupo5.dto.SacardineroDTO;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "sacardinero", schema = "grupo5", catalog = "")
public class SacardineroEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idSacarDinero", nullable = false)
    private Integer idSacarDinero;
    @Basic
    @Column(name = "cantidad", nullable = true, precision = 2)
    private BigDecimal cantidad;
    @ManyToOne
    @JoinColumn(name = "idoperacion", referencedColumnName = "idoperacion", nullable = false)
    private OperacionEntity operacionByIdoperacion;

    public Integer getIdSacarDinero() {
        return idSacarDinero;
    }

    public void setIdSacarDinero(Integer idSacarDinero) {
        this.idSacarDinero = idSacarDinero;
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

        if (idSacarDinero != null ? !idSacarDinero.equals(that.idSacarDinero) : that.idSacarDinero != null)
            return false;
        if (cantidad != null ? !cantidad.equals(that.cantidad) : that.cantidad != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSacarDinero != null ? idSacarDinero.hashCode() : 0;
        result = 31 * result + (cantidad != null ? cantidad.hashCode() : 0);
        return result;
    }

    public OperacionEntity getOperacionByIdoperacion() {
        return operacionByIdoperacion;
    }

    public void setOperacionByIdoperacion(OperacionEntity operacionByIdoperacion) {
        this.operacionByIdoperacion = operacionByIdoperacion;
    }

    public SacardineroDTO toDTO(){

        SacardineroDTO dto = new SacardineroDTO();

        dto.setOperacion(operacionByIdoperacion.getIdoperacion());
        dto.setCantidad(cantidad);
        dto.setIdSacarDinero(idSacarDinero);

        return dto;

    }
}
