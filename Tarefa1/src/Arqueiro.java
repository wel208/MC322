public class Arqueiro extends Heroi{

    //Atributos
    float precisao;

    //Construtor
    public Arqueiro(String nome, int HP, int forca, int protecao, int attackSpeed, int moveSpeed, int nivel, int experiencia, float precisao){
        super(nome, HP, forca, protecao, attackSpeed, moveSpeed, nivel, experiencia);
        this.precisao = precisao;
    }

    //Métodos
    @Override
    public void exibirStatus(){
        System.out.printf("\nO nosso arqueiro %s possui %d pontos de vida.", nome, pontosDeVida);
        System.out.printf("\n%s está no nível %d e possui %d pontos de experiência!", nome, nivel, experiencia);
        System.out.printf("\nUma admirável força de %d pontos e está provido de uma proteção de %d pontos!", forca, protecao);
        System.out.printf("\nAtualmente ele possui uma precisão de %.2f%%!", precisao * 100);
        System.out.printf("\nSua velocidade de ataque é de %d flechadas por turno e a de movimento é de %d metros por turno!", attackSpeed, moveSpeed);
    }

    @Override
    public void receberDano(int DMG){}

    @Override
    public void atacar(Personagem alvo){}

    @Override
    public void ganharExperiencia(int new_XP){}

    @Override
    public void usarHabilidadeEspecial(Personagem alvo){}
}
