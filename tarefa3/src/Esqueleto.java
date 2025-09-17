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
    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);
        double range = (double)arma.getAttackRange();
        double chance = Math.random();

        System.out.printf("\nO esqueleto esta a %d metro(s) do nosso heroi e ira ", distancia); 

        if (distancia <= range * 0.3){
            if (chance <= 0.2){
                System.out.println("ATACAR!\n"); Utilidades.esperar();
                atacar(alvo);
            }
            else{
                System.out.println("SE AFASTAR!\n"); Utilidades.esperar();
                mover(alvo);
            }
        }
        else if (distancia <= range * 0.5){
            if (chance <= 0.4){
                System.out.println("ATACAR!\n"); Utilidades.esperar();
                atacar(alvo);
            }
            else{
                System.out.println("SE AFASTAR!\n"); Utilidades.esperar();
                mover(alvo);
            }
        }
        else if (distancia <= range * 0.8){
            if (chance <= 0.7){
                System.out.println("ATACAR!\n"); Utilidades.esperar();
                atacar(alvo);
            }
            else{
                System.out.println("SE AFASTAR!\n"); Utilidades.esperar();
                mover(alvo);
            }
        }
        else if (distancia <= range){
                System.out.println("ATACAR!\n"); Utilidades.esperar();
                atacar(alvo);
        }
        else{
            System.out.println("SE APROXIMAR para poder atacar!\n"); Utilidades.esperar();
            mover(alvo);
        }
    }

    @Override
    public void atacar(Personagem alvo){
        int contador = 0;

        for (int i = 0; i < arma.getAttackSpeed(); i++){
            if (Math.random() < precisao){
                if (Math.random() > alvo.getDodgeChance()){
                    contador++;
                    int distancia = Utilidades.calcularDistancia(pos, alvo.getPos());

                    boolean critico = Math.random() < criticalChance;
                    double multiplicador = critico ? 1.2 : 1.0;

                    alvo.receberDano(forca * arma.getDano() * multiplicador * distancia/7);

                    if (critico){
                        System.out.println("NAO! O esqueleto ACERTOU um ATAQUE CRITICO no nosso heroi!");
                    }  
                    else{
                        System.out.println("NAO! O esqueleto ACERTOU o nosso heroi!");
                    }
                    Utilidades.esperar();
                }
                else
                    System.out.println("BOA! O heroi ESQUIVOU da flecha!"); Utilidades.esperar();
            }
            else
                System.out.println("ISSO! O esqueleto ERROU a flecha!"); Utilidades.esperar();
        }

        System.out.printf("\nO esqueleto acertou %d da(s) %d flecha(s) atirada(s)!\n", contador, arma.getAttackSpeed()); Utilidades.esperar();
    }

    @Override
    public void mover(Personagem alvo){
        boolean chegou = false;

        if (pos < arma.getAttackRange()){
            for (int i = 0; i < moveSpeed; i++){
                pos += (pos < alvo.getPos()) ? 1 : -1;
                if (Utilidades.calcularDistancia(pos, alvo.pos) == arma.getAttackRange()){
                    chegou = true;
                    break;
                }
            }
        }
        else{
            for (int i = 0; i < moveSpeed; i++){
                pos += (pos < alvo.getPos()) ? -1 : 1;
                if (Utilidades.calcularDistancia(pos, alvo.pos) == arma.getAttackRange()){
                    chegou = true;
                    break;
                }
            }
        }
        
        if (chegou){
            System.out.println("O esqueleto chegou na distancia ideal para ATACAR o heroi!"); Utilidades.esperar();
            atacar(alvo);
        }
        else{
            System.out.println("O esqueleto não conseguiu chegar na distancia ideal para ATACAR o heroi."); Utilidades.esperar();
        }
    }
}
