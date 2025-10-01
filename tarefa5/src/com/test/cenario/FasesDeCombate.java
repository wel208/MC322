package com.test.cenario;

import java.util.List;

import com.test.personagens.*;

public class FasesDeCombate implements Fase {

    //Atributos
    protected List<Monstro> Monstros;
    protected TipoCenario cenario;
    protected EfeitoDeCenario efeito;

    public FasesDeCombate(List<Monstro> Monstros, TipoCenario cenario, EfeitoDeCenario efeito){
        this.Monstros = Monstros;
        this.cenario = cenario;
        this.efeito = efeito;
    }

    //Método que executa cada uma das fases do jogo 
    public void iniciar(Heroi heroi){
        cenario.descreverCenario();
        efeito.descreverEfeito();
    }

    public String getTipoDeCenario(){
        return cenario.getDescricao();
    }

    public List<Monstro> getMonstros(){
        return Monstros;
    }

    public EfeitoDeCenario getEfeito(){
        return efeito;
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
