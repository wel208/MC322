import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Utilidades{

    static String[] turnosDezenas = {"", " DECIMO", " VIGESIMO", " TRIGESIMO", " QUADRAGESIMO", " QUINQUAGESIMO"};
    static String[] turnosUnidades = {"", "PRIMEIRO ", "SEGUNDO ", "TERCEIRO ", "QUARTO ", "QUINTO ", "SEXTO ", "SETIMO ", "OITAVO ", "NONO "};
    static String[] nomesHeroi = {"Alaric", "Godfrey", "Cedric", "Rowan", "Ulrich", "Leofric", "Aldwin", "Roderick", "Thrain", "Baldric", "Oswin", "Edrick", "Sigurd", "Gareth", "Torvald"};
    static String[] nomesCorvoRei = {"Morrigan", "Nocthar", "Kaelthar", "Bran"};
    static String[] nomesEsqueleto = {"Rattor", "Calvorn", "Drymor", "Ossarion"};
    static String[] nomesNinfa = {"Sylvara", "Lythienne", "Veyra", "Thalindra"};
    static String[] nomesRangedor = {"Oakmar", "Thryndor", "Elmgrim", "Dorvanth"};
    static String[] nomesGoblin = {"Snagrot", "Gribnak", "Zurgal", "Krivix"};
    static List<String> monstros = new ArrayList<>(Arrays.asList("Corvo Rei", "Esqueleto", "Goblin", "Ninfa da Floresta", "Rangedor"));
    static Random random = new Random();

    /*
     * Método que cria um herói com nome sorteado entre os 15 definidos
     * Chances iguais de criar um Cavaleiro ou um Arqueiro
     */
    public static Heroi criarHeroi(){
        String nome = nomesHeroi[random.nextInt(15)];
        if (Math.random() < 0.5)
            return new Cavaleiro(nome);
        else
            return new Arqueiro(nome);
    }

    /*
     * Método que sorteia um dos 5 monstros para batalhar com o herói
     * Não deixa que nenhum monstro apareça em mais de uma batalha
     * Cada monstro aparece a uma distância aleatória do herói dentro de um intervalo definido para ela
     * Os níveis dos monstros também são aleatórios dentro de um intervalo definido para cada batalha
     * O nome dos monstros é sorteado dentre os 4 definidos para cada classe
     */
    public static Monstro criarMonstro(int batalha, int pos){
        int indice = random.nextInt(6 - batalha);
        String monstro = monstros.remove(indice);

        int nivel = sortearNivel(batalha);
        int posicao = escolherPosicao(monstro, pos);

        if (monstro == "Corvo Rei")
            return new CorvoRei(nomesCorvoRei[random.nextInt(4)], nivel, posicao);
        else if (monstro == "Esqueleto")
            return new Esqueleto(nomesEsqueleto[random.nextInt(4)], nivel, posicao);
        else if (monstro == "Goblin")
            return new Goblin(nomesGoblin[random.nextInt(4)], nivel, posicao);
        else if (monstro == "Ninfa da Floresta")
            return new Ninfa(nomesNinfa[random.nextInt(4)], nivel, posicao);
        else
            return new Rangedor(nomesRangedor[random.nextInt(4)], nivel, posicao);
    }

    /*
     * Sorteio do nível do monstro que irá aparecer na floresta
     * 1ª batalha: [0,5]
     * 2ª batalha: [5, 10]
     * 3ª batalha: [10, 15]
     */
    public static int sortearNivel(int batalha){
        return random.nextInt(5 * (batalha - 1), 5 * batalha);
    }

    /*
     * Sorteio da distância que o monstro irá aparecer na floresta
     * Cada classe possui um intervalo predefinido
     */
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

    //Metódo que auxilia a identificar que turno estamos para printar corretamente por extenso
    public static String verificarTurno(int turno){
        int dezena = turno / 10;
        int unidade = turno - (dezena * 10);
        return turnosDezenas[dezena] + " " + turnosUnidades[unidade];
    }

    //Calcula e retorna a distância entre o herói e o monstro
    public static int calcularDistancia(int pos1, int pos2){
        return Math.abs(pos1 - pos2);
    }

    //Método para fazer uma pausa entre operações/prints do programa
    public static void esperar(int ms) {
        try{
            Thread.sleep(ms);
        } 
        catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    //Retorna de qual classe concreta o personagem é
    public static String verificarClasse(Personagem P){
        if (P instanceof Goblin)
            return "GOBLIN";
        else if (P instanceof CorvoRei)
            return "CORVO REI";
        else if (P instanceof Esqueleto)
            return "ESQUELETO";
        else if (P instanceof Rangedor)
            return "RANGEDOR";
        else if (P instanceof Ninfa)
            return "NINFA DA FLORESTA";
        else if (P instanceof Arqueiro)
            return "ARQUEIRO";
        else
            return "CAVALEIRO";
    }
}
