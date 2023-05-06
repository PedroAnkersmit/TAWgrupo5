package com.taw.grupo5.entity;

import javax.persistence.*;

@Entity
@Table(name = "cambiodivisa", schema = "grupo5", catalog = "")
public class CambiodivisaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idCambioDivisa", nullable = false)
    private Integer idCambioDivisa;
    @Basic
    @Column(name = "monedaventa", nullable = true, length = 50)
    private String monedaventa;
    @Basic
    @Column(name = "monedacompra", nullable = true, length = 50)
    private String monedacompra;
    @Basic
    @Column(name = "cantidadcompra", nullable = true, length = 50)
    private String cantidadcompra;
    @Basic
    @Column(name = "cantidadventa", nullable = true, length = 50)
    private String cantidadventa;
    @Basic
    @Column(name = "comision", nullable = true, length = 50)
    private String comision;
    @ManyToOne
    @JoinColumn(name = "idoperacion", referencedColumnName = "idoperacion", nullable = false)
    private OperacionEntity operacionByIdoperacion;

    public Integer getIdCambioDivisa() {
        return idCambioDivisa;
    }

    public void setIdCambioDivisa(Integer idCambioDivisa) {
        this.idCambioDivisa = idCambioDivisa;
    }

    public String getMonedaventa() {
        return monedaventa;
    }

    public void setMonedaventa(String monedaventa) {
        this.monedaventa = monedaventa;
    }

    public String getMonedacompra() {
        return monedacompra;
    }

    public void setMonedacompra(String monedacompra) {
        this.monedacompra = monedacompra;
    }

    public String getCantidadcompra() {
        return cantidadcompra;
    }

    public void setCantidadcompra(String cantidadcompra) {
        this.cantidadcompra = cantidadcompra;
    }

    public String getCantidadventa() {
        return cantidadventa;
    }

    public void setCantidadventa(String cantidadventa) {
        this.cantidadventa = cantidadventa;
    }

    public String getComision() {
        return comision;
    }

    public void setComision(String comision) {
        this.comision = comision;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CambiodivisaEntity that = (CambiodivisaEntity) o;

        if (idCambioDivisa != null ? !idCambioDivisa.equals(that.idCambioDivisa) : that.idCambioDivisa != null)
            return false;
        if (monedaventa != null ? !monedaventa.equals(that.monedaventa) : that.monedaventa != null) return false;
        if (monedacompra != null ? !monedacompra.equals(that.monedacompra) : that.monedacompra != null) return false;
        if (cantidadcompra != null ? !cantidadcompra.equals(that.cantidadcompra) : that.cantidadcompra != null)
            return false;
        if (cantidadventa != null ? !cantidadventa.equals(that.cantidadventa) : that.cantidadventa != null)
            return false;
        if (comision != null ? !comision.equals(that.comision) : that.comision != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCambioDivisa != null ? idCambioDivisa.hashCode() : 0;
        result = 31 * result + (monedaventa != null ? monedaventa.hashCode() : 0);
        result = 31 * result + (monedacompra != null ? monedacompra.hashCode() : 0);
        result = 31 * result + (cantidadcompra != null ? cantidadcompra.hashCode() : 0);
        result = 31 * result + (cantidadventa != null ? cantidadventa.hashCode() : 0);
        result = 31 * result + (comision != null ? comision.hashCode() : 0);
        return result;
    }

    public OperacionEntity getOperacionByIdoperacion() {
        return operacionByIdoperacion;
    }

    public void setOperacionByIdoperacion(OperacionEntity operacionByIdoperacion) {
        this.operacionByIdoperacion = operacionByIdoperacion;
    }
}
