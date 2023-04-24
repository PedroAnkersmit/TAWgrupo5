package com.taw.grupo5.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "operacion", schema = "grupo5", catalog = "")
public class OperacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_Operacion", nullable = false)
    private Integer idOperacion;
    @Basic
    @Column(name = "fecha", nullable = true)
    private Date fecha;
    @Basic
    @Column(name = "ID_Cliente", nullable = false)
    private Integer idCliente;
    @OneToOne(mappedBy = "operacionByIdOperacion")
    private CambiodivisaEntity cambiodivisaByIdOperacion;
    @ManyToOne
    @JoinColumn(name = "ID_Cuenta", referencedColumnName = "ID_Cuenta", nullable = false)
    private CuentaEntity cuentaByIdCuenta;
    @OneToOne(mappedBy = "operacionByIdOperacion")
    private SacardineroEntity sacardineroByIdOperacion;
    @OneToOne(mappedBy = "operacionByIdOperacion")
    private TransferenciaEntity transferenciaByIdOperacion;

    public Integer getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(Integer idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperacionEntity that = (OperacionEntity) o;

        if (idOperacion != null ? !idOperacion.equals(that.idOperacion) : that.idOperacion != null) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;
        if (idCliente != null ? !idCliente.equals(that.idCliente) : that.idCliente != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOperacion != null ? idOperacion.hashCode() : 0;
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        result = 31 * result + (idCliente != null ? idCliente.hashCode() : 0);
        return result;
    }

    public CambiodivisaEntity getCambiodivisaByIdOperacion() {
        return cambiodivisaByIdOperacion;
    }

    public void setCambiodivisaByIdOperacion(CambiodivisaEntity cambiodivisaByIdOperacion) {
        this.cambiodivisaByIdOperacion = cambiodivisaByIdOperacion;
    }

    public CuentaEntity getCuentaByIdCuenta() {
        return cuentaByIdCuenta;
    }

    public void setCuentaByIdCuenta(CuentaEntity cuentaByIdCuenta) {
        this.cuentaByIdCuenta = cuentaByIdCuenta;
    }

    public SacardineroEntity getSacardineroByIdOperacion() {
        return sacardineroByIdOperacion;
    }

    public void setSacardineroByIdOperacion(SacardineroEntity sacardineroByIdOperacion) {
        this.sacardineroByIdOperacion = sacardineroByIdOperacion;
    }

    public TransferenciaEntity getTransferenciaByIdOperacion() {
        return transferenciaByIdOperacion;
    }

    public void setTransferenciaByIdOperacion(TransferenciaEntity transferenciaByIdOperacion) {
        this.transferenciaByIdOperacion = transferenciaByIdOperacion;
    }
}
