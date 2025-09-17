public interface Combatente {
    String getNome();
    String getClasse();
    Arma getArma();
    int getPos();
    int getForca();
    double getSorte();
    double getAtributoUnico();
    double getCriticalChance();
    double getDodgeChance();
    boolean estaVivo();
    int receberDano(double dano);
    void receberCura(int cura);
    void escolherAcao(Combatente alvo);
}