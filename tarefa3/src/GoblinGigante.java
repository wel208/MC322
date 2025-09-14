public class GoblinGigante extends Monstro {

    // Construtor
    public GoblinGigante(String nome, int nivelDificuldade, int pos, Arma arma){
        super(nome, nivelDificuldade, pos, arma);
        this.pontosDeVidaMax = 80 + (nivelDificuldade - 1) * 20;
        this.pontosDeVida = this.pontosDeVidaMax;
        this.protecao = 0.4 + (nivelDificuldade - 1) * 0.05;
        this.forca = 12 + (nivelDificuldade - 1) * 4;
        this.moveSpeed = 4;
        this.xpConcedido = 25 + (nivelDificuldade * 15);
        this.sorte = 0.1 + (nivelDificuldade * 0.01);
    }

    @Override
    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);

        System.out.printf("\nO GOBLIN GIGANTE está a %d metro(s) do herói e irá ", distancia);

        if (distancia <= arma.getAttackRange()){
            System.out.println("ATACAR!\n"); Utilidades.esperar(200);
            atacar(alvo);
        } else {
            System.out.println("AVANÇAR!\n"); Utilidades.esperar(200);
            mover(alvo);
        }
    }

    @Override
    public void atacar(Personagem alvo){
        int contador = 0;

        for (int i = 0; i < arma.getAttackRange(); i++){

            if (Math.random() > alvo.dodgeChance){
                contador++;

                boolean critico = Math.random() < criticalChance;
                double multiplicador = critico ? 1.2 : 1.0;

                alvo.receberDano(forca * arma.getDano() * multiplicador);
                if (critico){
                    System.out.println("O Goblin Gigante acerta um GOLPE CRÍTICO devastador!");
                } 
                else{
                    System.out.println("O Goblin Gigante acerta um golpe pesado no herói!");
                }
                Utilidades.esperar(200);
            } 
            else{
                System.out.println("O herói esquiva do ataque pesado do Goblin Gigante!"); Utilidades.esperar(200);
            }
        }
        System.out.printf("\nO Goblin Gigante acertou %d de %d ataques!\n", contador, arma.getAttackSpeed()); Utilidades.esperar(200);
    }

    @Override
    public void mover(Personagem alvo){
        boolean chegou = false;
        int direcao = pos < alvo.getPos() ? 1 : -1;

        for (int i = 0; i < moveSpeed; i++){
            pos += direcao;

            if (Utilidades.calcularDistancia(pos, alvo.pos) == arma.getAttackSpeed()){
                chegou = true;
                System.out.println("O Goblin Gigante alcançou o herói e irá atacar!");
                atacar(alvo);
                break;
            }
        }

        if (!chegou)
            System.out.printf("O Goblin Gigante se aproxima e agora está a %d metro(s) do herói!\n", Utilidades.calcularDistancia(pos, alvo.pos)); Utilidades.esperar(200);
    }
}
