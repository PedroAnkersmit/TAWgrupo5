package com.taw.grupo5.entity;

import com.taw.grupo5.dto.ClienteDTO;
import com.taw.grupo5.dto.CuentaDTO;
import com.taw.grupo5.dto.OperacionDTO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cuenta", schema = "grupo5", catalog = "")
public class CuentaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idcuenta", nullable = false)
    private Integer idcuenta;
    @Basic
    @Column(name = "numerocuenta", nullable = true, length = 50)
    private String numerocuenta;
    @Basic
    @Column(name = "saldo", nullable = true, precision = 2)
    private BigDecimal saldo;
    @Basic
    @Column(name = "fechaapertura", nullable = false)
    private Date fechaapertura;
    @Basic
    @Column(name = "fechacierre", nullable = true)
    private Date fechacierre;
    @ManyToOne
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente", nullable = false)
    private ClienteEntity clienteByIdcliente;
    @ManyToOne
    @JoinColumn(name = "idestado", referencedColumnName = "idtipoestado", nullable = false)
    private TipoestadoEntity tipoestadoByIdestado;
    @OneToMany(mappedBy = "cuentaByIdcuenta")
    private List<OperacionEntity> operacionsByIdcuenta;

    public Integer getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(Integer idcuenta) {
        this.idcuenta = idcuenta;
    }

    public String getNumerocuenta() {
        return numerocuenta;
    }

    public void setNumerocuenta(String numerocuenta) {
        this.numerocuenta = numerocuenta;
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

    public void setFechaapertura(Date fechaapertura) {
        this.fechaapertura = fechaapertura;
    }

    public Date getFechacierre() {
        return fechacierre;
    }

    public void setFechacierre(Date fechacierre) {
        this.fechacierre = fechacierre;
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

    public ClienteEntity getClienteByIdcliente() {
        return clienteByIdcliente;
    }

    public void setClienteByIdcliente(ClienteEntity clienteByIdcliente) {
        this.clienteByIdcliente = clienteByIdcliente;
    }

    public TipoestadoEntity getTipoestadoByIdestado() {
        return tipoestadoByIdestado;
    }

    public void setTipoestadoByIdestado(TipoestadoEntity tipoestadoByIdestado) {
        this.tipoestadoByIdestado = tipoestadoByIdestado;
    }

    public List<OperacionEntity> getOperacionsByIdcuenta() {
        return operacionsByIdcuenta;
    }

    public void setOperacionsByIdcuenta(List<OperacionEntity> operacionsByIdcuenta) {
        this.operacionsByIdcuenta = operacionsByIdcuenta;
    }

    public CuentaDTO toDTO(){

        CuentaDTO dto = new CuentaDTO();

        dto.setFechaapertura(this.fechaapertura);
        dto.setFechacierre(this.fechacierre);
        dto.setSaldo(this.saldo);
        dto.setIdcuenta(this.idcuenta);
        dto.setNumerocuenta(this.numerocuenta);
        dto.setCliente(this.clienteByIdcliente.toDTO());
        dto.setTipoEstado(this.tipoestadoByIdestado.toDTO());

        List<OperacionDTO> operaciones = new ArrayList<>();

        for(OperacionEntity o : operacionsByIdcuenta){
            operaciones.add(o.toDTO());
        }

        dto.setOperacionesByIdOperacion(operaciones);

        return dto;
    }

    // Pablo
    public CuentaDTO toDTO(ClienteDTO c){

        CuentaDTO dto = new CuentaDTO();

        dto.setFechaapertura(this.fechaapertura);
        dto.setFechacierre(this.fechacierre);
        dto.setSaldo(this.saldo);
        dto.setIdcuenta(this.idcuenta);
        dto.setNumerocuenta(this.numerocuenta);
        dto.setCliente(c);
        dto.setTipoEstado(this.tipoestadoByIdestado.toDTO());

        List<OperacionDTO> operaciones = new ArrayList<>();

        for(OperacionEntity o : operacionsByIdcuenta){
            operaciones.add(o.toDTO());
        }

        dto.setOperacionesByIdOperacion(operaciones);

        return dto;

    }
}
