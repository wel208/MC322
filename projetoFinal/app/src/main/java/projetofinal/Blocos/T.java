package projetofinal.Blocos;
public class T extends Bloco {
    public T() {
        matriz = new int[][] {
            {0, 6, 0, 0},
            {6, 6, 6, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        color = 6;
        resetarPosicao();
    }
    
}
