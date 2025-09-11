import java.util.Random;

public class Main {
    public static void main(String[] args){
        Random r = new Random();

        boolean heroiMorto = false;

        Heroi heroi = Utilidades.criarHeroi();
        heroi.exibirStatus();

        ConstrutorDeCenario.gerarFases(r.nextInt(2, 10));
    }
}
