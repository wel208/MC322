package com.test.itens;

public enum TipoDeArma {
    CURTO_ALCANCE("Corpo a Corpo"),
    LONGO_ALCANCE("Longo Alcance");

    private String tipoString;

    private TipoDeArma(String tipoString){
        this.tipoString = tipoString;
    }

    public String getTipoString(){
        return tipoString;
    }
}
