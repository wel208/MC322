import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Main{

    public static void main(String[] args){
        Heroi heroi = criarHeroi();

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
            Monstro monstro = criarMonstro(i, heroi.pos);
            monstro.ambientarMonstro(heroi);
            
            Utilidades.esperar(1000);

            for (int j = 1; j < 60; j++){
                System.out.printf("\n---INICIO DO%sTURNO---\n", verificarTurno(j)); Utilidades.esperar(1000);

                System.out.println("\nVez de " + heroi.nome + "!"); Utilidades.esperar(1500);

                heroi.tomarDecisao(monstro);
                monstro.statusParcial();

                if (monstro.pontosDeVida <= 0){
                    System.out.printf("\n%s, %s MORREU\n", monstro.nome, Utilidades.verificarClasse(monstro)); Utilidades.esperar(1500);
                    break;
                }

                System.out.println("\nVez de " + monstro.nome + "!"); Utilidades.esperar(1500);

                monstro.tomarDecisao(heroi);
                heroi.statusParcial();

                if (heroi.pontosDeVida <= 0){
                    heroiMorto = true;
                    break;
                }
            }

            Utilidades.esperar(1000);

            if (heroiMorto){
                System.out.println("\nO HEROI FOI MORTO!\n");
                System.out.println("Infelizmente, " + heroi.nome + " falhou com seu reino e consigo mesmo.");
                System.out.println("Jamais saberemos o que acontecera com Lumenholt.");
                break;
            }
            else{
                heroi.ganharExperiencia(monstro.xpConcedido);
                Utilidades.esperar(1500);

                System.out.println("\nCom essa vitoria a seu favor, nosso guerreiro segue em frente para completar seu objetivo.\n"); Utilidades.esperar(2000);
            }

            if (i == 3){
                System.out.println("Depois de o nosso heroi tanto procurar, finalmente encontra a princesa perdida na floresta."); Utilidades.esperar(2000);
                System.out.println("Ele vai ate ela e a leva a salvo de volta para o reino de Lumenholt."); Utilidades.esperar(2000);
                System.out.println("O rei, por sua eterna gratidao, nomeia " + heroi.nome + " como seu sucessor ao glorioso Trono de Lumenholt."); Utilidades.esperar(2000);
            }
        }
    }

    static String[] turnosUnidades = {"", "PRIMEIRO ", "SEGUNDO ", "TERCEIRO ", "QUARTO ", "QUINTO ", "SEXTO ", "SETIMO ", "OITAVO ", "NONO "};
    static String[] turnosDezenas = {"", " DECIMO", " VIGESIMO", " TRIGESIMO", " QUADRAGESIMO", " QUINQUAGESIMO"};
    static String[] nomesHeroi = {"Alaric", "Godfrey", "Cedric", "Rowan", "Ulrich", "Leofric", "Aldwin", "Roderick", "Thrain", "Baldric", "Oswin", "Edrick", "Sigurd", "Gareth", "Torvald"};
    static String[] nomesCorvoRei = {"Morrigan", "Nocthar", "Kaelthar", "Bran"};
    static String[] nomesEsqueleto = {"Rattor", "Calvorn", "Drymor", "Ossarion"};
    static String[] nomesNinfa = {"Sylvara", "Lythienne", "Veyra", "Thalindra"};
    static String[] nomesRangedor = {"Oakmar", "Thryndor", "Elmgrim", "Dorvanth"};
    static String[] nomesGoblin = {"Snagrot", "Gribnak", "Zurgal", "Krivix"};
    static List<String> monstros = new ArrayList<>(Arrays.asList("Corvo Rei", "Esqueleto", "Goblin", "Ninfa da Floresta", "Rangedor"));

    static Random random = new Random();

    public static Heroi criarHeroi(){
        String nome = nomesHeroi[random.nextInt(0, 15)];
        if (Math.random() < 0.5)
            return new Cavaleiro(nome);
        else
            return new Arqueiro(nome);
    }

    public static Monstro criarMonstro(int batalha, int pos){
        int indice = random.nextInt(6 - batalha);
        String monstro = monstros.remove(indice);

        if (monstro == "Corvo Rei")
            return new CorvoRei(nomesCorvoRei[random.nextInt(4)], sortearNivel(batalha), escolherPosicao(monstro, pos));
        else if (monstro == "Esqueleto")
            return new Esqueleto(nomesEsqueleto[random.nextInt(4)], sortearNivel(batalha), escolherPosicao(monstro, pos));
        else if (monstro == "Goblin")
            return new Goblin(nomesGoblin[random.nextInt(4)], sortearNivel(batalha), escolherPosicao(monstro, pos));
        else if (monstro == "Ninfa da Floresta")
            return new Ninfa(nomesNinfa[random.nextInt(4)], sortearNivel(batalha), escolherPosicao(monstro, pos));
        else
            return new Rangedor(nomesRangedor[random.nextInt(4)], sortearNivel(batalha), escolherPosicao(monstro, pos));
    }

    public static int sortearNivel(int batalha){
        return random.nextInt(5 * (batalha - 1), 5 * batalha);
    }

    public static int escolherPosicao(String monstro, int pos){
        if (monstro == "Corvo Rei")
            return pos + 12 + random.nextInt(-3, 3);
        else if (monstro == "Esqueleto")
            return pos + 16 + random.nextInt(-2, 4);
        else if (monstro == "Goblin")
            return pos + 10 + random.nextInt(-2, 4);
        else if (monstro == "Ninfa da Floresta")
            return pos + random.nextInt(3, 6);
        else
            return pos + 12 + random.nextInt(-4, 4);
    }

    public static String verificarTurno(int turno){
        int dezena = turno / 10;
        int unidade = turno - (dezena * 10);
        return turnosDezenas[dezena] + " " + turnosUnidades[unidade];
    }
}
