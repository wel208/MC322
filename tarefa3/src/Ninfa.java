/*
 * Mulher que se transforma num enorme e forte monstro
 * Muitos pontos de vida e boa proteção
 * Consegue atacar a uma distância <= 3 metros
 * Não é rápida, pouca chance de esquivar de um ataque
 */

public class Ninfa extends Monstro{
    
    //Construtor
    public Ninfa(String nome, int nivelDificuldade, int pos, Arma arma){
        super(nome, nivelDificuldade, pos, arma);
        this.pontosDeVidaMax = 100 + (nivelDificuldade - 1) * 20;
        this.pontosDeVida = this.pontosDeVidaMax;
        this.protecao = 0.4 + (nivelDificuldade - 1) * 0.01;
        this.forca = 20 + (nivelDificuldade - 1) * 5;
        this.moveSpeed = 3;
        this.xpConcedido = 30 + (nivelDificuldade * 15);
        this.sorte = 0.3 + (nivelDificuldade * 0.02);
    }

    @Override
    public AcaoDeCombate escolherAcao(Combatente alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.getPos());

        System.out.printf("\nA NINFA está a %d metros do nosso herói e ira ", distancia);

        if (distancia <= arma.getAttackRange()){
            System.out.print("ATACAR!\n"); Utilidades.esperar();
            return acoes.get(1);
        }
        else{
            System.out.print("SE APROXIMAR!\n"); Utilidades.esperar();
            return acoes.get(0);
        }
    }
}
