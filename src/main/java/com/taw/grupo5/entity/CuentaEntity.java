package com.taw.grupo5.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "cuenta", schema = "grupo5", catalog = "")
public class CuentaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idcuenta", nullable = false)
    private Integer idCuenta;
    @Basic
    @Column(name = "numerocuenta", nullable = false, length = 50)
    private String numeroCuenta;
    @Basic
    @Column(name = "saldo", nullable = true, precision = 2)
    private BigDecimal saldo;
    @Basic
    @Column(name = "fechaapertura", nullable = false)
    private Date fechaApertura;
    @Basic
    @Column(name = "fechacierre", nullable = true)
    private Date fechaCierre;
    @ManyToOne
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente", nullable = false)
    private ClienteEntity clienteByIdCliente;
    @ManyToOne
    @JoinColumn(name = "idestado", referencedColumnName = "idtipoestado", nullable = false)
    private TipoestadoEntity tipoestadoByIdEstado;
    @OneToMany(mappedBy = "cuentaByIdCuenta")
    private List<OperacionEntity> operacionesByIdCuenta;

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CuentaEntity cuenta = (CuentaEntity) o;

        if (idCuenta != null ? !idCuenta.equals(cuenta.idCuenta) : cuenta.idCuenta != null) return false;
        if (numeroCuenta != null ? !numeroCuenta.equals(cuenta.numeroCuenta) : cuenta.numeroCuenta != null)
            return false;
        if (saldo != null ? !saldo.equals(cuenta.saldo) : cuenta.saldo != null) return false;
        if (fechaApertura != null ? !fechaApertura.equals(cuenta.fechaApertura) : cuenta.fechaApertura != null)
            return false;
        if (fechaCierre != null ? !fechaCierre.equals(cuenta.fechaCierre) : cuenta.fechaCierre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCuenta != null ? idCuenta.hashCode() : 0;
        result = 31 * result + (numeroCuenta != null ? numeroCuenta.hashCode() : 0);
        result = 31 * result + (saldo != null ? saldo.hashCode() : 0);
        result = 31 * result + (fechaApertura != null ? fechaApertura.hashCode() : 0);
        result = 31 * result + (fechaCierre != null ? fechaCierre.hashCode() : 0);
        return result;
    }

    public ClienteEntity getClienteByIdCliente() {
        return clienteByIdCliente;
    }

    public void setClienteByIdCliente(ClienteEntity clienteByIdCliente) {
        this.clienteByIdCliente = clienteByIdCliente;
    }

    public TipoestadoEntity getTipoestadoByIdEstado() {
        return tipoestadoByIdEstado;
    }

    public void setTipoestadoByIdEstado(TipoestadoEntity tipoestadoByIdEstado) {
        this.tipoestadoByIdEstado = tipoestadoByIdEstado;
    }

    public List<OperacionEntity> getOperacionesByIdCuenta() {
        return operacionesByIdCuenta;
    }

    public void setOperacionesByIdCuenta(List<OperacionEntity> operacionsByIdCuenta) {
        this.operacionesByIdCuenta = operacionsByIdCuenta;
    }
}
