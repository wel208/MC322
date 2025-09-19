public interface Fase {
    void iniciar(Heroi heroi, int nivel);
    boolean isConcluida();
    String getTipoDeCenario();
}