package com.rpg.personagens;

import com.rpg.cenario.Dificuldade;
import com.rpg.combate.*;
import com.rpg.itens.*;
import com.rpg.util.*;
public class CavaleiroCorrompido extends Monstro {

   public CavaleiroCorrompido(String nome, int nivel, Arma arma, Dificuldade dificuldade){
        super(nome, nivel, arma, dificuldade);

        this.pontosDeVidaMax = (int)(dificuldade.getMultiplicador() * (80 + (nivel * 15)));
        this.pontosDeVida = this.pontosDeVidaMax;
        this.protecao = Math.min(0.8, 0.2 + (nivel * 0.03));
        this.forca = dificuldade.getMultiplicador() * (12 + (nivel * 3));
        this.sorte = 0.2 + (nivel * 0.01);
        this.moveSpeed = 7;
        this.xpConcedido = 20 + (nivel * 10);
}

    @Override
    public AcaoDeCombate escolherAcao(Combatente alvo){
        double distancia = Utilidades.calcularDistancia(pos, alvo.getPos());

        System.out.printf("\nO Cavaleiro Corrompido esta a %.0f metro(s) do heroi e ira ", distancia);

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
