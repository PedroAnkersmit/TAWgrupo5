package com.taw.grupo5.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "empleado", schema = "grupo5", catalog = "")
public class EmpleadoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idempleado", nullable = false)
    private Integer idempleado;
    @Basic
    @Column(name = "nombre", nullable = true, length = 50)
    private String nombre;
    @OneToMany(mappedBy = "empleadoByIdempleado")
    private List<ConversacionEntity> conversacionsByIdempleado;
    @ManyToOne
    @JoinColumn(name = "idtipoempleado", referencedColumnName = "idtipoempleado", nullable = false)
    private TipoempleadoEntity tipoempleadoByIdtipoempleado;

    public Integer getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Integer idempleado) {
        this.idempleado = idempleado;
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

        EmpleadoEntity that = (EmpleadoEntity) o;

        if (idempleado != null ? !idempleado.equals(that.idempleado) : that.idempleado != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idempleado != null ? idempleado.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }

    public List<ConversacionEntity> getConversacionsByIdempleado() {
        return conversacionsByIdempleado;
    }

    public void setConversacionsByIdempleado(List<ConversacionEntity> conversacionsByIdempleado) {
        this.conversacionsByIdempleado = conversacionsByIdempleado;
    }

    public TipoempleadoEntity getTipoempleadoByIdtipoempleado() {
        return tipoempleadoByIdtipoempleado;
    }

    public void setTipoempleadoByIdtipoempleado(TipoempleadoEntity tipoempleadoByIdtipoempleado) {
        this.tipoempleadoByIdtipoempleado = tipoempleadoByIdtipoempleado;
    }
}
