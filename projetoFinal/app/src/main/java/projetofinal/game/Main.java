package projetofinal.game;
import javafx.application.Application;
import javafx.stage.Stage;
import projetofinal.MenuPrincipal.TelaInicial;
import javafx.scene.Scene;
import java.awt.Toolkit;
import java.awt.Dimension;

public class Main extends Application {

    private static Game jogo;

    @Override
    public void start(Stage primaryStage) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        primaryStage.setWidth(screenSize.getWidth());
        primaryStage.setHeight(screenSize.getHeight());
        primaryStage.setResizable(false);

        jogo = new Game();
        
        executarTelaInicial(primaryStage);
    }

    public static void executarTelaInicial(Stage primaryStage) {
        Scene telaInicial = TelaInicial.telaInicial(primaryStage);

        telaInicial.setOnKeyPressed(event -> {
            if (event.getCode() != null){
                executarMenuPrincipal(primaryStage);
            }
        });

        primaryStage.setScene(telaInicial);
        primaryStage.show();
    }

    public static void executarMenuPrincipal(Stage primaryStage) {
        Scene menuPrincipal = TelaInicial.menuPrincipal(primaryStage, jogo.getPlayer1(), jogo.getPlayer2());
        primaryStage.setScene(menuPrincipal);
    }

    public static void executarJogo(Stage primaryStage) {
        Scene telaJogo = TelaGame.telaJogo(jogo, primaryStage);
        primaryStage.setScene(telaJogo);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
