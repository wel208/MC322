package projetofinal.Blocos;
public class J extends Bloco {
    public J() {
        matriz = new int[][] {
            {1, 0, 0, 0},
            {1, 1, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        color = 4;
        resetarPosicao();
    }
}
