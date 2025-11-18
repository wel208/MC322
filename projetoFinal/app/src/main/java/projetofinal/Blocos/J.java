package projetofinal.Blocos;
public class J extends Bloco {
    public J() {
        matriz = new int[][] {
            {2, 0, 0, 0},
            {2, 2, 2, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        color = 2;
        resetarPosicao();
    }
}
