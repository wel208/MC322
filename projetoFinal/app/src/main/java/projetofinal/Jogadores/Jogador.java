package projetofinal.Jogadores;
import projetofinal.Blocos.*;
import projetofinal.Tabuleiro.*;
import projetofinal.game.*;

public class Jogador {

    private String nome;
    private Tabuleiro tabuleiro;
    public Bloco blocoAtual;
    private int pontos;
    private int nLinhas;
    private int nivel;
    private boolean perdeu;
    private GeradorDeBlocos bag;
    private Bloco guardado;
    private boolean guardou;

    public Jogador(Tabuleiro tabuleiro) {
        this.nome = "AAA";
        this.tabuleiro = tabuleiro;
        blocoAtual = null;
        guardado = null;
        guardou = false;
        this.pontos = 0;
        this.nLinhas = 0;
        this.nivel = 0;
        this.perdeu = false;
        this.bag = new GeradorDeBlocos();
    }

    public String getNome() { return nome; }
    public int getPontos() { return pontos; }
    public int getNLinhas() { return nLinhas; }
    public int getNivel() { return nivel; }
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
        nLinhas += linhas;
        nivel = 1 + bag.getRotacoes() / 2;
        guardou = false;
    }

    public void perder() {
        this.perdeu = true;
    }

    public void setBlocoAtual(Bloco bloco) {
        this.blocoAtual = bloco;
    }

    public void hold(){
        if (guardou == true)
            return;
        
        if(guardado == null){
            guardado = blocoAtual;
            guardado.resetarPosicao();
            blocoAtual = getBag().proximoBloco();
        }else
            swap();
        guardado.resetarPosicao();
        guardou = true;
    }

    public void swap(){
        Bloco auxiliar = blocoAtual;
        blocoAtual = guardado;
        guardado = auxiliar;
    }
}

