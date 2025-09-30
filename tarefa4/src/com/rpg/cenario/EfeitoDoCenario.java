package com.rpg.cenario;

import com.rpg.personagens.*;

public enum EfeitoDoCenario {
    CORAGEM ("O heroi fica mais corajoso no Castelo e ataca uma vez a mais por turno."){
        @Override
        public void aplicarEfeito(Heroi heroi){
            heroi.getArma().aumentaAttackSpeed();
        }
        public void removerEfeito(Heroi heroi){
            heroi.getArma().resetaAttackSpeed();
        }
    },
    RAIVA ("O heroi fica com muita raiva ao ver o Vilarejo tomado por monstros, diminuindo o dano recebido e aumentando sua velocidade de ataque."){
        @Override
        public void aplicarEfeito(Heroi heroi){
            heroi.aumentaProtecao(1.1);
            heroi.getArma().aumentaAttackSpeed();
        }
        @Override
        public void removerEfeito(Heroi heroi){
            heroi.resetaProtecao(1.1);
            heroi.getArma().resetaAttackSpeed();
        }
    },
    MEDO ("O heroi fica com medo na Floresta, o heroi fica mais travado, diminuindo a chance de conseguir esquivar de ataques."){
        @Override
        public void aplicarEfeito(Heroi heroi){
            heroi.diminuiEsquiva(1.1);
        }
        @Override
        public void removerEfeito(Heroi heroi){
            heroi.resetaEsquiva(1.1);
        }
    };

    private String descricao;

    private EfeitoDoCenario(String descricao){
        this.descricao = descricao;
    }

    public void descreverEfeito(){
        System.out.println(descricao);
    }

    public abstract void aplicarEfeito(Heroi heroi);

    public abstract void removerEfeito(Heroi heroi);
}
