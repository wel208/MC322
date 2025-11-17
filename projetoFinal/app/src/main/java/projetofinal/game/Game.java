package projetofinal.game;

import projetofinal.Blocos.*;
import projetofinal.Jogadores.Jogador;
import projetofinal.Tabuleiro.*;

public class Game implements GameInterface {

    private Jogador player1;
    private Jogador player2;

    private boolean pausado;
    private boolean terminado;

    public Game() {
        this.player1 = new Jogador(new Tabuleiro());
        this.player2 = new Jogador(new Tabuleiro());

        reiniciar();
    }

    @Override
    public void pausar() {
        pausado = !pausado;
    }

    @Override
    public void reiniciar() {
        pausado = false;
        terminado = false;

        player1.getTabuleiro().limpar();
        player2.getTabuleiro().limpar();

        player1.getBag().recarregarBag();
        player2.getBag().recarregarBag();
    }

    @Override
    public void atualizar() {
        if (pausado || terminado)
            return;

        atualizarPlayer(player1, player2);
        atualizarPlayer(player2, player1);
    }

    private void atualizarPlayer(Jogador atual, Jogador outro) {

        Bloco bloco = atual.getBlocoAtual();
        Tabuleiro tab = atual.getTabuleiro();
        int linhasFeitas = 0;
        
        bloco.moverEsquerda();
        if (tab.posicaoValida(bloco) != Validacao.OK)
            bloco.moverDireita();
        
        bloco.moverDireita();
        if (tab.posicaoValida(bloco) != Validacao.OK)
            bloco.moverEsquerda();

        bloco.moverBaixo();
        if (tab.posicaoValida(bloco) == Validacao.COLISAO) {
            atual.setBlocoAtual(atual.getBag().proximoBloco());
            linhasFeitas = tab.verificarLinhas();
            atual.adicionarPontos(linhasFeitas);
            if (linhasFeitas >= 2)
                outro.getTabuleiro().adicionarLinhasIncompletas(linhasFeitas);
        }
        if (tab.atingiuTopo()) {
                atual.perder();
                terminado = true;
        }
    }
    
    @Override
    public int[] getPontos() {
        int[] pontos = new int[2];
        pontos[0] = player1.getPontos();
        pontos[1] = player2.getPontos();
        return pontos;
    }

    public Jogador getPlayer1() { return player1; }
    public Jogador getPlayer2() { return player2; }
}