import java.util.List;
public abstract class Monstro extends Personagem implements Lootavel {

    // Atributos
    protected int xpConcedido;      // Quantidade de experiência que o monstro concede ao herói que derrotá-lo
    protected int nivelDificuldade; // Nível de dificuldade do monstro
    protected List<AcaoDeCombate> acoes = List.of(new Mover(), new AtaqueComum());

    // Construtor
    public Monstro(String nome, int nivelDificuldade, int pos, Arma arma) {
        super(nome);
        this.nivelDificuldade = nivelDificuldade;
        this.pos = pos;
        this.arma = arma;

        // Valores padrão para todos os monstros
        this.dodgeChance = 0.05 + (nivelDificuldade * 0.01); // Exemplo: 5% base + 1% por nível
    }

    public Item droparLoot(){
        return arma;
    }
}
