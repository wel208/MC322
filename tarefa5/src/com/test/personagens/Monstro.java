package com.test.personagens;

import java.util.List;

import com.test.cenario.Dificuldade;
import com.test.combate.*;
import com.test.itens.*;
import com.test.util.*;
public abstract class Monstro extends Personagem implements Lootavel {

    // Atributos
    protected int xpConcedido;         // Quantidade de experiência que o monstro concede ao herói que derrotá-lo
    protected int nivel;               // Nível do monstro
    protected Dificuldade dificuldade; //Dificuldade escolhida para o jogo
    protected List<Arma> listaDeArmasParaLargar;
    protected List<AcaoDeCombate> acoes = List.of(new Mover(), new AtaqueComum());

    // Construtor
    public Monstro(String nome, int nivel, Arma arma, Dificuldade dificuldade) {
        super(nome);
        this.dificuldade = dificuldade;
        this.nivel = nivel;
        this.arma = arma;

        // Valores padrão para todos os monstros
        this.dodgeChance = 0.05 + (nivel * 0.01); // Exemplo: 5% base + 1% por nível
    }

    @Override
    public void exibirStatus(){
        System.out.printf("\n%s, %s, no nivel %d possui:\n", nome, Utilidades.verificarClasse(this), nivel); Utilidades.esperar();
        System.out.printf("%d PONTOS DE VIDA;\n", pontosDeVida); Utilidades.esperar();
        System.out.printf("%.0f PONTOS DE PROTECAO;\n", protecao * 100); Utilidades.esperar();
        System.out.printf("%.0f PONTOS DE FORCA;\n", forca); Utilidades.esperar();
        System.out.printf("Utiliza %s como ARMA;\n", arma.getNome()); Utilidades.esperar();
        System.out.printf("E ao morrer concedera %d pontos de EXPERIENCIA ao heroi.\n", xpConcedido); Utilidades.esperar();
    }

    public Arma droparLoot(){
        return arma;
    }

    public int getXpConcedido(){
        return xpConcedido;
    }

    public Dificuldade getDificuldade(){
        return dificuldade;
    }  
}
