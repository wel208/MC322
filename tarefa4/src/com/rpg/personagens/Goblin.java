package com.rpg.personagens;

import com.rpg.itens.*;
import com.rpg.util.*;
import com.rpg.cenario.Dificuldade;
import com.rpg.combate.*;
public class Goblin extends Monstro{
    
    public Goblin(String nome, int nivel, Arma arma, Dificuldade dificuldade){
        super(nome, nivel, arma, dificuldade);

        this.pontosDeVidaMax = (int)(dificuldade.getMultiplicador() * (40 + (nivel - 1) * 8));
        this.pontosDeVida = this.pontosDeVidaMax;
        this.protecao = 0.2 + (nivel - 1) * 0.02;
        this.forca = dificuldade.getMultiplicador() * (25 + (nivel - 1) * 2);
        this.moveSpeed = 7;
        this.xpConcedido = 10 + (nivel * 8);
        this.sorte = 0.25 + (nivel * 0.01);
        this.dodgeChance = 0.1 + (nivel * 0.01);
    }

    @Override
    public AcaoDeCombate escolherAcao(Combatente alvo){
        double distancia = Utilidades.calcularDistancia(pos, alvo.getPos());

        System.out.printf("\nO GOBLIN esta a %.0f metros do nosso heroi e ira ", distancia);

        if (distancia <= arma.getAttackRange()){
            System.out.println("ATACA-LO!\n"); Utilidades.esperar();
            return acoes.get(1);
        }
        else{
            System.out.println("CORRER NA DIRECAO DELE!\n"); Utilidades.esperar();
            return acoes.get(0);
        }
    }
}
