package com.rpg.personagens;

import com.rpg.itens.*;
import com.rpg.util.*;
import com.rpg.cenario.Dificuldade;
import com.rpg.combate.*;
/*
 * Mulher que se transforma num enorme e forte monstro
 * Muitos pontos de vida e boa proteção
 * Consegue atacar a uma distância <= 3 metros
 * Não é rápida, pouca chance de esquivar de um ataque
 */

public class Ninfa extends Monstro{
    
    //Construtor
    public Ninfa(String nome, int nivel, Arma arma, Dificuldade dificuldade){
        super(nome, nivel, arma, dificuldade);
        
        this.pontosDeVidaMax = (int)(dificuldade.getMultiplicador() * (100 + (nivel - 1) * 20));
        this.pontosDeVida = this.pontosDeVidaMax;
        this.protecao = 0.4 + (nivel - 1) * 0.01;
        this.forca = dificuldade.getMultiplicador() * (40 + (nivel - 1) * 3);
        this.moveSpeed = 3;
        this.xpConcedido = 30 + (nivel * 15);
        this.sorte = 0.3 + (nivel * 0.02);
    }

    @Override
    public AcaoDeCombate escolherAcao(Combatente alvo){
        double distancia = Utilidades.calcularDistancia(pos, alvo.getPos());

        System.out.printf("\nA NINFA está a %.0f metros do nosso herói e ira ", distancia);

        if (distancia <= arma.getAttackRange()){
            System.out.print("ATACAR!\n"); Utilidades.esperar();
            return acoes.get(1);
        }
        else{
            System.out.print("SE APROXIMAR!\n"); Utilidades.esperar();
            return acoes.get(0);
        }
    }
}
