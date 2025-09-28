package com.rpglab.exceptions;

public class ArmasIguaisException extends Exception{
    
    public ArmasIguaisException(){
        super("O heroi ja esta com essa mesma arma");
    }
}
