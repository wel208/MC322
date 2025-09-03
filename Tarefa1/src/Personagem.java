public abstract class Personagem{

    //Atributos
    String nome;            //Nome dado ao personagem
    int pontosDeVida;       //Quantidade de pontos de vida que o personagem possui
    int nivel;              //Nível de experiência do personagem
    double protecao;        //Quantidade de pontos que o personagem possui de proteção contra ataques inimigos
    double forca;           //Força do personagem, capacidade de causar dano a inimigos
    int attackRange;        //Distância máxima que o ataque do personagem alcança
    int attackSpeed;        //Quantidade de ataques que o personagem consegue dar por turno
    int moveSpeed;          //Distância, em metros, que o personagem consegue percorrer em um turno
    double dodgeChance;     //Chance que o personagem possui de esquivar de um ataque inimigo
    double criticalChance;  //Chance do personagem causar dano crítico ao inimigo
    int pos;                //Valor que representa a posição do personagem no mapa

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
    public void receberDano(double forca){
        pontosDeVida -= forca * (1 - protecao);
    }

    public abstract void atacar(Personagem alvo);

    public abstract void mover(Personagem alvo);

    public abstract void tomarDecisao(Personagem alvo);
}