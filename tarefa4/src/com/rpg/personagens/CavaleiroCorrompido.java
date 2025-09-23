package com.rpg.personagens;

import com.rpg.itens.*;
import com.rpg.util.*;
import com.rpg.combate.*;
public class CavaleiroCorrompido extends Monstro {

   public CavaleiroCorrompido(String nome, int nivelDificuldade, int pos, Arma arma){
        super(nome, nivelDificuldade, pos, arma);

        this.pontosDeVidaMax = 80 + (nivelDificuldade * 15);
        this.pontosDeVida = this.pontosDeVidaMax;
        this.protecao = 0.2 + (nivelDificuldade * 0.03);
        this.forca = 12 + (nivelDificuldade * 3);
        this.sorte = 0.2 + (nivelDificuldade * 0.01);
        this.moveSpeed = 5;
        this.xpConcedido = 20 + (nivelDificuldade * 10);
}

    @Override
    public AcaoDeCombate escolherAcao(Combatente alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.getPos());

        System.out.printf("\nO Cavaleiro Corrompido esta a %d metro(s) do heroi e ira ", distancia);

        if (distancia <= arma.getAttackRange()){
            System.out.println("ATACAR!\n"); Utilidades.esperar();
            return acoes.get(1);
        }
        else{
            System.out.println("AVANCAR!\n"); Utilidades.esperar();
            return acoes.get(0);
        }
    }
}
