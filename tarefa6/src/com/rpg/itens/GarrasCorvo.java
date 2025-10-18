package com.rpg.itens;
import javax.xml.bind.annotation.*;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "garrasCorvo")
public class GarrasCorvo extends Arma {

    //Construtor
    public GarrasCorvo(){
        this.nome = "Garras do Corvo";
        this.tipo = TipoDeArma.LONGO_ALCANCE;
        this.dano = 1.2;
        this.minNivel = 4;
        this.attackRange = 0;
        this.attackSpeed = 4;
    }
    
}
