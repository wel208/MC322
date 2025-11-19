package projetofinal.Blocos;
import java.util.ArrayList;
import java.util.List;

public abstract class Bloco implements BlocoInterface {
    public int[][] matriz;
    public int[][] matriz1;
    public int[][] matriz2;
    public int[][] matriz3;
    public int[][] matriz4;
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

        int[][] novaMatriz = new int[4][4];

        if (matriz.equals(matriz1))
            for (int i = 0; i < 4; i++){
                for (int j = 0; j < 4; j++){
                    novaMatriz[i][j] = matriz2[i][j];
                }
            }

        else if (matriz.equals(matriz2))
            for (int i = 0; i < 4; i++){
                for (int j = 0; j < 4; j++){
                    novaMatriz[i][j] = matriz3[i][j];
                }
            }

        else if (matriz.equals(matriz3))
            for (int i = 0; i < 4; i++){
                for (int j = 0; j < 4; j++){
                    novaMatriz[i][j] = matriz4[i][j];
                }
            }

        else
            for (int i = 0; i < 4; i++){
                for (int j = 0; j < 4; j++){
                    novaMatriz[i][j] = matriz1[i][j];
                }
            }
        
        matriz = novaMatriz;

        // int matrizRotacionada[][] = new int[4][4];
        // for (int i = 0; i < 4; i++) {
        //     for (int j = 0; j < 4; j++) {
        //         matrizRotacionada[j][3 - i] = matriz[i][j];
        //     }
        // }
        // matriz = matrizRotacionada;
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
