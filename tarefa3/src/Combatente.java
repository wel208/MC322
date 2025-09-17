public interface Combatente {
    String getNome();
    Arma getArma();
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
    void escolherAcao(Combatente alvo);
}