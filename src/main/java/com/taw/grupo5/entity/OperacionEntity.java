package com.taw.grupo5.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "operacion", schema = "grupo5", catalog = "")
public class OperacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idoperacion", nullable = false)
    private Integer idoperacion;
    @Basic
    @Column(name = "fecha", nullable = true)
    private Date fecha;
    @Basic
    @Column(name = "idcliente", nullable = false)
    private Integer idcliente;
    @OneToOne(mappedBy = "operacionByIdoperacion")
    private CambiodivisaEntity cambiodivisaByIdoperacion;
    @ManyToOne
    @JoinColumn(name = "idcuenta", referencedColumnName = "idcuenta", nullable = false)
    private CuentaEntity cuentaByIdcuenta;
    @OneToOne(mappedBy = "operacionByIdoperacion")
    private SacardineroEntity sacardineroByIdoperacion;
    @OneToOne(mappedBy = "operacionByIdoperacion")
    private TransferenciaEntity transferenciaByIdoperacion;

    public Integer getIdoperacion() {
        return idoperacion;
    }

    public void setIdoperacion(Integer idoperacion) {
        this.idoperacion = idoperacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperacionEntity that = (OperacionEntity) o;

        if (idoperacion != null ? !idoperacion.equals(that.idoperacion) : that.idoperacion != null) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;
        if (idcliente != null ? !idcliente.equals(that.idcliente) : that.idcliente != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idoperacion != null ? idoperacion.hashCode() : 0;
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        result = 31 * result + (idcliente != null ? idcliente.hashCode() : 0);
        return result;
    }

    public CambiodivisaEntity getCambiodivisaByIdoperacion() {
        return cambiodivisaByIdoperacion;
    }

    public void setCambiodivisaByIdoperacion(CambiodivisaEntity cambiodivisaByIdoperacion) {
        this.cambiodivisaByIdoperacion = cambiodivisaByIdoperacion;
    }

    public CuentaEntity getCuentaByIdcuenta() {
        return cuentaByIdcuenta;
    }

    public void setCuentaByIdcuenta(CuentaEntity cuentaByIdcuenta) {
        this.cuentaByIdcuenta = cuentaByIdcuenta;
    }

    public SacardineroEntity getSacardineroByIdoperacion() {
        return sacardineroByIdoperacion;
    }

    public void setSacardineroByIdoperacion(SacardineroEntity sacardineroByIdoperacion) {
        this.sacardineroByIdoperacion = sacardineroByIdoperacion;
    }

    public TransferenciaEntity getTransferenciaByIdoperacion() {
        return transferenciaByIdoperacion;
    }

    public void setTransferenciaByIdoperacion(TransferenciaEntity transferenciaByIdoperacion) {
        this.transferenciaByIdoperacion = transferenciaByIdoperacion;
    }
}
