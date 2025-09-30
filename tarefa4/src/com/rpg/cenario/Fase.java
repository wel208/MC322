package com.rpg.cenario;

import com.rpg.personagens.*;
import java.util.List;
public interface Fase {
    void iniciar(Heroi heroi);
    boolean isConcluida();
    String getTipoDeCenario();
    List<Monstro> getMonstros();
    EfeitoDoCenario getEfeito();
}