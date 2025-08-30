public class Sucubo extends Monstro{
    
    //Construtor
    public Sucubo(String nome, int nivel, int pos){
        super(nome, nivel, pos);
        this.attackRange = 3;
        this.attackSpeed = (nivel <= 10) ? 1 : 2;
        this.dodgeChance = 0.05 + 0.02 * nivel;
        this.forca = 80 + 2 * nivel;
        this.moveSpeed = 2 + (nivel - 1);
        this.protecao = 40 + 2 * nivel;
        this.pontosDeVida = 100 + 2 * nivel;
        this.xpConcedido = 50 + 2 * nivel;
    }

    //Métodos
    @Override
    public void ambientarMonstro(){

    }
    
    @Override
    public void statusParcial(){
        System.out.printf("\n%s, O SUCUBO, está com %d PONTOS DE VIDA.", nome, pontosDeVida);
    }

    @Override
    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);

        System.out.printf("\nO sucubo está a %d metros do nosso herói e irá ", distancia);

        if (distancia <= attackRange){
            System.out.print("ATACAR!");
            atacar(alvo);
        }
        else{
            System.out.print("SE APROXIMAR!");
            mover(alvo);
        }
    }

    @Override
    public void atacar(Personagem alvo){
        int contador = 0;

        for (int i = 0; i < attackSpeed; i++){
            Utilidades.esperar(500);

            if (Math.random() > alvo.dodgeChance){         //Caso o goblin acerte o seu alvo
                contador++;

                if (Math.random() < criticalChance){       //Caso o ataque seja crítico
                    alvo.receberDano(forca * 1.2);
                    System.out.println("\nNÃO! O sucubo ACERTOU um ATAQUE CRÍTICO no nosso herói!");
                }
                else{                                      //Caso seja um ataque comum
                    alvo.receberDano(forca);
                    System.out.println("\nNÃO! O sucubo ACERTOU um golpe no herói!");
                }
            }
            else                                           //Caso o herói consiga desviar do ataque do goblin
                System.out.println("\nUFA! O sucubo ESQUIVOU do ataque do goblin!");
        }

        Utilidades.esperar(300);
        System.out.printf("\nO sucubo acertou %d dos %d ataques dados!", contador, attackSpeed);
    }

    @Override
    public void mover(Personagem alvo){

        boolean flag = false;

        if (pos < alvo.pos)
            while (pos < alvo.pos){
                pos++;
                if (Utilidades.calcularDistancia(pos, alvo.pos) == attackRange){
                    System.out.println("O SUCUBO ALCANÇOU O HERÓI E IRÁ ATACAR!");
                    flag = true;
                    atacar(alvo);
                    break;
                }
            }
        else
            while (pos > alvo.pos){
                pos--;
                if (Utilidades.calcularDistancia(pos, alvo.pos) == attackRange){
                    System.out.println("O SUCUBO ALCANÇOU O HERÓI E IRÁ ATACAR!");
                    flag = true;
                    atacar(alvo);
                    break;
                }
            }
        
        if (!flag){
            System.out.println("O sucubo ainda não alcançou o nosso herói! ");
            System.out.printf("Ele chegou a %d metros do %.", Utilidades.calcularDistancia(pos, alvo.pos), Utilidades.verificarClasse(alvo));
        }
    }


}
