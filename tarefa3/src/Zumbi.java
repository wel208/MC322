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
    public AcaoDeCombate escolherAcao(Combatente alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.getPos());

        System.out.printf("\nO ZUMBI está a %d metro(s) do herói e irá ", distancia);

        if (distancia <= arma.getAttackRange()){
            System.out.println("ATACAR!\n"); Utilidades.esperar();
            return acoes.get(1);
        }
        else{
            System.out.println("SE APROXIMAR!\n"); Utilidades.esperar();
            return acoes.get(0);
        }
    }
}
