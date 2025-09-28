package com.rpg.exceptions;

public class ArmasIguaisException extends Exception{
    
    public ArmasIguaisException(){
        super("O heroi ja esta com essa mesma arma");
    }
}
