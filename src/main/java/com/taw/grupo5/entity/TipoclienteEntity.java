package com.taw.grupo5.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipocliente", schema = "grupo5", catalog = "")
public class TipoclienteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idtipocliente", nullable = false)
    private Integer idtipocliente;
    @Basic
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @OneToMany(mappedBy = "tipoclienteByIdtipocliente")
    private List<ClienteEntity> clientesByIdtipocliente;

    public Integer getIdtipocliente() {
        return idtipocliente;
    }

    public void setIdtipocliente(Integer idtipocliente) {
        this.idtipocliente = idtipocliente;
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

        TipoclienteEntity that = (TipoclienteEntity) o;

        if (idtipocliente != null ? !idtipocliente.equals(that.idtipocliente) : that.idtipocliente != null)
            return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idtipocliente != null ? idtipocliente.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }

    public List<ClienteEntity> getClientesByIdtipocliente() {
        return clientesByIdtipocliente;
    }

    public void setClientesByIdtipocliente(List<ClienteEntity> clientesByIdtipocliente) {
        this.clientesByIdtipocliente = clientesByIdtipocliente;
    }
}
