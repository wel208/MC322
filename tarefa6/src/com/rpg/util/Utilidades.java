package com.rpg.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.rpg.cenario.*;
import com.rpg.combate.*;
import com.rpg.exceptions.ArmasIguaisException;
import com.rpg.exceptions.NaoPossuiNivelException;
import com.rpg.exceptions.TipoErradoDeArmaException;
import com.rpg.game.InputManager;
import com.rpg.game.Main;
import com.rpg.itens.*;
import com.rpg.personagens.*;

public class Utilidades{

    //Strings para printar qual o atual turno em uma batalha
    static String[] turnosDezenas = {"", " DECIMO", " VIGESIMO", " TRIGESIMO", " QUADRAGESIMO", " QUINQUAGESIMO", " SEXAGESIMO", " SEPTUAGESIMO", " OCTOGESIMO", " NONAGESIMO"};
    static String[] turnosUnidades = {"", "PRIMEIRO ", "SEGUNDO ", "TERCEIRO ", "QUARTO ", "QUINTO ", "SEXTO ", "SETIMO ", "OITAVO ", "NONO "};
    
    //Possíveis nomes para cada classe de personagem
    static String[] nomesHeroi = {"Alaric", "Godfrey", "Cedric", "Rowan", "Ulrich", "Leofric", "Aldwin", "Roderick", "Thrain", "Baldric", "Oswin", "Edrick", "Sigurd", "Gareth", "Torvald"};
    static String[] nomesCorvoRei = {"Morrigan", "Nocthar", "Kaelthar", "Bran"};
    static String[] nomesEsqueleto = {"Rattor", "Calvorn", "Drymor", "Ossarion"};
    static String[] nomesNinfa = {"Sylvara", "Lythienne", "Veyra", "Thalindra"};
    static String[] nomesGoblin = {"Snagrot", "Gribnak", "Zurgal", "Krivix"};
    static String[] nomesTroll = {"Grumgar", "Brogath", "Throgg", "Murgol"};
    static String[] nomesGoblinGigante = {"Grobnar", "Snarlg", "Bruglox", "Drakz"};
    static String[] nomesZumbi = {"Morbidus", "Putridius", "Cadaveron", "Necros"};
    static String[] nomesCavaleiroCorrompido = {"Sir Malrick", "Darthorn", "Vargan", "Mortivar"};

    //Possíveis armas iniciais para os heróis
    static List<Arma> armasLutador = List.of(new Espada(), new ClavaComum());
    static List<Arma> armasAtirador = List.of(new Funda(), new Arco());

    //Todas as armas, monstros e herois existentes do jogo
    static List<Arma> armasDoJogo = List.of(new Adagas(), new Arco(), new ClavaComum(), new ClavaEspinhos(), new Crossbow(), new Espada(), new FacaArremesso(), new Funda(), new GarrasCorvo(), new Lança(), new Machado(), new PenasCorvo());
    static List<String> monstrosDoJogo = List.of("Cavaleiro Corrompido", "Corvo Rei", "Esqueleto", "Goblin", "Goblin Gigante", "Ninfa da Floresta", "Troll", "Zumbi");

    /*
     * Possíveis armas que os monstros podem utilizar
     * Elas estão organizadas da mais fraca para a mais forte para cada monstro, exceto o Corvo Rei
     * Quanto maior a dificuldade, melhor a arma que o monstro usará
     */
    static List<Arma> armasCavaleiroCorrompido = List.of(new ClavaComum(), new Espada(), new Machado(), new ClavaEspinhos());
    static List<Arma> armasEsqueleto = List.of(new Funda(), new Arco(), new FacaArremesso(), new Crossbow());
    static List<Arma> armasNinfa = List.of(new Adagas(), new Lança(), new Machado(), new ClavaEspinhos());
    static List<Arma> armasZumbi = List.of(new ClavaComum(), new Espada(), new Machado(), new ClavaEspinhos());
    static List<Arma> armasGoblin = List.of(new Espada(), new Adagas(), new FacaArremesso(), new Lança());
    static List<Arma> armasGoblinGigante = List.of(new Espada(), new Lança(), new Machado(), new ClavaEspinhos());    
    static List<Arma> armasTroll = List.of(new Espada(), new Lança(), new Machado(), new ClavaEspinhos());
    static List<Arma> armasCorvoRei = List.of(new PenasCorvo(), new GarrasCorvo());
    
    //Objeto para utilização de aletoriedade
    static Random random = new Random();

    //Criação do herói, chances iguais de ser lutador ou atirador
    public static Heroi criarHeroi(){
        String mensagem = 
            "\nEscolha a classe do seu herói\n" +
            "==================================================================" +
            "\n[1] Lutador" + 
            "\n[2] Atirador\n" +
            "==================================================================" +
            "\nDigite sua opcao > ";
        
        int classe = InputManager.lerInteiro(mensagem, 1, 2);
        String nome = InputManager.lerString("\nDigite o nome do seu herói > ");

        if (classe == 1)
            return new Lutador(nome, escolherArma(armasLutador, null));
        else
            return new Atirador(nome, escolherArma(armasAtirador, null));
    }

    //Método que cria uma lista de monstros para uma fase
    public static ArrayList<Monstro> criarListaDeMonstro(TipoCenario cenario, int nivel, Dificuldade dificuldade){
        ArrayList<Monstro> monstros = new ArrayList<>();

        //Cria de 2 a 4 monstros por fase
        int numMontros = random.nextInt(2, 5);

        for (int i = 0; i < numMontros; i++)
            monstros.add(criarMonstro(cenario, nivel, dificuldade));
        
        return monstros;
    }

    //Cria um monstro para ser adicionado na lista de monstros de uma fase
    public static Monstro criarMonstro(TipoCenario cenario, int nivel, Dificuldade dificuldade){

        int indice = random.nextInt(cenario.getMonstros().size());
        String monstro = cenario.getMonstros().get(indice);

        if (monstro.equals("Corvo Rei"))
            return new CorvoRei(nomesCorvoRei[random.nextInt(nomesCorvoRei.length)], nivel, escolherArma(armasCorvoRei, dificuldade), dificuldade);

        else if (monstro.equals("Esqueleto"))
            return new Esqueleto(nomesEsqueleto[random.nextInt(nomesEsqueleto.length)], nivel, escolherArma(armasEsqueleto, dificuldade), dificuldade);

        else if (monstro.equals("Goblin"))
            return new Goblin(nomesGoblin[random.nextInt(nomesGoblin.length)], nivel, escolherArma(armasGoblin, dificuldade), dificuldade);

        else if (monstro.equals("Ninfa da Floresta"))
            return new Ninfa(nomesNinfa[random.nextInt(nomesNinfa.length)], nivel, escolherArma(armasNinfa, dificuldade), dificuldade);

        else if (monstro.equals("Cavaleiro Corrompido"))
            return new CavaleiroCorrompido(nomesCavaleiroCorrompido[random.nextInt(nomesCavaleiroCorrompido.length)], nivel, escolherArma(armasCavaleiroCorrompido, dificuldade), dificuldade);

        else if (monstro.equals("Zumbi"))
            return new Zumbi(nomesZumbi[random.nextInt(nomesZumbi.length)], nivel, escolherArma(armasZumbi, dificuldade), dificuldade);

        else if (monstro.equals("Goblin Gigante"))
            return new GoblinGigante(nomesGoblinGigante[random.nextInt(nomesGoblinGigante.length)], nivel, escolherArma(armasGoblinGigante, dificuldade), dificuldade);

        else
            return new Troll(nomesTroll[random.nextInt(nomesTroll.length)], nivel, escolherArma(armasTroll, dificuldade), dificuldade);
    }

    /*
     *Escolhe a arma do monstro com base na dificuldade do jogo
     *Quanto mais difícil, melhor a arma
     *O herói não depende da dificuldade do jogo para definir sua arma
    */
    private static Arma escolherArma(List<Arma> armas, Dificuldade dificuldade){
        int inicio, fim;

        //Caso especial para os heróis e CorvoRei, que possuem apenas duas armas iniciais possíveis
        if (armas.size() == 2)
            return armas.get(random.nextInt(armas.size()));
        
        if (dificuldade.equals(Dificuldade.FACIL)){
            inicio = 0;
            fim = 2;
        }
        else if (dificuldade.equals(Dificuldade.MEDIO)){
            inicio = 1;
            fim = 3;
        }
        else{
            inicio = 2;
            fim = 4;
        }

        return armas.get(random.nextInt(inicio, fim));
    }

    //Define a posição do monstro considerando a posição atual do herói
    public static int escolherPosicao(Combatente monstro, int posHeroi){

        if (monstro instanceof CorvoRei)
            return posHeroi + 12 + random.nextInt(-3, 3);

        else if (monstro instanceof Esqueleto)
            return posHeroi + 16 + random.nextInt(-4, 2);

        else if (monstro instanceof Goblin)
            return posHeroi + 10 + random.nextInt(-2, 4);

        else if (monstro instanceof Ninfa)
            return posHeroi + 3 + random.nextInt(-2, 2);

        else if (monstro instanceof CavaleiroCorrompido)
            return posHeroi + 10 + random.nextInt(-2, 2);

        else if (monstro instanceof Zumbi)
            return posHeroi + 8 + random.nextInt(-2, 3);

        else if (monstro instanceof GoblinGigante)
            return posHeroi + 10 + random.nextInt(-3, 2);

        else if (monstro instanceof Troll)
            return posHeroi + 10 + random.nextInt(-3, 2);

        else
            return posHeroi + 12 + random.nextInt(-4, 0);
    }

    //Calcula e retorna a distância entre o herói e o monstro
    public static double calcularDistancia(int pos1, int pos2){
        return (double)Math.abs(pos1 - pos2);
    }

    //Retorna de qual classe concreta o personagem é
    public static String verificarClasse(Combatente P){
        if (P instanceof Goblin)
            return "o Goblin";
        else if (P instanceof GoblinGigante)
            return "o Goblin Gigante";
        else if (P instanceof Troll)
            return "o Troll";
        else if (P instanceof Zumbi)
            return "o Zumbi";
        else if (P instanceof CavaleiroCorrompido)
            return "o Cavaleiro Corrompido";
        else if (P instanceof CorvoRei)
            return "o Corvo Rei";
        else if (P instanceof Esqueleto)
            return "o Esqueleto";
        else if (P instanceof Ninfa)
            return "a Ninfa";
        else if (P instanceof Atirador)
            return "o Atirador";
        else
            return "o Lutador";
    }

    //Metódo que auxilia a identificar que turno estamos para printar corretamente por extenso
    public static String verificarTurno(int turno){
        int dezena = turno / 10;
        int unidade = turno - (dezena * 10);
        return turnosDezenas[dezena] + " " + turnosUnidades[unidade];
    }

    //Método para fazer uma pausa entre operações/prints do programa
    public static void esperar() {
        try{
            Thread.sleep(50);
        } 
        catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    //Métodos utilizados para controlar menus de interação com o usuário
    public static int exibirMenuPrincipal(){
        String menu =
            "\n" + 
            "==================================================================" +
            "\n[1] Iniciar novo jogo" +
            "\n[2] Continuar jogo" +
            "\n[3] Conhecer as Classes de Heróis" + 
            "\n[4] Conhecer as Classes de Armas" + 
            "\n[5] Conhecer os Cenarios de Batalha" +
            "\n[6] Sair do jogo\n" +
            "==================================================================" +
            "\nDigite sua opcao > ";
        
        return InputManager.lerInteiro(menu, 1, 6);
    }

    public static void apresentarHerois(){
        String apresentacao = 
            "\n" +
            "==================================================================" +
            "\nExistem duas classes de heróis:\n" +

            "\nO Lutador: guerreiro que possui habilidades com armas de curto alcance e busca sempre estar próximo de seus inimigos." +
            "\nEle possui boa quantidade de pontos de vida, forca e protecao." +
            "\nO Lutador possui 'furia' como atributo unico, quando ele utiliza a furia, consegue atacar mais vezes em um mesmo turno\n" +

            "\nO Atirador: guerreiro que possui habilidade com armas de longo alcance, procurando sempre estar mais longe do inimigo para ser mais efetivo." +
            "\nEle possui menos, mas nao pouca, quantidade de pontos de vida, forca e protecao." +
            "\nO Atirador tem 'precisao' como atributo unico, quanto maior seu nivel, mais chance tem de acertar seu alvo\n" +
            "==================================================================" +
            "\nDeseja ver os atributos de cada classe detalhadamente? (s/n) > ";
        
        if (InputManager.lerSimNao(apresentacao))
            apresentarAtributosHerois();
    }

    private static void apresentarAtributosHerois(){
        System.out.println("\n==================================================================");
        System.out.println("Tabela com os atributos iniciais:\n");

        System.out.println("Classe   | Pontos de vida | Forca | Protecao | Vel. de Movimento");
        System.out.println("----------------------------------------------------------------");
        System.out.printf("Lutador  | %-14d | %-5d | %-8d | %d m/turno\n",120, 70, 60, 7);
        System.out.printf("Atirador | %-14d | %-5d | %-8d | %d m/turno\n",70, 40, 40, 9);
        System.out.println("==================================================================");

        InputManager.esperarEnter("Pressione ENTER para voltar ao menu principal. ");
    }

    public static void apresentarArmas(){
        String apresentacao =
            "\n" +
            "==================================================================" +
            "\nExistem dois tipos de arma:" +
            "\nDe Curto Alcance: Espada, Adagas, Clava Comum, Clava com Espinhos, Lança, Machado e Garras de Corvo" +
            "\nDe Longo Alcance: Arco e flecha, Crossbow, Faca de Arremesso, Funda e Penas de Corvo\n" +
            "==================================================================" +
            "\nDeseja ver detalhes de cada arma? (s/n) > ";
        
        if (InputManager.lerSimNao(apresentacao))
            apresentarDetalhesDasArmas();
    }

    private static void apresentarDetalhesDasArmas(){
        System.out.println("\n==================================================================");
        System.out.println("Tabela com todas as armas:\n");

        System.out.println("Nome da Arma      | Tipo          | Mult. de Dano | Alcance | Vel. de Ataque | Nivel Min. para equipar");
        System.out.println("------------------------------------------------------------------------------------------------------");

        for (Arma arma : armasDoJogo){
            System.out.printf("%-17s | %-13s | %-13.1f | %-7d | %-14d | %d\n", 
                arma.getNome(), arma.getStringTipo(), arma.getDano(), arma.getAttackRange(), arma.getAttackSpeed(), arma.getMinNivel());
        }

        System.out.println("==================================================================");

        InputManager.esperarEnter("Pressione ENTER para voltar ao menu principal. ");
    }

    public static void apresentarCenarios(){
        String apresentacao =
            "\n" +
            "==================================================================" +
            "\nExistem tres cenarios onde se pode ocorrer batalhas:" +
            "\nO Castelo, O Vilarejo Abandonado e o Acampamento da Floresta.\n" +
            "\nCada cenario possui uma lista de possíveis monstros que podem aparecer para batalhar com você.\n" +
            "\nO Acampamento é o local mais perigoso do nosso jogo.\n" +
            "==================================================================" +
            "\nDeseja ver quais sao os monstros de cada cenario? (s/n) > ";
        
        if (InputManager.lerSimNao(apresentacao))
            apresentarMonstros();
    }

    private static void apresentarMonstros(){
        System.out.println("\n==================================================================");
        System.out.println("Tabela com cenarios x monstros:\n");

        System.out.println("                      | Castelo | Vilarejo Abandonado | Acampamento da Floresta");
        System.out.println("-------------------------------------------------------------------------------");

        for (String monstro : monstrosDoJogo){
            System.out.printf("%-21s |   %s   |         %s         |            %s\n", monstro,
                verificarMonstroNoCenario(monstro, TipoCenario.CASTELO),
                verificarMonstroNoCenario(monstro, TipoCenario.VILAREJO),
                verificarMonstroNoCenario(monstro, TipoCenario.ACAMPAMENTO));
        }

        System.out.println("\n==================================================================");

        InputManager.esperarEnter("Pressione ENTER para voltar ao menu principal.");
    }

    private static String verificarMonstroNoCenario(String monstro, TipoCenario cenario){
        if (cenario.getMonstros().contains(monstro))
            return "Sim";
        else
            return "Nao";
    }

    public static boolean haJogoSalvo(){
        File arquivo = new File("saves/save1.xml");
        return arquivo.exists();
    }

    public static Dificuldade escolherDificuldade(){
        String menu = 
            "\nEscolha a dificuldade do jogo\n" +
            "==================================================================" +
            "\n[1] Facil" +
            "\n[2] Medio" + 
            "\n[3] Dificil\n" +
            "==================================================================" +
            "\nDigite sua opcao > ";
        
        int dificuldade = InputManager.lerInteiro(menu, 1, 3);

        if (dificuldade == 1)
            return Dificuldade.FACIL;
        else if (dificuldade == 2)
            return Dificuldade.MEDIO;
        else
            return Dificuldade.DIFICIL;
    }

    public static int exibirMenuPosBatalha(int caso){
        String menu = (caso == 1) ?
            "\n" + 
            "==================================================================" +
            "\n[1] Continuar jogo" +
            "\n[2] Tentar equipar nova arma" +
            "\n[3] Comparar arma atual e arma dropada" +
            "\n[4] Ver informacoes do heroi" + 
            "\n[5] Ver informacoes desta Fase" +
            "\n[6] Salvar jogo" +
            "\n[7] Desistir do jogo\n" +
            "==================================================================" +
            "\nDigite sua opcao > "
            :
            "\n" + 
            "==================================================================" +
            "\n[1] Continuar jogo" +
            "\n[2] Ver informacoes do heroi" + 
            "\n[3] Ver informacoes desta Fase" +
            "\n[4] Salvar jogo" +
            "\n[5] Desistir do jogo\n" +
            "==================================================================" +
            "\nDigite sua opcao > ";
        
        int max = (caso == 1) ? 7 : 5;

        return InputManager.lerInteiro(menu, 1, max);
    }

    public static boolean confirmarDesistencia(){
        String mensagem =
            "\nTem certeza que deseja desistir do jogo? Todo o progresso nao salvo sera perdido. (s/n) > ";
        
        return InputManager.lerSimNao(mensagem);
    }

    public static void tentarEquiparArma(Heroi heroi, Arma arma){

        try{
            if (heroi.getArma().equals(arma))
                throw new ArmasIguaisException();
            else if (heroi instanceof Lutador  && arma.getTipo().equals(TipoDeArma.LONGO_ALCANCE) ||
                     heroi instanceof Atirador && arma.getTipo().equals(TipoDeArma.CURTO_ALCANCE))
                throw new TipoErradoDeArmaException();
            else if (heroi.getNivel() < arma.getMinNivel())
                throw new NaoPossuiNivelException();
            else{
                heroi.setArma(arma);
                System.out.println(heroi.getNome() + " equipou " + arma.getNome() + "!");
            }
        } catch (ArmasIguaisException e){
            System.out.println(e.getMessage());
        } catch (TipoErradoDeArmaException e){
            System.out.println(e.getMessage());
        } catch (NaoPossuiNivelException e){
            System.out.println(e.getMessage());
        }
    }

    public static void compararArmas(Arma arma1, Arma arma2){
        System.out.println("\n==================================================================");
        System.out.println("Comparacao entre " + arma1.getNome() + " e " + arma2.getNome() + ":\n");

        System.out.println("Nome da Arma      | Tipo          | Mult. de Dano | Alcance | Vel. de Ataque | Nivel Min. para equipar");
        System.out.println("------------------------------------------------------------------------------------------------------");

        for (Arma arma : List.of(arma1, arma2)){
            System.out.printf("%-17s | %-13s | %-13.1f | %-7d | %-14d | %d\n", 
                arma.getNome(), arma.getStringTipo(), arma.getDano(), arma.getAttackRange(), arma.getAttackSpeed(), arma.getMinNivel());
        }

        System.out.println("==================================================================");

        InputManager.esperarEnter("Pressione ENTER para voltar ao menu pos-turno. ");
    }

    public static void apresentarInfoDaFase(Fase faseAtual, int monstrosDerrotados){
        System.out.println("\n==================================================================");
        System.out.println("Voce ja derrotou " + monstrosDerrotados + " dos " + faseAtual.getMonstros().size() + " monstros desta fase." + "\n");

        System.out.println("Voce ainda precisa derrotar " + (faseAtual.getMonstros().size() - monstrosDerrotados) + " monstro(s) para passar de fase.");
        System.out.println("==================================================================");

        InputManager.esperarEnter("Pressione ENTER para voltar ao menu pos-turno. ");
    }

    public static void encerrarJogo(){
        System.out.println("Voltando para o menu principal..."); esperar();
        Main.main(null);
    }
}
