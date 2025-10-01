package com.test.personagens;

import java.util.Random;

import com.test.cenario.Dificuldade;
import com.test.combate.*;
import com.test.itens.*;
import com.test.util.*;

import java.util.List;

public class CorvoRei extends Monstro {

    private double precisao;
    private List<Arma> armasPossiveis = List.of(new GarrasCorvo(), new PenasCorvo());
    
    public CorvoRei(String nome, int nivel, Arma arma, Dificuldade dificuldade){
        super(nome, nivel, arma, dificuldade);

        this.pontosDeVidaMax = (int)(dificuldade.getMultiplicador() * (30 + (nivel - 1) * 15));
        this.pontosDeVida = this.pontosDeVidaMax;
        this.protecao = Math.min(0.5, 0.3 + (nivel - 1) * 0.05);
        this.forca = dificuldade.getMultiplicador() * (10 + nivel);
        this.moveSpeed = 12;
        this.xpConcedido = 20 + (nivel * 10);
        this.sorte = 0.15 + (nivel * 0.03);
        this.acoes = List.of(new Mover(), new AtaqueComum());
    }

    @Override
    public AcaoDeCombate escolherAcao(Combatente alvo){
        double distancia = Utilidades.calcularDistancia(pos, alvo.getPos());
        double chance = Math.random();

        System.out.printf("\nO CORVO REI esta a %.0f metro(s) do %s e ira ", distancia, Utilidades.verificarClasse(alvo));

        if (distancia <= 1){
            System.out.println("SE MOVER!\n"); Utilidades.esperar();
            return acoes.get(0);
        }
        else{
            if (chance < 0.4){
                System.out.println("SE MOVER!\n"); Utilidades.esperar();
                return acoes.get(0);
            }
            else{
                System.out.println("ATACAR!\n"); Utilidades.esperar();

                Random r = new Random();
                setArma(armasPossiveis.get(r.nextInt(2))); //Chance de utilizar suas penas ou suas garras

                System.out.printf("\n%s, %s, ira utilizar %s!\n\n", nome, Utilidades.verificarClasse(this), arma.getNome());

                return acoes.get(1);
            }
        }
    }

    public double getAtributoUnico(){
        return precisao;
    }

    @Override
    public Arma droparLoot(){
        Random r = new Random();
        return armasPossiveis.get(r.nextInt(2));
    }
}
