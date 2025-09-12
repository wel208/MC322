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

        System.out.println("A fase possui " + monstros.size() + " monstros!");

        for (Monstro monstro : monstros) {
            heroi.exibirStatus();
            
            System.out.println("\nUm " + monstro.getClass().getSimpleName() + " equipado com " + monstro.arma.nome + " aparece!");

            // Loop de combate
            for (int i = 1; i < 60 && heroi.getPontosDeVida() > 0 && monstro.getPontosDeVida() > 0; i++) {
                System.out.println("\n---" + Utilidades.verificarTurno(i) + "TURNO ---");

                // Processa status antes das ações
                heroi.processarStatus();
                monstro.processarStatus();

                // Turno do herói
                if (heroi.getPontosDeVida() > 0) {
                    executarAcaoComStatus(heroi, monstro, rng);
                    monstro.statusParcial();
                    System.out.println("\n----------------------------------------");
                }

                // Turno do monstro
                if (monstro.getPontosDeVida() > 0) {
                    executarAcaoComStatus(monstro, heroi, rng);
                    heroi.statusParcial();
                }
            }

            // Resultado do combate
            if (heroi.getPontosDeVida() <= 0) {
                System.out.println("\nO herói foi derrotado!");
                return; // encerra a fase e o jogo
            } else {
                System.out.println("\nO monstro foi derrotado!");
                heroi.ganharExperiencia(monstro.xpConcedido);

                if(monstro.largaArma()) {
                    //Drop da arma
                    System.out.println(monstro.getNome() + " deixou sua arma: " + monstro.arma.getNome());
                    //Equipa automaticamente
                    if (heroi instanceof Lutador){
                        if (monstro.arma.getTipo() != "Corpo a Corpo"){
                            System.out.println("\n" + heroi.getNome() + " não pode equipar essa arma!\n");
                        }
                        else{
                            if (heroi.arma.minNivel > monstro.arma.minNivel && heroi.getNivel() < monstro.arma.minNivel){
                                System.out.println(heroi.getNome() + " soltou " + heroi.arma.getNome() + " e equipou " + monstro.arma.getNome() + "!");
                                heroi.setArma(monstro.arma);
                            }
                        }
                    }
                    else{
                        if (monstro.arma.getTipo() != "Longo Alcance"){
                            System.out.println(heroi.getNome() + " não pode equipar essa arma!\n");
                        }
                        else{
                            if (heroi.arma.minNivel > monstro.arma.minNivel && heroi.getNivel() < monstro.arma.minNivel){
                                System.out.println(heroi.getNome() + " soltou " + heroi.arma.getNome() + " e equipou " + monstro.arma.getNome() + "!\n");
                                heroi.setArma(monstro.arma);
                            }
                        }
                    }
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
