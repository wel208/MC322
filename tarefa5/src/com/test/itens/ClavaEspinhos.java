package com.test.itens;
public class ClavaEspinhos extends Arma {

    //Construtor
    public ClavaEspinhos(){
        this.nome = "Clava de Espinhos";
        this.tipo = TipoDeArma.CURTO_ALCANCE;
        this.dano = 1.3;
        this.minNivel = 4;
        this.attackRange = 1;
        this.attackSpeed = 2;
    }
    
}
