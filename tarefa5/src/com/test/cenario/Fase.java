package com.test.cenario;

import com.test.personagens.*;
import java.util.List;
public interface Fase {
    void iniciar(Heroi heroi);
    boolean isConcluida();
    String getTipoDeCenario();
    List<Monstro> getMonstros();
    EfeitoDeCenario getEfeito();
}