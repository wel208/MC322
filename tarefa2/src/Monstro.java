public abstract class Monstro extends Personagem{

    //Atributos
    int xpConcedido; //Quantidade de experiência que o monstro concede ao herói que derrotá-lo

    //Construtor
    public Monstro(String nome, int nivel, int pos){
        super(nome);
        this.nivel = nivel;
        this.pos = pos;
        this.criticalChance = 0.2;
    }

    //Método que integra o monstro com o cenário da história
    public abstract void ambientarMonstro(Heroi heroi);
}