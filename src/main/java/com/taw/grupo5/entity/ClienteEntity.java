package com.taw.grupo5.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "cliente", schema = "grupo5", catalog = "")
public class ClienteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_Cliente", nullable = false)
    private Integer idcliente;
    @Basic
    @Column(name = "ID_Conversacion", nullable = true)
    private Integer idconversacion;
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
    @Column(name = "fechaInicio", nullable = true)
    private Date fechainicio;
    @ManyToOne
    @JoinColumn(name = "ID_TipoCliente", referencedColumnName = "ID_TipoCliente", nullable = false)
    private TipoclienteEntity tipoclientebyidtipocliente;
    @ManyToOne
    @JoinColumn(name = "ID_Empresa", referencedColumnName = "ID_Empresa")
    private EmpresaEntity empresabyidempresa;
    @OneToMany(mappedBy = "clientebyidcliente")
    private List<ConversacionEntity> conversacionsbyidcliente;
    @OneToMany(mappedBy = "clientebyidcliente")
    private List<CuentaEntity> cuentasbyidcliente;

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idCliente) {
        this.idcliente = idCliente;
    }

    public Integer getIdconversacion() {
        return idconversacion;
    }

    public void setIdconversacion(Integer idConversacion) {
        this.idconversacion = idConversacion;
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

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechaInicio) {
        this.fechainicio = fechaInicio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClienteEntity cliente = (ClienteEntity) o;

        if (idcliente != null ? !idcliente.equals(cliente.idcliente) : cliente.idcliente != null) return false;
        if (idconversacion != null ? !idconversacion.equals(cliente.idconversacion) : cliente.idconversacion != null)
            return false;
        if (nombre != null ? !nombre.equals(cliente.nombre) : cliente.nombre != null) return false;
        if (email != null ? !email.equals(cliente.email) : cliente.email != null) return false;
        if (telefono != null ? !telefono.equals(cliente.telefono) : cliente.telefono != null) return false;
        if (fechainicio != null ? !fechainicio.equals(cliente.fechainicio) : cliente.fechainicio != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idcliente != null ? idcliente.hashCode() : 0;
        result = 31 * result + (idconversacion != null ? idconversacion.hashCode() : 0);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        result = 31 * result + (fechainicio != null ? fechainicio.hashCode() : 0);
        return result;
    }

    public TipoclienteEntity getTipoclientebyidtipocliente() {
        return tipoclientebyidtipocliente;
    }

    public void setTipoclientebyidtipocliente(TipoclienteEntity tipoclienteByIdTipoCliente) {
        this.tipoclientebyidtipocliente = tipoclienteByIdTipoCliente;
    }

    public EmpresaEntity getEmpresabyidempresa() {
        return empresabyidempresa;
    }

    public void setEmpresabyidempresa(EmpresaEntity empresaByIdEmpresa) {
        this.empresabyidempresa = empresaByIdEmpresa;
    }

    public List<ConversacionEntity> getConversacionsbyidcliente() {
        return conversacionsbyidcliente;
    }

    public void setConversacionsbyidcliente(List<ConversacionEntity> conversacionsByIdCliente) {
        this.conversacionsbyidcliente = conversacionsByIdCliente;
    }

    public List<CuentaEntity> getCuentasbyidcliente() {
        return cuentasbyidcliente;
    }

    public void setCuentasbyidcliente(List<CuentaEntity> cuentasByIdCliente) {
        this.cuentasbyidcliente = cuentasByIdCliente;
    }
}
