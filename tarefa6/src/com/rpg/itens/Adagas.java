package com.rpg.itens;
public class Adagas extends Arma {

    //Construtor
    public Adagas(){
        this.nome = "Adagas";
        this.tipo = TipoDeArma.CURTO_ALCANCE;
        this.dano = 0.8;
        this.minNivel = 1;
        this.attackRange = 0;
        this.attackSpeed = 3;
    }
}
