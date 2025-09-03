/*
 * Monstro com aparência das árvores da floresta
 * Possui dois tipos de ataque, um curta e um para longa distância
 * Monstro razoavelmente forte
 * Possui bastante pontos de vida e protecao
 * Bons pontos de força
 * Não é tão rápido, chance meio baixa de se esquivar de um ataque
 */

public class Rangedor extends Monstro{
    
    //Construtor
    public Rangedor(String nome, int nivel, int pos){
        super(nome, nivel, pos);
        this.attackSpeed = 2;
        this.attackRange = 12;
        this.dodgeChance = 0.15;
        this.forca = 50 + (2 * (nivel - 2));
        this.moveSpeed = 3;
        this.protecao = 0.45 + (nivel/100);
        this.pontosDeVida = 80 + nivel;
        this.xpConcedido = 40 + 2 * nivel;
    }

    //Métodos
    @Override
    public void ambientarMonstro(Heroi heroi){
        //Ambientação
        System.out.println("Em seu caminho, " + heroi.nome + " percebe um ranger de arvores cada vez mais forte se aproximando."); Utilidades.esperar(2000);
        System.out.println("Rapidamente surge uma raiz de arvore ransgando o chao ate seus pes, mas, com velocidade, consegue se livrar de ser atingido."); Utilidades.esperar(2000);
        System.out.println("Ate que ele entende o que foi aquilo: um RANGEDOR!"); Utilidades.esperar(2000);
        System.out.println("Um monstro humanoide com aparencia das arvores da floresta, coberto de musgos e fungos, que faz um som como o ranger de arvores."); Utilidades.esperar(2000);
        System.out.println("Ele consegue atacar tanto de perto, com seus galhos, quanto de longe, com essa habilidade das raizes."); Utilidades.esperar(2000);
        
        System.out.print("\nCriatura forte, alcanca inimigos longe, mas causa menos dano do que em um combate corpo a corpo."); Utilidades.esperar(2000);
        System.out.printf("\n%s, O RANGEDOR, no nivel %d, possui:", nome, nivel); Utilidades.esperar(2000);
        System.out.printf("\n%d PONTOS DE VIDA;", pontosDeVida); Utilidades.esperar(2000);
        System.out.printf("\n%.0f PONTOS DE FORCA;", forca); Utilidades.esperar(2000);
        System.out.printf("\nE, ao morrer, concedera %d PONTOS DE EXPERIENCIA ao heroi\n", xpConcedido); Utilidades.esperar(2000);

        System.out.printf("\n%s, entao, com muita coragem, vai para a batalha!\n", heroi.nome); Utilidades.esperar(1500);
    }

    /*
     * Se estiver a uma distância <= 12, atacará
     * Se não, irá na direção do heroi
     */
    @Override
    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);

        System.out.printf("\nO rangedor esta a %d metros do heroi e ira ", distancia);

        if (distancia <= attackRange){
            System.out.println("ATACAR!\n"); Utilidades.esperar(1500);
            atacar(alvo);
        }
        else{
            System.out.println("SE APROXIMAR!\n"); Utilidades.esperar(1500);
            mover(alvo);
        }
    }

    /*
     * Caso esteja a uma distância <= 3 do alvo, usará o ataque de curta distância, que são dois golpes
     * Se não, usará o ataque de longa distância, que dá uma diminuição de 10% na sua força
     */
    @Override
    public void atacar(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);

        if (distancia <= 3){                            //Condição para ataque de curta distância
            System.out.println("O RANGEDOR dara DOIS ataques CORPO A CORPO!\n"); Utilidades.esperar(1500);

            for (int i = 0; i < 2; i++){                //for para os dois ataques ao herói
                Utilidades.esperar(1500);

                if (Math.random() > alvo.dodgeChance){  //Caso ele acerte o ataque
                    System.out.println("NAO! Ele ACERTOU um ataque no nosso heroi!"); Utilidades.esperar(1000);
                    alvo.receberDano(forca);
                }
                else                                    //Caso o herói esquive do ataque
                    System.out.printf("BOA! O heroi ESQUIVOU do ataque de %s, O RANGEDOR\n", nome); Utilidades.esperar(1000);
            }
        }
        else{                                           //Condição para ataque de longa distância
            System.out.println("O RANGEDOR dara UM ataque A DISTANCIA!\n"); Utilidades.esperar(500);
            System.out.printf("Ele entao finca seus bracos no chao, criando raizes que vao ate %s, o nosso %s!", alvo.nome, Utilidades.verificarClasse(alvo)); Utilidades.esperar(500);
            System.out.println("\nSuas raizes tentam ACERTAR o nosso heroi\n"); Utilidades.esperar(500);

            if (Math.random() > alvo.dodgeChance){      //Caso ele acerte o ataque
                System.out.println("NAO! Ele ACERTOU o ataque no nosso heroi!"); Utilidades.esperar(1500);
                alvo.receberDano(forca * 0.9);
            }
            else                                        //Caso o heroi esquive do ataque
                System.out.printf("BOA! O heroi ESQUIVOU do ataque de %s, o RANGEDOR\n", nome); Utilidades.esperar(1500);
        }
    }

    /*
     * Sempre se moverá em direção ao seu inimigo
     * Se chegar a uma distância de 11 metros (attackRange - 1) do herói, ele atacará
     * Se não, será mostrado a distância que ele chegou do herói
     */
    @Override
    public void mover(Personagem alvo){
        boolean chegou = false;

        if (pos < alvo.pos)
            for (int i = 0; i < moveSpeed; i++){
                pos++;
                if (Utilidades.calcularDistancia(pos, alvo.pos) == attackRange - 1){
                    chegou = true;
                    System.out.println("O RANGEDOR chegou a uma distância de 11 metros do heroi e ira ATACAR!"); Utilidades.esperar(1500);
                    atacar(alvo);
                }
            }
        else
            for (int i = 0; i < moveSpeed; i++){
                pos--;
                if (Utilidades.calcularDistancia(pos, alvo.pos) == attackRange - 1){
                    chegou = true;
                    Utilidades.esperar(1500);
                    System.out.println("O RANGEDOR chegou a uma distância de 11 metros do heroi e ira ATACAR!"); Utilidades.esperar(1500);
                    atacar(alvo);
                }
            }

        if (!chegou){
            System.out.println("O rangedor ainda nao alcancou o guerreiro. ");
            System.out.printf("Ele esta a %d metros do heroi.\n", Utilidades.calcularDistancia(pos, alvo.pos)); Utilidades.esperar(1500);
        }
    }
}
