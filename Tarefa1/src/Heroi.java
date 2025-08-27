public abstract class Heroi extends Personagem{

    //Atributos
    int nivel;
    int experiencia;
    int max_XP;             //Indica a quantidade de experiência necessária para subir de nível
    int protecao;           //Pontos de proteção que o herói possui contra ataque de inimigos
    double criticalChance;  //A chance de um ataque certo causar dano crítico

    //Construtor
    public Heroi(String nome, int HP, int forca, int protecao, int moveSpeed, double dodgeChance){
        super(nome, HP, forca, 1, moveSpeed, dodgeChance);
        this.protecao = protecao;
        this.nivel = 0;
        this.experiencia = 0;
        this.max_XP = 50;
        this.criticalChance = 0.05;
    }

    //Métodos
    @Override
    public void receberDano(int forca){
        pontosDeVida -= forca * (1 - protecao); //O cálculo leva em consideração a quantidade de proteção que o herói possui contra ataques
    }

    public boolean ganharExperiencia(int new_XP, int max_XP){
        experiencia += new_XP;
        
        if (experiencia >= max_XP){ //Caso o herói suba de nível
            nivel++;
            experiencia -= (experiencia - max_XP);
            
            max_XP += 50;
            forca += 5;
            protecao += 3;
            criticalChance += 0.02;
            dodgeChance += 0.02;

            if (nivel > 0 && nivel % 3 == 0)
                attackSpeed++;

            return true;
        }

        else return false;
    }

    public abstract void usarHabilidadeEspecial(Personagem alvo);
}
