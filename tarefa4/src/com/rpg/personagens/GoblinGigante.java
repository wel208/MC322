package com.rpg.personagens;

import com.rpg.itens.*;
import com.rpg.util.*;
import com.rpg.combate.*;
public class GoblinGigante extends Monstro {

    // Construtor
    public GoblinGigante(String nome, int nivelDificuldade, int pos, Arma arma){
        super(nome, nivelDificuldade, pos, arma);
        this.pontosDeVidaMax = 80 + (nivelDificuldade - 1) * 20;
        this.pontosDeVida = this.pontosDeVidaMax;
        this.protecao = 0.4 + (nivelDificuldade - 1) * 0.05;
        this.forca = 25 + (nivelDificuldade - 1) * 3;
        this.moveSpeed = 4;
        this.xpConcedido = 25 + (nivelDificuldade * 15);
        this.sorte = 0.1 + (nivelDificuldade * 0.01);
    }

    @Override
    public AcaoDeCombate escolherAcao(Combatente alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.getPos());

        System.out.printf("\nO GOBLIN GIGANTE está a %d metro(s) do herói e irá ", distancia);

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
