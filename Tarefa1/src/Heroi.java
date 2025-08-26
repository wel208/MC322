public abstract class Heroi extends Personagem{

    //Atributos
    int nivel;
    int experiencia;
    int protecao;
    int attackSpeed;
    int moveSpeed;

    //Construtor
    public Heroi(String nome, int HP, int forca, int protecao, int attackSpeed, int moveSpeed, int nivel, int experiencia){
        super(nome, HP, forca);
        this.protecao = protecao;
        this.attackSpeed = attackSpeed;
        this.moveSpeed = moveSpeed;
        this.nivel = nivel;
        this.experiencia = experiencia;
    }

    //Métodos
    public abstract void ganharExperiencia(int new_XP);

    public abstract void usarHabilidadeEspecial(Personagem alvo);
}
