package projetofinal.Blocos;
public class Z extends Bloco {

    public static final int[][] matriz1 = new int[][]{
            {0, 0, 0, 0},
            {7, 7, 0, 0},
            {0, 7, 7, 0},
            {0, 0, 0, 0}
        };
        
    public static final int[][] matriz2 = new int[][]{
            {0, 0, 7, 0},
            {0, 7, 7, 0},
            {0, 7, 0, 0},
            {0, 0, 0, 0}
        };

    public static final int[][] matriz3 = new int[][]{
            {0, 0, 0, 0},
            {7, 7, 0, 0},
            {0, 7, 7, 0},
            {0, 0, 0, 0}
        };
        
    public static final int[][] matriz4 = new int[][]{
            {0, 0, 7, 0},
            {0, 7, 7, 0},
            {0, 7, 0, 0},
            {0, 0, 0, 0}
        };

    public Z() {
        matriz = new int[][]{
            {0, 0, 0, 0},
            {7, 7, 0, 0},
            {0, 7, 7, 0},
            {0, 0, 0, 0}
        };
        color = 7;
        resetarPosicao();
    }
}
