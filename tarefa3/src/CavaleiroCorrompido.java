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
    public void ambientarMonstro(Heroi heroi){
        System.out.println("\nO som de metal arranhando ecoa pela estrada..."); Utilidades.esperar(200);
        System.out.println("Das sombras, surge uma figura imponente, coberta por uma armadura negra e enferrujada."); Utilidades.esperar(200);
        System.out.println("É " + nome + ", outrora um cavaleiro nobre, agora corrompido pelas trevas."); Utilidades.esperar(200);
        System.out.println("Seus olhos brilham em um tom vermelho intenso, e sua espada parece sedenta por sangue."); Utilidades.esperar(200);

        System.out.printf("\n%s, o CAVALEIRO CORROMPIDO, no nível %d de dificuldade, possui:", nome, nivelDificuldade); Utilidades.esperar(200);
        System.out.printf("\n%d PONTOS DE VIDA;", pontosDeVida); Utilidades.esperar(200);
        System.out.printf("\n%.0f PONTOS DE FORÇA;", forca); Utilidades.esperar(200);
        System.out.printf("\nE, ao morrer, concederá %d PONTOS DE EXPERIÊNCIA ao herói.\n", xpConcedido); Utilidades.esperar(200);

        System.out.println("\nO herói sente o peso da presença desse inimigo e se prepara para a batalha."); Utilidades.esperar(200);
    }

    @Override
    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.getPos());

        System.out.printf("\nO cavaleiro está a %d metro(s) do herói e irá ", distancia);

        if (distancia <= arma.getAttackRange()){
            System.out.println("ATACAR!\n"); Utilidades.esperar(200);
            atacar(alvo);
        }
        else{
            System.out.println("AVANÇAR!\n"); Utilidades.esperar(200);
            mover(alvo);
        }
    }

    @Override
    public void atacar(Personagem alvo){
        int contador = 0;

        for (int i = 0; i < arma.getAttackSpeed(); i++){
            if (Math.random() > alvo.getDodgeChance()){
                contador++;
                double dano = forca * arma.getDano();

                if (Math.random() < this.getCriticalChance()){
                    dano *= 1.5;
                    System.out.println("NÃO! O cavaleiro desfere um GOLPE CRÍTICO com sua espada maldita!"); Utilidades.esperar(200);
                }

                alvo.receberDano(dano);
                System.out.println("NÃO! O cavaleiro ATINGE o herói com sua espada!"); Utilidades.esperar(200);

            } 
            else
                System.out.println("BOA! O herói esquiva do golpe pesado do cavaleiro!"); Utilidades.esperar(200);
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
            System.out.printf("O cavaleiro se aproxima e agora está a %d metro(s) do herói!\n", Utilidades.calcularDistancia(pos, alvo.getPos())); Utilidades.esperar(200);
    }
}
