package com.rpglab.personagens;

import com.rpglab.combate.*;
import com.rpglab.itens.*;
import com.rpglab.util.*;
public abstract class Personagem implements Combatente{

    //Atributos
    protected String nome;
    protected int pontosDeVida;
    protected int pontosDeVidaMax;
    protected double protecao;
    protected double forca;
    protected int moveSpeed;
    protected int pos;
    protected double criticalChance;
    protected double sorte;
    protected double dodgeChance;
    protected Arma arma;

    // Construtor
    public Personagem(String nome){
        this.nome = nome;
    }

    // Métodos utilitários
    public void statusParcial(){
        System.out.printf("\n%s, %s, está com %d PONTOS DE VIDA.\n", nome, Utilidades.verificarClasse(this), pontosDeVida); Utilidades.esperar();
    }

    public double receberDano(double forca){
        pontosDeVida -= (int)(forca * (1.0 - protecao));
        if (pontosDeVida < 0) pontosDeVida = 0;
        return (int)(forca * (1.0 - protecao));
    }

    public void receberCura(int cura){
        pontosDeVida += cura;
        if (pontosDeVida > pontosDeVidaMax)
            pontosDeVida = pontosDeVidaMax;
    }

    public boolean estaVivo(){
        return pontosDeVida > 0;
    }

    // Getters e Setters
    public String getNome(){
        return nome;
    }
    public Arma getArma(){
        return arma;
    }
    public void setArma(Arma arma){
        this.arma = arma;
    }
    public double getForca(){
        return forca;
    }
    public int getPontosDeVida(){
        return pontosDeVida;
    }
    public int getPontosDeVidaMax(){
        return pontosDeVidaMax;
    }
    public int getMoveSpeed(){
        return moveSpeed;
    }
    public double getDodgeChance(){ 
        return dodgeChance;
    }
    public double getCriticalChance(){ 
        return criticalChance; 
    }
    public int getPos(){ 
        return pos; 
    }
    public void setPos(int pos){
        this.pos = pos;
    }
    public double getSorte(){ 
        return sorte; 
    }

    public double getAtributoUnico() {
        return 0;
    }

    public abstract void exibirStatus();
}
