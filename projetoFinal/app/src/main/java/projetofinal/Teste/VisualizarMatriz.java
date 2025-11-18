package projetofinal.Teste;

import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class VisualizarMatriz {

    public static GridPane createMatrixView(int[][] matrix, String title) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(0, 0, 0, 0));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setAlignment(Pos.CENTER);
        
        // Adiciona um título acima da matriz
        Label titleLabel = new Label(title);
        gridPane.add(titleLabel, 0, 0, matrix[0].length, 1); // Span across columns

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                Label cellLabel = new Label(String.valueOf(matrix[i][j]));
                cellLabel.setMinSize(30, 30);
                cellLabel.setAlignment(Pos.CENTER);
                cellLabel.setStyle("-fx-border-color: black; -fx-padding: 5px;");
                
                // Adiciona o Label ao GridPane na posição correta (offset pela linha do título)
                gridPane.add(cellLabel, j, i + 1); 
            }
        }
        return gridPane;
    }
}
