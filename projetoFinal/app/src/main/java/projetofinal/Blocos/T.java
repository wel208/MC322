package projetofinal.Blocos;
public class T extends Bloco {

    public static final int[][] matriz1 = new int[][]{
            {0, 0, 0, 0},
            {0, 6, 0, 0},
            {6, 6, 6, 0},
            {0, 0, 0, 0}
        };
        
    public static final int[][] matriz2 = new int[][]{
            {0, 6, 0, 0},
            {0, 6, 6, 0},
            {0, 6, 0, 0},
            {0, 0, 0, 0}
        };

    public static final int[][] matriz3 = new int[][]{
            {0, 0, 0, 0},
            {6, 6, 6, 0},
            {0, 6, 0, 0},
            {0, 0, 0, 0}
        };

    public static final int[][] matriz4 = new int[][]{
            {0, 6, 0, 0},
            {6, 6, 0, 0},
            {0, 6, 0, 0},
            {0, 0, 0, 0}
        };

    public T() {
        matriz = new int[][]{
            {0, 0, 0, 0},
            {0, 6, 0, 0},
            {6, 6, 6, 0},
            {0, 0, 0, 0}
        };
        color = 6;
        resetarPosicao();
    }
    
}
