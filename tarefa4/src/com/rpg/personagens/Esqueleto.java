package com.rpg.personagens;

import com.rpg.itens.*;
import com.rpg.util.*;
import com.rpg.cenario.Dificuldade;
import com.rpg.combate.*;
/*
 * Classe de monstro bem semelhante ao Arqueiro
 * Não possui muita vida ou proteção
 * Prefere ataques de longa distância
 */

public class Esqueleto extends Monstro {
    
    //Atributos
    private double precisao;

    //Construtor
    public Esqueleto(String nome, int nivel, Arma arma, Dificuldade dificuldade){
        super(nome, nivel, arma, dificuldade);

        this.pontosDeVidaMax = (int)(dificuldade.getMultiplicador() * (50 + (nivel - 1) * 10));
        this.pontosDeVida = this.pontosDeVidaMax;
        this.protecao = 0.2 + (nivel - 1) * 0.03;
        this.forca = dificuldade.getMultiplicador() * (15 + (nivel - 1) * 2);
        this.precisao = 0.3 + (nivel - 1) * 0.05;
        this.moveSpeed = 10;
        this.xpConcedido = 15 + (nivel * 10);
        this.sorte = 0.3 + (nivel * 0.01);
    }

    @Override
    public AcaoDeCombate escolherAcao(Combatente alvo){
        double distancia = Utilidades.calcularDistancia(pos, alvo.getPos());
        int distanciaBase = arma.getAttackRange();
        double chance = Math.random();
        boolean atacarInim = false;

        System.out.printf("\nO esqueleto esta a %.0f metro(s) do nosso heroi e ira ", distancia); 

        if (distancia <= distanciaBase * 0.3)
            if (chance <= 0.2)
                atacarInim = true;

        else if (distancia <= distanciaBase * 0.5)
            if (chance <= 0.4)
                atacarInim = true;

        else if (distancia <= distanciaBase * 0.8)
            if (chance <= 0.7)
                atacarInim = true;

        else if (distancia <= distanciaBase)
            atacarInim = true;
        
        if (atacarInim){
            System.out.println("ATACAR!\n"); Utilidades.esperar();
            return acoes.get(1);
        }
        else{
            if (distancia < arma.getAttackRange())
                System.out.println("SE AFASTAR!\n");
            else
                System.out.println("SE APROXIMAR para poder atacar!\n");
            Utilidades.esperar();
            return acoes.get(0);
        }
    }

    @Override
    public double getAtributoUnico(){
        return precisao;
    }
}
