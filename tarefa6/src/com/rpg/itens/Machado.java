package com.rpg.itens;
import javax.xml.bind.annotation.*;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "machado")
public class Machado extends Arma {

    //Construtor
    public Machado(){
        this.nome = "Machado";
        this.tipo = TipoDeArma.CURTO_ALCANCE;
        this.dano = 1.1;
        this.minNivel = 2;
        this.attackRange = 1;
        this.attackSpeed = 2;
    }
    
}