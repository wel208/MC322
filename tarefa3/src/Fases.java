import java.util.ArrayList;

public class Fases implements Fase {

    //Atributos
    protected ArrayList<Monstro> Monstros;
    protected TipoCenario cenario;

    public Fases(ArrayList<Monstro> Monstros, TipoCenario cenario){
        this.Monstros = Monstros;
        this.cenario = cenario;
    }

    public void iniciar(Heroi heroi, int nivel){

        for (Monstro monstro : Monstros){
            System.out.println("\n=============================\n");
            System.out.printf("%s, %s, aparece para uma batalha! Vamos la!\n", monstro.getNome(), Utilidades.verificarClasse(monstro));

            while (heroi.estaVivo() && monstro.estaVivo()){
                AcaoDeCombate acao;

                acao = heroi.escolherAcao(monstro);
                acao.executar(heroi, monstro);

                if (monstro.estaVivo()){

                    AjudaExterna ajuda = new AjudaExterna();
                    if (ajuda.verificarGatilho(heroi, monstro))
                        ajuda.executar(monstro);

                    acao = monstro.escolherAcao(heroi);
                    acao.executar(monstro, heroi);
                }

                else{
                    System.out.printf("%s, %s, foi derrotado!", monstro.getNome(), Utilidades.verificarClasse(monstro)); Utilidades.esperar();

                    heroi.ganharExperiencia(monstro.getXpConcedido());

                    if (Math.random() < 0.7){
                        System.out.printf("%s, %s, dropou %s, sua arma!\n", monstro.getNome(), Utilidades.verificarClasse(monstro), monstro.getArma().getNome()); Utilidades.esperar();

                        boolean equipar = false;

                        if (heroi.getNivel() >= monstro.getArma().getMinNivel() && 
                            heroi.getArma().getDano() < monstro.getArma().getDano() && 
                            heroi.getArma().getTipo().equals(monstro.getArma().getTipo()) &&
                            heroi.getArma().getNome() != monstro.getArma().getNome()){
                                equipar = true;
                        }

                        if (equipar){
                            System.out.printf("\n%s ira equipar a arma largada pelo monstro!\n", heroi.getNome()); Utilidades.esperar();
                            heroi.setArma(monstro.droparLoot());
                            System.out.printf("\n%s equipou '%s'", heroi.getNome(), heroi.getArma().getNome()); Utilidades.esperar();
                        }
                        else{
                            System.out.printf("\n%s nao ira equipar a arma largada pelo monstro.\n", heroi.getNome()); Utilidades.esperar();
                        }
                    }
                    Monstros.remove(monstro);
                }
            }

            if (!isConcluida()){
                System.out.printf("\nAinda ha mais %d monstros para derrotar!\n", Monstros.size());
            }
        }
    }

    public String getTipoDeCenario(){
        return cenario.getDescricao();
    }

    public boolean isConcluida(){
        return Monstros.size() == 0;
    }
}
