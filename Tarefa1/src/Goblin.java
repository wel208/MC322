public class Goblin extends Monstro{
    
    //Construtor
    public Goblin(String nome, int nivel, int pos){
        super(nome, nivel, pos);
        this.attackRange = 1;
        this.attackSpeed = 3;
        this.dodgeChance = 0.4 + 0.2 * nivel;
        this.forca = 20 + 2 * nivel;
        this.moveSpeed = 6 + nivel;
        this.protecao = 0.15 + 0.02 * (nivel - 1);
        this.pontosDeVida = 30 + 2 * nivel;
        this.xpConcedido = 30 + 3 * nivel;
    }

    @Override
    public void ambientarMonstro(){

    }

    @Override
    public void statusParcial(){
        System.out.printf("\n%s, O GOBLIN, está com %d PONTOS DE VIDA.", nome, pontosDeVida);
    }

    @Override
    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);

        System.out.printf("\nO GOBLIN está a %d metros do nosso herói e irá ", distancia);

        if (distancia <= attackRange){
            System.out.print("ATACÁ-LO!");
            atacar(alvo);
        }
        else{
            System.out.print("CORRER NA DIREÇÃO DELE!");
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
                    System.out.println("\nNÃO! O goblin ACERTOU um ATAQUE CRÍTICO no nosso herói!");
                }
                else{                                      //Caso seja um ataque comum
                    alvo.receberDano(forca);
                    System.out.println("\nNÃO! O goblin ACERTOU um golpe no herói!");
                }
            }
            else                                           //Caso o herói consiga desviar do ataque do goblin
                System.out.println("\nUFA! O herói ESQUIVOU do ataque do goblin!");
        }

        System.out.printf("\nO goblin acertou %d dos %d ataques dados!", contador, attackSpeed);
    }

    public void mover(Personagem alvo){

        boolean flag = false;

        if (pos < alvo.pos)
            while (pos < alvo.pos){
                pos++;
                if (Utilidades.calcularDistancia(pos, alvo.pos) == attackRange){
                    System.out.println("O GOBLIN ALCANÇOU O HERÓI E IRÁ ATACAR!");
                    flag = true;
                    atacar(alvo);
                    break;
                }
            }
        else
            while (pos > alvo.pos){
                pos--;
                if (Utilidades.calcularDistancia(pos, alvo.pos) == attackRange){
                    System.out.println("O GOBLIN ALCANÇOU O HERÓI E IRÁ ATACAR!");
                    flag = true;
                    atacar(alvo);
                    break;
                }
            }
        
        if (!flag){
            System.out.println("O monstro ainda não alcançou o nosso herói. ");
            System.out.printf("Ele chegou a %d metros de distância.", Utilidades.calcularDistancia(pos, alvo.pos));
        }
    }

}
