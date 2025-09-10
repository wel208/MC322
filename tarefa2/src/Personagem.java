public abstract class Personagem{

    //Atributos
    protected String nome;            //Nome dado ao personagem
    protected int pontosDeVida;       //Quantidade de pontos de vida que o personagem possui
    protected double protecao;        //Quantidade de pontos que o personagem possui de proteção contra ataques inimigos
    protected double forca;           //Força do personagem, capacidade de causar dano a inimigos
    protected int moveSpeed;          //Distância, em metros, que o personagem consegue percorrer em um turno
    protected int pos;                //Valor que representa a posição do personagem no mapa
    protected double criticalChance;  //Chance do personagem causar dano crítico ao inimigo
    protected double sorte;           //Chance do personagem obter sucesso em suas ações
    protected Arma arma;              //Arma que o personagem está utilizando

    //Construtor
    public Personagem(String nome){
        this.nome = nome;
    }

    //Métodos
    public void statusParcial(){
        System.out.printf("\n%s, O %s, esta com %d PONTOS DE VIDA.\n", nome, Utilidades.verificarClasse(this), pontosDeVida); Utilidades.esperar(1500);
    }

    /*
     * O cálculo do dano é feito com base na força do inimigo e na proteção do personagem
     * 'protecao' < 1 sempre, a proteção absorverá parte da força do inimigo
    */ 
    protected void receberDano(double forca){
        pontosDeVida -= forca * (1 - protecao);
    }

    protected abstract void atacar(Personagem alvo);

    protected abstract void mover(Personagem alvo);

    public abstract void tomarDecisao(Personagem alvo);
}