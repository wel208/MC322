public abstract class Personagem implements Combatente{

    // Atributos base
    private String nome;
    private int pontosDeVida;
    private int pontosDeVidaMax;
    private double protecao;
    private double forca;
    private int moveSpeed;
    private int pos;
    private double criticalChance;
    private double sorte;
    private double dodgeChance;
    private Arma arma;

    // Construtor
    public Personagem(String nome){
        this.nome = nome;
    }

    // Métodos utilitários
    public void statusParcial(){
        System.out.printf("\n%s, O %s, está com %d PONTOS DE VIDA.\n", nome, Utilidades.verificarClasse(this), pontosDeVida);
        Utilidades.esperar(200);
    }

    protected void receberDano(double forca){
        pontosDeVida -= (int)(forca * (1 - protecao));
        if (pontosDeVida < 0) pontosDeVida = 0;
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
    public double getForca(){
        return forca;
    }
    public int getMoveSpeed(){
        return moveSpeed;
    }
    public Arma getArma(){
        return arma;
    }
    public void setPontosDeVidaMax(int pontosDeVidaMax) {
        this.pontosDeVidaMax = pontosDeVidaMax;
        if (pontosDeVida > pontosDeVidaMax)
            pontosDeVida = pontosDeVidaMax;
    }
}
