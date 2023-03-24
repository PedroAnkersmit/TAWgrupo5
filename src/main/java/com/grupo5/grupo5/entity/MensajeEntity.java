package com.grupo5.grupo5.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "mensaje", schema = "grupo5", catalog = "")
public class MensajeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_Mensaje")
    private int idMensaje;
    @Basic
    @Column(name = "contenido")
    private String contenido;
    @Basic
    @Column(name = "enviadoPorAsistente")
    private Byte enviadoPorAsistente;

    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
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

        if (idMensaje != that.idMensaje) return false;
        if (contenido != null ? !contenido.equals(that.contenido) : that.contenido != null) return false;
        if (enviadoPorAsistente != null ? !enviadoPorAsistente.equals(that.enviadoPorAsistente) : that.enviadoPorAsistente != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMensaje;
        result = 31 * result + (contenido != null ? contenido.hashCode() : 0);
        result = 31 * result + (enviadoPorAsistente != null ? enviadoPorAsistente.hashCode() : 0);
        return result;
    }
}
