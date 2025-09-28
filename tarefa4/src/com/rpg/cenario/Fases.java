package com.rpg.cenario;

import java.util.List;

import com.rpg.combate.*;
import com.rpg.eventos.*;
import com.rpg.personagens.*;
import com.rpg.util.*;

public class Fases implements Fase {

    //Atributos
    protected List<Monstro> Monstros;
    protected TipoCenario cenario;

    public Fases(List<Monstro> Monstros, TipoCenario cenario){
        this.Monstros = Monstros;
        this.cenario = cenario;
    }

    //Método que executa cada uma das fases do jogo 
    public void iniciar(Heroi heroi){

        cenario.descreverCenario();

        System.out.println("\n--------------------------\n"); Utilidades.esperar();

        int monstrosDerrotados = 0;

        //For que percorre todos os monstros presentes na fase 
        for (Monstro monstro : Monstros){
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
                                Utilidades.apresentarInfoDaFase(this, monstrosDerrotados);
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
                                Utilidades.apresentarInfoDaFase(this, monstrosDerrotados);
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
            if (!isConcluida()){
                System.out.println("\n--------------------------\n"); Utilidades.esperar();
            }
        }
    }

    public String getTipoDeCenario(){
        return cenario.getDescricao();
    }

    public List<Monstro> getMonstros(){
        return Monstros;
    }

    public boolean isConcluida(){
        boolean monstroVivo = false;
        for (Monstro monstro : Monstros){
            if (monstro.getPontosDeVida() > 0){
                monstroVivo = true;
                break;
            }
        }
        return !monstroVivo;
    }
}
