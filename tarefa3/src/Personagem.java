import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Personagem {

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

    // Getter para o nome
    public String getNome(){
        return nome;
    }

    // Métodos utilitários
    public void statusParcial(){
        System.out.printf("\n%s, O %s, está com %d PONTOS DE VIDA.\n", nome, Utilidades.verificarClasse(this), pontosDeVida);
        Utilidades.esperar(200);
    }

    protected void receberDano(double forca){
        pontosDeVida -= forca * (1 - protecao);
        if (pontosDeVida < 0) pontosDeVida = 0;
    }

    // Getters
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
        if (pontosDeVida > pontosDeVidaMax) pontosDeVida = pontosDeVidaMax;
    }

    // Métodos abstratos
    protected abstract void atacar(Personagem alvo);
    protected abstract void mover(Personagem alvo);
    public abstract void tomarDecisao(Personagem alvo);
}
