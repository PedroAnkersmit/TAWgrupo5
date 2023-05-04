package com.taw.grupo5.entity;/*
Created by Pedro Ankersmit Carrión
*/

import com.taw.grupo5.dto.EmpleadoDTO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "empleado", schema = "grupo5", catalog = "")
public class EmpleadoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idempleado", nullable = false)
    private Integer idempleado;
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

    public EmpleadoDTO toDTO(){
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setIdempleado(idempleado);
        dto.setTipoEmpleado(tipoempleadoByIdtipoempleado.toDTO());
        return dto;
    }
}
