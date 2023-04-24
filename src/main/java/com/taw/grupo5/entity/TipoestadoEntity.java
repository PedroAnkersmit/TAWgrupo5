package com.taw.grupo5.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipoestado", schema = "grupo5", catalog = "")
public class TipoestadoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_TipoEstado", nullable = false)
    private Integer idtipoestado;
    @Basic
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;
    @OneToMany(mappedBy = "tipoestadobyidestado")
    private List<CuentaEntity> cuentasbyidtipoestado;

    public Integer getIdtipoestado() {
        return idtipoestado;
    }

    public void setIdtipoestado(Integer idTipoEstado) {
        this.idtipoestado = idTipoEstado;
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

        if (idtipoestado != null ? !idtipoestado.equals(that.idtipoestado) : that.idtipoestado != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idtipoestado != null ? idtipoestado.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }

    public List<CuentaEntity> getCuentasbyidtipoestado() {
        return cuentasbyidtipoestado;
    }

    public void setCuentasbyidtipoestado(List<CuentaEntity> cuentasByIdTipoEstado) {
        this.cuentasbyidtipoestado = cuentasByIdTipoEstado;
    }
}
