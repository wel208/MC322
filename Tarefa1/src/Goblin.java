public class Goblin extends Monstro{
    
    //Construtor
    public Goblin(String nome, int nivel, int pos){
        super(nome, nivel, pos);
        this.attackRange = 1;
        this.attackSpeed = 3;
        this.dodgeChance = 0.4 + (0.01 * nivel);
        this.forca = 20 + nivel;
        this.moveSpeed = 5 + nivel;
        this.protecao = 0.15 + (0.02 * (nivel - 1));
        this.pontosDeVida = 25 + nivel;
        this.xpConcedido = 30 + (2 * nivel);
    }

    @Override
    public void ambientarMonstro(Heroi heroi){
        //Ambientação
        System.out.println("Depois de um bom tempo em seu percurso, " + heroi.nome + " comeca a ouvir alguns grunidos vindos de dentro da neblina"); Utilidades.esperar(2000);
        System.out.println("Ele logo entende o que aquilo significa: GOBLIN!"); Utilidades.esperar(2000);
        System.out.println("Uma verde criatura humanoide estranha. Um monstro baixo e rapido."); Utilidades.esperar(2000);
        System.out.println(nome + " entao aparece de tras de uma arvore, um goblin muito conhecido pelo reino de Lumenholt por suas frequentes aparicoes."); Utilidades.esperar(2000);

        //Apresentação de atributos
        System.out.print("\nNao muito forte, mas bem rapido, ataca apenas corpo a corpo"); Utilidades.esperar(2000);
        System.out.printf("\n%s, O GOBLIN, no nivel %d, possui:", nome, nivel); Utilidades.esperar(2000);
        System.out.printf("\n%d PONTOS DE VIDA;", pontosDeVida); Utilidades.esperar(2000);
        System.out.printf("\n%.0f PONTOS DE FORCA;", forca); Utilidades.esperar(2000);
        System.out.printf("\nE, ao morrer, concedera %d PONTOS DE EXPERIENCIA ao heroi\n", xpConcedido); Utilidades.esperar(2000);

        System.out.printf("\n%s, entao, se prepara para derrotar %s!\n", heroi.nome, nome); Utilidades.esperar(1500);
    }

    @Override
    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);

        System.out.printf("\nO GOBLIN esta a %d metros do nosso heroi e ira ", distancia);

        if (distancia <= attackRange){
            System.out.println("ATACA-LO!\n"); Utilidades.esperar(1500);
            atacar(alvo);
        }
        else{
            System.out.println("CORRER NA DIRECAO DELE!\n"); Utilidades.esperar(1500);
            mover(alvo);
        }
    }

    public void atacar(Personagem alvo){
        int contador = 0;
        for (int i = 0; i < attackSpeed; i++){

            if (Math.random() > alvo.dodgeChance){         //Caso o goblin acerte o seu alvo
                contador++;

                if (Math.random() < criticalChance){       //Caso o ataque seja crítico
                    alvo.receberDano(forca * 1.3);
                    System.out.println("NAO! O goblin ACERTOU um ATAQUE CRITICO no nosso heroi!"); Utilidades.esperar(1500);
                }
                else{                                      //Caso seja um ataque comum
                    alvo.receberDano(forca);
                    System.out.println("NAO! O goblin ACERTOU um golpe no heroi!"); Utilidades.esperar(1500);
                }
            }
            else                                           //Caso o herói consiga desviar do ataque do goblin
                System.out.println("UFA! O heroi ESQUIVOU do ataque do goblin!"); Utilidades.esperar(1500);
        }

        System.out.printf("O goblin acertou %d dos %d ataques dados!", contador, attackSpeed); Utilidades.esperar(1500);
    }

    public void mover(Personagem alvo){

        boolean chegou = false;

        if (pos < alvo.pos)
            for (int i = 0; i < moveSpeed; i ++){
                pos++;
                if (Utilidades.calcularDistancia(pos, alvo.pos) == attackRange){
                    System.out.println("O GOBLIN ALCANCOU O HEROI E IRA ATACAR!"); Utilidades.esperar(1500);
                    chegou = true;
                    atacar(alvo);
                    break;
                }
            }
        else
            for (int i = 0; i < moveSpeed; i ++){
                pos--;
                if (Utilidades.calcularDistancia(pos, alvo.pos) == attackRange){
                    System.out.println("O GOBLIN ALCANCOU O HEROI E IRA ATACAR!"); Utilidades.esperar(1500);
                    chegou = true;
                    atacar(alvo);
                    break;
                }
            }
        
        if (!chegou){
            Utilidades.esperar(1500);
            System.out.println("O goblin ainda não alcançou o nosso herói. ");
            System.out.printf("Ele esta a %d metros de distância.", Utilidades.calcularDistancia(pos, alvo.pos)); Utilidades.esperar(1500);
        }
    }

}
