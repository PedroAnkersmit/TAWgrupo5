package com.taw.grupo5.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipoempleado", schema = "grupo5", catalog = "")
public class TipoempleadoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idtipoempleado", nullable = false)
    private Integer idtipoempleado;
    @Basic
    @Column(name = "puesto", nullable = false, length = 50)
    private String puesto;
    @OneToMany(mappedBy = "tipoempleadoByIdtipoempleado")
    private List<EmpleadoEntity> empleadosByIdtipoempleado;

    public Integer getIdtipoempleado() {
        return idtipoempleado;
    }

    public void setIdtipoempleado(Integer idtipoempleado) {
        this.idtipoempleado = idtipoempleado;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipoempleadoEntity that = (TipoempleadoEntity) o;

        if (idtipoempleado != null ? !idtipoempleado.equals(that.idtipoempleado) : that.idtipoempleado != null)
            return false;
        if (puesto != null ? !puesto.equals(that.puesto) : that.puesto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idtipoempleado != null ? idtipoempleado.hashCode() : 0;
        result = 31 * result + (puesto != null ? puesto.hashCode() : 0);
        return result;
    }

    public List<EmpleadoEntity> getEmpleadosByIdtipoempleado() {
        return empleadosByIdtipoempleado;
    }

    public void setEmpleadosByIdtipoempleado(List<EmpleadoEntity> empleadosByIdtipoempleado) {
        this.empleadosByIdtipoempleado = empleadosByIdtipoempleado;
    }
}
