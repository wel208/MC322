public class Guerreiro extends Heroi{

    //Atributos
    int furia;

    //Construtor
    public Guerreiro(String nome, int HP, int forca, int nivel, int experiencia, int furia){
        super(nome, HP, forca, nivel, experiencia);
        this.furia = furia;
    }

    //Métodos
    @Override
    public void atacar(Personagem alvo){
        
    }
}