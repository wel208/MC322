package projetofinal.Blocos;
import java.util.ArrayList;
import java.util.List;

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

    public void moverCima() {
        posY--;
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

    public List<int[]> getAbsoluteCoord(){
        List<int[]> coords = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(matriz[i][j] != 0){
                    int x = posX + j;
                    int y = posY + i;
                    coords.add(new int[]{x, y});
                }
            }
        }
        return coords;
    }
    @Override
    public int getColor() {
        return color;
    }
    public void resetarPosicao() {
        posX = 5;
        posY = 0;
    }

    
}
