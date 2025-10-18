package com.rpg.itens;
import javax.xml.bind.annotation.*;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "adagas")
public class Adagas extends Arma {

    //Construtor
    public Adagas(){
        this.nome = "Adagas";
        this.dano = 0.8;
        this.tipo = TipoDeArma.CURTO_ALCANCE;
        this.minNivel = 1;
        this.attackRange = 0;
        this.attackSpeed = 3;
    }
}
