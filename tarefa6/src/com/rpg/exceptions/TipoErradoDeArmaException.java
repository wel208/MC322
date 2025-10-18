package com.rpg.exceptions;

public class TipoErradoDeArmaException extends Exception{
    
    public TipoErradoDeArmaException(){
        super("\nO heroi nao sabe utilizar esta arma");
    }
}
