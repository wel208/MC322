public abstract class Monstro extends Personagem{

    //Atributos
    protected int xpConcedido;                 //Quantidade de experiência que o monstro concede ao herói que derrotá-lo
    protected int nivelDificuldade;            //Nível de dificuldade do monstro

    //Construtor
    public Monstro(String nome, int nivelDificuldade, int pos, Arma arma){
        super(nome);
        this.nivelDificuldade = nivelDificuldade;
        this.pos = pos;
        this.arma = arma;
    }

    //Método que integra o monstro com o cenário da história
    public abstract void ambientarMonstro(Heroi heroi);

    public boolean largaArma(){
        double chance = Math.random();

        if (chance < 0.6) //60% de chance de largar uma arma
            return true;
        else
            return false;
    }
}