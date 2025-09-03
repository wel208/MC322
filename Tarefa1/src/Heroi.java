public abstract class Heroi extends Personagem{

    //Atributos
    int experiencia;        //Quantidade de experiência que o herói possui em seu nível atual
    int max_XP;             //Indica a quantidade de experiência necessária para subir de nível

    //Construtor
    public Heroi(String nome){ //Valores predefinidos para um guerreiro de nível 0
        super(nome);
        this.attackSpeed = 1;
        this.pos = 0;
        this.nivel = 0;
        this.experiencia = 0;
        this.max_XP = 20;
        this.criticalChance = 0.2;
    }

    //Métodos
    public void ganharExperiencia(int new_XP){
        int contador = 0;
        boolean subiu = false;

        int vidaAntigo = pontosDeVida;
        double forcaAntigo = forca;
        double protecaoAntigo = protecao;

        System.out.printf("\n%s recebeu %d pontos de experienca nessa batalha!\n", nome, new_XP);
        Utilidades.esperar(500);
        
        experiencia += new_XP;
    
        while (experiencia >= max_XP){ //Caso o herói suba de nível
            subiu = true;
            contador++;
            nivel++;
            experiencia = experiencia - max_XP;
            
            max_XP += 20;
            forca += 5;
            protecao += 0.05;
            moveSpeed++;
            criticalChance += 0.05;
            dodgeChance += 0.05;
            pontosDeVida += 10;

            if (nivel % 2 == 0){    //A cada dois niveis o herói começa a dar um ataque a mais por turno
                attackSpeed++;
                this.melhorarAtributoUnico();
            }
        }

        if (subiu){
            System.out.printf("\n%s subiu %d NIVEIS de experiencia!", nome, contador); Utilidades.esperar(600);
            System.out.printf("\n%s teve um aumento de:", nome); Utilidades.esperar(600);
            System.out.printf("\n%d pontos de VIDA;", pontosDeVida - vidaAntigo); Utilidades.esperar(600);
            System.out.printf("\n%.0f pontos de FORCA;", forca - forcaAntigo); Utilidades.esperar(600);
            System.out.printf("\n%.0f pontos de PROTECAO.\n", (protecao - protecaoAntigo) * 100); Utilidades.esperar(600);
        }

        exibirStatus(); Utilidades.esperar(600);
    }

    public abstract void exibirStatus();

    public abstract void usarHabilidadeEspecial(Personagem alvo);

    public abstract void melhorarAtributoUnico();
}
