package projetofinal.game;

import javafx.stage.Stage;
import javafx.geometry.Pos;
import java.lang.Math;
import javafx.scene.Scene;
import java.util.List;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.animation.*;
import java.util.HashSet;
import java.util.Set;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import projetofinal.Jogadores.Jogador;
import projetofinal.saves.JogadorAuxiliar;
import projetofinal.saves.PontuacoesSalvas;
import projetofinal.Blocos.*;

public class TelaGame {
    
    private static final long UM_SEGUNDO = 1_000_000_000L;
    private static long ultimoTick = 0;

    private static final long DECIMO_DE_SEGUNDO = 100_000_000L;

    private static final long TEMPO_MOV_LATERAL = 90_000_000L;
    private static long ultimoMovLateralJ1 = 0;
    private static long ultimoMovLateralJ2 = 0;

    private static final long TEMPO_QUEDA = 40_000_000L;
    private static long ultimaQuedaJ1 = 0;
    private static long ultimaQuedaJ2 = 0;

    private static boolean podeSairDoJogo = false;

    private static AnimationTimer timer;

    public static Scene telaJogo(Game game, Stage primaryStage){

        podeSairDoJogo = false;

        // Criação da parte visual dos tabuleiros, Holds e Nexts
        Canvas tab1 = new Canvas(250, 500);
        GraphicsContext gc1 = tab1.getGraphicsContext2D();
        Canvas tab2 = new Canvas(250, 500);
        GraphicsContext gc2 = tab2.getGraphicsContext2D();

        Canvas canvasHoldJ1 = new Canvas(120, 80);
        GraphicsContext gcHold1 = canvasHoldJ1.getGraphicsContext2D();
        Canvas canvasHoldJ2 = new Canvas(120, 80);
        GraphicsContext gcHold2 = canvasHoldJ2.getGraphicsContext2D();

        Canvas canvasNext1 = new Canvas(120, 260);
        GraphicsContext gcNext1 = canvasNext1.getGraphicsContext2D();
        Canvas canvasNext2 = new Canvas(120, 260);
        GraphicsContext gcNext2 = canvasNext2.getGraphicsContext2D();

        // Inicializando todas as informações referentes ao jogador 1
        Label pontosJogador1 = new Label("Pontuação: " + game.getPlayer1().getPontos());
        Label nivelJogador1 = new Label("Nível: " + game.getPlayer1().getPontos());
        Label linhasJogador1 = new Label("Linhas: " + game.getPlayer1().getNLinhas());
        pontosJogador1.setAlignment(Pos.CENTER);
        nivelJogador1.setAlignment(Pos.CENTER);
        linhasJogador1.setAlignment(Pos.CENTER);
        Label hold1 = new Label("Hold");
        hold1.setAlignment(Pos.CENTER);
        Label next1 = new Label("Next");
        next1.setAlignment(Pos.CENTER);
        Label nome1 = new Label(game.getPlayer1().getNome());

        gc1.setFill(Color.BLACK);
        gc1.fillRect(0, 0, tab1.getWidth(), tab1.getHeight());
        gc1.setStroke(Color.GRAY);
        for (int i = 0; i <= 250; i += 25){ 
            for (int j = 0; j <= 500; j += 25) 
                gc1.strokeRect(i, j, 25, 25);
        }

        gcHold1.setFill(Color.BLACK);
        gcHold1.fillRect(0, 0, canvasHoldJ1.getWidth(), canvasHoldJ1.getWidth());

        gcNext1.setFill(Color.BLACK);
        gcNext1.fillRect(0, 0, canvasNext1.getWidth(), canvasNext1.getHeight());

        VBox status1 = new VBox(5, pontosJogador1, nivelJogador1, linhasJogador1);
        VBox holdJ1 = new VBox(hold1, canvasHoldJ1);
        VBox lado1J1 = new VBox(350, holdJ1, status1);
        lado1J1.setAlignment(Pos.CENTER);

        VBox nextJ1 = new VBox(next1, canvasNext1);
        VBox lado2J1 = new VBox(200, nextJ1, nome1);
        lado2J1.setAlignment(Pos.CENTER);

        HBox jogo1 = new HBox(15, lado1J1, tab1, lado2J1);
        jogo1.setAlignment(Pos.CENTER);

        // Inicializando todas as informações referentes ao jogador 1
        Label pontosJogador2 = new Label("Pontuação: " + game.getPlayer2().getPontos());
        Label nivelJogador2 = new Label("Nível: " + game.getPlayer2().getPontos());
        Label linhasJogador2 = new Label("Linhas: " + game.getPlayer2().getNLinhas());
        pontosJogador2.setAlignment(Pos.CENTER);
        nivelJogador2.setAlignment(Pos.CENTER);
        linhasJogador2.setAlignment(Pos.CENTER);
        Label hold2 = new Label("Hold");
        hold2.setAlignment(Pos.CENTER);
        Label next2 = new Label("Next");
        next2.setAlignment(Pos.CENTER);
        Label nome2 = new Label(game.getPlayer2().getNome());

        gc2.setFill(Color.BLACK);
        gc2.fillRect(0, 0, tab2.getWidth(), tab2.getHeight());
        gc2.setStroke(Color.GRAY);
        for (int i = 0; i <= 250; i += 25){ 
            for (int j = 0; j <= 500; j += 25) 
                gc2.strokeRect(i, j, 25, 25);
        }

        gcHold2.setFill(Color.BLACK);
        gcHold2.fillRect(0, 0, canvasHoldJ2.getWidth(), canvasHoldJ2.getWidth());

        gcNext2.setFill(Color.BLACK);
        gcNext2.fillRect(0, 0, canvasNext2.getWidth(), canvasNext2.getHeight());
        
        VBox status2 = new VBox(5, pontosJogador2, nivelJogador2, linhasJogador2);
        VBox holdJ2 = new VBox(hold2, canvasHoldJ2);
        VBox lado1J2 = new VBox(350, holdJ2, status2);
        lado1J2.setAlignment(Pos.CENTER);

        VBox nextJ2 = new VBox(next2, canvasNext2);
        VBox lado2J2 = new VBox(200, nextJ2, nome2);
        lado2J2.setAlignment(Pos.CENTER);

        HBox jogo2 = new HBox(15, lado1J2, tab2, lado2J2);
        jogo2.setAlignment(Pos.CENTER);

        HBox jogos = new HBox(400, jogo1, jogo2);
        jogos.setAlignment(Pos.CENTER);

        //Elementos do menu de pausa
        Label jogoPausado = new Label("");
        Label acoesNaPausa = new Label("");
        VBox pausa = new VBox(20, jogoPausado, acoesNaPausa);
        pausa.setAlignment(Pos.CENTER);

        //Elementos que aparecem após o jogo acabar
        Label jogoTerminado = new Label("");
        Label jogadorVencedor = new Label("");
        Label pontuacaoVencedor = new Label("");
        VBox telaDeFim = new VBox(10, jogoTerminado, jogadorVencedor, pontuacaoVencedor);
        telaDeFim.setAlignment(Pos.CENTER);
        
        StackPane tela = new StackPane(jogos, pausa, telaDeFim);
        Scene cenaJogo = new Scene(tela);

        Set<KeyCode> teclasPressionadas = new HashSet<>();

        cenaJogo.setOnKeyPressed(event -> {
            teclasPressionadas.add(event.getCode());
            KeyCode tecla = event.getCode();

            if (tecla.equals(KeyCode.W) || tecla.equals(KeyCode.I) || tecla.equals(KeyCode.Q) || tecla.equals(KeyCode.U) || tecla.equals(KeyCode.E) || tecla.equals(KeyCode.O))
                game.atualizar(tecla);
            if (event.getCode() == KeyCode.R && game.pausado()){
                game.reiniciar();
                jogoPausado.setText("");
                acoesNaPausa.setText("");
            }
            if ((event.getCode() == KeyCode.M && game.pausado()) || (game.terminado() && podeSairDoJogo)){
                    game.reiniciar();
                    timer.stop();
                    ultimoMovLateralJ1 = 0;
                    ultimoMovLateralJ2 = 0;
                    ultimaQuedaJ1 = 0;
                    ultimaQuedaJ2 = 0;
                    Main.executarMenuPrincipal(primaryStage);
            }
            if ((event.getCode() == KeyCode.ESCAPE || event.getCode() == KeyCode.P) && !game.terminado()){
                game.pausar();
                if (game.pausado()){
                    jogoPausado.setText("JOGO PAUSADO");
                    acoesNaPausa.setText("Pressione 'R' para reiniciar o jogo    Pressione 'M' para voltar ao menu principal");
                }
                else{
                    jogoPausado.setText("");
                    acoesNaPausa.setText("");
                }
            }
        });

        cenaJogo.setOnKeyReleased(event -> {
            teclasPressionadas.remove(event.getCode());
        });

        timer = new AnimationTimer() {
            @Override
            public void handle(long now){

                if (game.terminado()){

                    Jogador vencedor = game.getPlayer1().perdeu() ? game.getPlayer2() : game.getPlayer1();
                    jogoTerminado.setText("O JOGO ACABOU");
                    jogadorVencedor.setText(vencedor.getNome() + " VENCEU!");
                    pontuacaoVencedor.setText("Pontuação: " + vencedor.getPontos() + "   Nível: " + vencedor.getNivel() + "   Nº de Linhas: " + vencedor.getNLinhas());

                    PontuacoesSalvas ranking = new PontuacoesSalvas();
                    ranking.carregarRank();
                    
                    ranking.atualizarRank(new JogadorAuxiliar(game.getPlayer1().getNome(), game.getPlayer1().getNivel(), game.getPlayer1().getNLinhas(), game.getPlayer1().getPontos()), !game.getPlayer1().perdeu());
                    ranking.atualizarRank(new JogadorAuxiliar(game.getPlayer2().getNome(), game.getPlayer2().getNivel(), game.getPlayer2().getNLinhas(), game.getPlayer2().getPontos()), !game.getPlayer2().perdeu());
                    timer.stop();

                    game.getPlayer1().resetPontuacao();
                    game.getPlayer2().resetPontuacao();

                    PauseTransition espera = new PauseTransition(javafx.util.Duration.seconds(3));

                    espera.setOnFinished(event -> {
                        podeSairDoJogo = true;
                    });

                    espera.play();

                    return;
                }

                if (now - ultimoTick >= UM_SEGUNDO - ((long)Math.max(game.getPlayer1().getNivel(), game.getPlayer2().getNivel()) - (long)1) * DECIMO_DE_SEGUNDO){
                    game.atualizar(KeyCode.S);
                    game.atualizar(KeyCode.K);
                    ultimoTick = now;
                }

                if (now - ultimoMovLateralJ1 >= TEMPO_MOV_LATERAL){
                    if (teclasPressionadas.contains(KeyCode.A)){
                        game.atualizar(KeyCode.A);
                    }
                    if (teclasPressionadas.contains(KeyCode.D)){
                        game.atualizar(KeyCode.D);
                    }
                    ultimoMovLateralJ1 = now;
                }
                
                if (now - ultimoMovLateralJ2 >= TEMPO_MOV_LATERAL){
                    if (teclasPressionadas.contains(KeyCode.L)){
                       game.atualizar(KeyCode.L);
                    }
                    if (teclasPressionadas.contains(KeyCode.J)){
                        game.atualizar(KeyCode.J);
                    }
                    ultimoMovLateralJ2 = now;
                }

                if (now - ultimaQuedaJ1 >= TEMPO_QUEDA){    
                    if (teclasPressionadas.contains(KeyCode.S)){
                        game.atualizar(KeyCode.S);
                    }
                    ultimaQuedaJ1 = now;
                }

                if (now - ultimaQuedaJ2 >= TEMPO_QUEDA){    
                    if (teclasPressionadas.contains(KeyCode.K)){
                        game.atualizar(KeyCode.K);
                    }
                    ultimaQuedaJ2 = now;
                }

                if (teclasPressionadas.contains(KeyCode.M) && game.pausado()){
                    teclasPressionadas.clear();
                    stop();
                }

                atualizarTabuleiro(game.getPlayer1(), gc1);
                atualizarTabuleiro(game.getPlayer2(), gc2);
                atualizarHold(game.getPlayer1(), gcHold1);
                atualizarHold(game.getPlayer2(), gcHold2);
                atualizarNext(game.getPlayer1(), gcNext1);
                atualizarNext(game.getPlayer2(), gcNext2);
                pontosJogador1.setText("Pontuação: " + game.getPlayer1().getPontos());
                pontosJogador2.setText("Pontuação: " + game.getPlayer2().getPontos());
                nivelJogador1.setText("Nível: " + game.getPlayer1().getNivel());
                nivelJogador2.setText("Nível: " + game.getPlayer2().getNivel());
                linhasJogador1.setText("Linhas: " + game.getPlayer1().getNLinhas());
                linhasJogador2.setText("Linhas: " + game.getPlayer2().getNLinhas());
            }
        };

        timer.start();

        return cenaJogo;
    }

    private static void atualizarTabuleiro(Jogador jogador, GraphicsContext gc){
        
        gc.clearRect(0, 0, 250, 500);

        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 10; j++){
                int elemento = jogador.getTabuleiro().getGrade()[i][j];

                if (elemento == 0)
                    gc.setFill(Color.BLACK);
                else if (elemento == 1)
                    gc.setFill(Color.LIGHTBLUE);
                else if (elemento == 2)
                    gc.setFill(Color.ORANGE);
                else if (elemento == 3) 
                    gc.setFill(Color.GREEN);
                else if (elemento == 4)
                    gc.setFill(Color.YELLOW);
                else if (elemento == 5)
                    gc.setFill(Color.RED);
                else if (elemento == 6)
                    gc.setFill(Color.PURPLE);
                else if (elemento == 7)
                    gc.setFill(Color.BLUE);
                else if (elemento == 8)
                    gc.setFill(Color.DARKGRAY);

                gc.fillRect(j * 25, i * 25, 25, 25);
            }
        }

        List<int[]> blocoCaindo = jogador.getBlocoAtual().getAbsoluteCoord();

        int x, y;
        
        for (int k = 0; k < 4; k++){
            x = blocoCaindo.get(k)[0];
            y = blocoCaindo.get(k)[1];

            for (int i = y; i < 20; i++){
                if (jogador.getTabuleiro().getGrade()[i][x] == 0){
                    gc.setFill(Color.DARKSLATEGRAY);
                    gc.fillRect(x * 25, i * 25, 25, 25);
                }
            }

            int corBloco = jogador.getBlocoAtual().getColor();

            if (corBloco == 0)
                gc.setFill(Color.BLACK);
            else if (corBloco == 1)
                gc.setFill(Color.LIGHTBLUE);
            else if (corBloco == 2)
                gc.setFill(Color.ORANGE);
            else if (corBloco == 3)
                gc.setFill(Color.GREEN);
            else if (corBloco == 4)
                gc.setFill(Color.YELLOW);
            else if (corBloco == 5)
                gc.setFill(Color.RED);
            else if (corBloco == 6)
                gc.setFill(Color.PURPLE);
            else if (corBloco == 7)
                gc.setFill(Color.BLUE);

            gc.fillRect(x * 25, y * 25, 25, 25);
        }

        gc.setStroke(Color.GRAY);
        for (int i = 0; i <= 250; i += 25){ 
            for (int j = 0; j <= 500; j += 25) 
                gc.strokeRect(i, j, 25, 25);
        }
    }
    
    private static void atualizarHold(Jogador jogador, GraphicsContext gc){

        if (!jogador.guardou())
            return;

        gc.clearRect(0, 0, 120, 80);

        gc.setFill(Color.BLACK);
        gc.setStroke(Color.GRAY);
        gc.fillRect(0, 0, 120, 80);

        int corBloco = jogador.getGuardado().getColor();

        if (corBloco == 1){
            gc.setFill(Color.LIGHTBLUE);
            gc.fillRect(20, 30, 80, 20);
            for (int i = 0; i < 4; i++){
                gc.fillRect(20 + i * 20, 30, 20, 20);
                gc.strokeRect(20 + i * 20, 30, 20, 20);
            }
        }
        else if (corBloco == 2){
            gc.setFill(Color.ORANGE);
            gc.fillRect(30, 20, 20, 20);
            gc.strokeRect(30, 20, 20, 20);
            for (int i = 0; i < 3; i++){
                gc.fillRect(30 + i * 20, 40, 20, 20);
                gc.strokeRect(30 + i * 20, 40, 20, 20);
            }
        }
        else if (corBloco == 3){
            gc.setFill(Color.GREEN);
            gc.fillRect(70, 20, 20, 20);
            gc.strokeRect(70, 20, 20, 20);
            for (int i = 0; i < 3; i++){
                gc.fillRect(30 + i * 20, 40, 20, 20);
                gc.strokeRect(30 + i * 20, 40, 20, 20);
            }
        }
        else if (corBloco == 4){
            gc.setFill(Color.YELLOW);
            gc.fillRect(40, 20, 40, 40);
            for (int i = 0; i < 2; i++){
                gc.strokeRect(40 + i * 20, 20 , 20, 20);
                gc.strokeRect(40 + i * 20, 40, 20, 20);
            }
        }
        else if (corBloco == 5){
            gc.setFill(Color.RED);
            for (int i = 0; i < 2; i++){
                gc.fillRect(30 + i * 20, 40, 20, 20);
                gc.fillRect(50 + i * 20, 20, 20, 20);
                gc.strokeRect(30 + i * 20, 40, 20, 20);
                gc.strokeRect(50 + i * 20, 20, 20, 20);
            }
        }
        else if (corBloco == 6){
            gc.setFill(Color.PURPLE);
            gc.fillRect(50, 20, 20, 20);
            gc.strokeRect(50, 20, 20, 20);

            for (int i = 0; i < 3; i++){
                gc.fillRect(30 + i * 20, 40, 20, 20);
                gc.strokeRect(30 + i * 20, 40, 20, 20);
            }
        }
        else if (corBloco == 7){
            gc.setFill(Color.BLUE);
            for (int i = 0; i < 2; i++){
                gc.fillRect(50 + i * 20, 40, 20, 20);
                gc.fillRect(30 + i * 20, 20, 20, 20);
                gc.strokeRect(50 + i * 20, 40, 20, 20);
                gc.strokeRect(30 + i * 20, 20, 20, 20);
            }
        }
    }

    private static void atualizarNext(Jogador jogador, GraphicsContext gc){
        
        gc.clearRect(0, 0, 120, 260);

        gc.setFill(Color.BLACK);
        gc.setStroke(Color.GRAY);
        gc.fillRect(0, 0, 120, 260);

        int corBloco;

        List<Bloco> listaNext = jogador.getBag().getNextList();

        for (int i = 0; i < listaNext.size(); i++){
            corBloco = listaNext.get(i).getColor();

            if (corBloco == 1){
                gc.setFill(Color.LIGHTBLUE);
                for (int j = 0; j < 4; j++){
                    gc.fillRect(20 + j * 20, i * 60 + 30, 20, 20);
                    gc.strokeRect(20 + j * 20, i * 60 + 30, 20, 20);
                }
            }
            else if (corBloco == 2){
                gc.setFill(Color.ORANGE);
                gc.fillRect(30, i * 60 + 20, 20, 20);
                gc.strokeRect(30, i * 60 + 20, 20, 20);
                for (int j = 0; j < 3; j++){
                    gc.fillRect(30 + j * 20, i * 60 + 40, 20, 20);
                    gc.strokeRect(30 + j * 20, i * 60 + 40, 20, 20);
                }
            }
            else if (corBloco == 3){
                gc.setFill(Color.GREEN);
                gc.fillRect(70, i * 60 + 20, 20, 20);
                gc.strokeRect(70, i * 60 + 20, 20, 20);
                for (int j = 0; j < 3; j++){
                    gc.fillRect(30 + j * 20, i * 60 + 40, 20, 20);
                    gc.strokeRect(30 + j * 20, i * 60 + 40, 20, 20);
                }
            }
            else if (corBloco == 4){
                gc.setFill(Color.YELLOW);
                gc.fillRect(40, i * 60 + 20, 40, 40);
                for (int j = 0; j < 2; j++){
                    gc.strokeRect(40 + j * 20, i * 60 + 20 , 20, 20);
                    gc.strokeRect(40 + j * 20, i * 60 + 40, 20, 20);
                }
            }
            else if (corBloco == 5){
                gc.setFill(Color.RED);
                for (int j = 0; j < 2; j++){
                    gc.fillRect(30 + j * 20, i * 60 + 40, 20, 20);
                    gc.fillRect(50 + j * 20, i * 60 + 20, 20, 20);
                    gc.strokeRect(30 + j * 20, i * 60 + 40, 20, 20);
                    gc.strokeRect(50 + j * 20, i * 60 + 20, 20, 20);
                }
            }
            else if (corBloco == 6){
                gc.setFill(Color.PURPLE);
                gc.fillRect(50, i * 60 + 20, 20, 20);
                gc.strokeRect(50, i * 60 + 20, 20, 20);

                for (int j = 0; j < 3; j++){
                    gc.fillRect(30 + j * 20, i * 60 + 40, 20, 20);
                    gc.strokeRect(30 + j * 20, i * 60 + 40, 20, 20);
                }
            }
            else if (corBloco == 7){
                gc.setFill(Color.BLUE);
                for (int j = 0; j < 2; j++){
                    gc.fillRect(50 + j * 20, i * 60 + 40, 20, 20);
                    gc.fillRect(30 + j * 20, i * 60 + 20, 20, 20);
                    gc.strokeRect(50 + j * 20, i * 60 + 40, 20, 20);
                    gc.strokeRect(30 + j * 20, i * 60 + 20, 20, 20);
                }
            }
        }
    }
}
