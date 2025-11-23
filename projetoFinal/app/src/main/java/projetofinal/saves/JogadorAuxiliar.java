package projetofinal.saves;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "jogador")
@XmlAccessorType(XmlAccessType.FIELD)
public class JogadorAuxiliar {

    private String nome;
    private int nivel;
    private int nLinhas;
    private int pontuacao;
    private int nVitorias;

    public JogadorAuxiliar(){}
    
    public JogadorAuxiliar(String nome, int nivel, int nLinhas, int pontuacao){
        this.nome = nome;
        this.nivel = nivel;
        this.nLinhas = nLinhas;
        this.pontuacao = pontuacao;
        nVitorias = 0;
    }

    public String getNome() { return nome; }
    public int getNivel() { return nivel; }
    public int getNLinhas() { return nLinhas; }
    public int getPontuacao() { return pontuacao; }
    public int getVitorias() { return nVitorias; }

    public void setPontuacao(int pontuacao) { this.pontuacao = pontuacao; }
    public void setNivel(int nivel) { this.nivel = nivel; }
    public void setNLinhas(int nLinhas) { this.nLinhas = nLinhas; }
    public void aumentarVitorias(){
        nVitorias += 1;
    }
}
