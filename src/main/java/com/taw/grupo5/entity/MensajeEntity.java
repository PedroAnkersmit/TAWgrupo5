package com.taw.grupo5.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "mensaje", schema = "grupo5", catalog = "")
public class MensajeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idmensaje", nullable = false)
    private Integer idmensaje;
    @Basic
    @Column(name = "contenido", nullable = true, length = -1)
    private String contenido;
    @Basic
    @Column(name = "enviadoporasistente", nullable = true)
    private Byte enviadoporasistente;
    @Basic
    @Column(name = "fechaenvio", nullable = true)
    private Date fechaenvio;
    @ManyToOne
    @JoinColumn(name = "idconversacion", referencedColumnName = "idconversacion", nullable = false)
    private ConversacionEntity conversacionByIdconversacion;

    public Integer getIdmensaje() {
        return idmensaje;
    }

    public void setIdmensaje(Integer idmensaje) {
        this.idmensaje = idmensaje;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Byte getEnviadoporasistente() {
        return enviadoporasistente;
    }

    public void setEnviadoporasistente(Byte enviadoporasistente) {
        this.enviadoporasistente = enviadoporasistente;
    }

    public Date getFechaenvio() {
        return fechaenvio;
    }

    public void setFechaenvio(Timestamp fechaenvio) {
        this.fechaenvio = fechaenvio;
    }

    public void setFechaenvio(Date fechaenvio) {
        this.fechaenvio = fechaenvio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MensajeEntity that = (MensajeEntity) o;

        if (idmensaje != null ? !idmensaje.equals(that.idmensaje) : that.idmensaje != null) return false;
        if (contenido != null ? !contenido.equals(that.contenido) : that.contenido != null) return false;
        if (enviadoporasistente != null ? !enviadoporasistente.equals(that.enviadoporasistente) : that.enviadoporasistente != null)
            return false;
        if (fechaenvio != null ? !fechaenvio.equals(that.fechaenvio) : that.fechaenvio != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idmensaje != null ? idmensaje.hashCode() : 0;
        result = 31 * result + (contenido != null ? contenido.hashCode() : 0);
        result = 31 * result + (enviadoporasistente != null ? enviadoporasistente.hashCode() : 0);
        result = 31 * result + (fechaenvio != null ? fechaenvio.hashCode() : 0);
        return result;
    }

    public ConversacionEntity getConversacionByIdconversacion() {
        return conversacionByIdconversacion;
    }

    public void setConversacionByIdconversacion(ConversacionEntity conversacionByIdconversacion) {
        this.conversacionByIdconversacion = conversacionByIdconversacion;
    }
}
