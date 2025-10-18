package com.rpg.itens;
import javax.xml.bind.annotation.*;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "espada")
public class Espada extends Arma {

    //Construtor
    public Espada(){
        this.nome = "Espada";
        this.tipo = TipoDeArma.CURTO_ALCANCE;
        this.dano = 1.1;
        this.minNivel = 0;
        this.attackRange = 1;
        this.attackSpeed = 1;
    }
}
