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
    protected Estado estado;

    public Bloco (){
        estado = Estado.ESTADO1;
    }

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
