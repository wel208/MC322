package com.test.itens;
public class Arco extends Arma {

    //Construtor
    public Arco(){
        this.nome = "Arco e Flecha";
        this.tipo = TipoDeArma.LONGO_ALCANCE;
        this.dano = 0.9;
        this.minNivel = 0;
        this.attackRange = 25;
        this.attackSpeed = 1;
    }
    
}
