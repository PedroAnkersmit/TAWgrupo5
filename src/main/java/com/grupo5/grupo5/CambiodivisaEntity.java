package com.grupo5.grupo5;

import jakarta.persistence.*;

@Entity
@Table(name = "cambiodivisa", schema = "grupo5", catalog = "")
public class CambiodivisaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_Operacion")
    private int idOperacion;
    @Basic
    @Column(name = "monedaVenta")
    private String monedaVenta;
    @Basic
    @Column(name = "monedaCompra")
    private String monedaCompra;
    @Basic
    @Column(name = "cantidadCompra")
    private String cantidadCompra;
    @Basic
    @Column(name = "cantidadVenta")
    private String cantidadVenta;
    @Basic
    @Column(name = "comision")
    private String comision;

    public int getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public String getMonedaVenta() {
        return monedaVenta;
    }

    public void setMonedaVenta(String monedaVenta) {
        this.monedaVenta = monedaVenta;
    }

    public String getMonedaCompra() {
        return monedaCompra;
    }

    public void setMonedaCompra(String monedaCompra) {
        this.monedaCompra = monedaCompra;
    }

    public String getCantidadCompra() {
        return cantidadCompra;
    }

    public void setCantidadCompra(String cantidadCompra) {
        this.cantidadCompra = cantidadCompra;
    }

    public String getCantidadVenta() {
        return cantidadVenta;
    }

    public void setCantidadVenta(String cantidadVenta) {
        this.cantidadVenta = cantidadVenta;
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

        if (idOperacion != that.idOperacion) return false;
        if (monedaVenta != null ? !monedaVenta.equals(that.monedaVenta) : that.monedaVenta != null) return false;
        if (monedaCompra != null ? !monedaCompra.equals(that.monedaCompra) : that.monedaCompra != null) return false;
        if (cantidadCompra != null ? !cantidadCompra.equals(that.cantidadCompra) : that.cantidadCompra != null)
            return false;
        if (cantidadVenta != null ? !cantidadVenta.equals(that.cantidadVenta) : that.cantidadVenta != null)
            return false;
        if (comision != null ? !comision.equals(that.comision) : that.comision != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOperacion;
        result = 31 * result + (monedaVenta != null ? monedaVenta.hashCode() : 0);
        result = 31 * result + (monedaCompra != null ? monedaCompra.hashCode() : 0);
        result = 31 * result + (cantidadCompra != null ? cantidadCompra.hashCode() : 0);
        result = 31 * result + (cantidadVenta != null ? cantidadVenta.hashCode() : 0);
        result = 31 * result + (comision != null ? comision.hashCode() : 0);
        return result;
    }
}
