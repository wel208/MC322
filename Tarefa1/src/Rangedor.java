public class Rangedor extends Monstro{
    
    //Construtor
    public Rangedor(String nome, int nivel, int pos){
        super(nome, nivel, pos);
        this.attackSpeed = 2;
        this.attackRange = 12;
        this.dodgeChance = 0.15;
        this.forca = 50 + 2 * (nivel - 2);
        this.moveSpeed = 3;
        this.protecao = 0.45 + nivel/100;
        this.pontosDeVida = 80 + nivel;
        this.xpConcedido = 40 + 2 * nivel;
    }

    //Métodos
    @Override
    public void ambientarMonstro(){

    }

    @Override
    public void statusParcial(){
        System.out.printf("\n%s, O RANGEDOR, está com %d PONTOS DE VIDA.", nome, pontosDeVida);
    }

    @Override
    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);

        System.out.printf("\nO rangedor está a %d metros do herói e irá ", distancia);

        if (distancia <= attackRange){
            System.out.print("ATACAR!");
            atacar(alvo);
        }
        else{
            System.out.print("SE APROXIMAR!");
            mover(alvo);
        }
    }

    @Override
    public void atacar(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);

        if (distancia <= 3){
            System.out.println("O RANGEDOR dará dois ATAQUES CORPO A CORPO!");

            for (int i = 0; i < 2; i++){
                Utilidades.esperar(500);

                if (Math.random() > alvo.dodgeChance){
                    System.out.println("NÃO! Ele ACERTOU um ataque no nosso herói!");
                    alvo.receberDano(forca);
                }
                else
                    System.out.println("BOA! O herói ESQUIVOU do ataque do monstro!");
            }
        }
        else{
            System.out.println("O RANGEDOR dará um ATAQUE À DISTÂNCIA!");
            alvo.receberDano(forca * 0.7);
        }
    }

    @Override
    public void mover(Personagem alvo){
        
        if (pos < alvo.pos)
            for (int i = 0; i < attackSpeed; i++){
                pos++;
                if (Utilidades.calcularDistancia(pos, alvo.pos) == attackRange - 3){
                    System.out.println("O RANGEDOR chegou a uma distância de 9 metros do herói!");
                    atacar(alvo);
                }
            }
        else
            for (int i = 0; i < attackSpeed; i++){
                pos--;
                if (Utilidades.calcularDistancia(pos, alvo.pos) == attackRange - 3){
                    System.out.println("O RANGEDOR chegou a uma distância de 9 metros do herói!");
                    atacar(alvo);
                }
            }
    }
}
