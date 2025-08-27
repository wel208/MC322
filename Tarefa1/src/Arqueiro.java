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

    }

    @Override
    public void usarHabilidadeEspecial(Personagem alvo){}

    @Override
    public void melhorarAtributoUnico(){}
}
