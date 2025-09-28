package com.rpg.combate;

import com.rpg.util.*;

public class AtaqueComum implements AcaoDeCombate{
    
    public void executar(Combatente usuario, Combatente alvo){
        int contador = 0;
        double dano = 0;
        int qtdAtque = usuario.getArma().getAttackSpeed();

        if ("Corpo a Corpo".equals(usuario.getArma().getTipo())){

            for (int i = 0; i < qtdAtque; i ++){

                if (Math.random() > alvo.getDodgeChance()){
                    contador++;

                    boolean critico = Math.random() < usuario.getCriticalChance();
                    double multiplicador = critico ? 1.2 : 1.0;

                    dano += alvo.receberDano(usuario.getForca() * usuario.getArma().getDano() * multiplicador);

                    if (critico){
                        System.out.printf("%s, %s, ACERTOU um ataque CRITICO em %s!\n", usuario.getNome(), Utilidades.verificarClasse(usuario), alvo.getNome());
                    }
                    else{
                        System.out.printf("%s, %s, ACERTOU um ataque em %s!\n", usuario.getNome(), Utilidades.verificarClasse(usuario), alvo.getNome());
                    }
                    Utilidades.esperar();
                }
                else{
                    System.out.printf("%s ESQUIVOU do ataque de %s!\n", Utilidades.verificarClasse(alvo), usuario.getNome()); Utilidades.esperar();
                }
            }
        }

        else{
            
            for (int i = 0; i < qtdAtque; i++){

                double chanceAcerto = Math.min(0.95, usuario.getAtributoUnico() + usuario.getSorte());

                if (Math.random() < chanceAcerto){

                    if (Math.random() > alvo.getDodgeChance()){
                        contador++;

                        double distancia = (double)Utilidades.calcularDistancia(usuario.getPos(), usuario.getPos());

                        boolean critico = Math.random() < usuario.getCriticalChance();
                        double multiplicador = critico ? 1.2 : 1.0;

                        dano += alvo.receberDano(usuario.getForca() * usuario.getArma().getDano() * multiplicador * (distancia/3.0));

                        if (critico){
                            System.out.printf("%s, %s, ACERTOU um ataque CRITICO em %s!\n", usuario.getNome(), Utilidades.verificarClasse(usuario), alvo.getNome());
                        }
                        else{
                            System.out.printf("%s, %s, ACERTOU um ataque em %s!\n", usuario.getNome(), Utilidades.verificarClasse(usuario), alvo.getNome());
                        }
                            Utilidades.esperar();
                    }
                    else{
                        System.out.printf("%s ESQUIVOU do ataque de %s!\n", alvo.getNome(), usuario.getNome()); Utilidades.esperar();
                    }
                }
                else{
                    System.out.printf("%s, %s, ERROU seu alvo!\n", usuario.getNome(), Utilidades.verificarClasse(usuario));
                }
            }
        }

        System.out.printf("%s, %s, acertou %d de %d ataque(s) dados, causando %.0f de dano.\n", usuario.getNome(), Utilidades.verificarClasse(usuario), contador, qtdAtque, dano); Utilidades.esperar();
        alvo.statusParcial();
    }
}
