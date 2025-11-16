package projetofinal.Blocos;

public abstract class Bloco implements BlocoInterface {
    protected int[][] matriz;
    protected int posX;
    protected int posY;
    protected int color;

    @Override
    public void moverDireita() {
        posX++;
    }

    @Override
    public void moverEsquerda() {
        posX--;
    }

    @Override
    public void moverBaixo() {
        posY++;
    }

    @Override
    public void rotacionar() {
        int matrizRotacionada[][] = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrizRotacionada[i][j] = matriz[4 - j - 1][4 - i];
            }
        }
    }
    @Override
    public int[] getPosicao() {
        int pos [] = new int[2];
        pos[0] = posX;
        pos[1] = posY;
        return pos;
    }
    @Override
    public int getForma() {
        return color;
    }
    public void resetarPosicao() {
        posX = 5;
        posY = 0;
    }
}
