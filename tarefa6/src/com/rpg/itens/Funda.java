package com.rpg.itens;
import javax.xml.bind.annotation.*;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "funda")
public class Funda extends Arma {

    //Construtor
    public Funda(){
        this.nome = "Funda";
        this.tipo = TipoDeArma.LONGO_ALCANCE;
        this.dano = 0.8;
        this.minNivel = 0;
        this.attackRange = 15;
        this.attackSpeed = 2;
    }
    
}
