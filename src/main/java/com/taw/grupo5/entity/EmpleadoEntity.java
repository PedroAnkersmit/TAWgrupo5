package com.taw.grupo5.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "empleado", schema = "grupo5", catalog = "")
public class EmpleadoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idempleado", nullable = false)
    private Integer idEmpleado;
    @OneToMany(mappedBy = "empleadobyidempleado")
    private List<ConversacionEntity> conversacionesByIdEmpleado;
    @ManyToOne
    @JoinColumn(name = "idtipoempleado", referencedColumnName = "idtipoempleado", nullable = false)
    private TipoempleadoEntity tipoempleadoByIdTipoempleado;

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

    public List<ConversacionEntity> getConversacionesByIdEmpleado() {
        return conversacionesByIdEmpleado;
    }

    public void setConversacionesByIdEmpleado(List<ConversacionEntity> conversacionsByIdEmpleado) {
        this.conversacionesByIdEmpleado = conversacionsByIdEmpleado;
    }

    public TipoempleadoEntity getTipoempleadoByIdTipoempleado() {
        return tipoempleadoByIdTipoempleado;
    }

    public void setTipoempleadoByIdTipoempleado(TipoempleadoEntity tipoempleadoByIdTipoEmpleado) {
        this.tipoempleadoByIdTipoempleado = tipoempleadoByIdTipoEmpleado;
    }
}
