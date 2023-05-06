package com.taw.grupo5.ui;

public class FiltroAsistente {
    private String nombreOCorreo;
    private Byte abierta;

    public FiltroAsistente(){
        nombreOCorreo="";
        Byte abierta = null;
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
