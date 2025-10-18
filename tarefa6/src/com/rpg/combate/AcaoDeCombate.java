package com.rpg.combate;
import javax.xml.bind.annotation.*;
@XmlSeeAlso({Mover.class, AtaqueComum.class, HabilidadeEspecial.class})
public interface AcaoDeCombate {
    void executar(Combatente usuario, Combatente alvo);
}
