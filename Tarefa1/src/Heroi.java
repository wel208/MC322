public class Heroi extends Personagem{

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
    public void ganharExperiencia(int new_XP){
        this.experiencia += new_XP;
    }

    public void usarHabilidadeEspecial(Personagem alvo){

    }

    @Override
    public String exibirStatus(){
        return String.format("Nome do personagem: %s\nQuantidade de vida: %d\nNível do personagem: %d\nQuantidade de experiência: %d\n", nome, pontosDeVida, nivel, experiencia);
    }
}
