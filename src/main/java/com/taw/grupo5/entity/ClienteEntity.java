package com.taw.grupo5.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "cliente", schema = "grupo5", catalog = "")
public class ClienteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idcliente", nullable = false)
    private Integer idCliente;
    @Basic
    @Column(name = "idconversacion", nullable = true)
    private Integer idConversacion;
    @Basic
    @Column(name = "nombre", nullable = true, length = 50)
    private String nombre;
    @Basic
    @Column(name = "email", nullable = true, length = 50)
    private String email;
    @Basic
    @Column(name = "telefono", nullable = true, length = 15)
    private String telefono;
    @Basic
    @Column(name = "fechainicio", nullable = true)
    private Date fechaInicio;
    @ManyToOne
    @JoinColumn(name = "idtipocliente", referencedColumnName = "idtipocliente", nullable = false)
    private TipoclienteEntity tipoclienteByIdTipocliente;
    @ManyToOne
    @JoinColumn(name = "idempresa", referencedColumnName = "idempresa")
    private EmpresaEntity empresaByIdEmpresa;
    @OneToMany(mappedBy = "clientebyidcliente")
    private List<ConversacionEntity> conversacionesByIdCliente;
    @OneToMany(mappedBy = "clientebyidcliente")
    private List<CuentaEntity> cuentasByIdCliente;

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdConversacion() {
        return idConversacion;
    }

    public void setIdConversacion(Integer idConversacion) {
        this.idConversacion = idConversacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClienteEntity cliente = (ClienteEntity) o;

        if (idCliente != null ? !idCliente.equals(cliente.idCliente) : cliente.idCliente != null) return false;
        if (idConversacion != null ? !idConversacion.equals(cliente.idConversacion) : cliente.idConversacion != null)
            return false;
        if (nombre != null ? !nombre.equals(cliente.nombre) : cliente.nombre != null) return false;
        if (email != null ? !email.equals(cliente.email) : cliente.email != null) return false;
        if (telefono != null ? !telefono.equals(cliente.telefono) : cliente.telefono != null) return false;
        if (fechaInicio != null ? !fechaInicio.equals(cliente.fechaInicio) : cliente.fechaInicio != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCliente != null ? idCliente.hashCode() : 0;
        result = 31 * result + (idConversacion != null ? idConversacion.hashCode() : 0);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        result = 31 * result + (fechaInicio != null ? fechaInicio.hashCode() : 0);
        return result;
    }

    public TipoclienteEntity getTipoclienteByIdTipocliente() {
        return tipoclienteByIdTipocliente;
    }

    public void setTipoclienteByIdTipocliente(TipoclienteEntity tipoclienteByIdTipoCliente) {
        this.tipoclienteByIdTipocliente = tipoclienteByIdTipoCliente;
    }

    public EmpresaEntity getEmpresaByIdEmpresa() {
        return empresaByIdEmpresa;
    }

    public void setEmpresaByIdEmpresa(EmpresaEntity empresaByIdEmpresa) {
        this.empresaByIdEmpresa = empresaByIdEmpresa;
    }

    public List<ConversacionEntity> getConversacionesByIdCliente() {
        return conversacionesByIdCliente;
    }

    public void setConversacionesByIdCliente(List<ConversacionEntity> conversacionsByIdCliente) {
        this.conversacionesByIdCliente = conversacionsByIdCliente;
    }

    public List<CuentaEntity> getCuentasByIdCliente() {
        return cuentasByIdCliente;
    }

    public void setCuentasByIdCliente(List<CuentaEntity> cuentasByIdCliente) {
        this.cuentasByIdCliente = cuentasByIdCliente;
    }
}
