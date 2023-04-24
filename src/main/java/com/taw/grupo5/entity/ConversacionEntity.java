package com.taw.grupo5.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "conversacion", schema = "grupo5", catalog = "")
public class ConversacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_Conversacion", nullable = false)
    private Integer idconversacion;
    @Basic
    @Column(name = "asunto", nullable = true, length = 100)
    private String asunto;
    @Basic
    @Column(name = "abierto", nullable = true)
    private Byte abierto;
    @ManyToOne
    @JoinColumn(name = "ID_Empleado", referencedColumnName = "ID_Empleado", nullable = false)
    private EmpleadoEntity empleadobyidempleado;
    @ManyToOne
    @JoinColumn(name = "ID_Cliente", referencedColumnName = "ID_Cliente", nullable = false)
    private ClienteEntity clientebyidcliente;
    @OneToMany(mappedBy = "conversacionbyidconversacion")
    private List<MensajeEntity> mensajesbyidconversacion;

    public Integer getIdconversacion() {
        return idconversacion;
    }

    public void setIdconversacion(Integer idConversacion) {
        this.idconversacion = idConversacion;
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

        if (idconversacion != null ? !idconversacion.equals(that.idconversacion) : that.idconversacion != null)
            return false;
        if (asunto != null ? !asunto.equals(that.asunto) : that.asunto != null) return false;
        if (abierto != null ? !abierto.equals(that.abierto) : that.abierto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idconversacion != null ? idconversacion.hashCode() : 0;
        result = 31 * result + (asunto != null ? asunto.hashCode() : 0);
        result = 31 * result + (abierto != null ? abierto.hashCode() : 0);
        return result;
    }

    public EmpleadoEntity getEmpleadobyidempleado() {
        return empleadobyidempleado;
    }

    public void setEmpleadobyidempleado(EmpleadoEntity empleadoByIdEmpleado) {
        this.empleadobyidempleado = empleadoByIdEmpleado;
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
