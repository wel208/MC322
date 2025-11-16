package projetofinal.Blocos;

public class Z extends Bloco {
    public Z() {
        matriz = new int[][] {
            {1, 1, 0, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        color = 7;
        resetarPosicao();
    }
}
