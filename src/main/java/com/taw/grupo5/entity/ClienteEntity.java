package com.taw.grupo5.entity;

import com.taw.grupo5.dto.ClienteDTO;
import com.taw.grupo5.dto.CuentaDTO;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente", schema = "grupo5", catalog = "")
public class ClienteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idcliente", nullable = false)
    private Integer idcliente;
    @Basic
    @Column(name = "idconversacion", nullable = true)
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
    @Column(name = "fechainicio", nullable = true)
    private Date fechainicio;
    @ManyToOne
    @JoinColumn(name = "idtipocliente", referencedColumnName = "idtipocliente", nullable = false)
    private TipoclienteEntity tipoclienteByIdtipocliente;
    @ManyToOne
    @JoinColumn(name = "idempresa", referencedColumnName = "idempresa")
    private EmpresaEntity empresaByIdempresa;
    @OneToMany(mappedBy = "clienteByIdcliente")
    private List<ConversacionEntity> conversacionsByIdcliente;
    @OneToMany(mappedBy = "clienteByIdcliente")
    private List<CuentaEntity> cuentasByIdcliente;

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getIdconversacion() {
        return idconversacion;
    }

    public void setIdconversacion(Integer idconversacion) {
        this.idconversacion = idconversacion;
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

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
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

    public TipoclienteEntity getTipoclienteByIdtipocliente() {
        return tipoclienteByIdtipocliente;
    }

    public void setTipoclienteByIdtipocliente(TipoclienteEntity tipoclienteByIdtipocliente) {
        this.tipoclienteByIdtipocliente = tipoclienteByIdtipocliente;
    }

    public EmpresaEntity getEmpresaByIdempresa() {
        return empresaByIdempresa;
    }

    public void setEmpresaByIdempresa(EmpresaEntity empresaByIdempresa) {
        this.empresaByIdempresa = empresaByIdempresa;
    }

    public List<ConversacionEntity> getConversacionsByIdcliente() {
        return conversacionsByIdcliente;
    }

    public void setConversacionsByIdcliente(List<ConversacionEntity> conversacionsByIdcliente) {
        this.conversacionsByIdcliente = conversacionsByIdcliente;
    }

    public List<CuentaEntity> getCuentasByIdcliente() {
        return cuentasByIdcliente;
    }

    public void setCuentasByIdcliente(List<CuentaEntity> cuentasByIdcliente) {
        this.cuentasByIdcliente = cuentasByIdcliente;
    }

    // Pablo
    public ClienteDTO toDTO(){

        ClienteDTO dto = new ClienteDTO();

        dto.setEmail(this.email);
        dto.setFechainicio(this.fechainicio);
        dto.setIdCliente(this.idcliente);
        dto.setTelefono(this.telefono);
        dto.setNombre(this.nombre);
        if(this.empresaByIdempresa != null) dto.setEmpresa(this.empresaByIdempresa.toDTO());
        dto.setIdConversacion(this.idconversacion);
        dto.setTipoCliente(this.tipoclienteByIdtipocliente.toDTO());

        List<CuentaDTO> cuentas = new ArrayList<>();

        for(CuentaEntity c : cuentasByIdcliente) cuentas.add(c.toDTO(dto));

        dto.setCuentasByIdCliente(cuentas);

        return dto;

    }
}
