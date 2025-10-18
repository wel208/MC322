package com.rpg.cenario;

import java.util.List;

import com.rpg.combate.Combatente;
import com.rpg.personagens.*;
import javax.xml.bind.annotation.*;
@XmlRootElement(name = "fasesDeCombate")
@XmlAccessorType(XmlAccessType.FIELD)
public class FasesDeCombate implements Fase {

    //Atributos
    @XmlElementRef
    private List<Monstro> Monstros;
    private TipoCenario cenario;
    private EfeitoDeCenario efeito;

    public FasesDeCombate(){}

    //Construtor
    public FasesDeCombate(List<Monstro> Monstros, TipoCenario cenario, EfeitoDeCenario efeito){
        this.Monstros = Monstros;
        this.cenario = cenario;
        this.efeito = efeito;
    }

    //Método que executa cada uma das fases do jogo 
    public void iniciar(Combatente heroi){
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
