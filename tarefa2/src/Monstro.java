public abstract class Monstro extends Personagem {

    // Atributos
    protected int xpConcedido;      // Quantidade de experiência que o monstro concede ao herói que derrotá-lo
    protected int nivelDificuldade; // Nível de dificuldade do monstro

    // Construtor
    public Monstro(String nome, int nivelDificuldade, int pos, Arma arma) {
        super(nome);
        this.nivelDificuldade = nivelDificuldade;
        this.pos = pos;
        this.arma = arma;

        // Valores padrão para todos os monstros
        this.attackSpeed = 1 + (nivelDificuldade / 3); // Exemplo: aumenta com a dificuldade
        this.dodgeChance = 0.05 + (nivelDificuldade * 0.01); // Exemplo: 5% base + 1% por nível
    }

    // Método que integra o monstro com o cenário da história
    public abstract void ambientarMonstro(Heroi heroi);

    public boolean largaArma() {
        return Math.random() < 0.6; // 60% de chance de largar uma arma
    }
}
