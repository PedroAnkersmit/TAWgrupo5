package com.taw.grupo5.entity;/*
Created by Pedro Ankersmit Carrión
*/

import com.taw.grupo5.dto.CambioDivisaDTO;
import com.taw.grupo5.dto.OperacionDTO;
import com.taw.grupo5.dto.SacardineroDTO;
import com.taw.grupo5.dto.TransferenciaDTO;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "operacionByIdoperacion")
    private List<CambiodivisaEntity> cambiodivisasByIdoperacion;
    @ManyToOne
    @JoinColumn(name = "idcuenta", referencedColumnName = "idcuenta", nullable = false)
    private CuentaEntity cuentaByIdcuenta;
    @OneToMany(mappedBy = "operacionByIdoperacion")
    private List<SacardineroEntity> sacardinerosByIdoperacion;
    @OneToMany(mappedBy = "operacionByIdoperacion")
    private List<TransferenciaEntity> transferenciasByIdoperacion;

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

        OperacionEntity operacion = (OperacionEntity) o;

        if (idoperacion != null ? !idoperacion.equals(operacion.idoperacion) : operacion.idoperacion != null)
            return false;
        if (fecha != null ? !fecha.equals(operacion.fecha) : operacion.fecha != null) return false;
        if (idcliente != null ? !idcliente.equals(operacion.idcliente) : operacion.idcliente != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idoperacion != null ? idoperacion.hashCode() : 0;
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        result = 31 * result + (idcliente != null ? idcliente.hashCode() : 0);
        return result;
    }

    public List<CambiodivisaEntity> getCambiodivisasByIdoperacion() {
        return cambiodivisasByIdoperacion;
    }

    public void setCambiodivisasByIdoperacion(List<CambiodivisaEntity> cambiodivisasByIdoperacion) {
        this.cambiodivisasByIdoperacion = cambiodivisasByIdoperacion;
    }

    public CuentaEntity getCuentaByIdcuenta() {
        return cuentaByIdcuenta;
    }

    public void setCuentaByIdcuenta(CuentaEntity cuentaByIdcuenta) {
        this.cuentaByIdcuenta = cuentaByIdcuenta;
    }

    public List<SacardineroEntity> getSacardinerosByIdoperacion() {
        return sacardinerosByIdoperacion;
    }

    public void setSacardinerosByIdoperacion(List<SacardineroEntity> sacardinerosByIdoperacion) {
        this.sacardinerosByIdoperacion = sacardinerosByIdoperacion;
    }

    public List<TransferenciaEntity> getTransferenciasByIdoperacion() {
        return transferenciasByIdoperacion;
    }

    public void setTransferenciasByIdoperacion(List<TransferenciaEntity> transferenciasByIdoperacion) {
        this.transferenciasByIdoperacion = transferenciasByIdoperacion;
    }

    public OperacionDTO toDTO(){
        OperacionDTO dto = new OperacionDTO();
        dto.setIdOperacion(idoperacion);
        dto.setCliente(idcliente);
        dto.setCuenta(cuentaByIdcuenta.toDTO());

        return dto;
    }
}
