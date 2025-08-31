public class Esqueleto extends Monstro{
    
    //Atributos
    int precisao;

    //Construtor
    public Esqueleto(String nome, int nivel, int pos){
        super(nome, nivel, pos);
        this.attackSpeed = (nivel <= 8) ? 1 : 2;
        this.dodgeChance = 0.15 + 0.05 * nivel;
        this.forca = 40 + 2 * nivel;
        this.moveSpeed = 5 + nivel;
        this.precisao = 55 + 5 * nivel;
        this.protecao = 0.25 + 0.01 * nivel;
        this.pontosDeVida = 30 + 2 * nivel;
        this.xpConcedido = 40 + 2 * nivel;
    }

    //Métodos
    @Override
    public void ambientarMonstro(){

    }

    @Override
    public void statusParcial(){
        System.out.printf("\n%s, O ESQUELETO, está com %d PONTOS DE VIDA.", nome, pontosDeVida);
    }

    @Override
    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);
        double chance = Math.random();

        System.out.printf("\nO esqueleto está a %d metros do nosso herói e irá ", distancia);

        if (distancia <= 5){
            if (chance <= 0.2){
                System.out.print("ATACAR!");
                atacar(alvo);
            }
            else{
                System.out.print("SE AFASTAR!");
                mover(alvo);
            }
        }
        else if (distancia <= 10){
            if (chance <= 0.4){
                System.out.print("ATACAR!");
                atacar(alvo);
            }
            else{
                System.out.print("SE AFASTAR!");
                mover(alvo);
            }
        }
        else if (distancia <= 15){
            if (chance <= 0.7){
                System.out.print("ATACAR!");
                atacar(alvo);
            }
            else{
                System.out.print("SE AFASTAR!");
                mover(alvo);
            }
        }
        else{
            if (chance <= 0.9){
                System.out.print("ATACAR!");
                atacar(alvo);
            }
            else{
                System.out.print("SE AFASTAR!");
                mover(alvo);
            }
        }
    }

    @Override
    public void atacar(Personagem alvo){
        int contador = 0;

        for (int i = 0; i < attackSpeed; i++){
            Utilidades.esperar(500);

            if (Math.random() < precisao){                 //Se o esqueleto possivelmente acerta seu alvo
                if (Math.random() > alvo.dodgeChance){     //Se o herói não esquivar
                    contador++;
                    int distancia = Utilidades.calcularDistancia(pos, alvo.pos);

                    if (Math.random() < criticalChance){
                        alvo.receberDano(forca * 1.2 * distancia/5);
                        System.out.println("NÃO! O esqueleto ACERTOU um ATAQUE CRÍTICO no nosso herói!");
                    }  
                    else{  
                        alvo.receberDano(forca * distancia/5);
                        System.out.println("NÃO! O esqueleto ACERTOU o nosso herói!");
                    }
                }
                else                                       //Se o herói esquivar da flecha
                    System.out.printf("\nBOA! O %s ESQUIVOU da flecha!", Utilidades.verificarClasse(alvo));
            }
            else
                System.out.println("ISSO! O esqueleto ERROU a flecha!");
        }

        Utilidades.esperar(300);
        System.out.printf("\nO esqueleto acertou %d da(s) %d flecha(s) atirada(s)!", contador, attackSpeed);
        alvo.statusParcial();
    }

    @Override
    public void mover(Personagem alvo){
        if (pos > alvo.pos)
            pos += moveSpeed;
        else
            pos -= moveSpeed;
        
        System.out.printf("\nO esqueleto chegou a uma distância de %d metros do herói!", Utilidades.calcularDistancia(pos, alvo.pos));
    }
}
