public class CavaleiroCorrompido extends Monstro {

   public CavaleiroCorrompido(String nome, int nivel, int pos, Arma arma){
        super(nome, nivel, pos, arma);

        this.pontosDeVidaMax = 80 + (nivel * 15);
        this.pontosDeVida = this.pontosDeVidaMax;

        this.protecao = 0.2;
        this.forca = 12 + (nivel * 3);
        this.criticalChance = 0.1;
        this.sorte = 0.1;
        this.dodgeChance = 0.05;
        this.attackSpeed = 2;
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

        if (chance < 0.1 && distancia <= 2) {
            golpePesado(alvo);
            return;
        }
        else if (chance < 0.2 && distancia > 2 && distancia <= 8) {
            investidaSombria(alvo);
            return;
        }

        if (distancia <= 2){
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

        for (int i = 0; i < this.getAttackSpeed(); i++){
            if (Math.random() > alvo.getDodgeChance()){
                contador++;
                double dano = forca;

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

        System.out.printf("\nO cavaleiro acertou %d de %d golpes!\n", contador, this.getAttackSpeed());
    }

    @Override
    public void mover(Personagem alvo){
        if (pos < alvo.getPos())
            pos += moveSpeed;
        else
            pos -= moveSpeed;

        System.out.printf("O cavaleiro se aproxima e agora está a %d metro(s) do herói!\n", Utilidades.calcularDistancia(pos, alvo.getPos()));
    }

    // Golpe Pesado — aplica Atordoado
    public void golpePesado(Personagem alvo){
        System.out.println("\nO cavaleiro ergue sua espada com ambas as mãos, preparando um GOLPE PESADO!");

        if (Math.random() > alvo.getDodgeChance() * 0.7){
            double dano = forca * 2.0;
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

        if (pos < alvo.getPos())
            pos = alvo.getPos() - 1;
        else
            pos = alvo.getPos() + 1;

        System.out.println("O cavaleiro fecha a distância em um instante!");

        if (Math.random() > alvo.getDodgeChance()){
            double dano = forca * 1.3;
            alvo.receberDano(dano);
            System.out.println("A investida atinge o herói com impacto brutal!");

            alvo.aplicarStatus("Defesa Reduzida", 2);
            System.out.println("A armadura do herói foi danificada! Defesa reduzida por 2 turnos.");
        } else {
            System.out.println("O herói consegue evitar o ataque no último momento!");
        }
    }
}
