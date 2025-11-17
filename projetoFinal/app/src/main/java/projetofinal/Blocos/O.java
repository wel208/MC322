package projetofinal.Blocos;
public class O extends Bloco {
    public O() {
        matriz = new int[][] {
            {0, 1, 1, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        color = 4;
        resetarPosicao();
    }
}
