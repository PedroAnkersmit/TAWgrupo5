package com.taw.grupo5.entity;

import javax.persistence.*;

@Entity
@Table(name = "mensaje", schema = "grupo5", catalog = "")
public class MensajeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_Mensaje", nullable = false)
    private Integer idmensaje;
    @Basic
    @Column(name = "contenido", nullable = true, length = -1)
    private String contenido;
    @Basic
    @Column(name = "enviadoPorAsistente", nullable = true)
    private Byte enviadoporasistente;
    @ManyToOne
    @JoinColumn(name = "ID_Conversacion", referencedColumnName = "ID_Conversacion", nullable = false)
    private ConversacionEntity conversacionbyidconversacion;

    public Integer getIdmensaje() {
        return idmensaje;
    }

    public void setIdmensaje(Integer idMensaje) {
        this.idmensaje = idMensaje;
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

    public void setEnviadoporasistente(Byte enviadoPorAsistente) {
        this.enviadoporasistente = enviadoPorAsistente;
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

        return true;
    }

    @Override
    public int hashCode() {
        int result = idmensaje != null ? idmensaje.hashCode() : 0;
        result = 31 * result + (contenido != null ? contenido.hashCode() : 0);
        result = 31 * result + (enviadoporasistente != null ? enviadoporasistente.hashCode() : 0);
        return result;
    }

    public ConversacionEntity getConversacionbyidconversacion() {
        return conversacionbyidconversacion;
    }

    public void setConversacionbyidconversacion(ConversacionEntity conversacionByIdConversacion) {
        this.conversacionbyidconversacion = conversacionByIdConversacion;
    }
}
