import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args){
        Random r = new Random();

        //Cria o herói
        Heroi heroi = Utilidades.criarHeroi();
        heroi.exibirStatus();

        // Gera um número aleatório de fases entre 2 e 9
        int fases = r.nextInt(2, 10);

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
