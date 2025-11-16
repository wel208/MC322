package projetofinal.Blocos;

public class T extends Bloco {
    public T() {
        matriz = new int[][] {
            {0, 1, 0, 0},
            {1, 1, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        color = 6;
        resetarPosicao();
    }
    
}
