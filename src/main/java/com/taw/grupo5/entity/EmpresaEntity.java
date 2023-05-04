package com.taw.grupo5.entity;/*
Created by Pedro Ankersmit Carrión
*/

import com.taw.grupo5.dto.EmpresaDTO;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "empresa", schema = "grupo5", catalog = "")
public class EmpresaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idempresa", nullable = false)
    private Integer idempresa;
    @Basic
    @Column(name = "nombre", nullable = true, length = 100)
    private String nombre;
    @Basic
    @Column(name = "fechacierre", nullable = true)
    private Date fechacierre;
    @OneToMany(mappedBy = "empresaByIdempresa")
    private List<ClienteEntity> clientesByIdempresa;

    public Integer getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

        EmpresaEntity that = (EmpresaEntity) o;

        if (idempresa != null ? !idempresa.equals(that.idempresa) : that.idempresa != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (fechacierre != null ? !fechacierre.equals(that.fechacierre) : that.fechacierre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idempresa != null ? idempresa.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (fechacierre != null ? fechacierre.hashCode() : 0);
        return result;
    }

    public List<ClienteEntity> getClientesByIdempresa() {
        return clientesByIdempresa;
    }

    public void setClientesByIdempresa(List<ClienteEntity> clientesByIdempresa) {
        this.clientesByIdempresa = clientesByIdempresa;
    }
    public EmpresaDTO toDTO(){
        EmpresaDTO dto = new EmpresaDTO();
        dto.setIdempresa(idempresa);
        dto.setFechacierre(fechacierre);
        dto.setNombre(nombre);
        return dto;
    }
}
