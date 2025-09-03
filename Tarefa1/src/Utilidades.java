public class Utilidades{

    public static int calcularDistancia(int pos1, int pos2){
        return Math.abs(pos1 - pos2);
    }

    public static void esperar(int ms) {
        try{
            Thread.sleep(ms);
        } 
        catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

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
