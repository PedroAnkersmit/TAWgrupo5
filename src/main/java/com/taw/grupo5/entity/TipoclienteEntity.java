package com.taw.grupo5.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipocliente", schema = "grupo5", catalog = "")
public class TipoclienteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idtipocliente", nullable = false)
    private Integer idTipocliente;
    @Basic
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @OneToMany(mappedBy = "tipoclientebyidtipocliente")
    private List<ClienteEntity> clientesByIdTipocliente;

    public Integer getIdTipocliente() {
        return idTipocliente;
    }

    public void setIdTipocliente(Integer idTipoCliente) {
        this.idTipocliente = idTipoCliente;
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

        if (idTipocliente != null ? !idTipocliente.equals(that.idTipocliente) : that.idTipocliente != null)
            return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTipocliente != null ? idTipocliente.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }

    public List<ClienteEntity> getClientesByIdTipocliente() {
        return clientesByIdTipocliente;
    }

    public void setClientesByIdTipocliente(List<ClienteEntity> clientesByIdTipoCliente) {
        this.clientesByIdTipocliente = clientesByIdTipoCliente;
    }
}
