package com.rpg.cenario;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.rpg.util.*;

public class ConstrutorDeCenario implements GeradorDeFases {
    
    //Métodos
    public List<Fase> gerar(int n, Dificuldade dificuldade){
        List<Fase> fases = new ArrayList<>();
        Random r = new Random();
        
        for (int i = 1; i <= n; i++){
            TipoCenario cenario;
            EfeitoDeCenario efeito;

            //Toda fase multipla de 3 sera no Acampamento da Floresta
            if (i % 3 == 0)
                cenario = TipoCenario.ACAMPAMENTO;
            else
                cenario = TipoCenario.values()[r.nextInt(2)];

            if (cenario.equals(TipoCenario.CASTELO))
                efeito = EfeitoDeCenario.CORAGEM;
            else if (cenario.equals(TipoCenario.VILAREJO))
                efeito = EfeitoDeCenario.RAIVA;
            else
                efeito = EfeitoDeCenario.MEDO;
                
            fases.add(new FasesDeCombate(Utilidades.criarListaDeMonstro(cenario, i, dificuldade), cenario, efeito));
        }

        return fases;
    }
}
