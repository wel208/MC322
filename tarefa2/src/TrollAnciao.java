public class TrollAnciao extends Monstro {

    private boolean emFuria = false;

    public TrollAnciao(String nome, int nivel, int pos, Arma arma){
        super(nome, nivel, pos, arma);
        this.pontosDeVidaMax = 120 + (nivel * 20);
        this.pontosDeVida = this.pontosDeVidaMax;
        this.protecao = 0.15;
        this.forca = 15 + (nivel * 3);
        this.criticalChance = 0.08;
        this.sorte = 0.05;
        this.dodgeChance = 0.03;
        this.attackSpeed = 2;
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
        verificarFuria();

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
        for (int i = 0; i < this.getAttackSpeed(); i++){
            if (Math.random() > alvo.getDodgeChance()){
                contador++;
                double dano = forca;

                if (Math.random() < this.getCriticalChance()){
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
        System.out.printf("\nO Troll Ancião acertou %d de %d ataques!\n", contador, this.getAttackSpeed());
    }

    @Override
    public void mover(Personagem alvo){
        if (pos < alvo.getPos())
            pos += moveSpeed;
        else
            pos -= moveSpeed;

        System.out.printf("O Troll Ancião se aproxima e agora está a %d metro(s) do herói!\n",
            Utilidades.calcularDistancia(pos, alvo.getPos()));
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
            double dano = forca * 2.0;
            alvo.receberDano(dano);
            System.out.println("O golpe atinge o herói com força esmagadora!");

            alvo.aplicarStatus("Atordoado", 1);
            System.out.println("O herói está ATORDOADO e perderá o próximo turno!");
        } else {
            System.out.println("O herói consegue evitar o golpe demolidor!");
        }
    }

    private void verificarFuria() {
        if (!emFuria && pontosDeVida <= (pontosDeVidaMax / 2)) {
            emFuria = true;
            forca *= 1.5;
            this.attackSpeed = this.getAttackSpeed() + 1;
            System.out.println("\nO Troll Ancião entra em FÚRIA! Sua força e velocidade aumentam!");
        }
    }
}
