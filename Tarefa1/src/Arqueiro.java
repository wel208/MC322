public class Arqueiro extends Heroi{

    //Atributos
    int precisao;

    //Construtor
    public Arqueiro(String nome, int HP, int forca, int nivel, int experiencia, int precisao){
        super(nome, HP, forca, nivel, experiencia);
        this.precisao = precisao;
    }

    //Métodos
    @Override
    public void exibirStatus(){
        System.out.printf("");
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
