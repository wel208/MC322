public class Cavaleiro extends Heroi{

    //Atributos
    int furia;

    //Construtor
    public Cavaleiro(String nome){ //Atributos predefinidos para um cavaleiro de nível 0
        super(nome);
        this.pontosDeVida = 85;
        this.attackRange = 1;
        this.moveSpeed = 6;
        this.dodgeChance = 0.4;
        this.forca = 70;
        this.protecao = 0.5;
        this.furia = 2;
    }

    //Métodos
    @Override
    public void exibirStatus(){
        System.out.printf("\n%s, O cavaleiro possui:", nome); Utilidades.esperar(500);
        System.out.printf("\n%d PONTOS DE VIDA;", pontosDeVida); Utilidades.esperar(500);
        System.out.printf("\nNÍVEL %d e %d/%d de EXPERIÊNCIA;", nivel, experiencia, max_XP); Utilidades.esperar(500);
        System.out.printf("\n%.0f pontos de FORÇA e %.0f pontos de PROTEÇÃO;", forca, protecao * 100); Utilidades.esperar(500);
        System.out.printf("\n%d pontos de FURIA;", furia); Utilidades.esperar(500);
        System.out.printf("\ncapacidade de dar %d ATAQUES POR TURNO e pode percorrer %d METROS POR TURNO.\n", attackSpeed, moveSpeed); Utilidades.esperar(500);
    }

    @Override
    public void atacar(Personagem alvo){
        int contador = 0;
        for (int i = 0; i < attackSpeed; i++){

            if (Math.random() > alvo.dodgeChance){         //Caso o herói acerte o seu alvo
                contador++;

                if (Math.random() < criticalChance){       //Caso o ataque seja crítico
                    alvo.receberDano(forca * 1.3);
                    System.out.println("\nISSO! O nosso cavaleiro ACERTOU um ATAQUE CRITICO em seu inimigo!"); Utilidades.esperar(1500);
                }
                else{                                      //Caso seja um ataque comum
                    alvo.receberDano(forca);
                    System.out.println("\nBOA! O nosso cavaleiro ACERTOU um golpe no inimigo!"); Utilidades.esperar(1500);
                }
            }
            else                                           //Caso o inimigo consiga desviar do ataque do herói
                System.out.println("\nNAO! O inimigo ESQUIVOU do ataque do nosso heroi!"); Utilidades.esperar(1500);
        }

        System.out.printf("\nO heroi acertou %d dos %d ataques dados!\n", contador, attackSpeed); Utilidades.esperar(1500);
    }

    /* A habilidade especial do cavaleiro é atacar 'furia' vezes a mais que o normal em apenas um turno
       A furia faz com que ele ataque mais vezes e com a força 50% maior que o seu nível de força
       O inimigo também tem um aumento na chance de se esquivar de um ataque do herói */
    @Override
    public void usarHabilidadeEspecial(Personagem alvo){
        int contador = 0;

        System.out.printf("RHHAAAA!! O NOSSO HEROI ENTROU EM FURIA E ATACARA %d VEZES NESTE TURNO!\n\n", attackSpeed + furia); Utilidades.esperar(1000);

        for(int i = 0; i < attackSpeed * furia; i++){
            System.out.print("Hah! "); Utilidades.esperar (500);
            if (Math.random() > alvo.dodgeChance * 1.1){   //Caso o herói acerte o seu alvo
                alvo.receberDano(forca * 1.2);
                contador++;
            }
        }

        System.out.printf("\n\nO nosso cavaleiro acertou %d golpe(s)!", contador); Utilidades.esperar(1500);
    }

    @Override
    public void melhorarAtributoUnico(){
        this.furia += 1;
    }

    @Override
    public void mover(Personagem alvo){

        boolean chegou = false;

        if (pos < alvo.pos)
            for (int i = 0; i < moveSpeed; i++){
                pos++;
                if (Utilidades.calcularDistancia(pos, alvo.pos) == attackRange){
                    System.out.println("O cavaleiro ALCANÇOU O MONSTRO E IRÁ ATACAR!"); Utilidades.esperar(1500);
                    chegou = true;
                    atacar(alvo);
                    break;
                }
            }
        else
            for (int i = 0; i < moveSpeed; i++){
                pos--;
                if (Utilidades.calcularDistancia(pos, alvo.pos) == attackRange){
                    System.out.println("O cavaleiro ALCANÇOU O MONSTRO E IRÁ ATACAR!"); Utilidades.esperar(1500);
                    chegou = true;
                    atacar(alvo);
                    break;
                }
            }
        
        if (!chegou){
            System.out.println("O nosso cavaleiro ainda não alcançou o inimigo. ");
            System.out.printf("Ele esta a %d metros do monstro.\n", Utilidades.calcularDistancia(pos, alvo.pos)); Utilidades.esperar(1500);
        }
    }

    @Override
    public void tomarDecisao(Personagem alvo){
        //ADICIONAR TEXTO SEMELHANTE A COMO ESTÁ NA CLASSE ARQUEIRO
        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);

        System.out.printf("\nO cavaleiro esta a %d metros do monstro e ira ", distancia);

        if (distancia <= attackRange){
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
}