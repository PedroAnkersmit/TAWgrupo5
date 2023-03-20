package com.grupo5.grupo5;

import jakarta.persistence.*;

@Entity
@Table(name = "conversacion", schema = "grupo5", catalog = "")
public class ConversacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_Conversacion")
    private int idConversacion;
    @Basic
    @Column(name = "asunto")
    private String asunto;
    @Basic
    @Column(name = "abierto")
    private Byte abierto;

    public int getIdConversacion() {
        return idConversacion;
    }

    public void setIdConversacion(int idConversacion) {
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

        if (idConversacion != that.idConversacion) return false;
        if (asunto != null ? !asunto.equals(that.asunto) : that.asunto != null) return false;
        if (abierto != null ? !abierto.equals(that.abierto) : that.abierto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idConversacion;
        result = 31 * result + (asunto != null ? asunto.hashCode() : 0);
        result = 31 * result + (abierto != null ? abierto.hashCode() : 0);
        return result;
    }
}
