public interface Evento {
    boolean verificarGatilho(Combatente heroi, Combatente monstro);
    void executar(Combatente alvo);
}
