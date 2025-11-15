package projetofinal.MenuPrincipal;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.geometry.*;
import javafx.scene.layout.*;
import projetofinal.Util.*;
import java.awt.Toolkit;
import java.awt.Dimension;

public class TelaInicial extends Application {

    // Tela inicial que aguarda o usuário pressionar qualquer tecla para continuar
    @Override
    public void start(Stage primaryStage) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        primaryStage.setWidth(screenSize.getWidth());
        primaryStage.setHeight(screenSize.getHeight());
        primaryStage.setResizable(false);

        StackPane layout = new StackPane();
        Label textoInicial = new Label("Aperte qualquer tecla para continuar");

        layout.getChildren().add(textoInicial);
        layout.setAlignment(Pos.CENTER);

        Scene cenaInicial = new Scene(layout);

        cenaInicial.setOnKeyPressed(event -> {
            if (event.getCode() != null){
                primaryStage.setScene(Menu(primaryStage));
                primaryStage.show();
            }
        });

        primaryStage.setScene(cenaInicial);
        primaryStage.show();
    }

    /*
     * Menu para:
     * - Escolha do nome dos jogadores
     * - Visualização do ranking de pontuação e vitórias dos jogadores
     * - Iniciar o jogo
     */
    private Scene Menu(Stage primaryStage) {

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

        // Organização dos elementos na tela
        VBox vBoxl1 = new VBox(10, l1J1Up, l1J1, l1J1Down, l1J2Up, l1J2, l1J2Down);
        VBox vBoxl2 = new VBox(10, l2J1Up, l2J1, l2J1Down, l2J2Up, l2J2, l2J2Down);
        VBox vBoxl3 = new VBox(10, l3J1Up, l3J1, l3J1Down, l3J2Up, l3J2, l3J2Down);
        vBoxl1.setAlignment(Pos.CENTER);
        vBoxl2.setAlignment(Pos.CENTER); 
        vBoxl3.setAlignment(Pos.CENTER);
        HBox hBoxNomes = new HBox(10, vBoxl1, vBoxl2, vBoxl3);
        hBoxNomes.setAlignment(Pos.CENTER);

        // Definição das ações dos botões
        l1J1Up.setOnAction(e -> {
            l1J1.setText(atualizarLetra(l1J1.getText(), true));
        });
        l1J1Down.setOnAction(e -> {
            l1J1.setText(atualizarLetra(l1J1.getText(), false));
        });
        l1J2Up.setOnAction(e -> {
            l1J2.setText(atualizarLetra(l1J2.getText(), true));
        });
        l1J2Down.setOnAction(e -> {
            l1J2.setText(atualizarLetra(l1J2.getText(), false));
        });
        l2J1Up.setOnAction(e -> {
            l2J1.setText(atualizarLetra(l2J1.getText(), true));
        });
        l2J1Down.setOnAction(e -> {
            l2J1.setText(atualizarLetra(l2J1.getText(), false));
        });
        l2J2Up.setOnAction(e -> {
            l2J2.setText(atualizarLetra(l2J2.getText(), true));
        });
        l2J2Down.setOnAction(e -> {
            l2J2.setText(atualizarLetra(l2J2.getText(), false));
        });
        l3J1Up.setOnAction(e -> {
            l3J1.setText(atualizarLetra(l3J1.getText(), true));
        });
        l3J1Down.setOnAction(e -> {
            l3J1.setText(atualizarLetra(l3J1.getText(), false));
        });
        l3J2Up.setOnAction(e -> {
            l3J2.setText(atualizarLetra(l3J2.getText(), true));
        });
        l3J2Down.setOnAction(e -> {
            l3J2.setText(atualizarLetra(l3J2.getText(), false));
        });

        return new Scene(hBoxNomes);
    }

    // Método que avança ou retrocede a letra ou o número atual na lista de letras e números
    private String atualizarLetra(String letraAtual, boolean incrementar) {
        int index = Utilidades.letrasENumeros.indexOf(letraAtual);
        if (incrementar){
            index = (index + 1) % Utilidades.letrasENumeros.size();
        } 
        else{
            index = (index - 1 + Utilidades.letrasENumeros.size()) % Utilidades.letrasENumeros.size();
        }
        return Utilidades.letrasENumeros.get(index);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
