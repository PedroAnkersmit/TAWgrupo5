package com.taw.grupo5.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "operacion", schema = "grupo5", catalog = "")
public class OperacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_Operacion", nullable = false)
    private Integer idoperacion;
    @Basic
    @Column(name = "fecha", nullable = true)
    private Date fecha;
    @Basic
    @Column(name = "ID_Cliente", nullable = false)
    private Integer idcliente;
    @OneToOne(mappedBy = "operacionbyidoperacion")
    private CambiodivisaEntity cambiodivisaByIdOperacion;
    @ManyToOne
    @JoinColumn(name = "ID_Cuenta", referencedColumnName = "ID_Cuenta", nullable = false)
    private CuentaEntity cuentabyidcuenta;
    @OneToOne(mappedBy = "operacionbyidoperacion")
    private SacardineroEntity sacardinerobyidoperacion;
    @OneToOne(mappedBy = "operacionbyidoperacion")
    private TransferenciaEntity transferenciabyidoperacion;

    public Integer getIdoperacion() {
        return idoperacion;
    }

    public void setIdoperacion(Integer idOperacion) {
        this.idoperacion = idOperacion;
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

    public void setIdcliente(Integer idCliente) {
        this.idcliente = idCliente;
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

    public CambiodivisaEntity getCambiodivisaByIdOperacion() {
        return cambiodivisaByIdOperacion;
    }

    public void setCambiodivisaByIdOperacion(CambiodivisaEntity cambiodivisaByIdOperacion) {
        this.cambiodivisaByIdOperacion = cambiodivisaByIdOperacion;
    }

    public CuentaEntity getCuentabyidcuenta() {
        return cuentabyidcuenta;
    }

    public void setCuentabyidcuenta(CuentaEntity cuentaByIdCuenta) {
        this.cuentabyidcuenta = cuentaByIdCuenta;
    }

    public SacardineroEntity getSacardinerobyidoperacion() {
        return sacardinerobyidoperacion;
    }

    public void setSacardinerobyidoperacion(SacardineroEntity sacardineroByIdOperacion) {
        this.sacardinerobyidoperacion = sacardineroByIdOperacion;
    }

    public TransferenciaEntity getTransferenciabyidoperacion() {
        return transferenciabyidoperacion;
    }

    public void setTransferenciabyidoperacion(TransferenciaEntity transferenciaByIdOperacion) {
        this.transferenciabyidoperacion = transferenciaByIdOperacion;
    }
}
