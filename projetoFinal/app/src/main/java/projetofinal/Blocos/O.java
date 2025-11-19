package projetofinal.Blocos;
public class O extends Bloco {

    public static final int[][] matriz1 = new int[][]{
            {0, 4, 4, 0},
            {0, 4, 4, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };

    public static final int[][] matriz2 = new int[][]{
            {0, 4, 4, 0},
            {0, 4, 4, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };

    public static final int[][] matriz3 = new int[][]{
            {0, 4, 4, 0},
            {0, 4, 4, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };

    public static final int[][] matriz4 = new int[][]{
            {0, 4, 4, 0},
            {0, 4, 4, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };

    public O() {
        matriz = new int[][]{
            {0, 4, 4, 0},
            {0, 4, 4, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        color = 4;
        resetarPosicao();
    }
}
