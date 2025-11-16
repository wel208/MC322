package projetofinal.Tabuleiro;
import projetofinal.Blocos.*;

public interface TabuleiroInterface {
    boolean posicaoValida(Bloco bloco);
    void fixarBloco(Bloco bloco);
    void verificarLinhas();
    boolean atingiuTopo();
}
