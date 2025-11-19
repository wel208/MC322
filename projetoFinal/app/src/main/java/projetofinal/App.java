package projetofinal;

import projetofinal.game.*;
import javafx.scene.input.KeyCode;

public class App {
    public static void main(String[] args) throws Exception {
        Game game = new Game();

        for (int i = 0; i < 4; i++) {
            // Loop interno para as colunas
            for (int j = 0; j < 4; j++) {
                System.out.print(game.getPlayer1().getBlocoAtual().matriz[i][j] + " "); // Imprime o elemento e um espaço
            }
            System.out.println(); // Pula para a próxima linha após imprimir todos os elementos da linha
        }
        System.out.println();

        
        for (;;) {
            game.atualizar(KeyCode.W);

            game.atualizar(KeyCode.S);
            if (game.terminado == true) {
                break;
            }
        }

        for (int i = 0; i < 4; i++) {
            // Loop interno para as colunas
            for (int j = 0; j < 4; j++) {
                System.out.print(game.getPlayer1().getBlocoAtual().matriz[i][j] + " "); // Imprime o elemento e um espaço
            }
            System.out.println(); // Pula para a próxima linha após imprimir todos os elementos da linha
        }
        System.out.println();

        for (int i = 3; i < 23; i++) {
            // Loop interno para as colunas
            for (int j = 0; j < 10; j++) {
                System.out.print(game.getPlayer1().getTabuleiro().tab[i][j] + " "); // Imprime o elemento e um espaço
            }
            System.out.println(); // Pula para a próxima linha após imprimir todos os elementos da linha
        }
    }
}
