package com.rpg.game;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.rpg.cenario.*;
import com.rpg.personagens.*;
import com.rpg.combate.*;
import com.rpg.eventos.AjudaExterna;
import com.rpg.save.GerenciadorDePersistencia;
import com.rpg.util.Utilidades;

@XmlRootElement(name = "batalha")
public class Batalha {

    //Atributos
    private String nomeBatalha;
    private List<Fase> listaDeFases;
    private Heroi heroi;

    public Batalha(){}

    //Construtor
    public Batalha(String nomeBatalha, List<Fase> listaDeFases, Heroi heroi){
        this.nomeBatalha = nomeBatalha;
        this.listaDeFases = listaDeFases;
        this.heroi = heroi;
    }

    //Métodos
    public Batalha carregarJogo(String nomeArquivo){
        return GerenciadorDePersistencia.carregarBatalha(nomeArquivo);
    }

    public void executarProxFase(){
        int nFase = 1;
        
        //For que percorre todas as fases do jogo
        for (Fase f : listaDeFases){
            if (!heroi.estaVivo())
                break;

            /*
             * Caso a fase já tenha sido concluída, pula para a próxima
             * Útil para quando o jogo é carregado de um jogo anterior
             */
            if (f.isConcluida()){
                nFase++;
                continue;
            }

            int monstrosDerrotados = 0;

            System.out.println("Estamos na " + nFase + "ª fase do jogo!");
            System.out.println("\n--------------------------\n"); Utilidades.esperar();

            //For que percorre todos os monstros presentes na fase 
            for (Monstro monstro : f.getMonstros()){
                if (!heroi.estaVivo())
                    break;

                /*
                 * Caso o monstro já tenha sido derrotado, pula para o próximo
                 * Útil para quando o jogo é carregado de um jogo anterior
                */
                if (monstro.getPontosDeVida() <= 0){
                    monstrosDerrotados++;
                    continue;
                }
                
                //Define a posição do monstro com base na posição do herói
                monstro.setPos(Utilidades.escolherPosicao(monstro, heroi.getPos()));

                System.out.printf("%s, %s, aparece para uma batalha! Vamos la!\n", monstro.getNome(), Utilidades.verificarClasse(monstro)); Utilidades.esperar();
                monstro.exibirStatus();

                for (int turno = 1; turno <= 100 && monstro.estaVivo(); turno++){
                    System.out.println("\n---INICIO DO" + Utilidades.verificarTurno(turno) + "TURNO---"); Utilidades.esperar();
                    turno++;

                    f.getEfeito().aplicarEfeito(heroi);

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

                        f.getEfeito().removerEfeito(heroi);
                        
                        if (!heroi.estaVivo())
                            break;
                    }

                    else{
                        monstrosDerrotados++;
                        System.out.printf("\n%s, %s, foi derrotado!\n", monstro.getNome(), Utilidades.verificarClasse(monstro)); Utilidades.esperar();
                        System.out.println("\n--------------------------"); Utilidades.esperar();

                        f.getEfeito().removerEfeito(heroi);
                        heroi.ganharExperiencia(monstro.getXpConcedido());

                        int caso;

                        //If-else que diz se o monstro irá dropar a arma ou não
                        if (Math.random() < 0.6 * monstro.getDificuldade().getMultiplicador()){
                            caso = 1;
                            System.out.printf("\n%s, %s, dropou %s, sua arma!\n", monstro.getNome(), Utilidades.verificarClasse(monstro), monstro.getArma().getNome()); Utilidades.esperar();
                        }
                        else{
                            caso = 2;
                            System.out.printf("\n%s, %s, NAO dropou sua arma!\n", monstro.getNome(), Utilidades.verificarClasse(monstro), monstro.getArma().getNome()); Utilidades.esperar();
                        }


                        boolean jogoSalvo = false;
                        //Loop para apresentação do menu pós-combate
                        while (true){
                            int escolha = Utilidades.exibirMenuPosBatalha(caso);
                            
                            if (caso == 1){
                                if (escolha == 1){
                                    break;
                                }
                                else if (escolha == 2){
                                    Utilidades.tentarEquiparArma(heroi, monstro.droparLoot());
                                }
                                else if(escolha == 3){
                                    Utilidades.compararArmas(heroi.getArma(), monstro.droparLoot());
                                }
                                else if (escolha == 4){
                                    heroi.exibirStatus();
                                }
                                else if (escolha == 5){
                                    Utilidades.apresentarInfoDaFase(f, monstrosDerrotados);
                                }
                                else if (escolha == 6){
                                    jogoSalvo = true;
                                    GerenciadorDePersistencia.salvarBatalha(this, nomeBatalha);
                                }
                                else if (escolha == 7){
                                    if (jogoSalvo){
                                        Utilidades.encerrarJogo();
                                    }
                                    else{
                                        if(Utilidades.confirmarDesistencia())
                                            Utilidades.encerrarJogo();
                                        else
                                            continue;
                                    }
                                }
                            }
                            else{
                                if (escolha == 1){
                                    break;
                                }
                                else if (escolha == 2){
                                    heroi.exibirStatus();
                                }
                                else if (escolha == 3){
                                    Utilidades.apresentarInfoDaFase(f, monstrosDerrotados);
                                }
                                else if (escolha == 4){
                                    GerenciadorDePersistencia.salvarBatalha(this, nomeBatalha);
                                }
                                else if (escolha == 5){
                                    if (jogoSalvo){
                                        Utilidades.encerrarJogo();
                                    }
                                    else{
                                        if(Utilidades.confirmarDesistencia())
                                            Utilidades.encerrarJogo();
                                        else
                                            continue;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            //Momento em que o jogo se encerra
            if (heroi.estaVivo())
                System.out.println("\nPARABENS! VOCE VENCEU TODAS AS FASES!");
            else
                System.out.println("\nO heroi morreu.\n\nGAME OVER!");
        }
    }
}
