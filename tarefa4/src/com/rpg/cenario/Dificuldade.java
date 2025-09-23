package com.rpg.cenario;

public enum Dificuldade {
    FACIL(0.5),
    MEDIO(1.0),
    DIFICIL(1.5);

    private double multiplicador;

    //Construtor
    private Dificuldade(double multiplicador){
        this.multiplicador = multiplicador;
    }

    //Getters
    public double getMultiplicador(){
        return multiplicador;
    }
}
