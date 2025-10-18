package com.rpg.personagens;

import com.rpg.combate.*;
import com.rpg.itens.*;
import com.rpg.util.*;
import javax.xml.bind.annotation.*;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Atirador.class, Lutador.class, CavaleiroCorrompido.class, CorvoRei.class, Esqueleto.class, Goblin.class, GoblinGigante.class, Ninfa.class, Troll.class, Zumbi.class})
public abstract class Personagem implements Combatente{

    //Atributos
    protected String nome;
    protected int pontosDeVida;
    protected int pontosDeVidaMax;
    protected double protecao;
    protected double protecaoMax;
    protected double forca;
    protected int moveSpeed;
    protected int pos;
    protected double criticalChance;
    protected double sorte;
    protected double dodgeChance;
    @XmlElements({
        @XmlElement(name="adagas", type=Adagas.class),
        @XmlElement(name="arco", type=Arco.class),
        @XmlElement(name="clavaComum", type=ClavaComum.class),
        @XmlElement(name="clavaEspinhos", type=ClavaEspinhos.class),
        @XmlElement(name="crossbow", type=Crossbow.class),
        @XmlElement(name="espada", type=Espada.class),
        @XmlElement(name="facaArremesso", type=FacaArremesso.class),
        @XmlElement(name="funda", type=Funda.class),
        @XmlElement(name="garrasCorvo", type=GarrasCorvo.class),
        @XmlElement(name="lança", type=Lança.class),
        @XmlElement(name="machado", type=Machado.class),
        @XmlElement(name="penasCorvo", type=PenasCorvo.class)
    })
    protected Arma arma;

    public Personagem(){}

    // Construtor
    public Personagem(String nome){
        this.nome = nome;
        this.protecaoMax = 0.9;
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
    @XmlTransient
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
