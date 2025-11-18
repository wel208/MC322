package projetofinal.Blocos;
public class O extends Bloco {
    public O() {
        matriz = new int[][] {
            {0, 4, 4, 0},
            {0, 4, 4, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        color = 4;
        resetarPosicao();
    }
}
