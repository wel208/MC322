package projetofinal.Blocos;

public interface BlocoInterface {
    void moverDireita();
    void moverEsquerda();
    void moverBaixo();
    void rotacionarSH();
    void rotacionarSA();
    int[] getPosicao();
    int getColor();
}
