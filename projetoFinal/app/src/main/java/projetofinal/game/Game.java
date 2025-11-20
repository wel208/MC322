package projetofinal.game;
import projetofinal.Blocos.*;
import projetofinal.Jogadores.*;
import projetofinal.Tabuleiro.*;
import javafx.scene.input.KeyCode;

public class Game implements GameInterface {

    private Jogador player1;
    private Jogador player2;

    private boolean pausado;
    public boolean terminado;

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

        player1.resetPontuacao();
        player2.resetPontuacao();

        player1.getTabuleiro().limpar();
        player2.getTabuleiro().limpar();

        player1.setBlocoAtual(player1.getBag().proximoBloco());
        player2.setBlocoAtual(player2.getBag().proximoBloco());
    }

    @Override
    public void atualizar(KeyCode tecla) {
        if (pausado || terminado)
            return;

        Jogador playerAtual;
        Jogador playerOutro;
        
        if (tecla == KeyCode.A || tecla == KeyCode.D || tecla == KeyCode.S || tecla == KeyCode.W || tecla == KeyCode.Q) {
            playerAtual = player1;
            playerOutro = player2;
        } 
        else {
            playerAtual = player2;
            playerOutro = player1;
        }

        atualizarPlayer(playerAtual, playerOutro, tecla);
    }

    private void atualizarPlayer(Jogador atual, Jogador outro, KeyCode tecla) {

        Bloco bloco = atual.getBlocoAtual();
        Tabuleiro tab = atual.getTabuleiro();
        int linhasFeitas = 0;

        switch(tecla){
        case KeyCode.A:
        case KeyCode.LEFT:
            bloco.moverEsquerda();
            if (tab.posicaoValida(bloco) != Validacao.OK)
                bloco.moverDireita();
        break;
        case KeyCode.D:
        case KeyCode.RIGHT:
            bloco.moverDireita();
            if (tab.posicaoValida(bloco) != Validacao.OK)
                bloco.moverEsquerda();
        break;
        case KeyCode.S:
        case KeyCode.DOWN:
            bloco.moverBaixo();
            if (tab.posicaoValida(bloco) == Validacao.COLISAO) {
                bloco.moverCima();
                tab.fixarBloco(bloco);
                atual.blocoAtual = atual.getBag().proximoBloco();
                linhasFeitas = tab.verificarLinhas();
                atual.adicionarPontos(linhasFeitas);
                if (linhasFeitas >= 2)
                    outro.getTabuleiro().adicionarLinhasIncompletas(linhasFeitas);
            }
        break;
        case KeyCode.W:
        case KeyCode.UP:
            bloco.rotacionarSH();
            if (tab.posicaoValida(bloco) != Validacao.OK) {
                bloco.moverCima();
                if (tab.posicaoValida(bloco) != Validacao.OK) {
                    bloco.moverBaixo();
                    bloco.moverEsquerda();
                }
                    if (tab.posicaoValida(bloco) != Validacao.OK) {
                        bloco.moverDireita();
                        bloco.moverDireita();
                    }
                        if (tab.posicaoValida(bloco) != Validacao.OK) {
                            bloco.moverEsquerda();
                            bloco.rotacionarSA();
                        }
            }
        break;
        case KeyCode.Q:
        case KeyCode.SHIFT:
            atual.hold();
        break;
        default:
            return;
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

    public boolean pausado() { return pausado; }
    public boolean terminado() { return terminado; }
}