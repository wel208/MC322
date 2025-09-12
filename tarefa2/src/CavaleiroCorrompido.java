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
        System.out.println("\nO som de metal arranhando ecoa pela estrada..."); Utilidades.esperar(2000);
        System.out.println("Das sombras, surge uma figura imponente, coberta por uma armadura negra e enferrujada."); Utilidades.esperar(2000);
        System.out.println("É " + nome + ", outrora um cavaleiro nobre, agora corrompido pelas trevas."); Utilidades.esperar(2000);
        System.out.println("Seus olhos brilham em um tom vermelho intenso, e sua espada parece sedenta por sangue."); Utilidades.esperar(2000);

        System.out.printf("\n%s, o CAVALEIRO CORROMPIDO, no nível %d de dificuldade, possui:", nome, nivelDificuldade); Utilidades.esperar(2000);
        System.out.printf("\n%d PONTOS DE VIDA;", pontosDeVida); Utilidades.esperar(2000);
        System.out.printf("\n%.0f PONTOS DE FORÇA;", forca); Utilidades.esperar(2000);
        System.out.printf("\nE, ao morrer, concederá %d PONTOS DE EXPERIÊNCIA ao herói.\n", xpConcedido); Utilidades.esperar(2000);

        System.out.println("\nO herói sente o peso da presença desse inimigo e se prepara para a batalha."); Utilidades.esperar(1500);
    }

    @Override
    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.getPos());
        double chance = Math.random();

        System.out.printf("\nO cavaleiro está a %d metro(s) do herói e irá ", distancia);

        if (chance < 0.1 && distancia <= arma.getAttackRange()) {
            System.out.println("USAR GOLPE PESADO!\n"); Utilidades.esperar(1500);
            golpePesado(alvo);
            return;
        }
        else if (chance < 0.2 && distancia > arma.getAttackRange() && distancia <= 8) {
            System.out.println("USAR INVESTIDA SOMBRIA!\n"); Utilidades.esperar(1500);
            investidaSombria(alvo);
            return;
        }

        if (distancia <= arma.getAttackRange()){
            System.out.println("ATACAR!\n"); Utilidades.esperar(1500);
            atacar(alvo);
        }
        else{
            System.out.println("AVANÇAR!\n"); Utilidades.esperar(1500);
            mover(alvo);
        }
    }

    @Override
    public void atacar(Personagem alvo){
        int contador = 0;

        for (int i = 0; i < arma.attackSpeed; i++){
            if (Math.random() > alvo.getDodgeChance()){
                contador++;
                double dano = forca + arma.dano;

                if (Math.random() < this.getCriticalChance()){
                    dano *= 1.5;
                    System.out.println("O cavaleiro desfere um GOLPE CRÍTICO com sua espada maldita!");
                }

                alvo.receberDano(dano);
                System.out.println("O cavaleiro atinge o herói com sua espada!");

                // Chance de aplicar sangramento nos ataques normais
                if (Math.random() < 0.25) {
                    alvo.aplicarStatus("Sangramento", 2);
                    System.out.println("O herói começa a sangrar!");
                }

            } else {
                System.out.println("O herói esquiva do golpe pesado do cavaleiro!");
            }
        }

        System.out.printf("\nO cavaleiro acertou %d de %d golpes!\n", contador, arma.getAttackSpeed());
    }

    @Override
    public void mover(Personagem alvo){
        boolean chegou = false;

        for (int i = 0; i < moveSpeed; i++){
            pos += (pos < alvo.pos) ? 1 : -1;
            if (Utilidades.calcularDistancia(pos, alvo.pos) <= arma.attackRange){
                System.out.println("O cavaleiro corrompido ALCANCOU o heroi E IRA ATACAR!\n"); Utilidades.esperar(1500);
                chegou = true;
                atacar(alvo);
                break;
            }
        }

        if (!chegou)
            System.out.printf("O cavaleiro se aproxima e agora está a %d metro(s) do herói!\n", Utilidades.calcularDistancia(pos, alvo.getPos()));
    }

    // Golpe Pesado — aplica Atordoado
    public void golpePesado(Personagem alvo){
        System.out.println("\nO cavaleiro ergue sua espada com ambas as mãos, preparando um GOLPE PESADO!");

        if (Math.random() > (alvo.getDodgeChance() + alvo.getSorte()) * 0.7){
            double dano = forca * arma.dano * 2.0;
            alvo.receberDano(dano);
            System.out.println("O golpe atinge o herói com força devastadora!");

            alvo.aplicarStatus("Atordoado", 1);
            System.out.println("O herói está ATORDOADO e perderá o próximo turno!");
        } else {
            System.out.println("O herói rola para o lado e evita o golpe mortal!");
        }
    }

    // Investida Sombria — aplica Defesa Reduzida
    public void investidaSombria(Personagem alvo){
        System.out.println("\nO cavaleiro envolve-se em sombras e avança rapidamente em uma INVESTIDA SOMBRIA!");

        pos = alvo.getPos() + arma.attackRange;

        System.out.println("O cavaleiro fecha a distância em um instante!");

        if (Math.random() > alvo.getDodgeChance() + alvo.getSorte()){
            double dano = forca * arma.dano * 1.3;
            alvo.receberDano(dano);
            System.out.println("A investida atinge o herói com impacto brutal!");

            alvo.aplicarStatus("Defesa Reduzida", 2);
            System.out.println("A armadura do herói foi danificada! Defesa reduzida por 2 turnos.");
        } else {
            System.out.println("O herói consegue evitar o ataque no último momento!");
        }
    }
}
