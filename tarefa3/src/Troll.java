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
    public void escolherAcao(Combatente alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.getPos());

        System.out.printf("\nO TROLL está a %d metro(s) do herói e irá ", distancia);

        if (distancia <= arma.getAttackRange()){
            System.out.println("ATACAR!\n"); Utilidades.esperar();
            acoes.get(1).executar(this, alvo);
        } 
        else{
            System.out.println("SE APROXIMAR!\n"); Utilidades.esperar();
            acoes.get(0).executar(this, alvo);
        }
    }
}
