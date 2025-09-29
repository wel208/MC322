package com.rpg.cenario;

import java.util.List;

import com.rpg.personagens.*;

public class FasesDeCombate implements Fase {

    //Atributos
    protected List<Monstro> Monstros;
    protected TipoCenario cenario;

    public FasesDeCombate(List<Monstro> Monstros, TipoCenario cenario){
        this.Monstros = Monstros;
        this.cenario = cenario;
    }

    //Método que executa cada uma das fases do jogo 
    public void iniciar(Heroi heroi){
        cenario.descreverCenario();
    }

    public String getTipoDeCenario(){
        return cenario.getDescricao();
    }

    public List<Monstro> getMonstros(){
        return Monstros;
    }

    public boolean isConcluida(){
        boolean monstroVivo = false;
        for (Monstro monstro : Monstros){
            if (monstro.getPontosDeVida() > 0){
                monstroVivo = true;
                break;
            }
        }
        return !monstroVivo;
    }
}
