package com.taw.grupo5.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "conversacion", schema = "grupo5", catalog = "")
public class ConversacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idconversacion", nullable = false)
    private Integer idConversacion;
    @Basic
    @Column(name = "asunto", nullable = true, length = 100)
    private String asunto;
    @Basic
    @Column(name = "abierto", nullable = true)
    private Byte abierto;
    @ManyToOne
    @JoinColumn(name = "idempleado", referencedColumnName = "idempleado", nullable = false)
    private EmpleadoEntity empleadoByIdEmpleado;
    @ManyToOne
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente", nullable = false)
    private ClienteEntity clientebyidcliente;
    @OneToMany(mappedBy = "conversacionbyidconversacion")
    private List<MensajeEntity> mensajesbyidconversacion;

    public Integer getIdConversacion() {
        return idConversacion;
    }

    public void setIdConversacion(Integer idConversacion) {
        this.idConversacion = idConversacion;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Byte getAbierto() {
        return abierto;
    }

    public void setAbierto(Byte abierto) {
        this.abierto = abierto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConversacionEntity that = (ConversacionEntity) o;

        if (idConversacion != null ? !idConversacion.equals(that.idConversacion) : that.idConversacion != null)
            return false;
        if (asunto != null ? !asunto.equals(that.asunto) : that.asunto != null) return false;
        if (abierto != null ? !abierto.equals(that.abierto) : that.abierto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idConversacion != null ? idConversacion.hashCode() : 0;
        result = 31 * result + (asunto != null ? asunto.hashCode() : 0);
        result = 31 * result + (abierto != null ? abierto.hashCode() : 0);
        return result;
    }

    public EmpleadoEntity getEmpleadoByIdEmpleado() {
        return empleadoByIdEmpleado;
    }

    public void setEmpleadoByIdEmpleado(EmpleadoEntity empleadoByIdEmpleado) {
        this.empleadoByIdEmpleado = empleadoByIdEmpleado;
    }

    public ClienteEntity getClientebyidcliente() {
        return clientebyidcliente;
    }

    public void setClientebyidcliente(ClienteEntity clienteByIdCliente) {
        this.clientebyidcliente = clienteByIdCliente;
    }

    public List<MensajeEntity> getMensajesbyidconversacion() {
        return mensajesbyidconversacion;
    }

    public void setMensajesbyidconversacion(List<MensajeEntity> mensajesByIdConversacion) {
        this.mensajesbyidconversacion = mensajesByIdConversacion;
    }
}
