public abstract class Heroi extends Personagem{

    //Atributos
    int experiencia;        //Quantidade de experiência que o herói possui em seu nível atual
    int max_XP;             //Indica a quantidade de experiência necessária para subir de nível

    //Construtor
    public Heroi(String nome, int pontosDeVida){ //Valores predefinidos para um guerreiro de nível 0
        super(nome, pontosDeVida);
        this.attackSpeed = 1;
        this.nivel = 0;
        this.experiencia = 0;
        this.max_XP = 50;
        this.criticalChance = 0.2;
    }

    //Métodos
    public boolean ganharExperiencia(int new_XP){
        experiencia += new_XP;
        
        if (experiencia >= max_XP){ //Caso o herói suba de nível
            nivel++;
            experiencia = experiencia - max_XP;
            
            max_XP += 50;
            forca += 5;
            protecao += 5;
            moveSpeed++;
            criticalChance += 0.05;
            dodgeChance += 0.05;

            if (nivel % 2 == 0){    //A cada dois niveis o herói começa a dar um ataque a mais por turno
                attackSpeed++;
                this.melhorarAtributoUnico();
            }

            return true;
        }

        else return false;
    }

    public abstract void usarHabilidadeEspecial(Personagem alvo);

    public abstract void melhorarAtributoUnico();
}
