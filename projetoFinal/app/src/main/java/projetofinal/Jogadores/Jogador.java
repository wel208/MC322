package projetofinal.Jogadores;

public class Jogador {

    private String nome;
    private int pontos;

    public Jogador(String nome) {
        this.nome = nome;
        this.pontos = 0;
    }

    public void pausar() {
        
    }

    public void moverDir() {

    }

    public void moverEsq() {

    }

    public void acelerarQueda() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void aumentarPontos(int pontos) {
        this.pontos += pontos;
    }

    public void resetarPontos() {
        this.pontos = 0;
    }
}