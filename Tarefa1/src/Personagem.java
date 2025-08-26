public class Personagem{

    //Atributos
    String nome;
    int pontosDeVida;
    int forca;

    //Construtor
    public Personagem(String nome, int HP, int forca){
        this.nome = nome;
        this.pontosDeVida = HP;
        this.forca = forca;
    }

    //Métodos
    public void receberDano(int DMG){
        pontosDeVida -= DMG;
    }

    public String exibirStatus(){
        return String.format("Nome do personagem: %s\nQuantidade de vida: %d\n", nome, pontosDeVida);
    }

    public void atacar(Personagem alvo){
        alvo.receberDano(forca);
    }
}