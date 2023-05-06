package com.taw.grupo5.dto;/*
Created by Pedro Ankersmit Carrión
*/

import java.io.Serializable;

public class MensajeDTO implements Serializable {
    private Integer idmensaje;

    private String contenido;

    private Byte enviadoporasistente;

    private ConversacionDTO conversacion;

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

    public ConversacionDTO getConversacion() {
        return conversacion;
    }

    public void setConversacion(ConversacionDTO conversacion) {
        this.conversacion = conversacion;
    }
}
