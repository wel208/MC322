import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fase {
    
    // Atributos
    private final List<Monstro> monstros = new ArrayList<>(); // Monstros presentes na fase
    private String ambiente;
    private int nivel;

    // Construtor
    public Fase(String ambiente, int nivel){
        this.ambiente = ambiente;
        this.nivel = nivel;
    }

    // Getters
    public int getNivel(){  
        return nivel;
    }
    public String getAmbiente(){
        return ambiente;
    }
    public List<Monstro> getMonstros(){
        return monstros;
    }

    // Adiciona um monstro à fase
    protected void adicionarMonstro(Monstro monstro){
        monstros.add(monstro);
    }

    // Executa a fase
    public void iniciar(Heroi heroi) {
        System.out.printf("\n=== Fase %d: %s ===\n", nivel, ambiente);
        Random rng = new Random();

        for (Monstro monstro : monstros) {
            System.out.println("\nUm " + monstro.getClass().getSimpleName() + " aparece!");

            // Loop de combate
            while (heroi.getPontosDeVida() > 0 && monstro.getPontosDeVida() > 0) {

                // Processa status antes das ações
                heroi.processarStatus();
                monstro.processarStatus();

                // Turno do herói
                if (heroi.getPontosDeVida() > 0) {
                    executarAcaoComStatus(heroi, monstro, rng);
                }

                // Turno do monstro
                if (monstro.getPontosDeVida() > 0) {
                    executarAcaoComStatus(monstro, heroi, rng);
                }
            }

            // Resultado do combate
            if (heroi.getPontosDeVida() <= 0) {
                System.out.println("\nO herói foi derrotado!");
                return; // encerra a fase e o jogo
            } else {
                System.out.println("\nO monstro foi derrotado!");

                if(monstro.largaArma() && monstro.arma != null) {
                    //Drop da arma
                    System.out.println(monstro.getNome() + " ???, Acho que caiu uma arma: " + monstro.arma.getNome());
                    //Equipa automaticamente
                    heroi.setArma(monstro.arma);
                    System.out.println(heroi.getNome() + " equipado " + monstro.arma.getNome() + "!");
                }
                }
            }
        System.out.println("\nFase concluída!");
    }

    // Aplica lógica de atordoamento e cegueira antes de permitir a ação
    private void executarAcaoComStatus(Personagem atacante, Personagem alvo, Random rng) {
        if (atacante.isAtordoadoTurno()) {
            System.out.println(atacante.getNome() + " está atordoado e perde a vez!");
            return;
        }

        if (atacante.isCego()) {
            // 50% de chance de errar completamente por cegueira
            if (rng.nextDouble() < 0.5) {
                System.out.println(atacante.getNome() + " está cego e erra sua ação!");
                return;
            }
        }

        // Se passou pelos checks, pode agir
        atacante.tomarDecisao(alvo);
    }
}
