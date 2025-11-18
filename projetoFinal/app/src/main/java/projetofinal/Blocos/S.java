package projetofinal.Blocos;
public class S extends Bloco {
    public S() {
        matriz = new int[][] {
            {0, 5, 5, 0},
            {5, 5, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        color = 5; 
        resetarPosicao();
    }
}
