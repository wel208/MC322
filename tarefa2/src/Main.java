import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args){
        Random r = new Random();

        //Cria o herói
        Heroi heroi = Utilidades.criarHeroi();
        heroi.exibirStatus();

        // Se já começar morto
        if (heroi.getPontosDeVida() <= 0) {
            System.out.println("O herói morreu!");
            return;
        }

        // Gera um número entre 2 e 10
        int fases = r.nextInt((10 - 2) + 1) + 2;

        // Gera as fases e guarda a lista
        List<Fase> listaFases = ConstrutorDeCenario.gerarFases(fases);

        // Percorre cada fase
        int numeroFase = 1;
        for (Fase fase : listaFases) {
            System.out.println("\n--- Iniciando Fase " + numeroFase + " ---");
            fase.iniciar(heroi); // Inicio do combate

            //Para o jogo se o heroi morre
            if (heroi.getPontosDeVida() <= 0) {
                System.out.println("\nFim de jogo!");
                return;
            }

            // Exibe status parcial do herói antes da próxima fase
            heroi.statusParcial();

            numeroFase++;
        }

        // Se chegou até aqui, venceu todas as fases
        System.out.println("\nParabéns! Você Venceu!");
    }
}
