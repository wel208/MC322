package com.rpg.itens;
public class Crossbow extends Arma {

    //Construtor
    public Crossbow(){
        this.nome = "Besta";
        this.tipo = TipoDeArma.LONGO_ALCANCE;
        this.dano = 1.3;
        this.minNivel = 3;
        this.attackRange = 30;
        this.attackSpeed = 2;
    }
}
