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
}
