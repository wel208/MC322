/*
 * Classe de monstro bem semelhante ao Arqueiro
 * Não possui muita vida ou proteção
 * Prefere ataques de longa distância
 */

public class Esqueleto extends Monstro {
    
    //Atributos
    private double precisao;

    //Construtor
    public Esqueleto(String nome, int nivel, int pos, Arma arma){
        super(nome, nivel, pos, arma);
        this.pontosDeVidaMax = 50 + (nivel - 1) * 10;
        this.pontosDeVida = this.pontosDeVidaMax;
        this.protecao = 0.2 + (nivel - 1) * 0.03;
        this.forca = 10 + (nivel - 1) * 2;
        this.precisao = 0.5 + (nivel - 1) * 0.05;
        this.moveSpeed = 10;
        this.xpConcedido = 15 + (nivel * 10);
        this.sorte = 0.3 + (nivel * 0.01);
    }

    @Override
    public void escolherAcao(Combatente alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.getPos());
        int distanciaBase = arma.getAttackRange();
        double chance = Math.random();
        boolean seMover = false, atacarInim = false;

        System.out.printf("\nO esqueleto esta a %d metro(s) do nosso heroi e ira ", distancia); 

        if (distancia <= distanciaBase * 0.3)
            if (chance <= 0.2)
                atacarInim = true;
            else
                seMover = true;

        else if (distancia <= distanciaBase * 0.5)
            if (chance <= 0.4)
                atacarInim = true;
            else
                seMover = true;

        else if (distancia <= distanciaBase * 0.8)
            if (chance <= 0.7)
                atacarInim = true;
            else
                seMover = true;

        else if (distancia <= distanciaBase)
            atacarInim = true;

        else
            seMover = true;

        if (seMover){
            if (distancia < arma.getAttackRange())
                System.out.println("SE AFASTAR!\n");
            else
                System.out.println("SE APROXIMAR para poder atacar!\n");
            Utilidades.esperar();
            acoes.get(0).executar(this, alvo);
        }
        else if (atacarInim){
            System.out.println("ATACAR!\n"); Utilidades.esperar();
            acoes.get(1).executar(this, alvo);
        }
    }
}
