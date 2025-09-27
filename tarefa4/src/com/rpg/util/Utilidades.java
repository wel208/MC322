package com.rpg.util;

import com.rpg.itens.*;
import com.rpg.cenario.*;
import com.rpg.personagens.*;
import com.rpg.combate.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utilidades{

    //Strings para printar qual o atual turno em uma batalha
    static String[] turnosDezenas = {"", " DECIMO", " VIGESIMO", " TRIGESIMO", " QUADRAGESIMO", " QUINQUAGESIMO"};
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

    //Possíveis armas que os monstros podem utilizar
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
        String nome = nomesHeroi[random.nextInt(nomesHeroi.length)];
        if (Math.random() < 0.5)
            return new Lutador(nome, escolherArma(armasLutador, null));
        else
            return new Atirador(nome, escolherArma(armasAtirador, null));
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
        int inicio = 0, fim = 2;

        if (dificuldade.equals(null))
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
    public static void esperar() {
        try{
            Thread.sleep(200);
        } 
        catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
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

    //Método que cria uma lista de monstros para uma fase
    public static ArrayList<Monstro> criarListaDeMonstro(TipoCenario cenario, int nivel, Dificuldade dificuldade){
        ArrayList<Monstro> monstros = new ArrayList<>();
        int numMontros = random.nextInt(3, 6);

        for (int i = 0; i < numMontros; i++)
            monstros.add(criarMonstro(cenario, nivel, dificuldade));
        
        return monstros;
    }
}
