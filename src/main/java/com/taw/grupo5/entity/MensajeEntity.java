package com.taw.grupo5.entity;

import javax.persistence.*;

@Entity
@Table(name = "mensaje", schema = "grupo5", catalog = "")
public class MensajeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idmensaje", nullable = false)
    private Integer idMensaje;
    @Basic
    @Column(name = "contenido", nullable = true, length = -1)
    private String contenido;
    @Basic
    @Column(name = "enviadoporasistente", nullable = true)
    private Byte enviadoPorAsistente;
    @ManyToOne
    @JoinColumn(name = "idconversacion", referencedColumnName = "idconversacion", nullable = false)
    private ConversacionEntity conversacionByIdConversacion;

    public Integer getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(Integer idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Byte getEnviadoPorAsistente() {
        return enviadoPorAsistente;
    }

    public void setEnviadoPorAsistente(Byte enviadoPorAsistente) {
        this.enviadoPorAsistente = enviadoPorAsistente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MensajeEntity that = (MensajeEntity) o;

        if (idMensaje != null ? !idMensaje.equals(that.idMensaje) : that.idMensaje != null) return false;
        if (contenido != null ? !contenido.equals(that.contenido) : that.contenido != null) return false;
        if (enviadoPorAsistente != null ? !enviadoPorAsistente.equals(that.enviadoPorAsistente) : that.enviadoPorAsistente != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMensaje != null ? idMensaje.hashCode() : 0;
        result = 31 * result + (contenido != null ? contenido.hashCode() : 0);
        result = 31 * result + (enviadoPorAsistente != null ? enviadoPorAsistente.hashCode() : 0);
        return result;
    }

    public ConversacionEntity getConversacionByIdConversacion() {
        return conversacionByIdConversacion;
    }

    public void setConversacionByIdConversacion(ConversacionEntity conversacionByIdConversacion) {
        this.conversacionByIdConversacion = conversacionByIdConversacion;
    }
}
