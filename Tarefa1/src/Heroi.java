public abstract class Heroi extends Personagem{

    //Atributos
    int nivel;
    int experiencia;
    int attackSpeed;

    //Construtor
    public Heroi(String nome, int HP, int forca, int nivel, int experiencia){
        super(nome, HP, forca);
        this.nivel = nivel;
        this.experiencia = experiencia;
    }

    //Métodos
    public abstract void ganharExperiencia(int new_XP);

    public abstract void usarHabilidadeEspecial(Personagem alvo);
}
