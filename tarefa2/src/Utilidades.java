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
    static String[] nomesTroll = {"Grumgar", "Brogath", "Throgg", "Murgol"};
    static String[] nomesGoblinGigante = {"Grobnar", "Snarlg", "Bruglox", "Drakz"};
    static String[] nomesZumbi = {"Morbidus", "Putridius", "Cadaveron", "Necros"};
    static String[] nomesCavaleiroCorrompido = {"Sir Malrick", "Darthorn", "Vargan", "Mortivar"};

    static String[] armasLutador = {"Espada", "Clava"};
    static String[] armasAtirador = {"Funda", "Arco"};

    static String[] armasCavaleiroCorrompido = {"Espada", "Machado", "Clava Comum", "Clava de Espinhos"};
    static String[] armasEsqueleto = {"Funda", "Arco", "Crossbow", "Faca de arremesso"};
    static String[] armasNinfa = {"Lança", "Adagas", "Machado", "Clava de Espinhos"};
    static String[] armasCorvoRei = {"Penas de corvo", "Garras de Corvo"};
    static String[] armasZumbi = {"Espada", "Machado", "Clava", "Clava de Espinhos"};
    static String[] armasGoblin = {"Espada", "Lança", "Faca de arremesso", "Adagas", "Clava Comum"};
    static String[] armasGoblinGigante = {"Machado", "Clava de Espinhos", "Espada", "Lança"};
    static String[] armasTroll = {"Machado", "Clava de Espinhos", "Espada", "Lança"};

    static List<String> monstrosCastelo = new ArrayList<>(Arrays.asList("Esqueleto", "Goblin", "Cavaleiro Corrompido", "Troll", "Zumbi"));
    static List<String> monstrosVilarejo = new ArrayList<>(Arrays.asList("Esqueleto", "Goblin", "Zumbi", "Troll"));
    static List<String> monstrosAcampamento = new ArrayList<>(Arrays.asList("Ninfa da Floresta", "Esqueleto", "Cavaleiro Corrompido", "Goblin Gigante", "Troll"));
    static Random random = new Random();

    /*
     * Método que cria um herói com nome sorteado entre os 15 definidos
     * Chances iguais de criar um Cavaleiro ou um Arqueiro
     */
    public static Heroi criarHeroi(){
        String nome = nomesHeroi[random.nextInt(15)];
        if (Math.random() < 0.5)
            return new Lutador(nome, escolherArma(armasLutador));
        else
            return new Atirador(nome, escolherArma(armasAtirador));
    }

    /*
     * Método que sorteia um dos 8 monstros para batalhar com o herói
     * Não deixa que nenhum monstro apareça em mais de uma batalha
     * Cada monstro aparece a uma distância aleatória do herói dentro de um intervalo definido para ela
     * Os níveis dos monstros também são aleatórios dentro de um intervalo definido para cada batalha
     * O nome dos monstros é sorteado dentre os 4 definidos para cada classe
     */
    public static Monstro criarMonstro(String ambiente, int fase){
        int indice;
        String monstro;

        if (ambiente.equals("Castelo")){
            indice = random.nextInt(monstrosCastelo.size());
            monstro = monstrosCastelo.get(indice);
        }
        else if (ambiente.equals("Vilarejo Destruído")){
            indice = random.nextInt(monstrosVilarejo.size());
            monstro = monstrosVilarejo.get(indice);
        }
        else{
            indice = random.nextInt(monstrosAcampamento.size());
            monstro = monstrosAcampamento.get(indice);
        }

        int posicao = escolherPosicao(monstro);

        if (monstro.equals("Corvo Rei"))
            return new CorvoRei(nomesCorvoRei[random.nextInt(nomesCorvoRei.length)], fase, posicao, escolherArma(armasCorvoRei));
        else if (monstro.equals("Esqueleto"))
            return new Esqueleto(nomesEsqueleto[random.nextInt(nomesEsqueleto.length)], fase, posicao, escolherArma(armasEsqueleto));
        else if (monstro.equals("Goblin"))
            return new Goblin(nomesGoblin[random.nextInt(nomesGoblin.length)], fase, posicao, escolherArma(armasGoblin));
        else if (monstro.equals("Ninfa da Floresta"))
            return new Ninfa(nomesNinfa[random.nextInt(nomesNinfa.length)], fase, posicao, escolherArma(armasNinfa));
        else if (monstro.equals("Cavaleiro Corrompido"))
            return new CavaleiroCorrompido(nomesCavaleiroCorrompido[random.nextInt(nomesCavaleiroCorrompido.length)], fase, posicao, escolherArma(armasCavaleiroCorrompido));
        else if (monstro.equals("Zumbi"))
            return new Zumbi(nomesZumbi[random.nextInt(nomesZumbi.length)], fase, posicao, escolherArma(armasZumbi));
        else if (monstro.equals("Goblin Gigante"))
            return new GoblinGigante(nomesGoblinGigante[random.nextInt(nomesGoblinGigante.length)], fase, posicao, escolherArma(armasGoblinGigante));
        else //Troll
            return new Troll(nomesTroll[random.nextInt(nomesTroll.length)], fase, posicao, escolherArma(armasTroll));
    }

    /*
     * Método que escolhe uma arma dentre as disponíveis para o personagem
     * Retorna um objeto do tipo Arma
     */
    private static Arma escolherArma(String[] armas){
        String armaEscolhida = armas[random.nextInt(armas.length)];
        switch (armaEscolhida){
            case "Espada":
                return new Espada();
            case "Machado":
                return new Machado();
            case "Clava Comum":
                return new ClavaComum();
            case "Clava de Espinhos":
                return new ClavaEspinhos();
            case "Funda":
                return new Funda();
            case "Arco":
                return new Arco();
            case "Crossbow":
                return new Crossbow();
            case "Faca de arremesso":
                return new FacaArremesso();
            case "Adagas":
                return new Adagas();
            case "Lança":
                return new Lança();
            case "Penas de corvo":
                return new PenasCorvo();
            case "Garras de Corvo":
                return new GarrasCorvo();
            default:
                return null;
        }
    }

    /*
     * Sorteio da distância que o monstro irá aparecer na floresta
     * Cada classe possui um intervalo predefinido
     */
    public static int escolherPosicao(String monstro){
        if (monstro.equals("Corvo Rei"))
            return 12 + random.nextInt(-3, 3);
        else if (monstro.equals("Esqueleto"))
            return 16 + random.nextInt(-4, 2);
        else if (monstro.equals("Goblin"))
            return 10 + random.nextInt(-2, 4);
        else if (monstro.equals("Ninfa da Floresta"))
            return 3 + random.nextInt(-1, 5);
        else if (monstro.equals("Cavaleiro Corrompido"))
            return 10 + random.nextInt(-2, 3);
        else if (monstro.equals("Zumbi"))
            return 8 + random.nextInt(-2, 3);
        else if (monstro.equals("Goblin Gigante"))
            return 10 + random.nextInt(-3, 4);
        else if (monstro.equals("Troll"))
            return 10 + random.nextInt(-3, 3);
        else
            return 12 + random.nextInt(-4, 4);
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
        else if (P instanceof GoblinGigante)
            return "GOBLIN GIGANTE";
        else if (P instanceof Troll)
            return "TROLL";
        else if (P instanceof Zumbi)
            return "ZUMBI";
        else if (P instanceof CavaleiroCorrompido)
            return "CAVALEIRO CORROMPIDO";
        else if (P instanceof CorvoRei)
            return "CORVO REI";
        else if (P instanceof Esqueleto)
            return "ESQUELETO";
        else if (P instanceof Ninfa)
            return "NINFA DA FLORESTA";
        else if (P instanceof Atirador)
            return "ATIRADOR";
        else if (P instanceof Lutador)
            return "LUTADOR";
        else
            return "DESCONHECIDO";
    }
}
