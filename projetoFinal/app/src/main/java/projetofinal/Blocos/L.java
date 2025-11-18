package projetofinal.Blocos;
public class L extends Bloco {
    public L() {
        matriz = new int[][] {
            {0, 0, 3, 0},
            {3, 3, 3, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        color = 3;
        resetarPosicao();
    }
    
}
