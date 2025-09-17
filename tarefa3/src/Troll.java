public class Troll extends Monstro {

    // Construtor
    public Troll(String nome, int nivel, int pos, Arma arma){
        super(nome, nivel, pos, arma);
        this.pontosDeVidaMax = 80 + (nivel - 1) * 30;
        this.pontosDeVida = this.pontosDeVidaMax;
        this.protecao = 0.5 + (nivel - 1) * 0.04;
        this.forca = 15 + (nivel - 1) * 3;
        this.moveSpeed = 4;
        this.xpConcedido = 50 + (nivel * 20);
        this.sorte = 0.3 + (nivel * 0.01);
    }

    @Override
    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);

        System.out.printf("\nO TROLL está a %d metro(s) do herói e irá ", distancia);

        if (distancia <= arma.getAttackRange()){
            System.out.println("ATACAR!\n"); Utilidades.esperar();
            atacar(alvo);
        } 
        else{
            System.out.println("SE APROXIMAR!\n"); Utilidades.esperar();
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
                double multiplicador = critico ? 1.2 : 1.0;

                alvo.receberDano(forca * arma.getDano() * multiplicador);

                if (Math.random() < criticalChance){
                    System.out.println("O Troll acerta um GOLPE CRÍTICO com sua clava!");
                } 
                else{
                    System.out.println("O Troll acerta um golpe pesado no herói!");
                }
                Utilidades.esperar();
            } 
            else{
                System.out.println("O herói esquiva do ataque do Troll!"); Utilidades.esperar();
            }
        }
        System.out.printf("\nO Troll acertou %d de %d ataques!\n", contador, arma.getAttackSpeed()); Utilidades.esperar();
    }

    @Override
    public void mover(Personagem alvo){
        boolean chegou = false;
        int direcao = pos < alvo.getPos() ? 1 : -1;

        for (int i = 0; i < moveSpeed; i++){
            pos += direcao;
            if (Utilidades.calcularDistancia(pos, alvo.pos) == arma.getAttackRange()){
                System.out.println("O troll ALCANCOU o heroi e vai ATACAR");
                atacar(alvo);
                chegou = true;
                break;
            }
        }

        if (!chegou){
            System.out.printf("O Troll se aproxima e agora está a %d metro(s) do herói!\n", Utilidades.calcularDistancia(pos, alvo.pos)); Utilidades.esperar();
        }
    }
}
