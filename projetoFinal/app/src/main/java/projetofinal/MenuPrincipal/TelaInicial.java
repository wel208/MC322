package projetofinal.MenuPrincipal;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public class TelaInicial extends Application{

    int contador = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Teste de botões");

        Button BTaumentar = new Button("+");
        Button BTdiminuir = new Button("-");

        BTaumentar.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        BTdiminuir.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");

        Label valor = new Label(String.valueOf(contador));

        valor.setStyle("-fx-font: 22 arial; -fx-background-color: #40c81bff; -fx-background-radius: 5;");

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(BTaumentar, valor, BTdiminuir);

        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        Scene cena = new Scene(vbox, 400, 400);
        primaryStage.setScene(cena);
        primaryStage.show();

        BTaumentar.setOnAction(event -> {
            contador++;
            valor.setText(String.valueOf(contador));
        });

        BTdiminuir.setOnAction(event -> {
            contador--;
            valor.setText(String.valueOf(contador));
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
