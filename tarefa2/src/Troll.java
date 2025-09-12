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
    public void ambientarMonstro(Heroi heroi){
        System.out.println("\nO chão treme com passos pesados..."); Utilidades.esperar(200);
        System.out.println("Das sombras surge um enorme Troll, com pele grossa e olhar faminto."); Utilidades.esperar(200);
        System.out.println("Sua respiração é pesada e ele segura um tronco como se fosse uma clava."); Utilidades.esperar(200);

        System.out.printf("\n%s, o TROLL, no nível %d de dificuldade, possui:", nome, nivelDificuldade); Utilidades.esperar(200);
        System.out.printf("\n%d PONTOS DE VIDA;", pontosDeVida); Utilidades.esperar(200);
        System.out.printf("\n%.0f PONTOS DE FORÇA;", forca); Utilidades.esperar(200);
        System.out.printf("\nE, ao morrer, concederá %d PONTOS DE EXPERIÊNCIA ao herói.\n", xpConcedido); Utilidades.esperar(200);

        System.out.println("\nO herói percebe que terá de usar estratégia para vencer essa criatura."); Utilidades.esperar(200);
    }

    @Override
    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);
        double chance = Math.random();

        System.out.printf("\nO TROLL está a %d metro(s) do herói e irá ", distancia);

        // 10% de chance de regenerar vida
        if (chance < 0.1) {
            regeneracaoSelvagem();
            return;
        }
        // 15% de chance de usar Golpe Demolidor se estiver no alcance
        else if (chance < 0.25 && distancia <= arma.attackRange) {
            golpeDemolidor(alvo);
            return;
        }

        if (distancia <= arma.attackRange){
            System.out.println("ATACAR!\n"); Utilidades.esperar(200);
            atacar(alvo);
        } else {
            System.out.println("SE APROXIMAR!\n"); Utilidades.esperar(200);
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
                    alvo.receberDano(forca * arma.dano * 1.3);
                    System.out.println("O Troll acerta um GOLPE CRÍTICO com sua clava!"); Utilidades.esperar(200);
                } else {
                    alvo.receberDano(forca * arma.dano);
                    System.out.println("O Troll acerta um golpe pesado no herói!"); Utilidades.esperar(200);
                }
            } else {
                System.out.println("O herói esquiva do ataque do Troll!"); Utilidades.esperar(200);
            }
        }
        System.out.printf("\nO Troll acertou %d de %d ataques!\n", contador, arma.attackSpeed); Utilidades.esperar(200);
    }

    @Override
    public void mover(Personagem alvo){
        boolean chegou = false;
        for (int i = 0; i < moveSpeed; i++){
            pos++;
            if (Utilidades.calcularDistancia(pos, alvo.pos) == arma.attackRange){
                chegou = true;
                break;
            }
        }

        if (!chegou) 
            System.out.printf("O Troll se aproxima e agora está a %d metro(s) do herói!\n", Utilidades.calcularDistancia(pos, alvo.pos)); Utilidades.esperar(200);
    }

    // Habilidade especial: Regeneração Selvagem
    public void regeneracaoSelvagem() {
        int cura = (int)((double)pontosDeVida * 0.15); // recupera 15% da vida atual
        pontosDeVida += cura;
        System.out.printf("\nO Troll ruge e sua pele se regenera, recuperando %d pontos de vida! Vida atual: %d\n", cura, pontosDeVida);
        Utilidades.esperar(200);
    }

    // Habilidade especial: Golpe Demolidor
    public void golpeDemolidor(Personagem alvo) {
        System.out.println("\nO Troll levanta sua clava gigante para um GOLPE DEMOLIDOR!");
        Utilidades.esperar(200);

        if (Math.random() > alvo.dodgeChance * 0.7) {
            double dano = forca * arma.dano * 2.0;
            alvo.receberDano(dano);
            System.out.println("O golpe atinge o herói com força esmagadora!");
            Utilidades.esperar(200);

            alvo.aplicarStatus("Atordoado", 1);
            System.out.println("O herói está ATORDOADO e perderá o próximo turno!");
            Utilidades.esperar(200);
        } else {
            System.out.println("O herói consegue evitar o golpe demolidor!");
            Utilidades.esperar(200);
        }
    }
}
