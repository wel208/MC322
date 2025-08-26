public class Personagem{

    //Atributos
    String nome;
    int pontosDeVida;
    int forca;

    //Construtor
    public Personagem(String name, int HP, int power){
        this.nome = name;
        this.pontosDeVida = HP;
        this.forca = power;
    }

    //Métodos
    public void receberDano(int DMG){
        this.pontosDeVida -= DMG;
    }

    public String exibirStatus(){
        return String.format("Nome do personagem: %s\nQuantidade de vida: %d\n", nome, pontosDeVida);
    }

    public void atacar(Personagem alvo){
        
    }
}


class Heroi extends Personagem{

    //Atributos
    int nivel;
    int experiencia;

    //Construtor
    public Heroi(String name, int HP, int power, int lvl, int XP){
        super(name, HP, power);
        this.nivel = lvl;
        this.experiencia = XP;
    }

    //Métodos
    public void ganharExperiencia(int new_XP){
        this.experiencia += new_XP;
    }

    @Override
    public String exibirStatus(){
        return String.format("Nome do personagem: %s\nQuantidade de vida: %d\nNível do personagem: %d\nQuantidade de experiência: %d\n", nome, pontosDeVida, nivel, experiencia);
    }
}