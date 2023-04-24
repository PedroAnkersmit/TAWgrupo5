package com.taw.grupo5.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "empleado", schema = "grupo5", catalog = "")
public class EmpleadoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_Empleado", nullable = false)
    private Integer idempleado;
    @OneToMany(mappedBy = "empleadobyidempleado")
    private List<ConversacionEntity> conversacionsByIdEmpleado;
    @ManyToOne
    @JoinColumn(name = "ID_TipoEmpleado", referencedColumnName = "ID_TipoEmpleado", nullable = false)
    private TipoempleadoEntity tipoempleadobyidtipoempleado;

    public Integer getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Integer idEmpleado) {
        this.idempleado = idEmpleado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmpleadoEntity that = (EmpleadoEntity) o;

        if (idempleado != null ? !idempleado.equals(that.idempleado) : that.idempleado != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idempleado != null ? idempleado.hashCode() : 0;
    }

    public List<ConversacionEntity> getConversacionsByIdEmpleado() {
        return conversacionsByIdEmpleado;
    }

    public void setConversacionsByIdEmpleado(List<ConversacionEntity> conversacionsByIdEmpleado) {
        this.conversacionsByIdEmpleado = conversacionsByIdEmpleado;
    }

    public TipoempleadoEntity getTipoempleadobyidtipoempleado() {
        return tipoempleadobyidtipoempleado;
    }

    public void setTipoempleadobyidtipoempleado(TipoempleadoEntity tipoempleadoByIdTipoEmpleado) {
        this.tipoempleadobyidtipoempleado = tipoempleadoByIdTipoEmpleado;
    }
}
