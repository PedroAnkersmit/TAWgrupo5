package com.grupo5.grupo5;

import jakarta.persistence.*;

@Entity
@Table(name = "tipoempleado", schema = "grupo5", catalog = "")
public class TipoempleadoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_TipoEmpleado")
    private int idTipoEmpleado;
    @Basic
    @Column(name = "puesto")
    private String puesto;

    public int getIdTipoEmpleado() {
        return idTipoEmpleado;
    }

    public void setIdTipoEmpleado(int idTipoEmpleado) {
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

        if (idTipoEmpleado != that.idTipoEmpleado) return false;
        if (puesto != null ? !puesto.equals(that.puesto) : that.puesto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTipoEmpleado;
        result = 31 * result + (puesto != null ? puesto.hashCode() : 0);
        return result;
    }
}
