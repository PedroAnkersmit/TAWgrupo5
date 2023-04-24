package com.taw.grupo5.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipoestado", schema = "grupo5", catalog = "")
public class TipoestadoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_TipoEstado", nullable = false)
    private Integer idTipoEstado;
    @Basic
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;
    @OneToMany(mappedBy = "tipoestadoByIdEstado")
    private List<CuentaEntity> cuentasByIdTipoEstado;

    public Integer getIdTipoEstado() {
        return idTipoEstado;
    }

    public void setIdTipoEstado(Integer idTipoEstado) {
        this.idTipoEstado = idTipoEstado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipoestadoEntity that = (TipoestadoEntity) o;

        if (idTipoEstado != null ? !idTipoEstado.equals(that.idTipoEstado) : that.idTipoEstado != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTipoEstado != null ? idTipoEstado.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }

    public List<CuentaEntity> getCuentasByIdTipoEstado() {
        return cuentasByIdTipoEstado;
    }

    public void setCuentasByIdTipoEstado(List<CuentaEntity> cuentasByIdTipoEstado) {
        this.cuentasByIdTipoEstado = cuentasByIdTipoEstado;
    }
}
