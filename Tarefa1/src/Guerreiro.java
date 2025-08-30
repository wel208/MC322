public class Guerreiro extends Heroi{

    //Atributos
    int furia;

    //Construtor
    public Guerreiro(String nome){ //Atributos predefinidos para um guerreiro de nível 0
        super(nome);
        this.pontosDeVida = 85;
        this.attackRange = 1;
        this.moveSpeed = 6;
        this.dodgeChance = 0.4;
        this.forca = 70;
        this.protecao = 50;
        this.furia = 2;
    }

    //Métodos
    @Override
    public void exibirStatus(){
        System.out.printf("\n%s, O GUERREIRO possui:", nome); Utilidades.esperar(500);
        System.out.printf("\n%d PONTOS DE VIDA;", pontosDeVida); Utilidades.esperar(500);
        System.out.printf("\nNÍVEL %d e %d/%d de EXPERIÊNCIA;", nivel, experiencia, max_XP); Utilidades.esperar(500);
        System.out.printf("\n%d pontos de FORÇA e %d pontos de PROTEÇÃO;", forca, protecao); Utilidades.esperar(500);
        System.out.printf("\n%d pontos de FURIA;", furia); Utilidades.esperar(500);
        System.out.printf("\ncapacidade de dar %d ATAQUES POR TURNO e pode percorrer %d METROS POR TURNO.", attackSpeed, moveSpeed); Utilidades.esperar(500);
    }

    @Override
    public void statusParcial(){
        Utilidades.esperar(500);
        System.out.printf("\n%s, O GUERREIRO está com %d PONTOS DE VIDA.", pontosDeVida);
    }

    @Override
    public void atacar(Personagem alvo){
        int contador = 0;
        for (int i = 0; i < attackSpeed; i++){
            Utilidades.esperar(500);

            if (Math.random() > alvo.dodgeChance){         //Caso o herói acerte o seu alvo
                contador++;

                if (Math.random() < criticalChance){       //Caso o ataque seja crítico
                    alvo.receberDano(forca * 1.3);
                    System.out.println("\nISSO! O nosso guerreiro ACERTOU um ATAQUE CRÍTICO em seu inimigo!");
                }
                else{                                      //Caso seja um ataque comum
                    alvo.receberDano(forca);
                    System.out.println("\nBOA! O nosso guerreiro ACERTOU um golpe no inimigo!");
                }
            }
            else                                           //Caso o inimigo consiga desviar do ataque do herói
                System.out.println("\nNÃO! O inimigo ESQUIVOU do ataque do nosso herói!");
        }

        System.out.printf("\nO herói acertou %d dos %d ataques dados!", contador, attackSpeed);
    }

    /* A habilidade especial do guerreiro é atacar 'furia' vezes mais que o normal em apenas um turno
       A furia faz com que ele ataque mais vezes e com a força 50% maior que o seu nível de força
       O inimigo também tem um aumento na chance de se esquivar de um ataque do herói */
    @Override
    public void usarHabilidadeEspecial(Personagem alvo){
        Utilidades.esperar(500);

        int contador = 0;

        System.out.printf("\nRHHAAAA!! O NOSSO HERÓI ENTROU EM FÚRIA E ATACARÁ %d VEZES NESTE TURNO!", attackSpeed * furia); Utilidades.esperar(300);

        for(int i = 0; i < attackSpeed * furia; i++){
            System.out.print("Hah! "); Utilidades.esperar(350);
            if (Math.random() > alvo.dodgeChance * 1.2){   //Caso o herói acerte o seu alvo
                alvo.receberDano(forca * 1.5);
                contador++;
            }
        }

        System.out.printf("\nO nosso guerreiro acertou %d golpe(s)!", contador);
    }

    @Override
    public void melhorarAtributoUnico(){
        this.furia += 1;
    }

    @Override
    public void mover(Personagem alvo){

        boolean flag = false;

        if (pos < alvo.pos)
            while (pos < alvo.pos){
                pos++;
                if (Utilidades.calcularDistancia(pos, alvo.pos) == attackRange){
                    System.out.println("O GUERREIRO ALCANÇOU O MONSTRO E IRÁ ATACAR!");
                    flag = true;
                    atacar(alvo);
                    break;
                }
            }
        else
            while (pos > alvo.pos){
                pos--;
                if (Utilidades.calcularDistancia(pos, alvo.pos) == attackRange){
                    System.out.println("O GUERREIRO ALCANÇOU O MONSTRO E IRÁ ATACAR!");
                    flag = true;
                    atacar(alvo);
                    break;
                }
            }
        
        if (!flag){
            System.out.println("O nosso guerreiro ainda não alcançou o inimigo. ");
            System.out.printf("Ele chegou a %d metros do monstro.", Utilidades.calcularDistancia(pos, alvo.pos));
        }
    }

    @Override
    public void tomarDecisao(Personagem alvo){
        //ADICIONAR TEXTO SEMELHANTE A COMO ESTÁ NA CLASSE ARQUEIRO
        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);

        System.out.printf("\nO guerreiro está a %d metros do monstro e irá ", distancia);

        if (distancia <= attackRange){
            if (Math.random() < 0.3){
                System.out.print("USAR SUA HABILIDADE ESPECIAL!");
                usarHabilidadeEspecial(alvo);
            }
            else{
                System.out.print("ATACAR O SEU INIMIGO!");
                atacar(alvo);
            }
        }
        else{
            System.out.print("CORRER NA DIREÇÃO DELE!");
            mover(alvo);
        }
    }
}