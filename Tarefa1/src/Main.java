public class Main{

    public static void main(String[] args){
        Heroi heroi = Utilidades.criarHeroi();

        //APRESENTAÇÃO DA HISTÓRIA
        System.out.println("Estamos em 1263."); Utilidades.esperar(1000);
        System.out.println("O nosso reino de Lumenholt vive um momento de extremo conflito com os monstros de Umbraeth, o Reino Sombrio."); Utilidades.esperar(2000);
        System.out.println("Sofremos ataque de um grupo de aberracoes. Invadiram o castelo e levaram a princesa para longe de nossas terras."); Utilidades.esperar(2000);
        System.out.println("Esse e o momento que se deve mostrar a paixao que se tem pelo lugar onde cresceu e se aperfeicoou."); Utilidades.esperar(2000);

        //APRESENTAÇÃO DO HERÓI + CENÁRIO
        System.out.println("Eu, " + heroi.nome + ", " + Utilidades.verificarClasse(heroi) + " mais bem treinado do reino, prometi para o rei que iria salvar a sua filha."); Utilidades.esperar(2000);
        System.out.println("E ca estou eu, na Floresta do Nevoeiro, local onde essas anomalias horrendas se escondem."); Utilidades.esperar(2000);
        System.out.println("Esse e um lugar obscuro, essas arvores altas e essa densa neblina quase nao me deixa ver a luz da Lua."); Utilidades.esperar(2000);
        System.out.println("O ranger das arvores e o corvejar dos corvos deve ter amedrontado os diversos homens que vieram ate aqui para defender o reino que tanto amavam."); Utilidades.esperar(2000);
        System.out.println("Homens esses que, muitas vezes, nunca mais voltaram..."); Utilidades.esperar(2000);
        System.out.println("Mas eu estou pronto. Voltarei para Lumenholt com a princesa a salvo e enfrentarei o que for preciso para que isso aconteca."); Utilidades.esperar(2000);
        System.out.println("\n" + heroi.nome + " finalmente adentra a floresta e inicia sua busca pela donzela.\n"); Utilidades.esperar(2000);

        //LOOP DE CADA BATALHA
        boolean heroiMorto = false;

        for (int i = 1; i < 4; i++){
            Monstro monstro = Utilidades.criarMonstro(i, heroi.pos); //Cria um monstro aleatório entre os 5 existentes para a batalha
            monstro.ambientarMonstro(heroi);                         //Ambienta o monstro na história

            for (int j = 1; j < 60; j++){                            //for que permite que a batalha ocorra em turnos

                System.out.printf("\n---INICIO DO%sTURNO---\n", Utilidades.verificarTurno(j)); Utilidades.esperar(1000);

                System.out.println("\n- Vez de " + heroi.nome + "! -"); Utilidades.esperar(1500);

                heroi.tomarDecisao(monstro);                         //Momento em que o herói escolhe seu primeiro passo
                monstro.statusParcial();                             //Mostra quantos pontos de vida o monstro ainda possui

                if (monstro.pontosDeVida <= 0){                      //Caso o monstro tenha morrido no ataque do herói
                    System.out.printf("\n%s, O %s, MORREU\n", monstro.nome, Utilidades.verificarClasse(monstro)); Utilidades.esperar(1500);
                    break;
                }

                System.out.println("\n- Vez de " + monstro.nome + "! -"); Utilidades.esperar(1500);

                monstro.tomarDecisao(heroi);                         //Resposta do monstro para a ação do herói
                heroi.statusParcial();                               //Mostra quantos pontos de vida o herói ainda possui

                if (heroi.pontosDeVida <= 0){                        //Caso o herói tenha morrido no ataque do monstro
                    heroiMorto = true;
                    break;
                }
            }

            Utilidades.esperar(1000);

            if (heroiMorto){    //Caso o heroi tenha morrido em batalha
                System.out.println("\nO HEROI FOI MORTO!\n");
                System.out.println("Infelizmente, " + heroi.nome + " falhou com seu reino e consigo mesmo.");
                System.out.println("Jamais saberemos o que acontecera com Lumenholt.");
                break;
            }
            else{               //Caso o herói tenha matado o monstro na batalha
                heroi.ganharExperiencia(monstro.xpConcedido);
                Utilidades.esperar(1500);

                System.out.println("\nCom essa vitoria a seu favor, nosso guerreiro segue em frente para completar seu objetivo.\n"); Utilidades.esperar(2000);
            }

            if (i == 3){        //Caso onde as três batalhas já foram ganhas
                System.out.println("Depois de o nosso heroi tanto procurar, finalmente encontra a princesa perdida na floresta."); Utilidades.esperar(2000);
                System.out.println("Ele vai ate ela e a leva a salvo de volta para o reino de Lumenholt."); Utilidades.esperar(2000);
                System.out.println("O rei, por sua eterna gratidao, nomeia " + heroi.nome + " como seu sucessor ao glorioso Trono de Lumenholt."); Utilidades.esperar(2000);
            }
        }
    }
}
