package com.rpg.game;

import com.rpg.util.*;
import com.rpg.cenario.*;
import com.rpg.personagens.*;

import java.util.Random;
import java.util.List;

public class Main {

    static Random r = new Random();
    public static void main (String[] args){

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

        Dificuldade dificuldade = Utilidades.escolherDificuldade();

        System.out.println("Dificuldade escolhida: " + dificuldade.getDificuldade());

        Heroi heroi = Utilidades.criarHeroi();

        System.out.printf("\nVoce se chama %s, %s, e ira enfrentar muitos monstros nesse jogo!\n", heroi.getNome(), Utilidades.verificarClasse(heroi)); Utilidades.esperar();

        heroi.exibirStatus();

        ConstrutorDeCenario construtor = new ConstrutorDeCenario();
        List<Fase> fases = construtor.gerar(r.nextInt(3, 9), dificuldade);

        System.out.printf("\n--------------------------\n\nO nosso jogo tera %d fases. Boa sorte!\n\n--------------------------\n", fases.size()); Utilidades.esperar();

        for (int i = 1; i <= fases.size(); i++){
            System.out.println("\nINICIO DA " + i + "º FASE!\n\n--------------------------\n"); Utilidades.esperar();
            
            Fase f = fases.get(i - 1);

            f.iniciar(heroi);
            if (!heroi.estaVivo())
                break;
        }

        if (heroi.estaVivo())
            System.out.println("\nPARABENS! VOCE VENCEU TODAS AS FASES!");
        else
            System.out.println("\nO heroi morreu.\n\nGAME OVER!");

        InputManager.fecharScanner();
        System.exit(0);
    }
}
