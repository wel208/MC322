public abstract class Arma {
    
    // Atributos
    String nome;
    String tipo;
    double dano;
    int minNivel;
    int attackRange;
    int attackSpeed;

    // Getters e Setters
    public int getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public double getDano() {
        return dano;
    }

    public void setDano(double dano) {
        this.dano = dano;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getMinNivel() {
        return minNivel;
    }

    public void setMinNivel(int minNivel) {
        this.minNivel = minNivel;
    }
}