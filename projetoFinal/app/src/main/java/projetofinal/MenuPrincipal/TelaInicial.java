package projetofinal.MenuPrincipal;

import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.geometry.*;
import javafx.scene.layout.*;
import projetofinal.Jogadores.*;
import projetofinal.Util.*;
import projetofinal.game.Main;
import javafx.scene.image.*;
public class TelaInicial {

    // Tela inicial que aguarda o usuário pressionar qualquer tecla para continuar
    public static Scene telaInicial(Stage primaryStage) {

        // Chamada da imagem de fundo
        Image img = new Image(TelaInicial.class.getResourceAsStream("/tetris.png"));
        ImageView backgroundView = new ImageView(img);

        // Configuração da imagem de fundo
        backgroundView.fitWidthProperty().bind(primaryStage.widthProperty());
        backgroundView.fitHeightProperty().bind(primaryStage.heightProperty());
        backgroundView.setOpacity(0.8);

        StackPane layout = new StackPane();
        Label textoInicial = new Label("Aperte qualquer tecla para continuar");

        layout.getChildren().addAll(backgroundView, textoInicial);
        layout.setAlignment(Pos.CENTER);

        Scene cenaInicial = new Scene(layout);

        return cenaInicial;
    }

    /*
     * Menu para:
     * - Escolha do nome dos jogadores
     * - Visualização do ranking de pontuação e vitórias dos jogadores
     * - Iniciar o jogo
     */
    public static Scene menuPrincipal(Stage primaryStage, Jogador J1, Jogador J2) {

        // Locais para os nomes dos jogadores
        Label l1J1 = new Label(Utilidades.letrasENumeros.get(0));
        Label l2J1 = new Label(Utilidades.letrasENumeros.get(0));
        Label l3J1 = new Label(Utilidades.letrasENumeros.get(0));
        Label l1J2 = new Label(Utilidades.letrasENumeros.get(0));
        Label l2J2 = new Label(Utilidades.letrasENumeros.get(0));
        Label l3J2 = new Label(Utilidades.letrasENumeros.get(0));

        // Botões para alterar as letras dos nomes dos jogadores
        Button l1J1Up = new Button("▲");
        Button l1J1Down = new Button("▼");
        Button l2J1Up = new Button("▲");
        Button l2J1Down = new Button("▼");
        Button l3J1Up = new Button("▲");
        Button l3J1Down = new Button("▼");
        Button l1J2Up = new Button("▲");
        Button l1J2Down = new Button("▼");
        Button l2J2Up = new Button("▲");
        Button l2J2Down = new Button("▼");
        Button l3J2Up = new Button("▲");
        Button l3J2Down = new Button("▼");

        // Mostrar o nome dos jogadores
        Label nomeJ1 = new Label("Jogador 1: " + J1.getNome());
        Label nomeJ2 = new Label("Jogador 2: " + J2.getNome());

        /*
         * Definição das ações dos botões:
         * - Atualiza a letra/número exibido no label correspondente
         * - Atualiza o nome do jogador com base nas letras/números selecionados
         */
        l1J1Up.setOnAction(e -> {
            l1J1.setText(atualizarLetra(l1J1.getText(), true));
            J1.setNome(atualizarNomeJogador(l1J1.getText(), l2J1.getText(), l3J1.getText()));
            nomeJ1.setText("Jogador 1: " + J1.getNome());
        });
        l1J1Down.setOnAction(e -> {
            l1J1.setText(atualizarLetra(l1J1.getText(), false));
            J1.setNome(atualizarNomeJogador(l1J1.getText(), l2J1.getText(), l3J1.getText()));
            nomeJ1.setText("Jogador 1: " + J1.getNome());
        });
        l1J2Up.setOnAction(e -> {
            l1J2.setText(atualizarLetra(l1J2.getText(), true));
            J2.setNome(atualizarNomeJogador(l1J2.getText(), l2J2.getText(), l3J2.getText()));
            nomeJ2.setText("Jogador 2: " + J2.getNome());
        });
        l1J2Down.setOnAction(e -> {
            l1J2.setText(atualizarLetra(l1J2.getText(), false));
            J2.setNome(atualizarNomeJogador(l1J2.getText(), l2J2.getText(), l3J2.getText()));
            nomeJ2.setText("Jogador 2: " + J2.getNome());
        });
        l2J1Up.setOnAction(e -> {
            l2J1.setText(atualizarLetra(l2J1.getText(), true));
            J1.setNome(atualizarNomeJogador(l1J1.getText(), l2J1.getText(), l3J1.getText()));
            nomeJ1.setText("Jogador 1: " + J1.getNome());
        });
        l2J1Down.setOnAction(e -> {
            l2J1.setText(atualizarLetra(l2J1.getText(), false));
            J1.setNome(atualizarNomeJogador(l1J1.getText(), l2J1.getText(), l3J1.getText()));
            nomeJ1.setText("Jogador 1: " + J1.getNome());
        });
        l2J2Up.setOnAction(e -> {
            l2J2.setText(atualizarLetra(l2J2.getText(), true));
            J2.setNome(atualizarNomeJogador(l1J2.getText(), l2J2.getText(), l3J2.getText()));
            nomeJ2.setText("Jogador 2: " + J2.getNome());
        });
        l2J2Down.setOnAction(e -> {
            l2J2.setText(atualizarLetra(l2J2.getText(), false));
            J2.setNome(atualizarNomeJogador(l1J2.getText(), l2J2.getText(), l3J2.getText()));
            nomeJ2.setText("Jogador 2: " + J2.getNome());
        });
        l3J1Up.setOnAction(e -> {
            l3J1.setText(atualizarLetra(l3J1.getText(), true));
            J1.setNome(atualizarNomeJogador(l1J1.getText(), l2J1.getText(), l3J1.getText()));
            nomeJ1.setText("Jogador 1: " + J1.getNome());
        });
        l3J1Down.setOnAction(e -> {
            l3J1.setText(atualizarLetra(l3J1.getText(), false));
            J1.setNome(atualizarNomeJogador(l1J1.getText(), l2J1.getText(), l3J1.getText()));
            nomeJ1.setText("Jogador 1: " + J1.getNome());
        });
        l3J2Up.setOnAction(e -> {
            l3J2.setText(atualizarLetra(l3J2.getText(), true));
            J2.setNome(atualizarNomeJogador(l1J2.getText(), l2J2.getText(), l3J2.getText()));
            nomeJ2.setText("Jogador 2: " + J2.getNome());
        });
        l3J2Down.setOnAction(e -> {
            l3J2.setText(atualizarLetra(l3J2.getText(), false));
            J2.setNome(atualizarNomeJogador(l1J2.getText(), l2J2.getText(), l3J2.getText()));
            nomeJ2.setText("Jogador 2: " + J2.getNome());
        });

        // Botão para iniciar o jogo
        Button iniciarJogo = new Button("Iniciar Jogo");
        iniciarJogo.setOnAction(event -> {
            Main.executarJogo();
        });

        // Botão para voltar à tela inicial
        Button voltarTelaInicial = new Button("Voltar à Tela Inicial");
        voltarTelaInicial.setOnAction(event -> {
            J1.setNome("AAA");
            J2.setNome("AAA");
            Main.executarTelaInicial(primaryStage);
        });

        // Organização dos botões e labels na tela
        VBox vBoxl1 = new VBox(10, l1J1Up, l1J1, l1J1Down, l1J2Up, l1J2, l1J2Down);
        VBox vBoxl2 = new VBox(10, l2J1Up, l2J1, l2J1Down, l2J2Up, l2J2, l2J2Down);
        VBox vBoxl3 = new VBox(10, l3J1Up, l3J1, l3J1Down, l3J2Up, l3J2, l3J2Down);
        vBoxl1.setAlignment(Pos.CENTER);
        vBoxl2.setAlignment(Pos.CENTER); 
        vBoxl3.setAlignment(Pos.CENTER);
        HBox hBoxNomes = new HBox(10, vBoxl1, vBoxl2, vBoxl3);
        hBoxNomes.setAlignment(Pos.CENTER);
        HBox hBoxBotoes = new HBox(10, voltarTelaInicial, iniciarJogo);
        hBoxBotoes.setAlignment(Pos.CENTER);
        VBox iniciar = new VBox(10, hBoxNomes, hBoxBotoes);
        iniciar.setAlignment(Pos.CENTER);
        VBox nomes = new VBox(10, nomeJ1, nomeJ2);

        StackPane layout = new StackPane();
        layout.getChildren().addAll(nomes, iniciar);   

        return new Scene(layout);
    }

    // Método que avança ou retrocede a letra ou o número atual na lista de letras e números
    private static String atualizarLetra(String letraAtual, boolean incrementar) {
        int index = Utilidades.letrasENumeros.indexOf(letraAtual);
        if (incrementar){
            index = (index + 1) % Utilidades.letrasENumeros.size();
        } 
        else{
            index = (index - 1 + Utilidades.letrasENumeros.size()) % Utilidades.letrasENumeros.size();
        }
        return Utilidades.letrasENumeros.get(index);
    }

    private static String atualizarNomeJogador(String l1, String l2, String l3) {
        return l1 + l2 + l3;
    }
}
