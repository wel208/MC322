import java.util.ArrayList;

public class Fases implements Fase {

    //Atributos
    protected ArrayList<Monstro> Monstros;
    protected TipoCenario cenario;

    public Fases(ArrayList<Monstro> Monstros, TipoCenario cenario){
        this.Monstros = Monstros;
        this.cenario = cenario;
    }

    public void iniciar(Heroi heroi){

        cenario.descreverCenario();

        System.out.println("\n--------------------------\n"); Utilidades.esperar();

        for (; Monstros.size() > 0;){
            Monstro monstro = Monstros.remove(0);

            System.out.printf("%s, %s, aparece para uma batalha! Vamos la!\n", monstro.getNome(), Utilidades.verificarClasse(monstro)); Utilidades.esperar();
            monstro.exibirStatus();

            int turno = 1;

            while (heroi.estaVivo() && monstro.estaVivo()){
                System.out.println("\n---INICIO DO" + Utilidades.verificarTurno(turno) + "TURNO---"); Utilidades.esperar();
                turno++;

                AcaoDeCombate acao;

                acao = heroi.escolherAcao(monstro);
                acao.executar(heroi, monstro);

                if (monstro.estaVivo()){

                    AjudaExterna ajuda = new AjudaExterna();
                    if (ajuda.verificarGatilho(heroi, monstro))
                        ajuda.executar(monstro);

                    System.out.println("\n--------------------------"); Utilidades.esperar();

                    acao = monstro.escolherAcao(heroi);
                    acao.executar(monstro, heroi);
                }

                else{
                    System.out.printf("\n%s, %s, foi derrotado!\n", monstro.getNome(), Utilidades.verificarClasse(monstro)); Utilidades.esperar();
                    System.out.println("\n--------------------------"); Utilidades.esperar();

                    heroi.ganharExperiencia(monstro.getXpConcedido());

                    heroi.exibirStatus();

                    if (Math.random() < 0.7){
                        System.out.printf("\n%s, %s, dropou %s, sua arma!\n", monstro.getNome(), Utilidades.verificarClasse(monstro), monstro.getArma().getNome()); Utilidades.esperar();

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
                            System.out.printf("\n%s equipou '%s'\n", heroi.getNome(), heroi.getArma().getNome()); Utilidades.esperar();
                        }
                        else{
                            System.out.printf("\n%s nao ira equipar a arma largada pelo monstro.\n", heroi.getNome()); Utilidades.esperar(); 
                        }
                    }
                }
            }

            if (!isConcluida()){
                System.out.printf("\nAinda ha mais %d monstro(s) para derrotar!\n", Monstros.size()); Utilidades.esperar();
                System.out.println("\n--------------------------\n"); Utilidades.esperar();
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
