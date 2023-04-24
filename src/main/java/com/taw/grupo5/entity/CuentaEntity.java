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
    @Column(name = "ID_Cuenta", nullable = false)
    private Integer idcuenta;
    @Basic
    @Column(name = "numeroCuenta", nullable = false, length = 50)
    private String numerocuenta;
    @Basic
    @Column(name = "saldo", nullable = true, precision = 2)
    private BigDecimal saldo;
    @Basic
    @Column(name = "fechaApertura", nullable = false)
    private Date fechaapertura;
    @Basic
    @Column(name = "fechaCierre", nullable = true)
    private Date fechacierre;
    @ManyToOne
    @JoinColumn(name = "ID_Cliente", referencedColumnName = "ID_Cliente", nullable = false)
    private ClienteEntity clientebyidcliente;
    @ManyToOne
    @JoinColumn(name = "ID_Estado", referencedColumnName = "ID_TipoEstado", nullable = false)
    private TipoestadoEntity tipoestadobyidestado;
    @OneToMany(mappedBy = "cuentabyidcuenta")
    private List<OperacionEntity> operacionsbyidCuenta;

    public Integer getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(Integer idCuenta) {
        this.idcuenta = idCuenta;
    }

    public String getNumerocuenta() {
        return numerocuenta;
    }

    public void setNumerocuenta(String numeroCuenta) {
        this.numerocuenta = numeroCuenta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Date getFechaapertura() {
        return fechaapertura;
    }

    public void setFechaapertura(Date fechaApertura) {
        this.fechaapertura = fechaApertura;
    }

    public Date getFechacierre() {
        return fechacierre;
    }

    public void setFechacierre(Date fechaCierre) {
        this.fechacierre = fechaCierre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CuentaEntity cuenta = (CuentaEntity) o;

        if (idcuenta != null ? !idcuenta.equals(cuenta.idcuenta) : cuenta.idcuenta != null) return false;
        if (numerocuenta != null ? !numerocuenta.equals(cuenta.numerocuenta) : cuenta.numerocuenta != null)
            return false;
        if (saldo != null ? !saldo.equals(cuenta.saldo) : cuenta.saldo != null) return false;
        if (fechaapertura != null ? !fechaapertura.equals(cuenta.fechaapertura) : cuenta.fechaapertura != null)
            return false;
        if (fechacierre != null ? !fechacierre.equals(cuenta.fechacierre) : cuenta.fechacierre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idcuenta != null ? idcuenta.hashCode() : 0;
        result = 31 * result + (numerocuenta != null ? numerocuenta.hashCode() : 0);
        result = 31 * result + (saldo != null ? saldo.hashCode() : 0);
        result = 31 * result + (fechaapertura != null ? fechaapertura.hashCode() : 0);
        result = 31 * result + (fechacierre != null ? fechacierre.hashCode() : 0);
        return result;
    }

    public ClienteEntity getClientebyidcliente() {
        return clientebyidcliente;
    }

    public void setClientebyidcliente(ClienteEntity clienteByIdCliente) {
        this.clientebyidcliente = clienteByIdCliente;
    }

    public TipoestadoEntity getTipoestadobyidestado() {
        return tipoestadobyidestado;
    }

    public void setTipoestadobyidestado(TipoestadoEntity tipoestadoByIdEstado) {
        this.tipoestadobyidestado = tipoestadoByIdEstado;
    }

    public List<OperacionEntity> getOperacionsbyidCuenta() {
        return operacionsbyidCuenta;
    }

    public void setOperacionsbyidCuenta(List<OperacionEntity> operacionsByIdCuenta) {
        this.operacionsbyidCuenta = operacionsByIdCuenta;
    }
}
