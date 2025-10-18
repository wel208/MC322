package com.rpg.combate;
import com.rpg.itens.TipoDeArma;
import com.rpg.util.*;
import javax.xml.bind.annotation.*;
@XmlRootElement(name = "ataqueComum")
@XmlAccessorType(XmlAccessType.FIELD)
public class AtaqueComum implements AcaoDeCombate{
    
    public AtaqueComum(){}

    public void executar(Combatente usuario, Combatente alvo){
        int contador = 0;
        double dano = 0;
        int qtdAtque = usuario.getArma().getAttackSpeed();

        //Lógica de funcionamento de um ataque utilizando uma arma corpo a corpo
        if (usuario.getArma().getTipo().equals(TipoDeArma.CURTO_ALCANCE)){

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

        //Lógica de funcionamento de um ataque utilizando uma arma de longo alcance
        else{
            
            for (int i = 0; i < qtdAtque; i++){

                double chanceAcerto = Math.min(0.95, usuario.getAtributoUnico() + usuario.getSorte());

                if (Math.random() < chanceAcerto){

                    if (Math.random() > alvo.getDodgeChance()){
                        contador++;

                        double distancia = Utilidades.calcularDistancia(usuario.getPos(), alvo.getPos());

                        boolean critico = Math.random() < usuario.getCriticalChance();
                        double multiplicador = critico ? 1.2 : 1.0;

                        dano += alvo.receberDano(usuario.getForca() * usuario.getArma().getDano() * multiplicador * distancia/7);

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
