package com.taw.grupo5.entity;

import javax.persistence.*;

@Entity
@Table(name = "cambiodivisa", schema = "grupo5", catalog = "")
public class CambiodivisaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_Operacion", nullable = false)
    private Integer idoperacion;
    @Basic
    @Column(name = "monedaVenta", nullable = true, length = 50)
    private String monedaventa;
    @Basic
    @Column(name = "monedaCompra", nullable = true, length = 50)
    private String monedacompra;
    @Basic
    @Column(name = "cantidadCompra", nullable = true, length = 50)
    private String cantidadcompra;
    @Basic
    @Column(name = "cantidadVenta", nullable = true, length = 50)
    private String cantidadventa;
    @Basic
    @Column(name = "comision", nullable = true, length = 50)
    private String comision;
    @OneToOne
    @JoinColumn(name = "ID_Operacion", referencedColumnName = "ID_Operacion", nullable = false)
    private OperacionEntity operacionbyidoperacion;

    public Integer getIdoperacion() {
        return idoperacion;
    }

    public void setIdoperacion(Integer idOperacion) {
        this.idoperacion = idOperacion;
    }

    public String getMonedaventa() {
        return monedaventa;
    }

    public void setMonedaventa(String monedaVenta) {
        this.monedaventa = monedaVenta;
    }

    public String getMonedacompra() {
        return monedacompra;
    }

    public void setMonedacompra(String monedaCompra) {
        this.monedacompra = monedaCompra;
    }

    public String getCantidadcompra() {
        return cantidadcompra;
    }

    public void setCantidadcompra(String cantidadCompra) {
        this.cantidadcompra = cantidadCompra;
    }

    public String getCantidadventa() {
        return cantidadventa;
    }

    public void setCantidadventa(String cantidadVenta) {
        this.cantidadventa = cantidadVenta;
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

        if (idoperacion != null ? !idoperacion.equals(that.idoperacion) : that.idoperacion != null) return false;
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
        int result = idoperacion != null ? idoperacion.hashCode() : 0;
        result = 31 * result + (monedaventa != null ? monedaventa.hashCode() : 0);
        result = 31 * result + (monedacompra != null ? monedacompra.hashCode() : 0);
        result = 31 * result + (cantidadcompra != null ? cantidadcompra.hashCode() : 0);
        result = 31 * result + (cantidadventa != null ? cantidadventa.hashCode() : 0);
        result = 31 * result + (comision != null ? comision.hashCode() : 0);
        return result;
    }

    public OperacionEntity getOperacionbyidoperacion() {
        return operacionbyidoperacion;
    }

    public void setOperacionbyidoperacion(OperacionEntity operacionByIdOperacion) {
        this.operacionbyidoperacion = operacionByIdOperacion;
    }
}
