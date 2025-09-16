public interface AcaoDeCombate {
    void atacar(Combatente usuario, Combatente alvo);
    void usarHabilidadeEspecial(Combatente usuario, Combatente alvo);
    void mover(Combatente usuario, Combatente alvo);
    void tomarDecisao(Combatente usuario, Combatente alvo);
}
