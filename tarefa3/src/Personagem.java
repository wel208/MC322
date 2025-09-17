public abstract class Personagem implements Combatente{

    // Atributos base
    protected String nome;
    protected int pontosDeVida;
    protected int pontosDeVidaMax;
    protected double protecao;
    protected double forca;
    protected int moveSpeed;
    protected int pos;
    protected double criticalChance;
    protected double sorte;
    protected double dodgeChance;
    protected Arma arma;

    // Construtor
    public Personagem(String nome){
        this.nome = nome;
    }

    // Métodos utilitários
    public void statusParcial(){
        System.out.printf("\n%s, O %s, está com %d PONTOS DE VIDA.\n", nome, Utilidades.verificarClasse(this), pontosDeVida); Utilidades.esperar();
    }

    public int receberDano(double forca){
        pontosDeVida -= (int)(forca * (1 - protecao));
        if (pontosDeVida < 0) pontosDeVida = 0;
        return (int)(forca * (1 - protecao));
    }

    // Getters
    public String getNome(){
        return nome;
    }
    public double getDodgeChance(){ 
        return dodgeChance;
    }
    public double getCriticalChance(){ 
        return criticalChance; 
    }
    public int getPos(){ 
        return pos; 
    }
    public double getSorte(){ 
        return sorte; 
    }
    public int getPontosDeVida(){ 
        return pontosDeVida; 
    }
    public int getPontosDeVidaMax(){ 
        return pontosDeVidaMax;
    }
    public void setPontosDeVidaMax(int pontosDeVidaMax) {
        this.pontosDeVidaMax = pontosDeVidaMax;
        if (pontosDeVida > pontosDeVidaMax)
            pontosDeVida = pontosDeVidaMax;
    }
}
