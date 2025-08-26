public class Personagem{

    //Atributos
    String nome;
    int pontosDeVida;
    int forca;

    //Métodos
    public Personagem(String name, int HP, int power){ //Construtor
        this.nome = name;
        this.pontosDeVida = HP;
        this.forca = power;
    }

    public void receberDano(int DMG){
        this.pontosDeVida = this.pontosDeVida - DMG;
    }

    public String exibirStatus(){
        return String.format("Nome do personagem: %s\nQuantidade de vida: %d\n", nome, pontosDeVida);
    }

    public void atacar(Personagem alvo){
        
    }

}