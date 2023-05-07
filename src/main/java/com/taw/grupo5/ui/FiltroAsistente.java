package com.taw.grupo5.ui;
/**
 * @author Hilaria Romero Bouyahia
 */
public class FiltroAsistente {
    private String asunto;
    private String nombreOCorreo;
    private Byte abierta;

    public FiltroAsistente(){
        asunto="";
        nombreOCorreo="";
        Byte abierta = null;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getNombreOCorreo() {
        return nombreOCorreo;
    }

    public void setNombreOCorreo(String nombreOCorreo) {
        this.nombreOCorreo = nombreOCorreo;
    }

    public Byte getAbierta() {
        return abierta;
    }

    public void setAbierta(Byte abierta) {
        this.abierta = abierta;
    }
}
