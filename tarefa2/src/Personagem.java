public abstract class Personagem{

    //Atributos
    String nome;            //Nome dado ao personagem
    int pontosDeVida;       //Quantidade de pontos de vida que o personagem possui
    int nivel;              //Nível de experiência do personagem
    double protecao;        //Quantidade de pontos que o personagem possui de proteção contra ataques inimigos
    double forca;           //Força do personagem, capacidade de causar dano a inimigos
    int moveSpeed;          //Distância, em metros, que o personagem consegue percorrer em um turno
    int pos;                //Valor que representa a posição do personagem no mapa
    Arma arma;               //Arma que o personagem está utilizando

    //Construtor
    public Personagem(String nome, Arma arma){
        this.nome = nome;
        this.arma = arma;
    }

    //Métodos
    public void statusParcial(){
        System.out.printf("\n%s, O %s, esta com %d PONTOS DE VIDA.\n", nome, Utilidades.verificarClasse(this), pontosDeVida); Utilidades.esperar(1500);
    }

    /*
     * O cálculo do dano é feito com base na força do inimigo e na proteção do personagem
     * 'protecao' < 1 sempre, a proteção absorverá parte da força do inimigo
    */ 
    public void receberDano(double forca){
        pontosDeVida -= forca * (1 - protecao);
    }

    public abstract void atacar(Personagem alvo);

    public abstract void mover(Personagem alvo);

    public abstract void tomarDecisao(Personagem alvo);
}