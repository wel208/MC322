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
    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.getPos());

        System.out.printf("\nA NINFA está a %d metros do nosso herói e ira ", distancia);

        if (distancia <= arma.getAttackRange()){
            System.out.print("ATACAR!\n"); Utilidades.esperar(200);
            atacar(alvo);
        }
        else{
            System.out.print("SE APROXIMAR!\n"); Utilidades.esperar(200);
            mover(alvo);
        }
    }
    
    @Override
    public void atacar(Personagem alvo){
        int contador = 0;

        for (int i = 0; i < arma.getAttackSpeed(); i++){

            if (Math.random() > alvo.getDodgeChance()){
                contador++;

                boolean critico = Math.random() < criticalChance;
                double multiplicador = critico ? 1.2 : 1.0;

                alvo.receberDano(forca * arma.getDano() * multiplicador);

                if (critico){
                    System.out.println("\nNAO! A ninfa ACERTOU um ATAQUE CRITICO no nosso heroi!");
                }
                else{
                    System.out.println("\nNAO! A ninfa ACERTOU um golpe no heroi!");
                }
                Utilidades.esperar(200);
            }
            else{
                System.out.println("\nUFA! O heroi ESQUIVOU do ataque da ninfa!"); Utilidades.esperar(200);
            }
        }

        System.out.printf("\nA ninfa ACERTOU %d de %d ataque(s) dado(s)!\n", contador, arma.getAttackSpeed()); Utilidades.esperar(200);
    }

    @Override
    public void mover(Personagem alvo){
        boolean chegou = false;
        int direcao = pos < alvo.getPos() ? 1 : -1;

        for (int i = 0; i < moveSpeed; i++){
            pos += direcao;

            if (Utilidades.calcularDistancia(pos, alvo.pos) == arma.attackRange){
                System.out.println("A NINFA ALCANCOU O HEROI E IRA ATACAR!"); Utilidades.esperar(200);
                chegou = true;
                atacar(alvo);
                break;
            }
        }
        
        if (!chegou){
            Utilidades.esperar(200);
            System.out.println("\nA NINFA ainda nao alcancou o nosso heroi!");
            System.out.printf("Ela esta a %d metros de %s.\n", Utilidades.calcularDistancia(pos, alvo.pos), alvo.nome); Utilidades.esperar(200);
        }
    }
}
