package com.test.eventos;

import com.test.combate.*;
public interface Evento {
    boolean verificarGatilho(Combatente heroi, Combatente monstro);
    void executar(Combatente alvo);
}
