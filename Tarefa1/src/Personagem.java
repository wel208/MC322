public abstract class Personagem{

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
    public void exibirStatus(){
        System.out.printf("Nome: %s\nPontos de vida: %d\nForça: %s", nome, pontosDeVida, forca);
    }

    public abstract void receberDano(int DMG);

    public abstract void atacar(Personagem alvo);
}