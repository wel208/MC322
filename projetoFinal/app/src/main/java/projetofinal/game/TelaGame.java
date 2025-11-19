package projetofinal.game;

import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import java.util.List;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import projetofinal.Jogadores.Jogador;

public class TelaGame {
    
    public static Scene telaJogo(Game game, Stage primaryStage){

        Canvas tab1 = new Canvas(250, 500);
        GraphicsContext gc1 = tab1.getGraphicsContext2D();
        Canvas tab2 = new Canvas(250, 500);
        GraphicsContext gc2 = tab2.getGraphicsContext2D();

        Label pontosJogador1 = new Label("Pontuação: " + game.getPlayer1().getPontos());
        Label nivelJogador1 = new Label("Nível: " + game.getPlayer1().getPontos());
        Label linhasJogador1 = new Label("Linhas: " + 0);
        Label hold1 = new Label("Hold");
        Label next1 = new Label("Next");
        Label nome1 = new Label(game.getPlayer1().getNome());

        gc1.setFill(Color.BLACK);
        gc1.fillRect(0, 0, tab1.getWidth(), tab1.getHeight());
        gc1.setStroke(Color.GRAY);
        for (int i = 0; i <= 250; i += 25){ 
            for (int j = 0; j <= 500; j += 25) 
                gc1.strokeRect(i, j, 25, 25);
        }
        
        VBox status1 = new VBox(5, pontosJogador1, nivelJogador1, linhasJogador1);
        VBox lado1J1 = new VBox(400, hold1, status1);
        lado1J1.setAlignment(Pos.CENTER);

        VBox lado2J1 = new VBox(450, next1, nome1);
        lado2J1.setAlignment(Pos.CENTER);

        HBox jogo1 = new HBox(30, lado1J1, tab1, lado2J1);
        jogo1.setAlignment(Pos.CENTER);

        Label pontosJogador2 = new Label("Pontuação: " + game.getPlayer2().getPontos());
        Label nivelJogador2 = new Label("Nível: " + game.getPlayer2().getPontos());
        Label linhasJogador2 = new Label("Linhas: " + 0);
        Label hold2 = new Label("Hold");
        Label next2 = new Label("Next");
        Label nome2 = new Label(game.getPlayer2().getNome());

        gc2.setFill(Color.BLACK);
        gc2.fillRect(0, 0, tab2.getWidth(), tab2.getHeight());
        gc2.setStroke(Color.GRAY);
        for (int i = 0; i <= 250; i += 25){ 
            for (int j = 0; j <= 500; j += 25) 
                gc2.strokeRect(i, j, 25, 25);
        }
        
        VBox status2 = new VBox(5, pontosJogador2, nivelJogador2, linhasJogador2);
        VBox lado1J2 = new VBox(400, hold2, status2);
        lado1J2.setAlignment(Pos.CENTER);

        VBox lado2J2 = new VBox(450, next2, nome2);
        lado2J2.setAlignment(Pos.CENTER);

        HBox jogo2 = new HBox(30, lado1J2, tab2, lado2J2);
        jogo2.setAlignment(Pos.CENTER);

        HBox tela = new HBox(500, jogo1, jogo2);
        tela.setAlignment(Pos.CENTER);
        
        Scene cenaJogo = new Scene(tela);

        cenaJogo.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.A){
                game.atualizar(KeyCode.A);
            }
            else if (event.getCode() == KeyCode.D){
                game.atualizar(KeyCode.D);
            }
            else if (event.getCode() == KeyCode.W){
                game.atualizar(KeyCode.W);
            }
            else if (event.getCode() == KeyCode.S){
                game.atualizar(KeyCode.S);
            }
            else if (event.getCode() == KeyCode.LEFT){
                game.atualizar(KeyCode.LEFT);
            }
            else if (event.getCode() == KeyCode.RIGHT){
                game.atualizar(KeyCode.RIGHT);
            }
            else if (event.getCode() == KeyCode.UP){
                game.atualizar(KeyCode.UP);
            }
            else if (event.getCode() == KeyCode.DOWN){
                game.atualizar(KeyCode.DOWN);
            }
            else if (event.getCode() == KeyCode.ESCAPE || event.getCode() == KeyCode.P){
                game.pausar();
            }

            atualizarTela(game, game.getPlayer1(), gc1);
            atualizarTela(game, game.getPlayer2(), gc2);
        });

        return cenaJogo;
    }

    public static void atualizarTela(Game game, Jogador jogador, GraphicsContext gc){
        
        gc.clearRect(0, 0, 250, 500);

        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 10; j++){
                if (jogador.getTabuleiro().getGrade()[i][j] == 0){
                    gc.setFill(Color.BLACK);
                }
                else if (jogador.getTabuleiro().getGrade()[i][j] == 1){
                    gc.setFill(Color.LIGHTBLUE);
                }
                else if (jogador.getTabuleiro().getGrade()[i][j] == 2){
                    gc.setFill(Color.ORANGE);
                }
                else if (jogador.getTabuleiro().getGrade()[i][j] == 3){
                    gc.setFill(Color.GREEN);
                }
                else if (jogador.getTabuleiro().getGrade()[i][j] == 4){
                    gc.setFill(Color.YELLOW);
                }
                else if (jogador.getTabuleiro().getGrade()[i][j] == 5){
                    gc.setFill(Color.RED);
                }
                else if (jogador.getTabuleiro().getGrade()[i][j] == 6){
                    gc.setFill(Color.PURPLE);
                }
                else if (jogador.getTabuleiro().getGrade()[i][j] == 7){
                    gc.setFill(Color.BLUE);
                }

                gc.fillRect(j * 25, i * 25, 25, 25);
            }
        }

        List<int[]> blocoCaindo = jogador.getBlocoAtual().getAbsoluteCoord();

        int y;
        int x;
        
        for (int k = 0; k < 4; k++){
            y = blocoCaindo.get(k)[0];
            x = blocoCaindo.get(k)[1];

            if (jogador.getBlocoAtual().getColor() == 1){
                gc.setFill(Color.LIGHTBLUE);
            }
            else if (jogador.getBlocoAtual().getColor() == 2){
                gc.setFill(Color.ORANGE);
            }
            else if (jogador.getBlocoAtual().getColor() == 3){
                gc.setFill(Color.GREEN);
            }
            else if (jogador.getBlocoAtual().getColor() == 4){
                gc.setFill(Color.YELLOW);
            }
            else if (jogador.getBlocoAtual().getColor() == 5){
                gc.setFill(Color.RED);
            }
            else if (jogador.getBlocoAtual().getColor() == 6){
                gc.setFill(Color.PURPLE);
            }
            else if (jogador.getBlocoAtual().getColor() == 7){
                gc.setFill(Color.BLUE);
            }

            gc.fillRect(y * 25, x * 25, 25, 25);
        }

        gc.setStroke(Color.GRAY);
        for (int i = 0; i <= 250; i += 25){ 
            for (int j = 0; j <= 500; j += 25) 
                gc.strokeRect(i, j, 25, 25);
        }
    }
}
