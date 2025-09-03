public class Arqueiro extends Heroi{

    //Atributos
    double precisao;

    //Construtor
    public Arqueiro(String nome){
        super(nome);
        this.pontosDeVida = 60;
        this.moveSpeed = 10;
        this.dodgeChance = 0.2;
        this.forca = 40;
        this.protecao = 0.4;
        this.precisao = 70;
    }

    //Métodos
    @Override
    public void exibirStatus(){
        System.out.printf("\n%s, O ARQUEIRO possui:", nome); Utilidades.esperar(1000);
        System.out.printf("\n%d PONTOS DE VIDA;", pontosDeVida); Utilidades.esperar(1000);
        System.out.printf("\nNIVEL %d e %d/%d de EXPERIENCIA;", nivel, experiencia, max_XP); Utilidades.esperar(1000);
        System.out.printf("\n%.0f pontos de FORCA e %.0f pontos de PROTECAO;", forca, protecao * 100); Utilidades.esperar(1000);
        System.out.printf("\n%.0f pontos de PRECISAO;", precisao); Utilidades.esperar(1000);
        System.out.printf("\nCapacidade de dar %d ATAQUE(S) POR TURNO e pode percorrer %d METROS POR TURNO.\n", attackSpeed, moveSpeed); Utilidades.esperar(1000);
    }

    @Override
    public void atacar(Personagem alvo){
        int contador = 0;
        for (int i = 0; i < attackSpeed; i++){

            if (Math.random() < precisao/100){                  //Caso o arqueiro possivelmente acerte o alvo
                if (Math.random() > alvo.dodgeChance){          //Caso o alvo não consiga se esquivar da flecha
                    contador++;
                    int distancia = Utilidades.calcularDistancia(pos, alvo.pos);

                    if (Math.random() < criticalChance){       //Caso o Arqueiro acerte um ataque crítico
                        alvo.receberDano(forca * 1.3 * distancia/7);
                        System.out.println("ISSO! O nosso arqueiro ACERTOU um ATAQUE CRITICO em seu inimigo!"); Utilidades.esperar(1500);
                    }
                    else{                                      //Caso seja um ataque comum
                        alvo.receberDano(forca * distancia/7);
                        System.out.println("BOA! O nosso arqueiro ACERTOU uma flecha no inimigo!"); Utilidades.esperar(1500);
                    }
                }
                else                                           //Caso o inimigo consiga desviar do ataque do herói
                    System.out.println("NAO! O inimigo ESQUIVOU da flecha do nosso heroi!"); Utilidades.esperar(1500);
            }
            else                                               //Caso o arqueiro erre o alvo
                System.out.println("NAO! O arqueiro ERROU a flechada!"); Utilidades.esperar(1500);
        }

        System.out.printf("\nO heroi acertou %d de %d ataque(s) dado(s)!\n", contador, attackSpeed); Utilidades.esperar(1500);
    }

    @Override
    public void usarHabilidadeEspecial(Personagem alvo){
        System.out.printf("%s atirara %d flechas neste turno!\n\n", nome, attackRange + 3); Utilidades.esperar(1500);

        int contador = 0;
        for (int i = 0; i < attackSpeed + 3; i++){

            if (Math.random() < (precisao/100) * 0.8){

                if (Math.random() > alvo.dodgeChance){
                    contador++;

                    int distancia = Utilidades.calcularDistancia(pos, alvo.pos);

                    alvo.receberDano(forca * distancia/5);
                    System.out.println("ISSO! O nosso arqueiro ACERTOU um ATAQUE CRITICO em seu inimigo!"); Utilidades.esperar(1500);
                }
                else{
                    System.out.println("NAO! O inimigo ESQUIVOU da flecha do nosso heroi!"); Utilidades.esperar(1500);
                    continue;
                }
            }
            else
                System.out.println("NAO! O arqueiro ERROU flecha!"); Utilidades.esperar(1500);
        }

        System.out.printf("\nO heroi acertou %d flechas das %d flechas atiradas!", contador, attackSpeed + 3); Utilidades.esperar(1500);
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
        
        System.out.printf("O arqueiro chegou a uma distancia de %d metros do monstro!\n", Utilidades.calcularDistancia(pos, alvo.pos)); Utilidades.esperar(1500);
    }

    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);
        double chance = Math.random();

        System.out.printf("\nO arqueiro esta a %d metros do monstro e ira ", distancia);

        if (distancia <= 5){
            if (chance <= 0.1){
                System.out.println("USAR SUA HABILIDADE ESPECIAL!\n"); Utilidades.esperar(1500);
                usarHabilidadeEspecial(alvo);
            }
            else if (chance <= 0.85){
                System.out.println("SE AFASTAR!\n"); Utilidades.esperar(1500);
                mover(alvo);
            }
            else{
                System.out.println("ATACAR!\n"); Utilidades.esperar(1500);
                atacar(alvo);
            }
        }
        else if (distancia <= 10){
            if (chance <= 0.15){
                System.out.println("USAR SUA HABILIDADE ESPECIAL!\n"); Utilidades.esperar(1500);
                usarHabilidadeEspecial(alvo);
            }
            else if (chance <= 0.7){
                System.out.println("SE AFASTAR!\n"); Utilidades.esperar(1500);
                mover(alvo);
            }
            else{
                System.out.println("ATACAR!\n"); Utilidades.esperar(1500);
                atacar(alvo);
            }
        }
        else if (distancia <= 15){
            if (chance <= 0.25){
                System.out.println("USAR SUA HABILIDADE ESPECIAL!\n"); Utilidades.esperar(1500);
                usarHabilidadeEspecial(alvo);
            }
            else if (chance <= 0.4){
                System.out.println("SE AFASTAR!\n"); Utilidades.esperar(1500);
                mover(alvo);
            }
            else{
                System.out.println("ATACAR!\n"); Utilidades.esperar(1500);
                atacar(alvo);
            }
        }
        else{
            if (chance <= 0.4){
                System.out.println("USAR SUA HABILIDADE ESPECIAL!\n"); Utilidades.esperar(1500);
                usarHabilidadeEspecial(alvo);
            }
            else if (chance <= 0.42){
                System.out.println("SE AFASTAR!\n"); Utilidades.esperar(1500);
                mover(alvo);
            }
            else{
                System.out.println("ATACAR!\n"); Utilidades.esperar(1500);
                atacar(alvo);
            }
        }
    }
}
