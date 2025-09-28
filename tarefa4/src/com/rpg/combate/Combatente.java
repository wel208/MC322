package com.rpg.combate;

import com.rpg.itens.*;
public interface Combatente {
    String getNome();
    Arma getArma();
    void setArma(Arma arma);
    int getPos();
    double getForca();
    int getMoveSpeed();
    double getSorte();
    double getAtributoUnico();
    double getCriticalChance();
    double getDodgeChance();
    void setPos(int pos);
    int getPontosDeVida();
    int getPontosDeVidaMax();
    boolean estaVivo();
    double receberDano(double dano);
    void receberCura(int cura);
    AcaoDeCombate escolherAcao(Combatente alvo);
    void statusParcial();
}