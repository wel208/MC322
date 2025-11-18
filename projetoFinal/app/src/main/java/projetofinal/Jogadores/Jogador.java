package projetofinal.Jogadores;
import projetofinal.Blocos.*;
import projetofinal.Tabuleiro.*;
import projetofinal.game.*;

public class Jogador {

    private String nome;
    private Tabuleiro tabuleiro;
    public Bloco blocoAtual;
    private int pontos;
    private boolean perdeu;
    private GeradorDeBlocos bag;

    public Jogador(Tabuleiro tabuleiro) {
        this.nome = "AAA";
        this.tabuleiro = tabuleiro;
        blocoAtual = null;
        this.pontos = 0;
        this.perdeu = false;
        this.bag = new GeradorDeBlocos();
    }

    public String getNome() { return nome; }
    public int getPontos() { return pontos; }
    public Tabuleiro getTabuleiro() { return tabuleiro; }
    public GeradorDeBlocos getBag() { return bag; }
    public Bloco getBlocoAtual() { return blocoAtual; }
    public boolean perdeu() { return perdeu; }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarPontos(int linhas) {
        // regra simples (pode mudar depois)
        pontos += 100 + linhas * 200;
    }

    public void perder() {
        this.perdeu = true;
    }

    public void setBlocoAtual(Bloco bloco) {
        this.blocoAtual = bloco;
    }
}

