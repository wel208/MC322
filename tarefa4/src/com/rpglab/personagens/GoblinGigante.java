package com.rpglab.personagens;

import com.rpglab.cenario.Dificuldade;
import com.rpglab.combate.*;
import com.rpglab.itens.*;
import com.rpglab.util.*;
public class GoblinGigante extends Monstro {

    // Construtor
    public GoblinGigante(String nome, int nivel, Arma arma, Dificuldade dificuldade){
        super(nome, nivel, arma, dificuldade);

        this.pontosDeVidaMax = (int)(dificuldade.getMultiplicador() * (80 + (nivel - 1) * 20));
        this.pontosDeVida = this.pontosDeVidaMax;
        this.protecao = 0.4 + (nivel - 1) * 0.05;
        this.forca = dificuldade.getMultiplicador() * (25 + (nivel - 1) * 3);
        this.moveSpeed = 6;
        this.xpConcedido = 25 + (nivel * 15);
        this.sorte = 0.1 + (nivel * 0.01);
    }

    @Override
    public AcaoDeCombate escolherAcao(Combatente alvo){
        double distancia = Utilidades.calcularDistancia(pos, alvo.getPos());

        System.out.printf("\nO GOBLIN GIGANTE está a %.0f metro(s) do herói e irá ", distancia);

        if (distancia <= arma.getAttackRange()){
            System.out.println("ATACAR!\n"); Utilidades.esperar();
            return acoes.get(1);
        } 
        else{
            System.out.println("AVANÇAR!\n"); Utilidades.esperar();
            return acoes.get(0);
        }
    }
}
