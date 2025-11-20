package projetofinal.Blocos;

public class I extends Bloco {

    public static final int[][] matriz1 = new int[][]{
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
    public static final int[][] matriz2 = new int[][]{
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0}
        };

    public static final int[][] matriz3 = new int[][]{
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };

    public static final int[][] matriz4 = new int[][]{
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0}
        };

    public I() {
        matriz = matriz1;
        color = 1;
        resetarPosicao();
    }
 
    @Override
    public void rotacionarSH() {

        if(estado.equals(Estado.ESTADO1)){
            estado = Estado.ESTADO2;
            matriz = matriz2;
        }else if(estado.equals(Estado.ESTADO2)){
            estado = Estado.ESTADO3;
            matriz = matriz3;
        }else if(estado.equals(Estado.ESTADO3)){
            estado = Estado.ESTADO4;
            matriz = matriz4;
        }else if(estado.equals(Estado.ESTADO4)){
            estado = Estado.ESTADO1;
            matriz = matriz1;
        }
    }

    @Override
    public void rotacionarSA() {

        if(estado.equals(Estado.ESTADO1)){
            estado = Estado.ESTADO4;
            matriz = matriz4;
        }else if(estado.equals(Estado.ESTADO2)){
            estado = Estado.ESTADO1;
            matriz = matriz1;
        }else if(estado.equals(Estado.ESTADO3)){
            estado = Estado.ESTADO2;
            matriz = matriz2;
        }else if(estado.equals(Estado.ESTADO4)){
            estado = Estado.ESTADO3;
            matriz = matriz3;
        }
    }
}
