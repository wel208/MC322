package projetofinal.Tabuleiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import projetofinal.Blocos.*;

public class Tabuleiro {
    private final int altura = 20;
    private final int largura = 10;

    public int[][] tab;

    public Tabuleiro() {
        tab = new int[altura][largura];
    }

    public Validacao posicaoValida(Bloco bloco){
        for (int[] coord : bloco.getAbsoluteCoord()) {
            int x = coord[0];
            int y = coord[1];

            if (x < 0 || x >= largura || y < 0)
                return Validacao.LIMITE;
            if (y >= altura || tab[y][x] != 0)
                return Validacao.COLISAO;
        }
        return Validacao.OK;
    }

    public void fixarBloco(Bloco bloco) {
        for (int[] coord : bloco.getAbsoluteCoord()) {
            int x = coord[0];
            int y = coord[1];
            tab[y][x] = bloco.getColor();
        }
    }

    private void removerLinha(int linha) {
        for (int y = linha; y > 0; y--) {
            for (int x = 0; x < largura; x++) {
                tab[y][x] = tab[y - 1][x];
            }
        }
        for (int x = 0; x < largura; x++) {
            tab[0][x] = 0;
        }
    }

    public int verificarLinhas() {
        List<Integer> linhasParaRemover = new ArrayList<>();

        for (int y = 0; y < altura; y++) {
            boolean completa = true;

            for (int x = 0; x < largura; x++) {
                if (tab[y][x] == 0) {
                    completa = false;
                    break;
                }
            }

            if (completa) {
                linhasParaRemover.add(y);
            }
        }

        if (linhasParaRemover.isEmpty()) {
            return 0;
        }

        for (int linha : linhasParaRemover) {
            removerLinha(linha);
        }

        return linhasParaRemover.size();
    }

    public boolean atingiuTopo() {
        for (int x = 0; x < largura; x++) {
            if (tab[2][x] != 0)
                return true;
        }
        return false;
    }

    public int[][] getGrade() {
        return tab;
    }

    public void limpar() {
        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                tab[y][x] = 0;
            }
        }
    }

    public void adicionarLinhasIncompletas(int numLinhas) {
        Random rand = new Random();
        int buraco = rand.nextInt(largura);

        for (int y = 0; y < altura - numLinhas; y++) {
            for (int x = 0; x < largura; x++) {
                tab[y][x] = tab[y + numLinhas][x];
            }
        }

        for (int y = altura - numLinhas; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                if (x == buraco) {
                    tab[y][x] = 0;  
                } else {
                    tab[y][x] = 8; 
                }
            }
        }
    }
}
