package com.test.itens;
public abstract class Arma implements Item {
    
    // Atributos
    protected String nome;
    protected TipoDeArma tipo;
    protected double dano;
    protected int minNivel;
    protected int attackRange;
    protected int attackSpeed;

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