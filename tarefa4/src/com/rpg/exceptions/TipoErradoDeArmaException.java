package com.rpg.exceptions;

public class TipoErradoDeArmaException extends Exception{
    
    public TipoErradoDeArmaException(){
        super("O heroi nao sabe utilizar esta arma");
    }
}
