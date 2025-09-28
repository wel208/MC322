package com.rpglab.exceptions;

public class NaoPossuiNivelException extends Exception{
    
    public NaoPossuiNivelException(){
        super("O heroi nao possui o nivel necessario para equipar esta arma.");
    }
}
