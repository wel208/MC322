package com.rpg.cenario;

import com.rpg.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConstrutorDeCenario implements GeradorDeFases {
    
    //Métodos
    public List<Fase> gerar(int n, Dificuldade dificuldade){
        List<Fase> fases = new ArrayList<>();
        Random r = new Random();
        
        for (int i = 0; i < n; i++){
            TipoCenario cenario;
            if ((i + 1) % 3 == 0)
                cenario = TipoCenario.ACAMPAMENTO;
            else
                cenario = TipoCenario.values()[r.nextInt(2)];
                
            fases.add(new Fases(Utilidades.criarListaDeMonstro(cenario, i, dificuldade), cenario));
        }

        return fases;
    }
}
