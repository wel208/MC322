/*
 * Classe de herói que não tem uma movimentação tão rápida
 * Executa ataques corpo a corpo
 * Boa quantidade de pontos de vida, de força e de proteção
 * Boa chance de esquivar de ataques inimigos
 */

public class Lutador extends Heroi{

    //Atributos
    private int furia;

    //Construtor
    public Lutador(String nome, Arma arma){ //Atributos predefinidos para um lutador de nível 0
        super(nome, arma);
        this.pontosDeVida = 200;
        this.moveSpeed = 7;
        this.forca = 70;
        this.protecao = 0.6;
        this.furia = 1;
    }

    //Métodos
    @Override
    public void exibirStatus(){
        System.out.printf("\n%s, O lutador possui:", nome); Utilidades.esperar();
        System.out.printf("\n%d PONTOS DE VIDA;", pontosDeVida); Utilidades.esperar();
        System.out.printf("\nNIVEL %d e %d/%d de EXPERIENCIA;", getNivel(), getExperiencia(), getExpProximoNivel()); Utilidades.esperar();
        System.out.printf("\n%.0f pontos de FORCA e %.0f pontos de PROTECAO;", forca, protecao * 100); Utilidades.esperar();
        System.out.printf("\n%d pontos de FURIA;", furia); Utilidades.esperar();
        System.out.printf("\n%.2f pontos de SORTE;", sorte); Utilidades.esperar();
        System.out.printf("\n%s esta usando %s como ARMA;", nome, arma.nome); Utilidades.esperar();
        System.out.printf("\ncapacidade de dar %d ATAQUES POR TURNO e pode percorrer %d METROS POR TURNO.\n", arma.attackSpeed, moveSpeed); Utilidades.esperar();
    }

    /*
     * Caso o lutador esteja a uma distância maior que 1 metro do inimigo, ele irá avançar
     * Se ele já estiver a uma distância <= 1, ele decidirá entre usar o ataque comum ou sua habilidade especial
     */
    @Override
    public void escolherAcao(Combatente alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.getPos());

        System.out.printf("\nO lutador esta a %d metros do monstro e ira ", distancia);

        if (distancia > arma.attackRange){
            System.out.println("CORRER NA DIRECAO DELE!\n"); Utilidades.esperar();
            acoes.get(0).executar(this, alvo);
        }
        else{
            if (Math.random() >= 0.3){
                System.out.println("ATACAR O SEU INIMIGO!"); Utilidades.esperar();
                acoes.get(1).executar(this, alvo);
            }
            else{
                System.out.println("USAR SUA HABILIDADE ESPECIAL!\n"); Utilidades.esperar();
                acoes.get(2).executar(this, alvo);
            }
        }
    }

    /*
     * Método que aumenta a furia do lutador a cada nivel de XP par alcançado
     */
    @Override
    protected void melhorarAtributoUnico(){
        this.furia += 1;
    }

    @Override
    public double getAtributoUnico(){
        return furia;
    }
}