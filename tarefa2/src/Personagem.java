import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Personagem {

    // Atributos base
    protected String nome;            // Nome dado ao personagem
    protected int pontosDeVida;       // Quantidade de pontos de vida que o personagem possui
    protected int pontosDeVidaMax;    // Pontos de vida maxima que o personagem pode se curar
    protected double protecao;        // Proteção contra ataques inimigos (0.0 a <1.0)
    protected double forca;           // Força do personagem
    protected int moveSpeed;          // Distância que o personagem percorre por turno
    protected int pos;                // Posição no mapa
    protected double criticalChance;  // Chance de causar dano crítico
    protected double sorte;           // Chance de sucesso em ações
    protected double dodgeChance;     // Chance de esquivar de ataques (0.0 a 1.0)
    protected int attackSpeed;        // Quantidade de ataques por turno
    protected Arma arma;              // Arma utilizada

    // Estados temporários derivados de Status
    private boolean atordoadoTurno;   // Reseta a cada turno, aplicado por "atordoado"
    private boolean cego;             // Persiste enquanto durar "cegueira"

    // Lista para armazenar status ativos
    protected List<Status> statusAtivos = new ArrayList<>();

    // Construtor
    public Personagem(String nome){
        this.nome = nome;
    }

    // Getter para o nome (novo)
    public String getNome() {
        return nome;
    }

    // Métodos utilitários
    public void statusParcial(){
        System.out.printf("\n%s, O %s, está com %d PONTOS DE VIDA.\n",
            nome, Utilidades.verificarClasse(this), pontosDeVida);
        Utilidades.esperar(1500);
    }

    protected void receberDano(double forca){
        pontosDeVida -= forca * (1 - protecao);
        if (pontosDeVida < 0) pontosDeVida = 0;
        if (pontosDeVida > pontosDeVidaMax) pontosDeVida = pontosDeVidaMax;
    }

    public void aplicarStatus(String nomeStatus, int duracao){
        Status s = new Status(nomeStatus, duracao);
        statusAtivos.add(s);
        System.out.printf("%s recebeu o status %s por %d turnos.\n", nome, nomeStatus, duracao);
    }

    public void processarStatus() {
        this.atordoadoTurno = false;

        Iterator<Status> it = statusAtivos.iterator();
        while (it.hasNext()) {
            Status s = it.next();

            if (!s.aplicado) {
                s.aplicarAoEntrar(this);
                s.aplicado = true;
            }

            s.aplicarNoTurno(this);
            s.tick();

            if (s.expirou()) {
                s.reverterAoSair(this);
                System.out.println(nome + " não está mais com o status " + s.nome + ".");
                it.remove();
            }
        }
    }

    // Getters e Setters
    public double getDodgeChance() { return dodgeChance; }
    public void setDodgeChance(double dodgeChance) { this.dodgeChance = dodgeChance; }
    public int getAttackSpeed() { return attackSpeed; }
    public void setAttackSpeed(int attackSpeed) { this.attackSpeed = attackSpeed; }
    public double getCriticalChance() { return criticalChance; }
    public double getForca() { return forca; }
    public int getPos() { return pos; }
    public int getPontosDeVida() { return pontosDeVida; }
    public void setPontosDeVida(int pontosDeVida) {
        this.pontosDeVida = Math.max(0, Math.min(pontosDeVida, pontosDeVidaMax));
    }
    public int getPontosDeVidaMax() { return pontosDeVidaMax; }
    public void setPontosDeVidaMax(int pontosDeVidaMax) {
        this.pontosDeVidaMax = pontosDeVidaMax;
        if (pontosDeVida > pontosDeVidaMax) pontosDeVida = pontosDeVidaMax;
    }

    public boolean isAtordoadoTurno() { return atordoadoTurno; }
    protected void setAtordoadoTurno(boolean atordoadoTurno) { this.atordoadoTurno = atordoadoTurno; }
    public boolean isCego() { return cego; }
    protected void setCego(boolean cego) { this.cego = cego; }

    // Métodos abstratos
    protected abstract void atacar(Personagem alvo);
    protected abstract void mover(Personagem alvo);
    public abstract void tomarDecisao(Personagem alvo);

    // Classe interna Status
    protected static class Status {
        String nome;
        int duracao;
        boolean aplicado = false;
        Double deltaProtecao = null;

        Status(String nome, int duracao){
            this.nome = nome;
            this.duracao = duracao;
        }

        public void tick() { duracao--; }
        public boolean expirou() { return duracao <= 0; }

        public void aplicarAoEntrar(Personagem alvo) {
            switch (nome.toLowerCase()) {
                case "defesa_reduzida":
                case "defesa reduzida":
                case "fratura":
                    double reducao = 0.15;
                    double antes = alvo.protecao;
                    alvo.protecao = Math.max(0.0, alvo.protecao - reducao);
                    deltaProtecao = antes - alvo.protecao;
                    System.out.println(alvo.nome + " teve a defesa reduzida!");
                    break;
                case "cegueira":
                case "cego":
                    alvo.setCego(true);
                    System.out.println(alvo.nome + " está cego e pode errar suas ações!");
                    break;
            }
        }

        public void aplicarNoTurno(Personagem alvo) {
            switch (nome.toLowerCase()) {
                case "sangramento":
                    alvo.setPontosDeVida(alvo.getPontosDeVida() - 5);
                    System.out.println(alvo.nome + " sofre 5 de dano por sangramento!");
                    break;
                case "envenenado":
                case "veneno":
                    alvo.setPontosDeVida(alvo.getPontosDeVida() - 4);
                    System.out.println(alvo.nome + " sofre 4 de dano por veneno!");
                    break;
                case "atordoado":
                case "stun":
                    alvo.setAtordoadoTurno(true);
                    System.out.println(alvo.nome + " está atordoado e perderá a vez!");
                    break;
            }
        }

        public void reverterAoSair(Personagem alvo) {
            switch (nome.toLowerCase()) {
                case "defesa_reduzida":
                case "defesa reduzida":
                case "fratura":
                    if (deltaProtecao != null) {
                        alvo.protecao = Math.min(0.99, alvo.protecao + deltaProtecao);
                        deltaProtecao = null;
                    }
                    break;
                case "cegueira":
                case "cego":
                    alvo.setCego(false);
                    break;
            }
        }
    }
}
