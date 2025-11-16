package projetofinal.Blocos;

public class I extends Bloco {
    public I() {
        matriz = new int[][] {
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        color = 1;
        resetarPosicao();
    }
    
}
