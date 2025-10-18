package com.rpg.itens;
import javax.xml.bind.annotation.*;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "penasCorvo")
public class PenasCorvo extends Arma {

    //Construtor
    public PenasCorvo(){
        this.nome = "Penas de Corvo";
        this.tipo = TipoDeArma.LONGO_ALCANCE;
        this.dano = 0.9;
        this.minNivel = 4;
        this.attackRange = 30;
        this.attackSpeed = 4;
    }
}
