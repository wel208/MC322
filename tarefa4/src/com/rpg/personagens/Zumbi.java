package com.rpg.personagens;

import com.rpg.cenario.Dificuldade;
import com.rpg.combate.*;
import com.rpg.itens.*;
import com.rpg.util.*;
public class Zumbi extends Monstro {

    // Construtor
    public Zumbi(String nome, int nivel, Arma arma, Dificuldade dificuldade){
        super(nome, nivel, arma, dificuldade);

        this.pontosDeVidaMax = (int)(dificuldade.getMultiplicador() * (50 + (nivel * 10)));
        this.pontosDeVida = this.pontosDeVidaMax;
        this.protecao = 0.2 + (nivel * 0.03);
        this.forca = dificuldade.getMultiplicador() * (15 + (nivel * 4));
        this.moveSpeed = 6;
        this.xpConcedido = 30 + (nivel * 10);
        this.sorte = 0.2 + (nivel * 0.01);
        this.dodgeChance = 0.05 + (nivel * 0.01);
}

    @Override
    public AcaoDeCombate escolherAcao(Combatente alvo){
        double distancia = Utilidades.calcularDistancia(pos, alvo.getPos());

        System.out.printf("\nO ZUMBI está a %.0f metro(s) do herói e irá ", distancia);

        if (distancia <= arma.getAttackRange()){
            System.out.println("ATACAR!\n\n"); Utilidades.esperar();
            return acoes.get(1);
        }
        else{
            System.out.println("SE APROXIMAR!\n"); Utilidades.esperar();
            return acoes.get(0);
        }
    }
}
