package com.rpglab.cenario;

public enum Dificuldade {  
    FACIL(0.5, "Facil"),
    MEDIO(1.0, "Medio"),
    DIFICIL(1.5, "Dificil");

    private double multiplicador; //Multiplicador para alterar a vida e a forca dos monstros
    private String dificuldade;  

    //Construtor
    private Dificuldade(double multiplicador, String dificuldade){
        this.multiplicador = multiplicador;
        this.dificuldade = dificuldade;
    }

    //Getters
    public double getMultiplicador(){
        return multiplicador;
    }
    public String getDificuldade(){
        return dificuldade;
    }
}
