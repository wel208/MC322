package com.rpg.itens;
import javax.xml.bind.annotation.*;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "clavaComum")
public class ClavaComum extends Arma {

    //Construtor
    public ClavaComum(){
        this.nome = "Clava Comum";
        this.tipo = TipoDeArma.CURTO_ALCANCE;
        this.dano = 0.85;
        this.minNivel = 0;
        this.attackRange = 1;
        this.attackSpeed = 2;
    }
    
}
