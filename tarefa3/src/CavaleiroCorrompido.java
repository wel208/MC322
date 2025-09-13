public class CavaleiroCorrompido extends Monstro {

   public CavaleiroCorrompido(String nome, int nivelDificuldade, int pos, Arma arma){
        super(nome, nivelDificuldade, pos, arma);

        this.pontosDeVidaMax = 80 + (nivelDificuldade * 15);
        this.pontosDeVida = this.pontosDeVidaMax;
        this.protecao = 0.2 + (nivelDificuldade * 0.03);
        this.forca = 12 + (nivelDificuldade * 3);
        this.sorte = 0.2 + (nivelDificuldade * 0.01);
        this.moveSpeed = 5;
        this.xpConcedido = 20 + (nivelDificuldade * 10);
}

    @Override
    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.getPos());

        System.out.printf("\nO cavaleiro esta a %d metro(s) do heroi e ira ", distancia);

        if (distancia <= arma.getAttackRange()){
            System.out.println("ATACAR!\n"); Utilidades.esperar(200);
            atacar(alvo);
        }
        else{
            System.out.println("AVANCAR!\n"); Utilidades.esperar(200);
            mover(alvo);
        }
    }

    @Override
    public void atacar(Personagem alvo){
        int contador = 0;

        for (int i = 0; i < arma.getAttackSpeed(); i++){

            if (Math.random() > alvo.getDodgeChance()){
                contador++;

                boolean critico = Math.random() < this.getCriticalChance();
                double multiplicador = critico ? 1.2 : 1.0;

                alvo.receberDano(forca * arma.getDano() * multiplicador);

                if (critico){
                    System.out.println("NAO! O cavaleiro desfere um GOLPE CRITICO com sua espada maldita!");
                }
                else{
                    System.out.println("NAO! O cavaleiro ATINGE o heroi com sua espada!");
                }
                Utilidades.esperar(200);
            } 
            else
                System.out.println("BOA! O heroi esquiva do golpe pesado do cavaleiro!"); Utilidades.esperar(200);
        }

        System.out.printf("\nO cavaleiro corrompido acertou %d de %d golpes!\n", contador, arma.getAttackSpeed()); Utilidades.esperar(200);
    }

    @Override
    public void mover(Personagem alvo){
        boolean chegou = false;

        for (int i = 0; i < moveSpeed; i++){
            pos += (pos < alvo.pos) ? 1 : -1;
            if (Utilidades.calcularDistancia(pos, alvo.pos) <= arma.getAttackRange()){
                System.out.println("O cavaleiro corrompido ALCANCOU o heroi E IRA ATACAR!\n"); Utilidades.esperar(200);
                chegou = true;
                atacar(alvo);
                break;
            }
        }

        if (!chegou)
            System.out.printf("O cavaleiro se aproxima e agora esta a %d metro(s) do heroi!\n", Utilidades.calcularDistancia(pos, alvo.getPos())); Utilidades.esperar(200);
    }
}
