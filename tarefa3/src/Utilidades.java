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
    static String[] nomesGoblin = {"Snagrot", "Gribnak", "Zurgal", "Krivix"};
    static String[] nomesTroll = {"Grumgar", "Brogath", "Throgg", "Murgol"};
    static String[] nomesGoblinGigante = {"Grobnar", "Snarlg", "Bruglox", "Drakz"};
    static String[] nomesZumbi = {"Morbidus", "Putridius", "Cadaveron", "Necros"};
    static String[] nomesCavaleiroCorrompido = {"Sir Malrick", "Darthorn", "Vargan", "Mortivar"};

    static String[] armasLutador = {"Espada", "Clava Comum"};
    static String[] armasAtirador = {"Funda", "Arco"};

    static String[] armasCavaleiroCorrompido = {"Espada", "Machado", "Clava Comum", "Clava de Espinhos"};
    static String[] armasEsqueleto = {"Funda", "Arco", "Crossbow", "Faca de arremesso"};
    static String[] armasNinfa = {"Lança", "Adagas", "Machado", "Clava de Espinhos"};
    static String[] armasCorvoRei = {"Penas de corvo", "Garras de Corvo"};
    static String[] armasZumbi = {"Espada", "Machado", "Clava", "Clava de Espinhos"};
    static String[] armasGoblin = {"Espada", "Lança", "Faca de arremesso", "Adagas", "Clava Comum"};
    static String[] armasGoblinGigante = {"Machado", "Clava de Espinhos", "Espada", "Lança"};
    static String[] armasTroll = {"Machado", "Clava de Espinhos", "Espada", "Lança"};

    static List<String> monstrosCastelo = new ArrayList<>(Arrays.asList("Goblin", "Cavaleiro Corrompido", "Troll", "Zumbi"));
    static List<String> monstrosVilarejo = new ArrayList<>(Arrays.asList("Goblin", "Zumbi", "Troll"));
    static List<String> monstrosAcampamento = new ArrayList<>(Arrays.asList("Ninfa da Floresta", "Cavaleiro Corrompido", "Goblin Gigante", "Troll"));
    static Random random = new Random();

    public static Heroi criarHeroi(){
        String nome = nomesHeroi[random.nextInt(nomesHeroi.length)];
        if (Math.random() < 0.5)
            return new Lutador(nome, escolherArma(armasLutador));
        else
            return new Atirador(nome, escolherArma(armasAtirador));
    }

    public static Monstro criarMonstro(String ambiente, int fase){
        int indice;
        String monstro;
        List<String> listaMonstros;

        if (ambiente.equals("Castelo")){
            listaMonstros = new ArrayList<>(monstrosCastelo);
            indice = random.nextInt(listaMonstros.size());
            monstro = listaMonstros.remove(indice);
        }
        else if (ambiente.equals("Vilarejo Destruído")){
            listaMonstros = new ArrayList<>(monstrosVilarejo);
            indice = random.nextInt(listaMonstros.size());
            monstro = listaMonstros.remove(indice);
        }
        else{
            listaMonstros = new ArrayList<>(monstrosAcampamento);
            indice = random.nextInt(listaMonstros.size());
            monstro = listaMonstros.remove(indice);
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
        else
            return new Troll(nomesTroll[random.nextInt(nomesTroll.length)], fase, posicao, escolherArma(armasTroll));
    }

    private static Arma escolherArma(String[] armas){
        String armaEscolhida = armas[random.nextInt(armas.length)];
        switch (armaEscolhida){
            case "Espada": return new Espada();
            case "Machado": return new Machado();
            case "Clava Comum": return new ClavaComum();
            case "Clava de Espinhos": return new ClavaEspinhos();
            case "Funda": return new Funda();
            case "Arco": return new Arco();
            case "Crossbow": return new Crossbow();
            case "Faca de arremesso": return new FacaArremesso();
            case "Adagas": return new Adagas();
            case "Lança": return new Lança();
            case "Penas de corvo": return new PenasCorvo();
            case "Garras de Corvo": return new GarrasCorvo();
            default: return null;
        }
    }

   public static int escolherPosicao(String monstro){
        if (monstro.equals("Corvo Rei"))
            return 12 + random.nextInt((3 - (-3)) + 1) + (-3);
        else if (monstro.equals("Esqueleto"))
            return 16 + random.nextInt((2 - (-4)) + 1) + (-4);
        else if (monstro.equals("Goblin"))
            return 10 + random.nextInt((4 - (-2)) + 1) + (-2);
        else if (monstro.equals("Ninfa da Floresta"))
            return 3 + random.nextInt((5 - (-1)) + 1) + (-1);
        else if (monstro.equals("Cavaleiro Corrompido"))
            return 10 + random.nextInt((3 - (-2)) + 1) + (-2);
        else if (monstro.equals("Zumbi"))
            return 8 + random.nextInt((3 - (-2)) + 1) + (-2);
        else if (monstro.equals("Goblin Gigante"))
            return 10 + random.nextInt((4 - (-3)) + 1) + (-3);
        else if (monstro.equals("Troll"))
            return 10 + random.nextInt((3 - (-3)) + 1) + (-3);
        else
            return 12 + random.nextInt((4 - (-4)) + 1) + (-4);
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
