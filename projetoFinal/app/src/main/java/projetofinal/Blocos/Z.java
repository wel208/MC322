package projetofinal.Blocos;
public class Z extends Bloco {
    public Z() {
        matriz = new int[][] {
            {7, 7, 0, 0},
            {0, 7, 7, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        color = 7;
        resetarPosicao();
    }
}
