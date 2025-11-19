package projetofinal.Blocos;
public class J extends Bloco {

    public static final int[][] matriz1 = new int[][]{
            {0, 0, 0, 0},
            {2, 0, 0, 0},
            {2, 2, 2, 0},
            {0, 0, 0, 0}
        };

    public static final int[][] matriz2 = new int[][]{
            {0, 2, 2, 0},
            {0, 2, 0, 0},
            {0, 2, 0, 0},
            {0, 0, 0, 0}
        };

    public static final int[][] matriz3 = new int[][]{
            {0, 0, 0, 0},
            {2, 2, 2, 0},
            {0, 0, 2, 0},
            {0, 0, 0, 0}
        };

    public static final int[][] matriz4 = new int[][]{
            {0, 0, 2, 0},
            {0, 0, 2, 0},
            {0, 2, 2, 0},
            {0, 0, 0, 0}
        };

    public J() {
        matriz = new int[][]{
            {0, 0, 0, 0},
            {2, 0, 0, 0},
            {2, 2, 2, 0},
            {0, 0, 0, 0}
        };
        color = 2;
        resetarPosicao();
    }
}
