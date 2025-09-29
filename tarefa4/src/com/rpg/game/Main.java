package com.rpg.game;

import java.util.Random;

import com.rpg.cenario.*;
import com.rpg.combate.AcaoDeCombate;
import com.rpg.eventos.AjudaExterna;
import com.rpg.personagens.*;
import com.rpg.util.*;

import java.util.List;

public class Main {

    static Random r = new Random(); //Objeto para utilização da aletoriedade no jogo
    public static void main (String[] args){
        
        //Loop para exibição do menu principal do jogo
        while (true){
            int escolha = Utilidades.exibirMenuPrincipal();

            if (escolha == 1){
                break;
            }
            else if (escolha == 2){
                Utilidades.apresentarHerois();
            }
            else if (escolha == 3){
                Utilidades.apresentarArmas();
            }
            else if (escolha == 4){
                Utilidades.apresentarCenarios();
            }
            else if (escolha == 5){
                InputManager.fecharScanner();
                System.exit(0);
            }
        }

        //Escolha da Dificuldade do jogo
        Dificuldade dificuldade = Utilidades.escolherDificuldade();
        System.out.println("Dificuldade escolhida: " + dificuldade.getDificuldade());

        //Escolha da classe e nome do herói que será utilizado no jogo
        Heroi heroi = Utilidades.criarHeroi();
        System.out.printf("\nVoce se chama %s, %s, e ira enfrentar muitos monstros nesse jogo!\n", heroi.getNome(), Utilidades.verificarClasse(heroi)); Utilidades.esperar();
        heroi.exibirStatus();

        //Construção das fases do jogo, serão geradas entre 3 e 8 fases em um jogo
        ConstrutorDeCenario construtor = new ConstrutorDeCenario();
        List<Fase> fases = construtor.gerar(r.nextInt(3, 9), dificuldade);

        System.out.printf("\n--------------------------\n\nO nosso jogo tera %d fases. Boa sorte!\n\n--------------------------\n", fases.size()); Utilidades.esperar();

        //For que roda cada uma das fases do jogo
        for (int i = 1; i <= fases.size(); i++){
            System.out.println("\nINICIO DA " + i + "º FASE!\n\n--------------------------\n"); Utilidades.esperar();
            
            Fase f = fases.get(i - 1);

            f.iniciar(heroi);

            System.out.println("\n--------------------------\n"); Utilidades.esperar();

            int monstrosDerrotados = 0;

            //For que percorre todos os monstros presentes na fase 
            for (Monstro monstro : f.getMonstros()){
                if (!heroi.estaVivo())
                    break;

                //Define a posição do monstro com base na posição do herói
                monstro.setPos(Utilidades.escolherPosicao(monstro, heroi.getPos()));

                System.out.printf("%s, %s, aparece para uma batalha! Vamos la!\n", monstro.getNome(), Utilidades.verificarClasse(monstro)); Utilidades.esperar();
                monstro.exibirStatus();

                //For para cada um dos turnos de uma batalha
                for (int turno = 1; turno <= 100 && monstro.estaVivo(); turno++){
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

                        if (!heroi.estaVivo())
                            break;
                    }

                    else{
                        monstrosDerrotados++;
                        System.out.printf("\n%s, %s, foi derrotado!\n", monstro.getNome(), Utilidades.verificarClasse(monstro)); Utilidades.esperar();
                        System.out.println("\n--------------------------"); Utilidades.esperar();

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
                                    Utilidades.encerrarJogo();
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
                                    Utilidades.encerrarJogo();
                                }
                            }
                        }
                    }
                }
                if (!heroi.estaVivo())
                    break;
                if (!f.isConcluida()){
                    System.out.println("\n--------------------------\n"); Utilidades.esperar();
                }
            }

                if (!heroi.estaVivo())
                    break;
        }

        //Momento em que o jogo se encerra
        if (heroi.estaVivo())
            System.out.println("\nPARABENS! VOCE VENCEU TODAS AS FASES!");
        else
            System.out.println("\nO heroi morreu.\n\nGAME OVER!");

        InputManager.fecharScanner();
        System.exit(0);
    }
}
