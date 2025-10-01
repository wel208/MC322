package com.test.combate;

import com.test.personagens.*;
import com.test.util.*;

public class HabilidadeEspecial implements AcaoDeCombate {

    public void executar(Combatente usuario, Combatente alvo){
        int contador = 0;
        double dano = 0;
        int qtdAtque = (usuario instanceof Lutador) ? usuario.getArma().getAttackSpeed() + (int)usuario.getAtributoUnico() : usuario.getArma().getAttackSpeed() + 2;

        //lógica da habilidade especial do Lutador
        if (usuario instanceof Lutador){

            System.out.printf("RHHAAAA!! O NOSSO HEROI ENTROU EM FURIA E ATACARA %d VEZES NESTE TURNO!\n\n", qtdAtque); Utilidades.esperar();

            for(int i = 0; i < qtdAtque; i++){
                System.out.print("Hah! "); Utilidades.esperar();
                if (Math.random() > alvo.getDodgeChance() && Math.random() < usuario.getSorte() * 0.9){   //Chance maior do inimigo esquivar do ataque do lutador
                    dano += alvo.receberDano(usuario.getForca() * usuario.getArma().getDano() * 1.1);             //Dano 10% maior que o normal
                    contador++;
                }
            }
        }

        //Lógica da habilidade especial do Atirador
        else{
            double distancia = Utilidades.calcularDistancia(usuario.getPos(), alvo.getPos());

            System.out.printf("%s ATIRARA %d PROJETEIS NESTE TURNO!\n\n", usuario.getNome(), qtdAtque);

            for (int i = 0; i < qtdAtque; i++){
                System.out.print("Uhf! ");
                if (Math.random() > alvo.getDodgeChance() && Math.random() < Math.min(0.9, (usuario.getAtributoUnico() + usuario.getSorte()) * 0.9)){
                    contador++;
                    dano += alvo.receberDano(usuario.getForca() * usuario.getArma().getDano() * distancia/6);
                }
            }
        }

        System.out.printf("\n\nO nosso heroi acertou %d ataque(s), causando %.0f de dano!\n", contador, dano); Utilidades.esperar();
        alvo.statusParcial();
    }
}
