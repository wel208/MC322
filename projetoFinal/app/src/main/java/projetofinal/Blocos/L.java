package projetofinal.Blocos;
public class L extends Bloco {
    public L() {
        matriz = new int[][] {
            {0, 0, 1, 0},
            {1, 1, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        color = 3;
        resetarPosicao();
    }
    
}
