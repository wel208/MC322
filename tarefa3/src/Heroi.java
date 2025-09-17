import java.util.ArrayList;
import java.util.List;

public abstract class Heroi extends Personagem{

    //Atributos
    private int experiencia;        //Quantidade de experiência que o herói possui em seu nível atual
    private int expProximoNivel;    //Indica a quantidade de experiência necessária para subir de nível
    private int nivel;              //Nível de experiência do personagem
    private List<AcaoDeCombate> acoes = List.of(new AtaqueComum(), new HabilidadeEspecial());

    //Construtor
    public Heroi(String nome, Arma arma){ //Valores predefinidos para um heroi de nível 0
        super(nome);
        this.arma = arma;
        this.pos = 0;
        this.nivel = 0;
        this.experiencia = 0;
        this.expProximoNivel = 20;
        this.sorte = 0.3;
        this.criticalChance = 0.05;
    }

    //Métodos

    /*
     * Metódo chamado ao derrotar um inimigo
     * Caso suba de nível, terá melhora em seus atributos
     */
    public void ganharExperiencia(int new_XP){
        int contador = 0;
        boolean subiu = false;

        int vidaAntigo = pontosDeVida;
        double forcaAntigo = forca;
        double protecaoAntigo = protecao;

        System.out.printf("\n%s recebeu %d pontos de experienca nessa batalha!\n", nome, new_XP);
        Utilidades.esperar(200);
        
        experiencia += new_XP;
    
        while (experiencia >= expProximoNivel){ //Caso o herói suba de nível
            this.subirDeNivel();
            subiu = true;
            contador++; 
        }

        if (subiu){                 //Se tiver subido de nível, mostra o melhora nos atributos principais
            System.out.printf("\n%s subiu %d NIVEIS de experiencia!", nome, contador); Utilidades.esperar(200);
            System.out.printf("\n%s teve um aumento de:", nome); Utilidades.esperar(200);
            System.out.printf("\n%d pontos de VIDA;", pontosDeVida - vidaAntigo); Utilidades.esperar(200);
            System.out.printf("\n%.0f pontos de FORCA;", forca - forcaAntigo); Utilidades.esperar(200);
            System.out.printf("\n%.0f pontos de PROTECAO.\n", (protecao - protecaoAntigo) * 100); Utilidades.esperar(200);
        }
    }

    private void subirDeNivel(){ //Método que melhora os atributos do herói ao subir de nível
        experiencia = experiencia - expProximoNivel;
        nivel++;
        expProximoNivel += 10;
        forca += 5;
        protecao += 0.05;
        pontosDeVida += 30;
        sorte += 0.02;

        if (nivel % 2 == 0){    //A cada dois niveis o herói começa a dar um ataque a mais por turno e tem um aumento do atributo único
            this.melhorarAtributoUnico();
        }
    }

    //Método que equipa uma nova arma ao herói
    public void equiparArma(Arma novaArma){
        this.arma = novaArma;
        System.out.printf("\n%s esta equipando a arma %s!\n", nome, novaArma.nome); Utilidades.esperar(200);
    }

    protected int getExperiencia(){
        return experiencia;
    }
    protected int getExpProximoNivel(){
        return expProximoNivel;
    }
    protected int getNivel(){
        return nivel;
    }
    protected void setArma(Arma arma){
        this.arma = arma;
    }

    public abstract void exibirStatus(); //Método que mostra como está todos os atributos do herói no momento

    protected abstract void usarHabilidadeEspecial(Personagem alvo);

    protected abstract void melhorarAtributoUnico();
}
