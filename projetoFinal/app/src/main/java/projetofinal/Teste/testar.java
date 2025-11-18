package projetofinal.Teste;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class testar extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Dados das matrizes
        int[][] matrixA = {{1, 2}, {3, 4}};
        int[][] matrixB = {{5, 6}, {7, 8}};
        int[][] matrixC = {{9, 0}, {1, 2}};

        // Cria as visualizações das matrizes
        GridPane viewA = VisualizarMatriz.createMatrixView(matrixA, "Matriz A");
        GridPane viewB = VisualizarMatriz.createMatrixView(matrixB, "Matriz B");
        GridPane viewC = VisualizarMatriz.createMatrixView(matrixC, "Matriz C");

        // Organiza as visualizações em um layout horizontal (HBox)
        HBox hbox = new HBox(20); // Espaçamento de 20px entre as matrizes
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(viewA, viewB, viewC);
        
        // Coloca o HBox dentro de um VBox se quiser adicionar outros elementos ou 
        // centralizar melhor na cena (ou use diretamente como root)
        VBox root = new VBox(hbox);
        root.setAlignment(Pos.CENTER);
        
        // Define a cena
        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Múltiplas Matrizes JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

