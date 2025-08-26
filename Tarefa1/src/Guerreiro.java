public class Guerreiro extends Heroi{

    //Atributos
    int furia;

    //Construtor
    public Guerreiro(String nome, int HP, int forca, int protecao, int attackSpeed, 
                     int moveSpeed, int nivel, int experiencia, int furia){
        super(nome, HP, forca, protecao, attackSpeed, moveSpeed, nivel, experiencia);
        this.furia = furia;
    }

    //Métodos
    @Override
    public void exibirStatus(){
        System.out.printf("\nO nosso guerreiro %s possui %d pontos de vida.", nome, pontosDeVida);
        System.out.printf("\n%s está no nível %d e possui %d pontos de experiência!", nome, nivel, experiencia);
        System.out.printf("\nUma incrível força admirável de %d pontos e está provido de uma proteção de %d pontos!", forca, protecao);
        System.out.printf("\nSeu nível de fúria está em %d!");
        System.out.printf("\nSua velocidade de ataque é de %d golpes por turno e a de movimento é de %d metros por turno!", attackSpeed, moveSpeed);
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