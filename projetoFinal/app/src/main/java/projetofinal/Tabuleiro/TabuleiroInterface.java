package projetofinal.Tabuleiro;
import projetofinal.Blocos.*;

public interface TabuleiroInterface {
    Validacao posicaoValida(Bloco bloco);
    void fixarBloco(Bloco bloco);
    void verificarLinhas();
    boolean atingiuTopo();
}
