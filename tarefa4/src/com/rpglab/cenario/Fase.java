package com.rpglab.cenario;

import com.rpglab.personagens.*;
public interface Fase {
    void iniciar(Heroi heroi);
    boolean isConcluida();
    String getTipoDeCenario();
}