public class Utilidades{

    public static int calcularDistancia(int pos1, int pos2){
        return Math.abs(pos1 - pos2);
    }

    public static void esperar(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public String verificarClasse(Personagem P){
        if (P instanceof Goblin)
            return "Goblin";
        else if (P instanceof CorvoRei)
            return "Corvo rei";
        else if (P instanceof Esqueleto)
            return "Esqueleto";
        else if (P instanceof Rangedor)
            return "Rangedor";
        else
            return "Sucubo";
    }
}
