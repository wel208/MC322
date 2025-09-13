public class Goblin extends Monstro{
    
    public Goblin(String nome, int nivelDificuldade, int pos, Arma arma){
        super(nome, nivelDificuldade, pos, arma);
        this.pontosDeVidaMax = 40 + (nivelDificuldade - 1) * 8;
        this.pontosDeVida = this.pontosDeVidaMax;
        this.protecao = 0.2 + (nivelDificuldade - 1) * 0.02;
        this.forca = 25 + (nivelDificuldade - 1) * 2;
        this.moveSpeed = 7;
        this.xpConcedido = 10 + (nivelDificuldade * 8);
        this.sorte = 0.25 + (nivelDificuldade * 0.01);
        this.dodgeChance = 0.1 + (nivelDificuldade * 0.01);
    }

    @Override
    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.getPos());

        System.out.printf("\nO GOBLIN esta a %d metros do nosso heroi e ira ", distancia);

        if (distancia <= arma.getAttackRange()){
            System.out.println("ATACA-LO!\n"); Utilidades.esperar(200);
            atacar(alvo);
        }
        else{
            System.out.println("CORRER NA DIRECAO DELE!\n"); Utilidades.esperar(200);
            mover(alvo);
        }
    }

    public void atacar(Personagem alvo){
        int contador = 0;

        for (int i = 0; i < arma.getAttackSpeed(); i++){

            if (Math.random() > alvo.getDodgeChance()){
                contador++;

                boolean critico = Math.random() < criticalChance;
                double multiplicador = critico ? 1.2 : 1.0;

                alvo.receberDano(forca * arma.getDano() * multiplicador);

                if (critico){
                    System.out.println("NAO! O goblin ACERTOU um ATAQUE CRITICO no nosso heroi!");
                }
                else{
                    System.out.println("NAO! O goblin ACERTOU um golpe no heroi!");
                }
                Utilidades.esperar(200);
            }
            else{
                System.out.println("UFA! O heroi ESQUIVOU do ataque do goblin!"); Utilidades.esperar(200);
            }
        }

        System.out.printf("\nO goblin acertou %d dos %d ataques dados!\n", contador, arma.attackSpeed); Utilidades.esperar(200);
    }

    public void mover(Personagem alvo){
        boolean chegou = false;
        int direcao = pos < alvo.getPos() ? 1 : -1;

        for (int i = 0; i < moveSpeed; i++){
            pos += direcao;
            if (Utilidades.calcularDistancia(pos, alvo.getPos()) == arma.getAttackRange()){
                System.out.println("O GOBLIN ALCANCOU O HEROI E IRA ATACAR!\n"); Utilidades.esperar(200);
                chegou = true;
                atacar(alvo);
                break;
            }
        }
        
        if (!chegou){
            System.out.println("O goblin ainda nao alcancou o nosso heroi. "); Utilidades.esperar(200);
            System.out.printf("Ele esta a %d metros de distância.", Utilidades.calcularDistancia(pos, alvo.pos)); Utilidades.esperar(200);
        }
    }
}
