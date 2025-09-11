/*
 * Classe de herói que não tem uma movimentação tão rápida
 * Executa ataques corpo a corpo
 * Boa quantidade de pontos de vida, de força e de proteção
 * Boa chance de esquivar de ataques inimigos
 */

public class Lutador extends Heroi{

    //Atributos
    private int furia;

    //Construtor
    public Lutador(String nome, Arma arma){ //Atributos predefinidos para um lutador de nível 0
        super(nome, arma);
        this.pontosDeVida = 85;
        this.moveSpeed = 7;
        this.forca = 70;
        this.protecao = 0.6;
        this.furia = 1;
    }

    //Métodos
    @Override
    public void exibirStatus(){
        System.out.printf("\n%s, O lutador possui:", nome); Utilidades.esperar(1000);
        System.out.printf("\n%d PONTOS DE VIDA;", pontosDeVida); Utilidades.esperar(1000);
        System.out.printf("\nNIVEL %d e %d/%d de EXPERIENCIA;", getNivel(), getExperiencia(), getExpProximoNivel()); Utilidades.esperar(1000);
        System.out.printf("\n%.0f pontos de FORCA e %.0f pontos de PROTECAO;", forca, protecao * 100); Utilidades.esperar(1000);
        System.out.printf("\n%d pontos de FURIA;", furia); Utilidades.esperar(1000);
        System.out.printf("\n%.2f pontos de SORTE;", sorte); Utilidades.esperar(1000);
        System.out.printf("\n%s esta usando %s como ARMA;", nome, arma.nome); Utilidades.esperar(1000);
        System.out.printf("\ncapacidade de dar %d ATAQUES POR TURNO e pode percorrer %d METROS POR TURNO.\n", arma.attackSpeed, moveSpeed); Utilidades.esperar(1000);
    }

    /*
     * Caso o lutador esteja a uma distância maior que 1 metro do inimigo, ele irá avançar
     * Se ele já estiver a uma distância <= 1, ele decidirá entre usar o ataque comum ou sua habilidade especial
     */
    @Override
    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);

        System.out.printf("\nO lutador esta a %d metros do monstro e ira ", distancia);

        if (distancia <= arma.attackRange){
            if (Math.random() < 0.3){
                System.out.println("USAR SUA HABILIDADE ESPECIAL!\n"); Utilidades.esperar(1500);
                usarHabilidadeEspecial(alvo);
            }
            else{
                System.out.println("ATACAR O SEU INIMIGO!"); Utilidades.esperar(1500);
                atacar(alvo);
            }
        }
        else{
            System.out.println("CORRER NA DIRECAO DELE!\n"); Utilidades.esperar(1500);
            mover(alvo);
        }
    }

    /*
     * O ataque comum do lutador consiste em dar 'attackSpeed' ataques no seu inimigo
     * Caso acerte um dano crítico, dará 30% a mais de dano que o normal
     * Existe a possibilidade do inimigo esquivar do ataque
     */
    @Override
    protected void atacar(Personagem alvo){
        int contador = 0;
        for (int i = 0; i < arma.attackSpeed; i++){

            if (Math.random() > alvo.sorte){      //Caso o herói acerte o seu alvo
                contador++;

                boolean critico = Math.random() < criticalChance + (sorte / 10.0); //Chance de causar um dano crítico
                double multiplicador = critico ? 1.2 : 1.0;

                alvo.receberDano(forca * arma.dano * multiplicador);

                if (critico){   //Caso o ataque seja crítico
                    System.out.println("ISSO! O nosso lutador ACERTOU um ATAQUE CRITICO em seu inimigo!"); Utilidades.esperar(1500);
                }
                else{           //Caso seja um ataque comum
                    System.out.println("BOA! O nosso lutador ACERTOU um golpe no inimigo!"); Utilidades.esperar(1500);
                }
            }
            else                                  //Caso o inimigo consiga desviar do ataque do herói
                System.out.println("NAO! O inimigo ESQUIVOU do ataque do nosso heroi!"); Utilidades.esperar(1500);
        }

        System.out.printf("\nO heroi acertou %d dos %d ataques dados!\n", contador, arma.attackSpeed); Utilidades.esperar(1500);
    }

    /*
     * O lutador vai em direção do seu alvo
     * Caso ele alcance o alvo nessa investida, irá atacá-lo
     * Se não, será exibida a distância que ele chegou do monstro
     */
    @Override
    protected void mover(Personagem alvo){

        boolean chegou = false;

        for (int i = 0; i < moveSpeed; i++){
            pos += (pos < alvo.pos) ? 1 : -1;
            if (Utilidades.calcularDistancia(pos, alvo.pos) <= arma.attackRange){
                System.out.println("O lutador ALCANCOU O MONSTRO E IRA ATACAR!\n"); Utilidades.esperar(1500);
                chegou = true;
                atacar(alvo);
                break;
            }
        }
        
        if (!chegou){
            System.out.println("O nosso lutador ainda nao alcancou o inimigo.");
            System.out.printf("Ele esta a %d metros do monstro.\n", Utilidades.calcularDistancia(pos, alvo.pos)); Utilidades.esperar(1500);
        }
    }


    /* 
     * A habilidade especial do lutador é atacar 'furia' vezes a mais que o normal em apenas um turno
     * A furia faz com que ele ataque mais vezes e com a força 20% maior que o seu nível de força
     * O inimigo também tem um aumento na chance de se esquivar de um ataque do herói 
    */

    @Override
    protected void usarHabilidadeEspecial(Personagem alvo){
        int contador = 0;

        System.out.printf("RHHAAAA!! O NOSSO HEROI ENTROU EM FURIA E ATACARA %d VEZES NESTE TURNO!\n\n", arma.attackSpeed + furia); Utilidades.esperar(1000);

        for(int i = 0; i < arma.attackSpeed + furia; i++){
            System.out.print("Hah! "); Utilidades.esperar (500);
            if (Math.random() > alvo.sorte * 1.1){   //Chance maior do inimigo esquivar do ataque do lutador
                alvo.receberDano(forca * arma.dano * 1.1);       //Dano 10% maior que o normal
                contador++;
            }
        }

        System.out.printf("\n\nO nosso lutador acertou %d golpe(s)!\n", contador); Utilidades.esperar(1500);
    }

    /*
     * Método que aumenta a furia do lutador a cada nivel de XP par alcançado
     */
    @Override
    protected void melhorarAtributoUnico(){
        this.furia += 1;
    }
}