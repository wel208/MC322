public abstract class Monstro extends Personagem{

    //Atributos
    int xpConcedido; //Quantidade de experiência que o monstro cede ao herói que derrotá-lo

    //Construtor
    public Monstro(String nome, int pontosDeVida, int nivel, int xpConcedido){
        super(nome, pontosDeVida);
        this.criticalChance = 0.10;
    }

    //Métodos
    public abstract void apresentarMonstro();
}