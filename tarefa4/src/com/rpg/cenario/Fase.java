package com.rpg.cenario;

import com.rpg.personagens.*;
public interface Fase {
    void iniciar(Heroi heroi);
    boolean isConcluida();
    String getTipoDeCenario();
}