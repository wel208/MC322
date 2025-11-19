package projetofinal.Blocos;
public class L extends Bloco {

    public static final int[][] matriz1 = new int[][]{
            {0, 0, 0, 0},
            {0, 0, 3, 0},
            {3, 3, 3, 0},
            {0, 0, 0, 0}
        };
        
    public static final int[][] matriz2 = new int[][]{
            {0, 3, 0, 0},
            {0, 3, 0, 0},
            {0, 3, 3, 0},
            {0, 0, 0, 0}
        };

    public static final int[][] matriz3 = new int[][]{
            {0, 0, 0, 0},
            {0, 3, 3, 3},
            {0, 3, 0, 0},
            {0, 0, 0, 0}
        };

    public static final int[][] matriz4 = new int[][]{
            {0, 3, 3, 0},
            {0, 0, 3, 0},
            {0, 0, 3, 0},
            {0, 0, 0, 0}
        };

    public L() {
        matriz = new int[][]{
            {0, 0, 0, 0},
            {0, 0, 3, 0},
            {3, 3, 3, 0},
            {0, 0, 0, 0}
        };
        color = 3;
        resetarPosicao();
    }
    
}
