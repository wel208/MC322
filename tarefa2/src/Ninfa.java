/*
 * Mulher que se transforma num enorme e forte monstro
 * Muitos pontos de vida e boa proteção
 * Consegue atacar a uma distância <= 3 metros
 * Não é rápida, pouca chance de esquivar de um ataque
 */

public class Ninfa extends Monstro{
    
    //Construtor
    public Ninfa(String nome, int nivel, int pos, Arma arma){
        super(nome, nivel, pos, arma);
    }

    //Métodos
    @Override
    public void ambientarMonstro(Heroi heroi){
        //Ambientação
        System.out.println("\nAo caminhar, " + heroi.nome + " comeca a ouvir um choro distante."); Utilidades.esperar(2000);
        System.out.println("Seria a princesa?"); Utilidades.esperar(2000);
        System.out.println("Ele vai correndo ate a origem do choro!"); Utilidades.esperar(2000);
        System.out.println("Era, sim, uma mulher! Mas nao era a princesa... Ele segue ate a donzela para entender o que esta acontecendo."); Utilidades.esperar(2000);
        System.out.println("Ele entao toca em seu ombro e, quando a mulher se vira, ele percebe que nao devia ter baixado tanto a guarda."); Utilidades.esperar(2000);
        System.out.println("O seu rosto monstruoso nao escondia, se tratava de uma NINFA DA FLORESTA."); Utilidades.esperar(2000);
        System.out.println("Uma criatura sombria que protegia a floresta de Umbraeth atraindo homens e, entao, os matando."); Utilidades.esperar(2000);
        System.out.println("Ela faz isso se transformando em uma enorme aberracao."); Utilidades.esperar(2000);
        System.out.println("Enquanto ela se transforma, " + heroi.nome + " se afasta."); Utilidades.esperar(2000);

        //Apresentação de atributos
        System.out.print("\nA ninfa e um ser muito forte, mas lento, seu ataque pode alcancar ate 3 metros de distancia."); Utilidades.esperar(2000);
        System.out.printf("\n%s, A NINFA, no nivel %d de dificuldade, possui:", nome, nivelDificuldade); Utilidades.esperar(2000);
        System.out.printf("\n%d PONTOS DE VIDA;", pontosDeVida); Utilidades.esperar(2000);
        System.out.printf("\n%.0f PONTOS DE FORCA;", forca); Utilidades.esperar(2000);
        System.out.printf("\nE, ao morrer, concedera %d PONTOS DE EXPERIENCIA ao heroi.\n", xpConcedido); Utilidades.esperar(2000);

        System.out.printf("\n%s, depois de se afastar do monstro, pensa em como pode derrotar %s.\n", heroi.nome, nome); Utilidades.esperar(1500);
    }

    /*
     * Se estiver a uma distância <= 3, irá atacar
     * Se não, irá na direção do heroi
     */
    @Override
    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);

        System.out.printf("\nA NINFA esta a %d metros do nosso heroi e ira ", distancia);

        if (distancia <= arma.attackRange){
            System.out.print("ATACAR!\n"); Utilidades.esperar(1500);
            atacar(alvo);
        }
        else{
            System.out.print("SE APROXIMAR!\n"); Utilidades.esperar(1500);
            mover(alvo);
        }
    }

    /*
     * Dará 'attackSpeed' ataques no guerreiro
     * Seu ataque crítico dá um aumento de 10% em sua força
     */
    @Override
    public void atacar(Personagem alvo){
        int contador = 0;

        for (int i = 0; i < arma.attackSpeed; i++){             //for para dar todos os ataques da rodada

            if (Math.random() > alvo.sorte){         //Caso a ninfa acerte o seu alvo
                contador++;

                if (Math.random() < criticalChance){       //Caso o ataque seja crítico
                    alvo.receberDano(forca * 1.1);
                    System.out.println("\nNAO! A ninfa ACERTOU um ATAQUE CRITICO no nosso heroi!"); Utilidades.esperar(1000);
                }
                else{                                      //Caso seja um ataque comum
                    alvo.receberDano(forca);
                    System.out.println("\nNAO! A ninfa ACERTOU um golpe no heroi!"); Utilidades.esperar(1000);
                }
            }
            else                                           //Caso o herói consiga desviar do ataque da ninfa
                System.out.println("\nUFA! O heroi ESQUIVOU do ataque da ninfa!"); Utilidades.esperar(1000);
        }

        System.out.printf("\nA ninfa ACERTOU %d de %d ataque(s) dado(s)!\n", contador, attackSpeed); Utilidades.esperar(1500);
    }

    /*
     * Sempre se moverá na direção do inimigo
     * Caso chegue a uma distância == 'attackRange', atacará
     * Se não, mostrará qual a distância até o guerreiro
     */
    @Override
    public void mover(Personagem alvo){

        boolean chegou = false;

        if (pos < alvo.pos)
            for (int i = 0; i < moveSpeed; i ++){
                pos++;
                if (Utilidades.calcularDistancia(pos, alvo.pos) == arma.attackRange){
                    System.out.println("A NINFA ALCANCOU O HEROI E IRA ATACAR!"); Utilidades.esperar(1500);
                    chegou = true;
                    atacar(alvo);
                    break;
                }
            }
        else
            for (int i = 0; i < moveSpeed; i ++){
                pos--;
                if (Utilidades.calcularDistancia(pos, alvo.pos) == arma.attackRange){
                    System.out.println("A NINFA ALCANÇOU O HEROI E IRA ATACAR!"); Utilidades.esperar(1500);
                    chegou = true;
                    atacar(alvo);
                    break;
                }
            }
        
        if (!chegou){
            Utilidades.esperar(1500);
            System.out.println("\nA NINFA ainda nao alcancou o nosso heroi!");
            System.out.printf("Ela esta a %d metros de %s.\n", Utilidades.calcularDistancia(pos, alvo.pos), alvo.nome); Utilidades.esperar(1500);
        }
    }


}
