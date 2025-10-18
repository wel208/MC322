package com.rpg.itens;
import javax.xml.bind.annotation.*;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "lanca")
public class Lança extends Arma {

    //Construtor
    public Lança(){
        this.nome = "Lança";
        this.tipo = TipoDeArma.CURTO_ALCANCE;
        this.dano = 1.1;
        this.minNivel = 2;
        this.attackRange = 3;
        this.attackSpeed = 2;
    }
    
}
