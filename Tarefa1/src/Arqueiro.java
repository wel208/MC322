public class Arqueiro extends Heroi{

    //Atributos
    int precisao;

    //Construtor
    public Arqueiro(String nome){
        super(nome);
        this.pontosDeVida = 60;
        this.moveSpeed = 6;
        this.dodgeChance = 0.2;
        this.forca = 40;
        this.protecao = 0.2;
        this.precisao = 70;
    }

    //Métodos
    @Override
    public void exibirStatus(){
        System.out.printf("\n%s, O ARQUEIRO possui:", nome); Utilidades.esperar(500);
        System.out.printf("\n%d PONTOS DE VIDA;", pontosDeVida); Utilidades.esperar(500);
        System.out.printf("\nNÍVEL %d e %d/%d de EXPERIÊNCIA;", nivel, experiencia, max_XP); Utilidades.esperar(500);
        System.out.printf("\n%d pontos de FORÇA e %d pontos de PROTEÇÃO;", forca, protecao); Utilidades.esperar(500);
        System.out.printf("\n%d pontos de PRECISÃO;", precisao); Utilidades.esperar(500);
        System.out.printf("\ncapacidade de dar %d ATAQUES POR TURNO e pode percorrer %d METROS POR TURNO.", attackSpeed, moveSpeed); Utilidades.esperar(500);
    }

    @Override
    public void statusParcial(){
        System.out.printf("\n%s, O ARQUEIRO está com %d PONTOS DE VIDA.", pontosDeVida);
    }

    @Override
    public void atacar(Personagem alvo){
        int contador = 0;
        for (int i = 0; i < attackSpeed; i++){

            if (Math.random() < precisao){                      //Caso o arqueiro possivelmente acerte o alvo
                if (Math.random() > alvo.dodgeChance){          //Caso o alvo não consiga se esquivar da flecha
                    contador++;
                    int distancia = Utilidades.calcularDistancia(pos, alvo.pos);

                    if (Math.random() < criticalChance){       //Caso o Arqueiro acerte um ataque crítico
                        alvo.receberDano(forca * 1.3 * distancia/5);
                        System.out.println("ISSO! O nosso arqueiro ACERTOU um ATAQUE CRÍTICO em seu inimigo!");
                    }
                    else{                                      //Caso seja um ataque comum
                        alvo.receberDano(forca * distancia/5);
                        System.out.println("BOA! O nosso arqueiro ACERTOU uma flecha no inimigo!");
                    }
                }
                else                                           //Caso o inimigo consiga desviar do ataque do herói
                    System.out.println("NÃO! O inimigo ESQUIVOU da flecha do nosso herói!");
            }
            else                                               //Caso o arqueiro erre o alvo
                System.out.println("NÃO! O arqueiro ERROU a flechada!");
        }

        System.out.printf("\nO herói acertou %d dos %d ataques dados!", contador, attackSpeed);
    }

    @Override
    public void usarHabilidadeEspecial(Personagem alvo){
        int contador = 0;
        for (int i = 0; i < attackSpeed * 3; i++){

            if (Math.random() < precisao/100 * 0.8){
                if (Math.random() > alvo.dodgeChance){
                    contador++;

                    int distancia = Utilidades.calcularDistancia(pos, alvo.pos);

                    alvo.receberDano(forca * 1.3 * distancia/5);
                    System.out.println("ISSO! O nosso arqueiro ACERTOU um ATAQUE CRÍTICO em seu inimigo!");
                }
                else{
                    System.out.println("NÃO! O inimigo ESQUIVOU da flecha do nosso herói!");
                    continue;
                }
            }
            else
                System.out.println("NÃO! O arqueiro ERROU flecha!");
        }

        System.out.printf("\nO herói acertou %d flechas das %d flechas atiradas!", contador, attackSpeed * 3);
    }

    @Override
    public void melhorarAtributoUnico(){
        precisao += 5;
    }

    @Override
    public void mover(Personagem alvo){ //O arqueiro se move na direção contrária de onde o seu inimigo está
        if (pos > alvo.pos)
            pos += moveSpeed;
        else
            pos -= moveSpeed;
        
        System.out.printf("\nO arqueiro chegou a uma distância de %d metros do monstro!", Utilidades.calcularDistancia(pos, alvo.pos));
    }

    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);
        double chance = Math.random();

        System.out.printf("\nO arqueiro está a %d metros do monstro e irá ", distancia);

        if (distancia <= 5){
            if (chance <= 0.1){
                System.out.print("USAR SUA HABILIDADE ESPECIAL!");
                usarHabilidadeEspecial(alvo);
            }
            else if (chance <= 0.85){
                System.out.print("SE AFASTAR!");
                mover(alvo);
            }
            else{
                System.out.print("ATACAR!");
                atacar(alvo);
            }
        }
        else if (distancia <= 10){
            if (chance <= 0.15){
                System.out.print("USAR SUA HABILIDADE ESPECIAL!");
                usarHabilidadeEspecial(alvo);
            }
            else if (chance <= 0.7){
                System.out.print("SE AFASTAR!");
                mover(alvo);
            }
            else{
                System.out.print("ATACAR!");
                atacar(alvo);
            }
        }
        else if (distancia <= 15){
            if (chance <= 0.25){
                System.out.print("USAR SUA HABILIDADE ESPECIAL!");
                usarHabilidadeEspecial(alvo);
            }
            else if (chance <= 0.4){
                System.out.print("SE AFASTAR!");
                mover(alvo);
            }
            else{
                System.out.print("ATACAR!");
                atacar(alvo);
            }
        }
        else{
            if (chance <= 0.4){
                System.out.print("USAR SUA HABILIDADE ESPECIAL!");
                usarHabilidadeEspecial(alvo);
            }
            else if (chance <= 0.42){
                System.out.print("SE AFASTAR!");
                mover(alvo);
            }
            else{
                System.out.print("ATACAR!");
                atacar(alvo);
            }
        }
    }
}
