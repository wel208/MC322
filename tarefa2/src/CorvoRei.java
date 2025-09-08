import java.util.Random;

/*
 * Classe de monstro com movimentação rápida
 * Executa ataques a qualquer distância
 * Não possui muitos pontos de vida ou proteção
 * Boa chance de esquivar de ataques
 * Não possui muita força
 */

public class CorvoRei extends Monstro{
    
    //Construtor
    public CorvoRei(String nome, int nivel, int pos){
        super(nome, nivel, pos);
        this.attackSpeed = 5 + nivel % 3;
        this.dodgeChance = 0.4 + 0.02 * nivel;
        this.forca = 6 + nivel/5;
        this.moveSpeed = 12;
        this.protecao = 0.1;
        this.pontosDeVida = 20 + (2 * (nivel - 2));
        this.xpConcedido = 30 + (2 * nivel);
    }

    //Métodos
    @Override
    public void ambientarMonstro(Heroi heroi){
        //Ambientação
        System.out.println("\nApos certo tempo caminhando, o nosso heroi percebe que o barulho dos corvos esta cada vez mais alto."); Utilidades.esperar(2000);
        System.out.println("Ate que chega o momento em que " + nome + " aparece, o Corvo Rei."); Utilidades.esperar(2000);
        System.out.println("Um corvo com penas que refletem luz de forma que parece ter pedacos de ouro em suas asas."); Utilidades.esperar(2000);
        System.out.println("Ele, cercado de sua horda de corvos, se mostra pronto para impedir que o heroi complete seu objetivo."); Utilidades.esperar(2000);
        System.out.println("Com um simples corvejo, pode fazer com que seus capangas executem suas vontades."); Utilidades.esperar(2000);
        System.out.println("Porem ele tem um problema: ele nao pode fazer isso enquanto voa. Ele pode mandar seus servos atacarem, mas nao pode voar enquanto isso."); Utilidades.esperar(2000);
        System.out.println("Com " + nome + " morto, sua horda nao conseguira seguir em combate."); Utilidades.esperar(2000);
        System.out.println("O nosso heroi sabe disso e usara essa informacao a seu favor."); Utilidades.esperar(2000);
        System.out.println("O heroi entao se prepara para a batalha."); Utilidades.esperar(2000);

        //Apresentação de atributos
        System.out.printf("\nEste e um oponente agil, consegue se movimentar rapidamente de um local para outro."); Utilidades.esperar(2000);
        System.out.printf("\nAlem de conseguir esquivar de ataques inimigos com certa facilidade"); Utilidades.esperar(2000);
        System.out.printf("\n%s, O CORVO REI, que esta no nivel %d, possui:", nome, nivel); Utilidades.esperar(2000);
        System.out.printf("\n%d PONTOS DE VIDA;", pontosDeVida); Utilidades.esperar(1500);
        System.out.printf("\nSeus capangas tem %.0f PONTOS DE FORCA;", forca); Utilidades.esperar(1500);
        System.out.printf("\nE, ao morrer, concedera %d PONTOS DE EXPERIENCIA ao heroi\n", xpConcedido); Utilidades.esperar(1500);

        System.out.println("\nAgora, tomado pela raiva, o nosso heroi comeca a sua batalha!"); Utilidades.esperar(2000);
    }

    /*
     * Se ele estiver a uma distância <= 1 de seu inimigo, irá se movimentar
     * Se não, ele escolhe entre atacar ou se mover, com preferência pouco maior para atacar
     */
    @Override
    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);
        double chance = Math.random();

        System.out.printf("\nO CORVO REI esta a %d metro(s) do %s e ira ", distancia, Utilidades.verificarClasse(alvo));
        
        if (distancia <= 1){
            System.out.println("SE MOVER!\n"); Utilidades.esperar(1500);
            mover(alvo);
        }
        else{
            if (chance < 0.4){
                System.out.println("SE MOVER!\n"); Utilidades.esperar(1500);
                mover(alvo);
            }
            else{
                System.out.println("ATACAR!\n"); Utilidades.esperar(1500);
                atacar(alvo);
            }
        }
    }

    /*
     * Seu ataque consiste em enviar seus capangas atacarem o herói
     * Os corvos atacam 'attackSpeed' vezes, tendo chance do guerreiro esquivar
     */
    @Override
    public void atacar(Personagem alvo){
        Random r = new Random();
        String[] corvejo = {"CROAC!", "CRAC!", "CRO!"};

        int contador = 0;

        for (int i = 0; i < attackSpeed; i++){                    //for para executar todos os ataques da rodada
            System.out.print(corvejo[r.nextInt(2)] + " ");  //Sorteio de qual barulho o corvo que está atacando fará
            Utilidades.esperar(500);

            if (Math.random() > alvo.dodgeChance){                //Se o corvo acertar seu ataque
                contador++;
                alvo.receberDano(forca);
            }
        }

        System.out.printf("\n\nOs corvos acertaram %d dos %d ataques dados!\n", contador, attackSpeed); Utilidades.esperar(1500);
    }

    /*
     * A movimentação do Corvo Rei não tem um critério de se afastar ou não
     * existe uma chance 50/50 de ele ir para mais longe ou mais perto do guerreiro
     * Ele sempre percorrerá 'moveSpeed' metros, ou para perto, ou para longe do herói
     */
    @Override
    public void mover(Personagem alvo){
        if (Math.random() < 0.5)
            pos += moveSpeed;
        else
            pos -= moveSpeed;
        
        System.out.printf("%s, o CORVO REI, agora esta a %d metros do nosso guerreiro!\n", nome, Utilidades.calcularDistancia(pos, alvo.pos)); Utilidades.esperar(1500);
    }
}
