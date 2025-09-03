public abstract class Monstro extends Personagem{

    //Atributos
    int xpConcedido; //Quantidade de experiência que o monstro cede ao herói que derrotá-lo

    //Construtor
    public Monstro(String nome, int nivel, int pos){
        super(nome);
        this.nivel = nivel;
        this.pos = pos;
        this.criticalChance = 0.2;
    }

    //Métodos
    public abstract void ambientarMonstro(Heroi heroi);
}