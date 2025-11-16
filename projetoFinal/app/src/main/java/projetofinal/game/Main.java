package projetofinal.game;
import javafx.application.Application;
import javafx.stage.Stage;
import projetofinal.Jogadores.Jogador;
import projetofinal.MenuPrincipal.TelaInicial;
import javafx.scene.Scene;
import java.awt.Toolkit;
import java.awt.Dimension;

public class Main extends Application {

    private static Jogador jogador1;
    private static Jogador jogador2;

    @Override
    public void start(Stage primaryStage) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        primaryStage.setWidth(screenSize.getWidth());
        primaryStage.setHeight(screenSize.getHeight());
        primaryStage.setResizable(false);

        jogador1 = new Jogador("AAA");
        jogador2 = new Jogador("AAA");

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
        Scene menuPrincipal = TelaInicial.menuPrincipal(primaryStage, jogador1, jogador2);
        primaryStage.setScene(menuPrincipal);
    }

    public static void executarJogo() {
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
