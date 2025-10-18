package com.rpg.itens;
import javax.xml.bind.annotation.*;
@XmlSeeAlso({Adagas.class, Arco.class, ClavaComum.class, ClavaEspinhos.class, Crossbow.class, Espada.class, FacaArremesso.class, Funda.class, GarrasCorvo.class, Lança.class, Machado.class, PenasCorvo.class})
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Arma implements Item {

    // Atributos
    protected String nome;
    protected TipoDeArma tipo;
    protected double dano;
    protected int minNivel;
    protected int attackRange;
    protected int attackSpeed;

    public Arma(){}

    // Getters
    public int getAttackRange() {
        return attackRange;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public double getDano() {
        return dano;
    }

    public String getNome() {
        return nome;
    }

    public String getStringTipo() {
        return tipo.getTipoString();
    }

    public TipoDeArma getTipo() {
        return tipo;
    }

    public int getMinNivel() {
        return minNivel;
    }

    public void aumentaAttackSpeed(){
        attackSpeed += 1;
    }
    
    public void resetaAttackSpeed(){
        attackSpeed -= 1;
    }
}