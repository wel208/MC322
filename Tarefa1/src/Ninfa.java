public class Ninfa extends Monstro{
    
    //Construtor
    public Ninfa(String nome, int nivel, int pos){
        super(nome, nivel, pos);
        this.attackRange = 3;
        this.attackSpeed = (nivel <= 12) ? 1 : 2;
        this.dodgeChance = 0.05 + (0.02 * nivel);
        this.forca = 80 + (2 * nivel);
        this.moveSpeed = 2 + (nivel % 2);
        this.protecao = 0.4 + (0.02 * nivel);
        this.pontosDeVida = 100 + (2 * nivel - 2);
        this.xpConcedido = 50 + (2 * nivel);
    }

    //Métodos
    @Override
    public void ambientarMonstro(Heroi heroi){
        //Ambientação
        System.out.println("Ao caminhar, " + heroi.nome + " comeca a ouvir um choro distante."); Utilidades.esperar(2000);
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
        System.out.printf("\n%s, A NINFA, no nivel %d, possui:", nome, nivel); Utilidades.esperar(2000);
        System.out.printf("\n%d PONTOS DE VIDA;", pontosDeVida); Utilidades.esperar(2000);
        System.out.printf("\n%.0f PONTOS DE FORCA;", forca); Utilidades.esperar(2000);
        System.out.printf("\nE, ao morrer, concedera %d PONTOS DE EXPERIENCIA ao heroi.\n", xpConcedido); Utilidades.esperar(2000);

        System.out.printf("\n%s, depois de se afastar do monstro, pensa em como pode derrotar %s.\n", heroi.nome, nome); Utilidades.esperar(1500);
    }

    @Override
    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);

        System.out.printf("\nA NINFA esta a %d metros do nosso heroi e ira ", distancia);

        if (distancia <= attackRange){
            System.out.print("ATACAR!\n"); Utilidades.esperar(1500);
            atacar(alvo);
        }
        else{
            System.out.print("SE APROXIMAR!\n"); Utilidades.esperar(1500);
            mover(alvo);
        }
    }

    @Override
    public void atacar(Personagem alvo){
        int contador = 0;

        for (int i = 0; i < attackSpeed; i++){

            if (Math.random() > alvo.dodgeChance){         //Caso o goblin acerte o seu alvo
                contador++;

                if (Math.random() < criticalChance){       //Caso o ataque seja crítico
                    alvo.receberDano(forca * 1.2);
                    System.out.println("\nNAO! A ninfa ACERTOU um ATAQUE CRITICO no nosso heroi!"); Utilidades.esperar(1000);
                }
                else{                                      //Caso seja um ataque comum
                    alvo.receberDano(forca);
                    System.out.println("\nNAO! A ninfa ACERTOU um golpe no heroi!"); Utilidades.esperar(1000);
                }
            }
            else                                           //Caso o herói consiga desviar do ataque do goblin
                System.out.println("\nUFA! O heroi ESQUIVOU do ataque da ninfa!"); Utilidades.esperar(1000);
        }

        System.out.printf("\nA ninfa ACERTOU %d de %d ataque(s) dado(s)!\n", contador, attackSpeed); Utilidades.esperar(1500);
    }

    @Override
    public void mover(Personagem alvo){

        boolean chegou = false;

        if (pos < alvo.pos)
            for (int i = 0; i < moveSpeed; i ++){
                pos++;
                if (Utilidades.calcularDistancia(pos, alvo.pos) == attackRange){
                    System.out.println("A NINFA ALCANCOU O HEROI E IRA ATACAR!"); Utilidades.esperar(1500);
                    chegou = true;
                    atacar(alvo);
                    break;
                }
            }
        else
            for (int i = 0; i < moveSpeed; i ++){
                pos--;
                if (Utilidades.calcularDistancia(pos, alvo.pos) == attackRange){
                    System.out.println("A NINFA ALCANÇOU O HEROI E IRA ATACAR!"); Utilidades.esperar(1500);
                    chegou = true;
                    atacar(alvo);
                    break;
                }
            }
        
        if (!chegou){
            Utilidades.esperar(1500);
            System.out.println("\nA NINFA ainda nao alcançou o nosso heroi!");
            System.out.printf("Ela esta a %d metros de %s.\n", Utilidades.calcularDistancia(pos, alvo.pos), alvo.nome); Utilidades.esperar(1500);
        }
    }


}
