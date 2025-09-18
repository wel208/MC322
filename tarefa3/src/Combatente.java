public interface Combatente {
    String getNome();
    Arma getArma();
    void setArma(Arma arma);
    int getPos();
    double getForca();
    int getMoveSpeed();
    double getSorte();
    double getAtributoUnico();
    double getCriticalChance();
    double getDodgeChance();
    void setPos(int pos);
    boolean estaVivo();
    int receberDano(double dano);
    void receberCura(int cura);
    AcaoDeCombate escolherAcao(Combatente alvo);
}