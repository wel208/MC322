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
    public void ambientarMonstro(Heroi heroi){
        System.out.println("\nO chão começa a tremer levemente..."); Utilidades.esperar(2000);
        System.out.println("Das sombras da floresta surge uma figura enorme e grotesca."); Utilidades.esperar(2000);
        System.out.println("É " + nome + ", um Goblin Gigante, conhecido por esmagar seus inimigos com força bruta."); Utilidades.esperar(2000);
        System.out.println("Sua pele é grossa e seus músculos parecem feitos de pedra."); Utilidades.esperar(2000);

        System.out.printf("\n%s, o GOBLIN GIGANTE, no nível %d de dificuldade, possui:", nome, nivelDificuldade); Utilidades.esperar(2000);
        System.out.printf("\n%d PONTOS DE VIDA;", pontosDeVida); Utilidades.esperar(2000);
        System.out.printf("\n%.0f PONTOS DE FORÇA;", forca); Utilidades.esperar(2000);
        System.out.printf("\nE, ao morrer, concederá %d PONTOS DE EXPERIÊNCIA ao herói.\n", xpConcedido); Utilidades.esperar(2000);

        System.out.println("\nO herói sente o peso da presença desse inimigo e se prepara para a batalha."); Utilidades.esperar(1500);
    }

    @Override
    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);
        double chance = Math.random();

        System.out.printf("\nO GOBLIN GIGANTE está a %d metro(s) do herói e irá ", distancia);

        // 15% de chance de usar Esmagamento Brutal se estiver perto
        if (chance < 0.15 && distancia <= arma.attackRange) {
            esmagamentoBrutal(alvo);
            return;
        }

        // Comportamento normal
        if (distancia <= arma.attackRange){
            System.out.println("ATACAR!\n"); Utilidades.esperar(1500);
            atacar(alvo);
        } else {
            System.out.println("AVANÇAR!\n"); Utilidades.esperar(1500);
            mover(alvo);
        }
    }

    @Override
    public void atacar(Personagem alvo){
        int contador = 0;
        for (int i = 0; i < arma.attackSpeed; i++){
            if (Math.random() > alvo.dodgeChance){
                contador++;
                if (Math.random() < criticalChance){
                    alvo.receberDano(forca * arma.dano * 1.4);
                    System.out.println("O Goblin Gigante acerta um GOLPE CRÍTICO devastador!"); Utilidades.esperar(1500);
                } else {
                    alvo.receberDano(forca * arma.dano);
                    System.out.println("O Goblin Gigante acerta um golpe pesado no herói!"); Utilidades.esperar(1500);
                }
            } else {
                System.out.println("O herói esquiva do ataque pesado do Goblin Gigante!"); Utilidades.esperar(1500);
            }
        }
        System.out.printf("\nO Goblin Gigante acertou %d de %d ataques!\n", contador, arma.attackSpeed); Utilidades.esperar(1500);
    }

    @Override
    public void mover(Personagem alvo){
        boolean chegou = false;

        for (int i = 0; i < moveSpeed; i++){
            pos++;
            if (Utilidades.calcularDistancia(pos, alvo.pos) == arma.attackRange){
                chegou = true;
                System.out.println("O Goblin Gigante alcançou o herói e irá atacar!");
                atacar(alvo);
                break;
            }
        }

        if (!chegou)
            System.out.printf("O Goblin Gigante se aproxima e agora está a %d metro(s) do herói!\n", Utilidades.calcularDistancia(pos, alvo.pos)); Utilidades.esperar(1500);
    }

    // Habilidade especial: Esmagamento Brutal
    public void esmagamentoBrutal(Personagem alvo) {
        System.out.println("\nO Goblin Gigante ergue sua clava enorme e prepara um ESMAGAMENTO BRUTAL!");
        Utilidades.esperar(2000);

        if (Math.random() > alvo.dodgeChance * 0.8) {
            double dano = forca * arma.dano * 2.0;
            alvo.receberDano(dano);
            System.out.println("O golpe atinge o herói com força esmagadora!");
            Utilidades.esperar(1500);

            // Aplica atordoamento por 1 turno
            alvo.aplicarStatus("Atordoado", 1);
            System.out.println("O herói está ATORDOADO e perderá o próximo turno!");
            Utilidades.esperar(1500);
        } else {
            System.out.println("O herói consegue rolar para o lado e evitar o golpe!");
            Utilidades.esperar(1500);
        }
    }
}
