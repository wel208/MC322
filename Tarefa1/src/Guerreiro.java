public class Guerreiro extends Heroi{

    //Atributos
    int furia;

    //Construtor
    public Guerreiro(String nome, int HP){
        super(nome, HP);
        this.dodgeChance = 0.4;
        this.forca = 70;
        this.protecao = 40;
        this.furia = 2;
    }

    //Métodos
    @Override
    public void exibirStatus(){
        System.out.printf("\nO nosso guerreiro %s possui %d pontos de vida.", nome, pontosDeVida);
        System.out.printf("\n%s está no nível %d e possui %d pontos de experiência!", nome, nivel, experiencia);
        System.out.printf("\nUma incrível força admirável de %d pontos e está provido de uma proteção de %d pontos!", forca, protecao);
        System.out.printf("\nSeu nível de fúria está em %d!", furia);
        System.out.printf("\nSua velocidade de ataque é de %d golpes por turno e a de movimento é de %d metros por turno!", attackSpeed, moveSpeed);
    }

    @Override
    public void atacar(Personagem alvo){
        for (int i = 0; i < attackSpeed; i++){

            if (Math.random() > alvo.dodgeChance){         //Caso o herói acerte o seu alvo

                if (Math.random() < this.criticalChance){  //Caso o ataque seja crítico
                    alvo.receberDano(forca * 1.3);
                    System.out.println("\nISSO! O nosso guerreiro ACERTOU um ATAQUE CRÍTICO em seu inimigo!");
                }
                else{                                      //Caso seja um ataque comum
                    alvo.receberDano(forca);
                    System.out.println("\nBOA! O nosso guerreiro ACERTOU um golpe no inimigo!");
                }
            }
            else
                System.out.println("\nNÃO! O inimigo ESQUIVOU do ataque do nosso herói!");
        }
    }

    /* A habilidade especial do guerreiro é atacar 'furia' vezes mais que o normal em apenas um turno
       A furia faz com que ele ataque mais vezes e com a força 50% maior que o seu nível de força
       O inimigo também tem uma redução na chance de se esquivar de um ataque do herói */
    @Override
    public int usarHabilidadeEspecial(Personagem alvo){
        int contador = 0;
        System.out.printf("AHHHH!! O NOSSO HERÓI ENTROU EM FÚRIA E ATACARÁ %d VEZES NESTE TURNO!", attackSpeed * furia);

        for(int i = 0; i < attackSpeed * furia; i++){       
            if (Math.random() > alvo.dodgeChance * 0.2){   //Caso o herói acerte o seu alvo
                alvo.receberDano(forca * 1.5);
                contador++;
            }
        }

        return contador;
    }

    @Override
    public void melhorarAtributoUnico(Personagem heroi){

    }
}