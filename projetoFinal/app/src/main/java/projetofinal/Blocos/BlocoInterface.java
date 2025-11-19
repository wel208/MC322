package projetofinal.Blocos;

public interface BlocoInterface {
    void moverDireita();
    void moverEsquerda();
    void moverBaixo();
    void rotacionar();
    int[] getPosicao();
    int getColor();
}
