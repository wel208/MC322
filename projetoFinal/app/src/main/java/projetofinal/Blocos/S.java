package projetofinal.Blocos;

public class S extends Bloco {
    public S() {
        matriz = new int[][] {
            {0, 1, 1, 0},
            {1, 1, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        color = 5; 
        resetarPosicao();
    }
}
