package com.rpg.cenario;

import java.util.List;

public enum TipoCenario {
    //Constantes
    CASTELO ("VAMOS! O castelo esta sendo invadido!", EfeitoDeCenario.CORAGEM, List.of("Goblin", "Cavaleiro Corrompido", "Troll", "Zumbi", "Esqueleto")),
    VILAREJO ("Caramba, um vilarejo abandonado... Monstros tomaram conta deste lugar.", EfeitoDeCenario.RAIVA, List.of("Goblin", "Zumbi", "Troll", "Goblin Gigante", "Esqueleto")),
    ACAMPAMENTO ("Estamos num acampamento no meio da floresta. Tenha cuidado, ha monstros la fora.", EfeitoDeCenario.MEDO, List.of("Ninfa da Floresta", "Cavaleiro Corrompido", "Goblin Gigante", "Corvo Rei", "Troll", "Esqueleto"));
    
    //Atributos
    private final String descricao;
    private final EfeitoDeCenario efeito;
    private final List<String> monstros; //Monstros que podem aparecer no cenario definido

    //Construtor
    TipoCenario(String descricao, EfeitoDeCenario efeito, List<String> monstros){
        this.descricao = descricao;
        this.efeito = efeito;
        this.monstros = monstros;
    }

    //Métodos
    public void descreverCenario(){
        System.out.println(descricao);
    }

    public String getDescricao(){
        return descricao;
    }

    public EfeitoDeCenario getEfeito(){
        return efeito;
    }

    public List<String> getMonstros(){
        return monstros;
    }
}