public class TrollAnciao extends Monstro {

    public TrollAnciao(String nome, int nivelDificuldade, int pos, Arma arma){
        super(nome, nivelDificuldade, pos, arma);
        this.pontosDeVidaMax = 120 + (nivelDificuldade * 20);
        this.pontosDeVida = this.pontosDeVidaMax;
        this.protecao = 0.15;
        this.forca = 15 + (nivelDificuldade * 3);
        this.sorte = 0.3 + (nivelDificuldade * 0.01);
        this.moveSpeed = 3;
        this.xpConcedido = 40 + (nivelDificuldade * 15);
        this.dodgeChance = 0.1 + (nivelDificuldade * 0.01);
    }

    @Override
    public void ambientarMonstro(Heroi heroi){
        System.out.println("\nUm rugido profundo ecoa pela caverna..."); Utilidades.esperar(2000);
        System.out.println("Das sombras surge um Troll Ancião, mais alto e mais largo que qualquer outro."); Utilidades.esperar(2000);
        System.out.println("Suas cicatrizes contam histórias de batalhas antigas, e seus olhos brilham com ódio ancestral."); Utilidades.esperar(2000);

        System.out.printf("\n%s, o TROLL ANCIÃO, no nível %d de dificuldade, possui:", nome, nivelDificuldade);
        System.out.printf("\n%d PONTOS DE VIDA;", pontosDeVida);
        System.out.printf("\n%.0f PONTOS DE FORÇA;", forca);
        System.out.printf("\nE, ao morrer, concederá %d PONTOS DE EXPERIÊNCIA ao herói.\n", xpConcedido);
    }

    @Override
    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.getPos());
        double chance = Math.random();

        System.out.printf("\nO TROLL ANCIÃO está a %d metro(s) do herói e irá ", distancia);

        if (chance < 0.1) {
            regeneracaoSelvagem();
            return;
        }
        else if (chance < 0.25 && distancia <= arma.getAttackRange()) {
            golpeDemolidor(alvo);
            return;
        }

        if (distancia <= arma.getAttackRange()){
            atacar(alvo);
        } else {
            mover(alvo);
        }
    }

    @Override
    public void atacar(Personagem alvo){
        int contador = 0;
        for (int i = 0; i < arma.attackSpeed; i++){
            if (Math.random() > alvo.getDodgeChance()){
                contador++;
                double dano = forca * arma.dano;

                if (Math.random() < getCriticalChance()){
                    dano *= 1.4;
                    System.out.println("O Troll Ancião acerta um GOLPE CRÍTICO devastador!");
                }

                alvo.receberDano(dano);
                System.out.println("O Troll Ancião acerta um golpe pesado no herói!");

                if (Math.random() < 0.2) {
                    alvo.aplicarStatus("Fratura", 2);
                    System.out.println("O herói sofre uma fratura e terá sua defesa reduzida!");
                }

            } else {
                System.out.println("O herói esquiva do ataque do Troll Ancião!");
            }
        }
        System.out.printf("\nO Troll Ancião acertou %d de %d ataques!\n", contador, arma.attackSpeed);
    }

    @Override
    public void mover(Personagem alvo){
        boolean chegou = false;
        for (int i = 0; i < moveSpeed; i++){
            pos += (pos < alvo.getPos()) ? 1 : -1;
            if (Utilidades.calcularDistancia(pos, alvo.getPos()) == arma.attackRange){
                chegou = true;
                System.out.println("O Troll Ancião avança com passos pesados e está pronto para atacar!");
                atacar(alvo);
                break;
            }
        }

        if (!chegou)
            System.out.printf("O Troll Ancião se aproxima e agora está a %d metro(s) do herói!\n", Utilidades.calcularDistancia(pos, alvo.getPos()));
    }

    public void regeneracaoSelvagem() {
        int cura = (int)(pontosDeVidaMax * 0.15);
        int antes = pontosDeVida;
        pontosDeVida = Math.min(pontosDeVida + cura, pontosDeVidaMax);
        int curado = pontosDeVida - antes;

        System.out.printf("\nO Troll Ancião ruge e sua pele se regenera, recuperando %d pontos de vida! Vida atual: %d/%d\n",
            curado, pontosDeVida, pontosDeVidaMax);
    }

    public void golpeDemolidor(Personagem alvo) {
        System.out.println("\nO Troll Ancião levanta sua clava gigante para um GOLPE DEMOLIDOR!");
        if (Math.random() > alvo.getDodgeChance() * 0.7) {
            double dano = forca * arma.dano * 2.0;
            alvo.receberDano(dano);
            System.out.println("O golpe atinge o herói com força esmagadora!");

            alvo.aplicarStatus("Atordoado", 1);
            System.out.println("O herói está ATORDOADO e perderá o próximo turno!");
        } else {
            System.out.println("O herói consegue evitar o golpe demolidor!");
        }
    }
}
