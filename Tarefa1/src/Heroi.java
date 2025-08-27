public abstract class Heroi extends Personagem{

    //Atributos
    int nivel;
    int experiencia;
    int max_XP;             //Indica a quantidade de experiência necessária para subir de nível
    int protecao;           //Pontos de proteção que o herói possui contra ataque de inimigos
    double criticalChance;  //A chance de um ataque certo causar dano crítico

    //Construtor
    public Heroi(String nome, int pontosDeVida){
        super(nome, pontosDeVida);
        this.attackSpeed = 1;
        this.nivel = 0;
        this.experiencia = 0;
        this.max_XP = 50;
        this.criticalChance = 0.2;
    }

    //Métodos
    @Override
    public void receberDano(double forca){
        pontosDeVida -= forca * (1 - protecao/100); //O cálculo leva em consideração a quantidade de proteção que o herói possui contra ataques
    }

    public boolean ganharExperiencia(int new_XP, int max_XP){
        experiencia += new_XP;
        
        if (experiencia >= max_XP){ //Caso o herói suba de nível
            nivel++;
            experiencia = experiencia - max_XP;
            
            max_XP += 50;
            forca += 5;
            protecao += 5;
            criticalChance += 0.05;
            dodgeChance += 0.05;

            if (nivel > 0 && nivel % 3 == 0){ //A cada três niveis o herói começa a dar um ataque a mais por turno
                attackSpeed++;
                this.melhorarAtributoUnico(this);
            }

            return true;
        }

        else return false;
    }

    public abstract int usarHabilidadeEspecial(Personagem alvo);

    public abstract void melhorarAtributoUnico(Personagem heroi);
}
