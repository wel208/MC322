package com.rpg.cenario;

import com.rpg.combate.Combatente;
import com.rpg.personagens.*;
import java.util.List;
import javax.xml.bind.annotation.*;
@XmlSeeAlso({FasesDeCombate.class})
public interface Fase {
    void iniciar(Combatente heroi);
    boolean isConcluida();
    String getTipoDeCenario();
    List<Monstro> getMonstros();
    EfeitoDeCenario getEfeito();
}