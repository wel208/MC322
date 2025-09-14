public class Zumbi extends Monstro {

    // Construtor
    public Zumbi(String nome, int nivelDificuldade, int pos, Arma arma) {
    super(nome, nivelDificuldade, pos, arma);
    this.pontosDeVidaMax = 50 + (nivelDificuldade * 10);
    this.pontosDeVida = this.pontosDeVidaMax;
    this.protecao = 0.2 + (nivelDificuldade * 0.03);
    this.forca = 15 + (nivelDificuldade * 4);
    this.moveSpeed = 3 + nivelDificuldade;
    this.xpConcedido = 30 + (nivelDificuldade * 10);
    this.sorte = 0.2 + (nivelDificuldade * 0.01);
    this.dodgeChance = 0.05 + (nivelDificuldade * 0.01);
}

    @Override
    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);

        System.out.printf("\nO ZUMBI está a %d metro(s) do herói e irá ", distancia);

        if (distancia <= arma.getAttackRange()){
            System.out.println("ATACAR!\n"); Utilidades.esperar(200);
            atacar(alvo);
        }
        else{
            System.out.println("SE APROXIMAR!\n"); Utilidades.esperar(200);
            mover(alvo);
        }
    }

    @Override
    public void atacar(Personagem alvo){
        int contador = 0;

        for (int i = 0; i < arma.getAttackSpeed(); i++){

            if (Math.random() > alvo.getDodgeChance()){
                contador++;

                boolean critico = Math.random() < criticalChance;
                double multiplicador = critico ? 1.2 : 1.1;

                alvo.receberDano(forca * arma.getDano() * multiplicador);

                if (critico){
                    System.out.println("O Zumbi desfere um golpe CRÍTICO com suas garras!");
                } 
                else{
                    System.out.println("O Zumbi acerta um golpe pesado no herói!");
                }
                Utilidades.esperar(200);
            } 
            else{
                System.out.println("O herói esquiva do ataque lento do Zumbi!"); Utilidades.esperar(200);
            }
        }
        System.out.printf("\nO Zumbi acertou %d de %d ataques!\n", contador, arma.getAttackSpeed()); Utilidades.esperar(200);
    }

    @Override
    public void mover(Personagem alvo){
        boolean chegou = false;
        int direcao = pos < alvo.getPos() ? 1 : -1;

        for (int i = 0; i < moveSpeed; i++){
            pos += direcao;
            if (Utilidades.calcularDistancia(pos, alvo.pos) == arma.attackRange){
                System.out.println("O zumbi ALCANCOU o heroi e vai ATACAR");
                chegou = true;
                break;
            }
        }

        if (!chegou)
            System.out.printf("O Zumbi se arrasta e agora está a %d metro(s) do herói!\n", Utilidades.calcularDistancia(pos, alvo.pos)); Utilidades.esperar(200);
    }
}
