package com.rpglab.eventos;

import com.rpglab.combate.*;
public interface Evento {
    boolean verificarGatilho(Combatente heroi, Combatente monstro);
    void executar(Combatente alvo);
}
