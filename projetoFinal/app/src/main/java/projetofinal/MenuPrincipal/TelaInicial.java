package projetofinal.MenuPrincipal;

import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.layout.*;
import projetofinal.Jogadores.*;
import projetofinal.Util.*;
import projetofinal.game.Main;
import projetofinal.saves.JogadorAuxiliar;
import projetofinal.saves.PontuacoesSalvas;
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
        Label l1J1 = new Label(String.valueOf(J1.getNome().charAt(0)));
        Label l2J1 = new Label(String.valueOf(J1.getNome().charAt(1)));
        Label l3J1 = new Label(String.valueOf(J1.getNome().charAt(2)));
        Label l1J2 = new Label(String.valueOf(J2.getNome().charAt(0)));
        Label l2J2 = new Label(String.valueOf(J2.getNome().charAt(1)));
        Label l3J2 = new Label(String.valueOf(J2.getNome().charAt(2)));

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

        /*
         * Definição das ações dos botões:
         * - Atualiza a letra/número exibido no label correspondente
         * - Atualiza o nome do jogador com base nas letras/números selecionados
         */
        l1J1Up.setOnAction(e -> {
            l1J1.setText(atualizarLetra(l1J1.getText(), true));
            J1.setNome(atualizarNomeJogador(l1J1.getText(), l2J1.getText(), l3J1.getText()));
        });
        l1J1Down.setOnAction(e -> {
            l1J1.setText(atualizarLetra(l1J1.getText(), false));
            J1.setNome(atualizarNomeJogador(l1J1.getText(), l2J1.getText(), l3J1.getText()));
        });
        l1J2Up.setOnAction(e -> {
            l1J2.setText(atualizarLetra(l1J2.getText(), true));
            J2.setNome(atualizarNomeJogador(l1J2.getText(), l2J2.getText(), l3J2.getText()));
        });
        l1J2Down.setOnAction(e -> {
            l1J2.setText(atualizarLetra(l1J2.getText(), false));
            J2.setNome(atualizarNomeJogador(l1J2.getText(), l2J2.getText(), l3J2.getText()));
        });
        l2J1Up.setOnAction(e -> {
            l2J1.setText(atualizarLetra(l2J1.getText(), true));
            J1.setNome(atualizarNomeJogador(l1J1.getText(), l2J1.getText(), l3J1.getText()));
        });
        l2J1Down.setOnAction(e -> {
            l2J1.setText(atualizarLetra(l2J1.getText(), false));
            J1.setNome(atualizarNomeJogador(l1J1.getText(), l2J1.getText(), l3J1.getText()));
        });
        l2J2Up.setOnAction(e -> {
            l2J2.setText(atualizarLetra(l2J2.getText(), true));
            J2.setNome(atualizarNomeJogador(l1J2.getText(), l2J2.getText(), l3J2.getText()));
        });
        l2J2Down.setOnAction(e -> {
            l2J2.setText(atualizarLetra(l2J2.getText(), false));
            J2.setNome(atualizarNomeJogador(l1J2.getText(), l2J2.getText(), l3J2.getText()));
        });
        l3J1Up.setOnAction(e -> {
            l3J1.setText(atualizarLetra(l3J1.getText(), true));
            J1.setNome(atualizarNomeJogador(l1J1.getText(), l2J1.getText(), l3J1.getText()));
        });
        l3J1Down.setOnAction(e -> {
            l3J1.setText(atualizarLetra(l3J1.getText(), false));
            J1.setNome(atualizarNomeJogador(l1J1.getText(), l2J1.getText(), l3J1.getText()));
        });
        l3J2Up.setOnAction(e -> {
            l3J2.setText(atualizarLetra(l3J2.getText(), true));
            J2.setNome(atualizarNomeJogador(l1J2.getText(), l2J2.getText(), l3J2.getText()));
        });
        l3J2Down.setOnAction(e -> {
            l3J2.setText(atualizarLetra(l3J2.getText(), false));
            J2.setNome(atualizarNomeJogador(l1J2.getText(), l2J2.getText(), l3J2.getText()));
        });

        // Botão para iniciar o jogo
        Button iniciarJogo = new Button("Iniciar Jogo");
        iniciarJogo.setOnAction(event -> {
            Main.executarJogo(primaryStage);
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

        // Inicializa o ranking de pontuação
        TableView<JogadorAuxiliar> rankingPontuacao = new TableView<>();
        rankingPontuacao.setMaxWidth(265);
        rankingPontuacao.setMaxHeight(280);
        PontuacoesSalvas pontuacoes = new PontuacoesSalvas();
        pontuacoes.carregarRank();

        // Colunas presentes na tabela de ranking
        TableColumn<JogadorAuxiliar, String> colNomeP = new TableColumn<>("Nome");
        TableColumn<JogadorAuxiliar, Integer> colPontuacaoP = new TableColumn<>("Pontuação");
        TableColumn<JogadorAuxiliar, Integer> colNivelP = new TableColumn<>("Nível");
        TableColumn<JogadorAuxiliar, Integer> colNLinhasP = new TableColumn<>("Nº de Linhas");

        // Configurando de onde a tabela pega os dados
        colNomeP.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colPontuacaoP.setCellValueFactory(new PropertyValueFactory<>("pontuacao"));
        colNivelP.setCellValueFactory(new PropertyValueFactory<>("nivel"));
        colNLinhasP.setCellValueFactory(new PropertyValueFactory<>("nLinhas"));

        // Adiciona as colunas na tabela
        rankingPontuacao.getColumns().add(colNomeP);
        rankingPontuacao.getColumns().add(colPontuacaoP);
        rankingPontuacao.getColumns().add(colNivelP);
        rankingPontuacao.getColumns().add(colNLinhasP);

        // Definindo o tamanho das colunas
        colNomeP.setMaxWidth(50);
        colPontuacaoP.setMaxWidth(80);
        colNivelP.setMaxWidth(50);
        colNLinhasP.setMaxWidth(100);

        ObservableList<JogadorAuxiliar> dados = FXCollections.observableArrayList(pontuacoes.getRankPontuacao());
        rankingPontuacao.setItems(dados);

        // Inicializa o ranking de vitórias
        TableView<JogadorAuxiliar> rankingVitorias = new TableView<>();
        rankingVitorias.setMaxWidth(350);
        rankingVitorias.setMaxHeight(280);

        // Colunas do ranking de vitórias
        TableColumn<JogadorAuxiliar, String> colNomeV = new TableColumn<>("Nome");
        TableColumn<JogadorAuxiliar, Integer> colVitoriasV = new TableColumn<>("Nº de Vitórias");
        TableColumn<JogadorAuxiliar, Integer> colPontuacaoV = new TableColumn<>("Pontuação");
        TableColumn<JogadorAuxiliar, Integer> colNivelV = new TableColumn<>("Nível");
        TableColumn<JogadorAuxiliar, Integer> colNLinhasV = new TableColumn<>("Nº de Linhas");

        // Configurando de onde as colunas pegam os dados
        colNomeV.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colVitoriasV.setCellValueFactory(new PropertyValueFactory<>("vitorias"));
        colPontuacaoV.setCellValueFactory(new PropertyValueFactory<>("pontuacao"));
        colNivelV.setCellValueFactory(new PropertyValueFactory<>("nivel"));
        colNLinhasV.setCellValueFactory(new PropertyValueFactory<>("nLinhas"));

        // Adiciona as colunas na segunda tabela
        rankingVitorias.getColumns().add(colNomeV);
        rankingVitorias.getColumns().add(colVitoriasV);
        rankingVitorias.getColumns().add(colPontuacaoV);
        rankingVitorias.getColumns().add(colNivelV);
        rankingVitorias.getColumns().add(colNLinhasV);

        dados = FXCollections.observableArrayList(pontuacoes.getRankVitorias());
        rankingVitorias.setItems(dados);

        StackPane layout = new StackPane();
        layout.getChildren().addAll(rankingPontuacao, rankingVitorias, iniciar);   

        StackPane.setAlignment(rankingPontuacao, Pos.TOP_LEFT);
        StackPane.setAlignment(rankingVitorias, Pos.TOP_RIGHT);

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
