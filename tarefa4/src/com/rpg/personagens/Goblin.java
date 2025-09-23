package com.rpg.personagens;

import com.rpg.itens.*;
import com.rpg.util.*;
import com.rpg.combate.*;
public class Goblin extends Monstro{
    
    public Goblin(String nome, int nivelDificuldade, int pos, Arma arma){
        super(nome, nivelDificuldade, pos, arma);
        this.pontosDeVidaMax = 40 + (nivelDificuldade - 1) * 8;
        this.pontosDeVida = this.pontosDeVidaMax;
        this.protecao = 0.2 + (nivelDificuldade - 1) * 0.02;
        this.forca = 25 + (nivelDificuldade - 1) * 2;
        this.moveSpeed = 7;
        this.xpConcedido = 10 + (nivelDificuldade * 8);
        this.sorte = 0.25 + (nivelDificuldade * 0.01);
        this.dodgeChance = 0.1 + (nivelDificuldade * 0.01);
    }

    @Override
    public AcaoDeCombate escolherAcao(Combatente alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.getPos());

        System.out.printf("\nO GOBLIN esta a %d metros do nosso heroi e ira ", distancia);

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
