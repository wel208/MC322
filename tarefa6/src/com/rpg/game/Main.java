package com.rpg.game;

import java.util.Random;
import com.rpg.cenario.*;
import com.rpg.personagens.*;
import com.rpg.util.*;
import java.util.List;

public class Main {

    static Random r = new Random(); //Objeto para utilização da aletoriedade no jogo
    public static void main (String[] args){

        boolean jogoSalvo = Utilidades.haJogoSalvo();
        Batalha batalha = null;
        boolean carregouJogo = false;

        int escolha;
        //Loop para exibição do menu principal do jogo
        while (true){
            escolha = Utilidades.exibirMenuPrincipal();

            if (escolha == 1){
                break;
            }
            else if (escolha == 2){
                if (jogoSalvo){
                    batalha = Batalha.carregarJogo("save1");
                    carregouJogo = true;
                    break;
                }
                else{
                    System.out.println("\nNao ha nenhum jogo salvo."); Utilidades.esperar();
                }
            }
            else if (escolha == 3){
                Utilidades.apresentarHerois();
            }
            else if (escolha == 4){
                Utilidades.apresentarArmas();
            }
            else if (escolha == 5){
                Utilidades.apresentarCenarios();
            }
            else if (escolha == 6){
                InputManager.fecharScanner();
                System.exit(0);
            }
        }

        if (!carregouJogo){
            //Escolha da Dificuldade do jogo
            Dificuldade dificuldade = Utilidades.escolherDificuldade();
            System.out.println("Dificuldade escolhida: " + dificuldade.getDificuldade());

            //Escolha da classe e nome do herói que será utilizado no jogo
            Heroi heroi = Utilidades.criarHeroi();
            System.out.printf("\nVoce se chama %s, %s, e ira enfrentar muitos monstros nesse jogo!\n", heroi.getNome(), Utilidades.verificarClasse(heroi)); Utilidades.esperar();
            heroi.exibirStatus();

            //Construção das fases do jogo, serão geradas entre 3 e 5 fases em um jogo
            ConstrutorDeCenario construtor = new ConstrutorDeCenario();
            List<Fase> fases = construtor.gerar(r.nextInt(3, 6), dificuldade);

            batalha = new Batalha("save1", fases, heroi);
        }

        batalha.executarProxFase();

        InputManager.fecharScanner();
        System.exit(0);
    }
}
