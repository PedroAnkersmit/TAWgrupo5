package com.taw.grupo5.entity;

import javax.persistence.*;
import java.util.List;
import java.util.List;

@Entity
@Table(name = "conversacion", schema = "grupo5", catalog = "")
public class ConversacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idconversacion", nullable = false)
    private Integer idconversacion;
    @Basic
    @Column(name = "asunto", nullable = true, length = 100)
    private String asunto;
    @Basic
    @Column(name = "abierto", nullable = true)
    private Byte abierto;
    @ManyToOne
    @JoinColumn(name = "idempleado", referencedColumnName = "idempleado", nullable = false)
    private EmpleadoEntity empleadoByIdempleado;
    @ManyToOne
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente", nullable = false)
    private ClienteEntity clienteByIdcliente;
    @OneToMany(mappedBy = "conversacionByIdconversacion")
    private List<MensajeEntity> mensajesByIdconversacion;

    public Integer getIdconversacion() {
        return idconversacion;
    }

    public void setIdconversacion(Integer idconversacion) {
        this.idconversacion = idconversacion;
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

    public EmpleadoEntity getEmpleadoByIdempleado() {
        return empleadoByIdempleado;
    }

    public void setEmpleadoByIdempleado(EmpleadoEntity empleadoByIdempleado) {
        this.empleadoByIdempleado = empleadoByIdempleado;
    }

    public ClienteEntity getClienteByIdcliente() {
        return clienteByIdcliente;
    }

    public void setClienteByIdcliente(ClienteEntity clienteByIdcliente) {
        this.clienteByIdcliente = clienteByIdcliente;
    }

    public List<MensajeEntity> getMensajesByIdconversacion() {
        return mensajesByIdconversacion;
    }

    public void setMensajesByIdconversacion(List<MensajeEntity> mensajesByIdconversacion) {
        this.mensajesByIdconversacion = mensajesByIdconversacion;
    }

    public void setMensajesByIdconversacion(List<MensajeEntity> mensajesByIdconversacion) {
        this.mensajesByIdconversacion = mensajesByIdconversacion;
    }
}
