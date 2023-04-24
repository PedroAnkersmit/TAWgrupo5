package com.taw.grupo5.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "empleado", schema = "grupo5", catalog = "")
public class EmpleadoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_Empleado", nullable = false)
    private Integer idEmpleado;
    @OneToMany(mappedBy = "empleadoByIdEmpleado")
    private List<ConversacionEntity> conversacionsByIdEmpleado;
    @ManyToOne
    @JoinColumn(name = "ID_TipoEmpleado", referencedColumnName = "ID_TipoEmpleado", nullable = false)
    private TipoempleadoEntity tipoempleadoByIdTipoEmpleado;

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmpleadoEntity that = (EmpleadoEntity) o;

        if (idEmpleado != null ? !idEmpleado.equals(that.idEmpleado) : that.idEmpleado != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idEmpleado != null ? idEmpleado.hashCode() : 0;
    }

    public List<ConversacionEntity> getConversacionsByIdEmpleado() {
        return conversacionsByIdEmpleado;
    }

    public void setConversacionsByIdEmpleado(List<ConversacionEntity> conversacionsByIdEmpleado) {
        this.conversacionsByIdEmpleado = conversacionsByIdEmpleado;
    }

    public TipoempleadoEntity getTipoempleadoByIdTipoEmpleado() {
        return tipoempleadoByIdTipoEmpleado;
    }

    public void setTipoempleadoByIdTipoEmpleado(TipoempleadoEntity tipoempleadoByIdTipoEmpleado) {
        this.tipoempleadoByIdTipoEmpleado = tipoempleadoByIdTipoEmpleado;
    }
}
