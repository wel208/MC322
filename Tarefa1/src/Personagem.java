public abstract class Personagem{

    //Atributos
    String nome;
    int pontosDeVida;
    double forca;              //Força do personagem, capacidade de causar dano a inimigos
    int attackSpeed;        //Quantidade de ataques que o personagem consegue dar por turno
    int moveSpeed;          //Distância, em metros, que o personagem consegue percorrer em um turno
    double dodgeChance;     //Chance que o personagem possui de esquivar de um ataque inimigo

    //Construtor
    public Personagem(String nome, int pontosDeVida){
        this.nome = nome;
        this.pontosDeVida = pontosDeVida;
    }

    //Métodos
    public void exibirStatus(){
        System.out.printf("\nNome: %s", nome);
        System.out.printf("\nPontos de vida: %d", pontosDeVida);
        System.out.printf("\nForça: %d", forca);
    }

    public void receberDano(double dano){
        pontosDeVida -= dano;
    }

    public abstract void atacar(Personagem alvo);
}