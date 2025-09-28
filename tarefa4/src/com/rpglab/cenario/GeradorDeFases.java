package com.rpglab.cenario;

import java.util.List;
public interface GeradorDeFases {
    List<Fase> gerar(int quantidadeDeFases, Dificuldade dificuldade);
}
