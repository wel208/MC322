public class Esqueleto extends Monstro{
    
    //Atributos
    double precisao;

    //Construtor
    public Esqueleto(String nome, int nivel, int pos){
        super(nome, nivel, pos);
        this.attackSpeed = (nivel <= 8) ? 1 : 2;
        this.dodgeChance = 0.15 + 0.05 * nivel;
        this.forca = 40 + (2 * (nivel - 3));
        this.moveSpeed = 5 + nivel;
        this.precisao = 55 + (5 * nivel);
        this.protecao = 0.25 + 0.01 * nivel;
        this.pontosDeVida = 30 + (2 * nivel);
        this.xpConcedido = 40 + 2 * nivel;
    }

    //Métodos
    @Override
    public void ambientarMonstro(Heroi heroi){
        //Ambientação
        System.out.println("Enquanto o nosso guerreiro segue o seu rumo, comeca a ouvir um som de bater de ossos vindo do meio das arvores."); Utilidades.esperar(2000);
        System.out.println("Ele entao olha em direcao ao barulho, procurando entender de onde vem."); Utilidades.esperar(2000);
        System.out.println("Quando, de repente, uma flecha VEM NA SUA DIRECAO."); Utilidades.esperar(2000);
        System.out.println("Por sorte, " + heroi.nome + " e rapido o suficiente para esquivar."); Utilidades.esperar(2000);
        System.out.println("Dentre as arvores aparece entao " + nome + ", um arqueiro de Lumenholt que aparenta ter sido corrompido pela floresta."); Utilidades.esperar(2000);
        System.out.println(nome + " foi um corajoso batalhador nessa longa guerra, mas infelizmente foi morto pelas aberracoes ao tentar derrotar o Corvo Rei."); Utilidades.esperar(2000);
        System.out.println("Infelizmente nao ha o que o nosso heroi fazer a nao ser derrota-lo."); Utilidades.esperar(2000);

        //Apresentação de atributos
        System.out.print("\nBom atirador, prefere ataques feitos a longa distancia."); Utilidades.esperar(2000);
        System.out.printf("\n%s, O ESQUELETO ARQUEIRO, no nivel %d, possui:", nome, nivel); Utilidades.esperar(2000);
        System.out.printf("\n%d PONTOS DE VIDA;", pontosDeVida); Utilidades.esperar(2000);
        System.out.printf("\n%.0f PONTOS DE FORCA;", forca); Utilidades.esperar(2000);
        System.out.printf("\nE, ao morrer, concedera %d PONTOS DE EXPERIENCIA ao heroi\n", xpConcedido); Utilidades.esperar(2000);

        System.out.printf("\nNeste momento, mesmo triste pelo que aconteceu com %s, nosso heroi se prepara para a batalha.\n", nome); Utilidades.esperar(1500);
    }

    @Override
    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);
        double chance = Math.random();

        System.out.printf("\nO esqueleto esta a %d metro(s) do nosso heroi e ira ", distancia); 

        if (distancia <= 5){
            if (chance <= 0.2){
                System.out.println("ATACAR!\n"); Utilidades.esperar(1500);
                atacar(alvo);
            }
            else{
                System.out.println("SE AFASTAR!\n"); Utilidades.esperar(1500);
                mover(alvo);
            }
        }
        else if (distancia <= 10){
            if (chance <= 0.4){
                System.out.println("ATACAR!\n"); Utilidades.esperar(1500);
                atacar(alvo);
            }
            else{
                System.out.println("SE AFASTAR!\n"); Utilidades.esperar(1500);
                mover(alvo);
            }
        }
        else if (distancia <= 15){
            if (chance <= 0.7){
                System.out.println("ATACAR!\n"); Utilidades.esperar(1500);
                atacar(alvo);
            }
            else{
                System.out.println("SE AFASTAR!\n"); Utilidades.esperar(1500);
                mover(alvo);
            }
        }
        else{
            if (chance <= 0.9){
                System.out.println("ATACAR!\n"); Utilidades.esperar(1500);
                atacar(alvo);
            }
            else{
                System.out.println("SE AFASTAR!\n"); Utilidades.esperar(1500);
                mover(alvo);
            }
        }
    }

    @Override
    public void atacar(Personagem alvo){
        int contador = 0;

        for (int i = 0; i < attackSpeed; i++){

            if (Math.random() < precisao/100){                 //Se o esqueleto possivelmente acerta seu alvo
                if (Math.random() > alvo.dodgeChance){     //Se o herói não esquivar
                    contador++;
                    int distancia = Utilidades.calcularDistancia(pos, alvo.pos);

                    if (Math.random() < criticalChance){
                        alvo.receberDano(forca * 1.2 * distancia/7);
                        System.out.println("NAO! O esqueleto ACERTOU um ATAQUE CRITICO no nosso heroi!"); Utilidades.esperar(1000);
                    }  
                    else{  
                        alvo.receberDano(forca * distancia/7);
                        System.out.println("NAO! O esqueleto ACERTOU o nosso heroi!"); Utilidades.esperar(1000);
                    }
                }
                else                                       //Se o herói esquivar da flecha
                    System.out.println("BOA! O heroi ESQUIVOU da flecha!"); Utilidades.esperar(1000);
            }
            else
                System.out.println("ISSO! O esqueleto ERROU a flecha!"); Utilidades.esperar(1000);
        }

        Utilidades.esperar(1500);
        System.out.printf("\nO esqueleto acertou %d da(s) %d flecha(s) atirada(s)!\n", contador, attackSpeed); Utilidades.esperar(1500);
    }

    @Override
    public void mover(Personagem alvo){
        if (pos > alvo.pos)
            pos += moveSpeed;
        else
            pos -= moveSpeed;
        
        System.out.printf("O esqueleto chegou a uma distancia de %d metro(s) do heroi!\n", Utilidades.calcularDistancia(pos, alvo.pos)); Utilidades.esperar(1500);
    }
}
