package com.rpg.personagens;

import com.rpg.itens.*;
import com.rpg.util.*;
import com.rpg.combate.*;
/*
 * Classe de herói que não tem uma movimentação tão rápida
 * Executa ataques corpo a corpo
 * Boa quantidade de pontos de vida, de força e de proteção
 * Boa chance de esquivar de ataques inimigos
 */

public class Lutador extends Heroi{

    //Atributos
    private int furia;

    //Construtor
    public Lutador(String nome, Arma arma){ //Atributos predefinidos para um lutador de nível 0
        super(nome, arma);
        this.pontosDeVidaMax = 110;
        this.pontosDeVida = pontosDeVidaMax;
        this.moveSpeed = 7;
        this.forca = 70;
        this.protecao = 0.6;
        this.furia = 1;
        this.dodgeChance = 0.4;
    }

    //Métodos
    @Override
    public void exibirStatus(){
        System.out.printf("\n%s, O lutador possui:", nome); Utilidades.esperar();
        System.out.printf("\n%d PONTOS DE VIDA;", pontosDeVida); Utilidades.esperar();
        System.out.printf("\nNIVEL %d e %d/%d de EXPERIENCIA;", getNivel(), getExperiencia(), getExpProximoNivel()); Utilidades.esperar();
        System.out.printf("\n%.0f pontos de FORCA e %.0f pontos de PROTECAO;", forca, protecao * 100); Utilidades.esperar();
        System.out.printf("\n%d pontos de FURIA;", furia); Utilidades.esperar();
        System.out.printf("\n%.0f/100 pontos de SORTE;", sorte * 100); Utilidades.esperar();
        System.out.printf("\n%s esta usando %s como ARMA;", nome, arma.getNome()); Utilidades.esperar();
        System.out.printf("\nCapacidade de dar %d ATAQUES POR TURNO e pode percorrer %d METROS POR TURNO.\n", arma.getAttackSpeed(), moveSpeed); Utilidades.esperar();
    }

    /*
     * Caso o lutador esteja a uma distância maior que 1 metro do inimigo, ele irá avançar
     * Se ele já estiver a uma distância <= attackRange da arma, ele decidirá entre usar o ataque comum ou sua habilidade especial
     */
    @Override
    public AcaoDeCombate escolherAcao(Combatente alvo){
        double distancia = Utilidades.calcularDistancia(pos, alvo.getPos());

        System.out.printf("\n%s, o lutador, esta a %.0f metros do monstro e ira ", nome, distancia);

        if (distancia > arma.getAttackRange()){
            System.out.println("CORRER NA DIRECAO DELE!\n"); Utilidades.esperar();
            return acoes.get(0);
        }
        else{
            if (Math.random() > 0.3){
                System.out.println("ATACAR O SEU INIMIGO!\n"); Utilidades.esperar();
                return acoes.get(1);
            }
            else{
                System.out.println("USAR SUA HABILIDADE ESPECIAL!\n"); Utilidades.esperar();
                return acoes.get(2);
            }
        }
    }

    /*
     * Método que aumenta a furia do lutador a cada nivel de XP par alcançado
     */
    @Override
    protected void melhorarAtributoUnico(){
        this.furia += 1;
    }

    @Override
    public double getAtributoUnico(){
        return furia;
    }
}