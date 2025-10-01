package com.test.itens;
public class FacaArremesso extends Arma {
    
    //Construtor
    public FacaArremesso(){
        this.nome = "Faca de Arremesso";
        this.tipo = TipoDeArma.LONGO_ALCANCE;
        this.dano = 0.8;
        this.minNivel = 1;
        this.attackRange = 10;
        this.attackSpeed = 3;
    }

}
