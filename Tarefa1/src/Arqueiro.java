public class Arqueiro extends Heroi{

    //Atributos
    int precisao;

    //Construtor
    public Arqueiro(String nome, int pontosDeVida){
        super(nome, pontosDeVida);
        this.moveSpeed = 5;
        this.dodgeChance = 0.2;
        this.forca = 40;
        this.protecao = 20;
        this.precisao = 70;
    }

    //Métodos
    @Override
    public void exibirStatus(){
        System.out.printf("\nO nosso arqueiro %s possui %d pontos de vida.", nome, pontosDeVida);
        System.out.printf("\n%s está no nível %d e possui %d pontos de experiência!", nome, nivel, experiencia);
        System.out.printf("\nUma admirável força de %d pontos e está provido de uma proteção de %d pontos!", forca, protecao);
        System.out.printf("\nAtualmente ele possui uma precisão de %.2f%%!", precisao);
        System.out.printf("\nSua velocidade de ataque é de %d flechadas por turno e a de movimento é de %d metros por turno!", attackSpeed, moveSpeed);
    }

    @Override
    public void atacar(Personagem alvo){
        int contador = 0;
        for (int i = 0; i < attackSpeed; i++){

            if (Math.random() > alvo.dodgeChance){
                contador++;

                if (Math.random() < criticalChance){
                    alvo.receberDano(forca * 1.3);  //PRECISA ADICIONAR O CALCULO DO DANO DO ARQUEIRO CONSIDERANDO DISTANCIA
                    System.out.println("\nISSO! O nosso guerreiro ACERTOU um ATAQUE CRÍTICO em seu inimigo!");
                }
                else{                                      //Caso seja um ataque comum
                    alvo.receberDano(forca);
                    System.out.println("\nBOA! O nosso guerreiro ACERTOU um golpe no inimigo!");
                }
            }
            else                                           //Caso o inimigo consiga desviar do ataque do herói
                System.out.println("\nNÃO! O inimigo ESQUIVOU do ataque do nosso herói!");
        }

        System.out.printf("\nO herói acertou %d dos %d ataques dados!", contador, attackSpeed);
    }

    @Override
    public void usarHabilidadeEspecial(Personagem alvo){
        
    }

    @Override
    public void melhorarAtributoUnico(){}
}
