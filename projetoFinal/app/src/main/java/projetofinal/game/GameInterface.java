package projetofinal.game;
import javafx.scene.input.KeyCode;
public interface GameInterface {
    void pausar();
    void reiniciar();
    void atualizar(KeyCode tecla);
    int[] getPontos();
}
