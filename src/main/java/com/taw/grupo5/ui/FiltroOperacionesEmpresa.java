package com.taw.grupo5.ui;

import java.sql.Date;
import java.time.LocalDate;

public class FiltroOperacionesEmpresa {
        private String tipoCliente;
        private Date fechaMinima, fechaMaxima;

        public FiltroOperacionesEmpresa(){
            this.tipoCliente = "";
            this.fechaMinima = Date.valueOf(LocalDate.of(1900, 1, 1));
            this.fechaMaxima = Date.valueOf(LocalDate.of(2100, 1, 1));
        }
}
