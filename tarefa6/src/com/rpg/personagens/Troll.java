package com.rpg.personagens;

import com.rpg.cenario.Dificuldade;
import com.rpg.combate.*;
import com.rpg.itens.*;
import com.rpg.util.*;
import javax.xml.bind.annotation.*;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "troll")
public class Troll extends Monstro {

    public Troll(){
        super("nome", 1, null, null);
    }

    // Construtor
    public Troll(String nome, int nivel, Arma arma, Dificuldade dificuldade){
        super(nome, nivel, arma, dificuldade);

        this.pontosDeVidaMax = (int)(dificuldade.getMultiplicador() * (80 + (nivel - 1) * 30));
        this.pontosDeVida = this.pontosDeVidaMax;
        this.protecao = Math.min(0.5, 0.5 + (nivel - 1) * 0.04);
        this.forca = dificuldade.getMultiplicador() * (40 + (nivel - 1) * 2);
        this.moveSpeed = 6;
        this.xpConcedido = 50 + (nivel * 20);
        this.sorte = 0.3 + (nivel * 0.01);
    }

    @Override
    public AcaoDeCombate escolherAcao(Combatente alvo){
        double distancia = Utilidades.calcularDistancia(pos, alvo.getPos());

        System.out.printf("\nO TROLL está a %.0f metro(s) do herói e irá ", distancia);

        if (distancia <= arma.getAttackRange()){
            System.out.println("ATACAR!\n"); Utilidades.esperar();
            return acoes.get(1);
        } 
        else{
            System.out.println("SE APROXIMAR!\n"); Utilidades.esperar();
            return acoes.get(0);
        }
    }
}
