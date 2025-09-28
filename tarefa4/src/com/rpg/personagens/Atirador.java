package com.rpg.personagens;

import com.rpg.itens.*;
import com.rpg.util.*;
import com.rpg.combate.*;
/*
 * Classe de herói que tem movimentação rápida
 * Não possui muitos pontos de vida e proteção
 * Prefere dar ataques a longa distãncia
 */

public class Atirador extends Heroi {

    //Atributos
    private double precisao;

    //Construtor
    public Atirador(String nome, Arma arma){
        super(nome, arma);
        this.pontosDeVidaMax = 70;
        this.pontosDeVida = pontosDeVidaMax;
        this.moveSpeed = 9;
        this.forca = 40;
        this.protecao = 0.4;
        this.precisao = 0.3;
        this.dodgeChance = 0.25;
    }
    
    //Métodos
    @Override
    public void exibirStatus(){
        System.out.printf("\n%s, O ATIRADOR possui:", nome); Utilidades.esperar();
        System.out.printf("\n%d PONTOS DE VIDA;", pontosDeVida); Utilidades.esperar();
        System.out.printf("\nNIVEL %d e %d/%d de EXPERIENCIA;", getNivel(), getExperiencia(), getExpProximoNivel()); Utilidades.esperar();
        System.out.printf("\n%.0f pontos de FORCA e %.0f pontos de PROTECAO;", forca, protecao * 100); Utilidades.esperar();
        System.out.printf("\n%.0f%% de PRECISAO;", precisao * 100); Utilidades.esperar();
        System.out.printf("\n%.0f/100 de SORTE;", sorte * 100); Utilidades.esperar();
        System.out.printf("\n%s esta utilizando %s como ARMA;", nome, arma.getNome()); Utilidades.esperar();
        System.out.printf("\nCapacidade de dar %d ATAQUE(S) POR TURNO e pode percorrer %d METROS POR TURNO.\n", arma.getAttackSpeed(), moveSpeed); Utilidades.esperar();
    }

    /*
     * O atirador toma sua decisão com base na distância que ele está do inimigo
     * Quanto mais perto, mais chance de se afastar
     * Quanto mais longe, mais chance de atacar ou usar sua habilidade especial
     */
    @Override
    public AcaoDeCombate escolherAcao(Combatente alvo){
        double distancia = Utilidades.calcularDistancia(pos, alvo.getPos());
        double distanciaBase = (double)arma.getAttackRange();
        double chance = Math.random();
        boolean seMover = false, atacarInim = false;

        System.out.printf("\n%s, o atirador, esta a %.0f metros do monstro e ira ", nome, distancia); Utilidades.esperar();

        if (distancia < distanciaBase * 0.3){
            if (chance < 0.95)
                seMover = true;
            else if (chance < 0.99)
                atacarInim = true;
        }

        else if (distancia <= distanciaBase * 0.8){
            if (chance < 0.45)
                seMover = true;
            else if (chance < 0.9)
                atacarInim = true;
        }

        else if (distancia <= arma.getAttackRange()){
            if (chance < 0.1)
                seMover = true;
            else if (chance < 0.85)
                atacarInim = true;
        }

        else
            seMover = true;

        if (seMover){
            if (distancia < arma.getAttackRange()){
                System.out.println("se AFASTAR do inimigo para tentar atacar!\n"); Utilidades.esperar();
            }
            else{
                System.out.println("ter que se APROXIMAR do inimigo para tentar atacar!\n"); Utilidades.esperar();
            }
            return acoes.get(0);
        }
        else if (atacarInim){
            System.out.println("ATACAR seu inimigo!\n");
            return acoes.get(1);
        }
        else{
            System.out.println("usar sua HABILIDADE ESPECIAL!\n");
            return acoes.get(2);
        }
    }

    /*
     * Método que aumenta a precisão do atirador a cada nível de XP par alcançado
     */
    @Override
    protected void melhorarAtributoUnico(){
        precisao += 0.05;
        if (precisao > 0.6) precisao = 0.6;
    }

    @Override
    public double getAtributoUnico(){
        return precisao;
    }
}
