package com.taw.grupo5.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipoempleado", schema = "grupo5", catalog = "")
public class TipoempleadoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idtipoempleado", nullable = false)
    private Integer idTipoEmpleado;
    @Basic
    @Column(name = "puesto", nullable = false, length = 50)
    private String puesto;
    @OneToMany(mappedBy = "tipoempleadobyidtipoempleado")
    private List<EmpleadoEntity> empleadosByIdTipoempleado;

    public Integer getIdTipoEmpleado() {
        return idTipoEmpleado;
    }

    public void setIdTipoEmpleado(Integer idTipoEmpleado) {
        this.idTipoEmpleado = idTipoEmpleado;
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

        if (idTipoEmpleado != null ? !idTipoEmpleado.equals(that.idTipoEmpleado) : that.idTipoEmpleado != null)
            return false;
        if (puesto != null ? !puesto.equals(that.puesto) : that.puesto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTipoEmpleado != null ? idTipoEmpleado.hashCode() : 0;
        result = 31 * result + (puesto != null ? puesto.hashCode() : 0);
        return result;
    }

    public List<EmpleadoEntity> getEmpleadosByIdTipoempleado() {
        return empleadosByIdTipoempleado;
    }

    public void setEmpleadosByIdTipoempleado(List<EmpleadoEntity> empleadosByIdTipoEmpleado) {
        this.empleadosByIdTipoempleado = empleadosByIdTipoEmpleado;
    }
}
