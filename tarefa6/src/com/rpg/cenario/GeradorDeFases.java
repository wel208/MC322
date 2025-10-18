package com.rpg.cenario;

import java.util.List;
import javax.xml.bind.annotation.*;
@XmlSeeAlso({ConstrutorDeCenario.class})
public interface GeradorDeFases {
    List<Fase> gerar(int quantidadeDeFases, Dificuldade dificuldade);
}
