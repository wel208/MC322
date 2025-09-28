package com.rpg.cenario;

import java.util.List;

public enum TipoCenario {
    //Constantes
    CASTELO ("VAMOS! O castelo esta sendo invadido!", List.of("Goblin", "Cavaleiro Corrompido", "Troll", "Zumbi")),
    VILAREJO ("Caramba, um vilarejo abandonado... Monstros tomaram conta deste lugar.", List.of("Goblin", "Zumbi", "Troll", "Goblin Gigante")),
    ACAMPAMENTO ("Estamos num acampamento no meio da floresta. Tenha cuidado, ha monstros la fora.", List.of("Ninfa da Floresta", "Cavaleiro Corrompido", "Goblin Gigante", "Corvo Rei", "Troll"));

    //Atributos
    private final String descricao;
    private final List<String> monstros;

    //Construtor
    TipoCenario(String descricao, List<String> monstros){
        this.descricao = descricao;
        this.monstros = monstros;
    }

    //Métodos
    public void descreverCenario(){
        System.out.println(descricao);
    }

    public String getDescricao(){
        return descricao;
    }

    public List<String> getMonstros(){
        return monstros;
    }
}