public class Guerreiro extends Heroi{

    //Atributos
    int furia;

    //Construtor
    public Guerreiro(String nome, int HP){
        super(nome, HP, 50, 40, 1, 0.03);
        this.furia = 2;
    }

    //Métodos
    @Override
    public void exibirStatus(){
        System.out.printf("\nO nosso guerreiro %s possui %d pontos de vida.", nome, pontosDeVida);
        System.out.printf("\n%s está no nível %d e possui %d pontos de experiência!", nome, nivel, experiencia);
        System.out.printf("\nUma incrível força admirável de %d pontos e está provido de uma proteção de %d pontos!", forca, protecao);
        System.out.printf("\nSeu nível de fúria está em %d!", furia);
        System.out.printf("\nSua velocidade de ataque é de %d golpes por turno e a de movimento é de %d metros por turno!", attackSpeed, moveSpeed);
    }

    @Override
    public boolean atacar(Personagem alvo){

        double chance = Math.random();
        if (chance < alvo.dodgeChance) //Caso o inimigo consiga esquivar do ataque do guerreiro
            return false;
        
        else{                          //Caso o herói acerte o seu alvo
            alvo.receberDano(forca);
            return true;
        }
    }

    @Override
    public void usarHabilidadeEspecial(Personagem alvo){}
}