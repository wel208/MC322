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
    public void ambientarMonstro(Heroi heroi){
        System.out.println("\nUm cheiro pútrido invade o ar..."); Utilidades.esperar(2000);
        System.out.println("Das sombras, surge um Zumbi cambaleante, com pedaços de carne pendendo de seu corpo."); Utilidades.esperar(2000);
        System.out.println("Seus olhos sem vida fixam-se no herói, e um grunhido gutural ecoa."); Utilidades.esperar(2000);

        System.out.printf("\n%s, o ZUMBI, no nível %d de dificuldade, possui:", nome, nivelDificuldade); Utilidades.esperar(2000);
        System.out.printf("\n%d PONTOS DE VIDA;", pontosDeVida); Utilidades.esperar(2000);
        System.out.printf("\n%.0f PONTOS DE FORÇA;", forca); Utilidades.esperar(2000);
        System.out.printf("\nE, ao morrer, concederá %d PONTOS DE EXPERIÊNCIA ao herói.\n", xpConcedido); Utilidades.esperar(2000);

        System.out.println("\nO herói percebe que não será fácil derrubar essa criatura."); Utilidades.esperar(1500);
    }

    @Override
    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);
        double chance = Math.random();

        System.out.printf("\nO ZUMBI está a %d metro(s) do herói e irá ", distancia);

        // 10% de chance de regenerar vida
        if (chance < 0.1) {
            regeneracaoMacabra();
            return;
        }
        // 15% de chance de usar Mordida Infectada se estiver no alcance
        else if (chance < 0.25 && distancia <= arma.attackRange) {
            mordidaInfectada(alvo);
            return;
        }

        if (distancia <= arma.attackRange){
            System.out.println("ATACAR!\n"); Utilidades.esperar(1500);
            atacar(alvo);
        } else {
            System.out.println("SE APROXIMAR!\n"); Utilidades.esperar(1500);
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
                    alvo.receberDano(forca * arma.dano * 1.2);
                    System.out.println("O Zumbi desfere um golpe CRÍTICO com suas garras!"); Utilidades.esperar(1500);
                } else {
                    alvo.receberDano(forca * arma.dano);
                    System.out.println("O Zumbi acerta um golpe pesado no herói!"); Utilidades.esperar(1500);
                }
            } else {
                System.out.println("O herói esquiva do ataque lento do Zumbi!"); Utilidades.esperar(1500);
            }
        }
        System.out.printf("\nO Zumbi acertou %d de %d ataques!\n", contador, arma.attackSpeed); Utilidades.esperar(1500);
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
            System.out.printf("O Zumbi se arrasta e agora está a %d metro(s) do herói!\n", Utilidades.calcularDistancia(pos, alvo.pos)); Utilidades.esperar(1500);
    }

    // Habilidade especial: Regeneração Macabra
    public void regeneracaoMacabra() {
        int cura = (int)(pontosDeVidaMax * 0.1); // recupera 10% da vida máxima
        pontosDeVida = Math.min(pontosDeVida + cura, pontosDeVidaMax);
        System.out.printf("\nO Zumbi solta um grunhido e sua carne começa a se recompor, recuperando %d pontos de vida! Vida atual: %d\n", cura, pontosDeVida);
        Utilidades.esperar(1500);
    }

    // Habilidade especial: Mordida Infectada
    public void mordidaInfectada(Personagem alvo) {
        System.out.println("\nO Zumbi avança lentamente e tenta uma MORDIDA INFECTADA!");
        Utilidades.esperar(2000);

        if (Math.random() > alvo.dodgeChance * 0.9) {
            double dano = forca * arma.dano * 1.5;
            alvo.receberDano(dano);
            System.out.println("A mordida rasga a carne do herói, espalhando infecção!");
            Utilidades.esperar(1500);

            // 50% de chance de aplicar Envenenado, 50% de aplicar Sangramento
            if (Math.random() < 0.5) {
                alvo.aplicarStatus("Envenenado", 3);
                System.out.println("O herói está ENVENENADO e perderá vida a cada turno!");
            } else {
                alvo.aplicarStatus("Sangramento", 3);
                System.out.println("O herói está SANGRANDO e perderá vida a cada turno!");
            }
            Utilidades.esperar(1500);
        } else {
            System.out.println("O herói consegue evitar a mordida do Zumbi!");
            Utilidades.esperar(1500);
        }
    }
}
