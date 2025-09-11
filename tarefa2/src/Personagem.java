import java.util.ArrayList;
import java.util.List;

public abstract class Personagem {

    // Atributos
    protected String nome;            // Nome dado ao personagem
    protected int pontosDeVida;       // Quantidade de pontos de vida que o personagem possui
    protected int pontosDeVidaMax;    // Pontos de vida maxima que o personagem pode se curar
    protected double protecao;        // Proteção contra ataques inimigos
    protected double forca;           // Força do personagem
    protected int moveSpeed;          // Distância que o personagem percorre por turno
    protected int pos;                // Posição no mapa
    protected double criticalChance;  // Chance de causar dano crítico
    protected double sorte;           // Chance de sucesso em ações
    protected double dodgeChance;     // Chance de esquivar de ataques (0.0 a 1.0)
    protected int attackSpeed;        // Quantidade de ataques por turno
    protected Arma arma;              // Arma utilizada

    // Lista para armazenar status ativos
    protected List<Status> statusAtivos = new ArrayList<>();

    // Construtor
    public Personagem(String nome){
        this.nome = nome;
    }

    // Métodos
    public void statusParcial(){
        System.out.printf("\n%s, O %s, está com %d PONTOS DE VIDA.\n",
            nome, Utilidades.verificarClasse(this), pontosDeVida);
        Utilidades.esperar(1500);
    }

    /*
     * O cálculo do dano é feito com base na força do inimigo e na proteção do personagem
     * 'protecao' < 1 sempre, a proteção absorverá parte da força do inimigo
    */ 
    protected void receberDano(double forca){
        pontosDeVida -= forca * (1 - protecao);
    }

    // Método para aplicar status
    public void aplicarStatus(String nomeStatus, int duracao){
        statusAtivos.add(new Status(nomeStatus, duracao));
        System.out.printf("%s recebeu o status %s por %d turnos.\n", nome, nomeStatus, duracao);
    }

    // Getters e Setters
    public double getDodgeChance() {
        return dodgeChance;
    }

    public void setDodgeChance(double dodgeChance) {
        this.dodgeChance = dodgeChance;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public double getCriticalChance() {
        return criticalChance;
    }

    public double getForca() {
        return forca;
    }

    public int getPos() {
        return pos;
    }
    public int getPontosDeVida() {
    return pontosDeVida;
    }

    public void setPontosDeVida(int pontosDeVida) {
        this.pontosDeVida = pontosDeVida;
    }

    public int getPontosDeVidaMax() {
        return pontosDeVidaMax;
    }

    public void setPontosDeVidaMax(int pontosDeVidaMax) {
        this.pontosDeVidaMax = pontosDeVidaMax;
    }


    // Métodos abstratos que as subclasses devem implementar
    protected abstract void atacar(Personagem alvo);

    protected abstract void mover(Personagem alvo);

    public abstract void tomarDecisao(Personagem alvo);

    // Classe interna simples para representar um status
    protected static class Status {
        String nome;
        int duracao;

        Status(String nome, int duracao){
            this.nome = nome;
            this.duracao = duracao;
        }
    }
}
