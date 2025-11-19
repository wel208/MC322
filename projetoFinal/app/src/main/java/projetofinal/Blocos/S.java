package projetofinal.Blocos;
public class S extends Bloco {

    public static final int[][] matriz1 = new int[][]{
            {0, 0, 0, 0},
            {0, 5, 5, 0},
            {5, 5, 0, 0},
            {0, 0, 0, 0}
        };
        
    public static final int[][] matriz2 = new int[][]{
            {0, 5, 0, 0},
            {0, 5, 5, 0},
            {0, 0, 5, 0},
            {0, 0, 0, 0}
        };

    public static final int[][] matriz3 = new int[][]{
            {0, 0, 0, 0},
            {0, 5, 5, 0},
            {5, 5, 0, 0},
            {0, 0, 0, 0}
        };

    public static final int[][] matriz4 = new int[][]{
            {0, 5, 0, 0},
            {0, 5, 5, 0},
            {0, 0, 5, 0},
            {0, 0, 0, 0}
        };

    public S() {
        matriz = new int[][]{
            {0, 0, 0, 0},
            {0, 5, 5, 0},
            {5, 5, 0, 0},
            {0, 0, 0, 0}
        };
        color = 5; 
        resetarPosicao();
    }
}
