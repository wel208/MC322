package projetofinal.saves;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "jogador")
@XmlAccessorType(XmlAccessType.FIELD)
public class JogadorAuxiliar {

    private String nome;
    private int nivel;
    private int nLinhas;
    private int pontuacao;

    public JogadorAuxiliar(){}
    
    public JogadorAuxiliar(String nome, int nivel, int nLinhas, int pontuacao){
        this.nome = nome;
        this.nivel = nivel;
        this.nLinhas = nLinhas;
        this.pontuacao = pontuacao;
    }

    public String getNome() { return nome; }
    public int getNivel() { return nivel; }
    public int getNLinhas() { return nLinhas; }
    public int getPontuacao() {return pontuacao; }
}
