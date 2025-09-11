/*
 * Classe de monstro bem semelhante ao Arqueiro
 * Não possui muita vida ou proteção
 * Prefere ataques de longa distância
 */

public class Esqueleto extends Monstro {
    
    //Atributos
    double precisao;

    //Construtor
    public Esqueleto(String nome, int nivel, int pos, Arma arma){
        super(nome, nivel, pos, arma);
    }

    @Override
    public void ambientarMonstro(Heroi heroi){
        System.out.println("\nEnquanto o nosso guerreiro segue o seu rumo, comeca a ouvir um som de bater de ossos vindo do meio das arvores."); Utilidades.esperar(2000);
        System.out.println("Ele entao olha em direcao ao barulho, procurando entender de onde vem."); Utilidades.esperar(2000);
        System.out.println("Quando, de repente, uma flecha VEM NA SUA DIRECAO."); Utilidades.esperar(2000);
        System.out.println("Por sorte, " + heroi.nome + " e rapido o suficiente para esquivar."); Utilidades.esperar(2000);
        System.out.println("Dentre as arvores aparece entao " + nome + ", um arqueiro de Lumenholt que aparenta ter sido corrompido pela floresta."); Utilidades.esperar(2000);
        System.out.println(nome + " foi um corajoso batalhador nessa longa guerra, mas infelizmente foi morto pelas aberracoes ao tentar derrotar o Corvo Rei."); Utilidades.esperar(2000);
        System.out.println("Infelizmente nao ha o que o nosso heroi fazer a nao ser derrota-lo."); Utilidades.esperar(2000);

        System.out.print("\nBom atirador, prefere ataques feitos a longa distancia."); Utilidades.esperar(2000);
        System.out.printf("\n%s, O ESQUELETO ARQUEIRO, no nivel %d de dificuldade, possui:", nome, nivelDificuldade); Utilidades.esperar(2000);
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

        if (chance < 0.1) {
            disparoPreciso(alvo);
            return;
        }
        else if (chance < 0.2) {
            flechaEnvenenada(alvo);
            return;
        }

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
            if (Math.random() < precisao/100){
                if (Math.random() > alvo.dodgeChance){
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
                else
                    System.out.println("BOA! O heroi ESQUIVOU da flecha!"); Utilidades.esperar(1000);
            }
            else
                System.out.println("ISSO! O esqueleto ERROU a flecha!"); Utilidades.esperar(1000);
        }

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

    public void disparoPreciso(Personagem alvo) {
        System.out.println("\nO esqueleto se concentra... seus ossos rangem enquanto ele prepara um DISPARO PRECISO!");
        Utilidades.esperar(2000);

        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);
        double chanceAcerto = (precisao + 20) / 100;
        double chanceCritico = criticalChance + 0.15;

        if (Math.random() < chanceAcerto) {
            if (Math.random() > (alvo.dodgeChance * 0.5)) {
                double danoBase = forca * (distancia / 6.0);

                if (Math.random() < chanceCritico) {
                    danoBase *= 1.5;
                    System.out.println("ATAQUE CRÍTICO! A flecha perfura a armadura do herói!");
                } else {
                    System.out.println("A flecha atinge o herói com força!");
                }

                alvo.receberDano(danoBase);
            } else {
                System.out.println("O herói conseguiu se esquivar por pouco!");
            }
        } else {
            System.out.println("O disparo passou raspando e errou o alvo!");
        }

        Utilidades.esperar(1500);
    }

    public void flechaEnvenenada(Personagem alvo) {
        System.out.println("\nO esqueleto prepara uma FLECHA ENVENENADA e a dispara contra o herói!");
        Utilidades.esperar(2000);

        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);
        double chanceAcerto = precisao / 100;
        double danoBase = forca * (distancia / 8.0);

        if (Math.random() < chanceAcerto) {
            if (Math.random() > alvo.dodgeChance) {
                alvo.receberDano(danoBase);
                System.out.println("A flecha envenenada acerta o herói! O veneno começa a agir...");
                Utilidades.esperar(1500);

                alvo.aplicarStatus("Envenenado", 3);
                System.out.println("O herói está ENVENENADO e perderá vida a cada turno!");
                Utilidades.esperar(1500);
            } else {
                System.out.println("O herói esquiva da flecha envenenada!");
                Utilidades.esperar(1500);
            }
        } else {
            System.out.println("A flecha envenenada erra o alvo!");
            Utilidades.esperar(1500);
        }
    }
}
