/*
 * Classe de herói que tem movimentação rápida
 * Não possui muitos pontos de vida e proteção
 * Prefere dar ataques a longa distãncia
 */

public class Atirador extends Heroi {

    //Atributos
    private double precisao;

    //Construtor
    public Atirador(String nome, Arma arma){
        super(nome, arma);
        this.pontosDeVida = 150;
        this.moveSpeed = 10;
        this.forca = 40;
        this.protecao = 0.4;
        this.precisao = 0.3;
    }
    
    //Métodos
    @Override
    public void exibirStatus(){
        System.out.printf("\n%s, O ATIRADOR possui:", nome); Utilidades.esperar(200);
        System.out.printf("\n%d PONTOS DE VIDA;", pontosDeVida); Utilidades.esperar(200);
        System.out.printf("\nNIVEL %d e %d/%d de EXPERIENCIA;", getNivel(), getExperiencia(), getExpProximoNivel()); Utilidades.esperar(200);
        System.out.printf("\n%.0f pontos de FORCA e %.0f pontos de PROTECAO;", forca, protecao * 100); Utilidades.esperar(200);
        System.out.printf("\n%.0f pontos de PRECISAO;", precisao); Utilidades.esperar(200);
        System.out.printf("\n%.0f%% de SORTE;", sorte * 100); Utilidades.esperar(200);
        System.out.printf("\n%s esta utilizando %s como ARMA;", nome, arma.getNome()); Utilidades.esperar(200);
        System.out.printf("\nCapacidade de dar %d ATAQUE(S) POR TURNO e pode percorrer %d METROS POR TURNO.\n", arma.getAttackSpeed(), moveSpeed); Utilidades.esperar(200);
    }

    /*
     * O atirador toma sua decisão com base na distância que ele está do inimigo
     * Quanto mais perto, mais chance de se afastar
     * Quanto mais longe, mais chance de atacar ou usar sua habilidade especial
     */
    @Override
    public void escolherAcao(Combatente alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.getPos());
        double chance = Math.random();

        System.out.printf("\nO atirador esta a %d metros do monstro e ira ", distancia); Utilidades.esperar(200);

        if (distancia < (double)arma.getAttackRange() * 0.3){
            if (chance < 0.8){
                System.out.println("se AFASTAR do inimigo!\n"); Utilidades.esperar(200);
                mover(alvo);
            }
            else if (chance < 0.95){
                System.out.println("ATACAR o inimigo!\n"); Utilidades.esperar(200);
                atacar(alvo);
            }
            else{
                System.out.println("USAR SUA HABILIDADE ESPECIAL nesse turno!\n"); Utilidades.esperar(200);
                usarHabilidadeEspecial(alvo);
            }
        }
        else if (distancia <= (double)arma.getAttackRange() * 0.8){
            if (chance < 0.45){
                System.out.println("se AFASTAR do inimigo!\n"); Utilidades.esperar(200);
                mover(alvo);
            }
            else if (chance < 0.9){
                System.out.println("ATACAR o inimigo!\n"); Utilidades.esperar(200);
                atacar(alvo);
            }
            else{
                System.out.println("USAR SUA HABILIDADE ESPECIAL nesse turno!\n"); Utilidades.esperar(200);
                usarHabilidadeEspecial(alvo);
            }
        }
        else if (distancia <= arma.getAttackRange()){
            if (chance < 0.15){
                System.out.println("se AFASTAR do inimigo!\n"); Utilidades.esperar(200);
                mover(alvo);
            }
            else if (chance < 0.75){
                System.out.println("ATACAR o inimigo!\n"); Utilidades.esperar(200);
                atacar(alvo);
            }
            else{
                System.out.println("USAR SUA HABILIDADE ESPECIAL nesse turno!\n"); Utilidades.esperar(200);
                usarHabilidadeEspecial(alvo);
            }
        }
        else{
            System.out.println("ter que se APROXIMAR do inimigo para atacar!\n"); Utilidades.esperar(200);
            mover(alvo);
        }
    }

    /*
     * O ataque do atirador leva em consideração sua precisão
     * Quanto mais longe do inimigo o atirador estiver, maior o dano causado
     */
    @Override
    protected void atacar(Personagem alvo){
        int contador = 0;

        for (int i = 0; i < arma.getAttackSpeed(); i++) {

            double chanceAcerto = Math.min(0.99, precisao + sorte);

            if (Math.random() < chanceAcerto) {

                if (Math.random() > alvo.getDodgeChance()){
                    contador++;
                    int distancia = Utilidades.calcularDistancia(pos, alvo.getPos());

                    boolean critico = Math.random() < criticalChance;
                    double multiplicador = critico ? 1.2 : 1.0;

                    alvo.receberDano(forca * arma.getDano() * distancia/5 * multiplicador);

                    if (critico){
                        System.out.println("BOA! O atirador ACERTOU um ataque CRITICO no inimigo!");
                    }
                    else{
                        System.out.println("BOA! O atirador ACERTOU o inimigo!");
                    }
                    Utilidades.esperar(200);
                }
                else{
                    System.out.println("NAO! O inimigo ESQUIVOU do ataque!"); Utilidades.esperar(200);
                }
            }
            else{
                System.out.println("NAO! O atirador ERROU o ataque!"); Utilidades.esperar(200);
            }
        }
        System.out.printf("\nO heroi acertou %d de %d ataque(s) dado(s)!\n", contador, arma.getAttackSpeed()); Utilidades.esperar(200);
    }

    /*
     * A movimentação do atirador é sempre a de se afastar do inimigo
     * A menos que ele esteja longe demais
     * Nesse caso, ele se aproxima
     * Por não ter tanta vida e não ter um bom dano em curta distância
     * Ele sempre tentará ficar longe do monstro para poder ter vantagem
     */
    @Override
    protected void mover(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.getPos());
        boolean chegou = false;

        //Se estiver longe demais, se aproxima
        if (distancia > arma.getAttackRange()){
            for (int i = 0; i < moveSpeed; i++){
                pos += (pos < alvo.getPos()) ? 1 : -1;
                if (distancia == arma.getAttackRange()){
                    System.out.println("O atirador ALCANCOU A DISTANCIA IDEAL e ira ATACAR!\n"); Utilidades.esperar(200);
                    chegou = true;
                    break;
                }
            }
        }
        //Se estiver perto demais, se afasta
        else{
            for (int i = 0; i < moveSpeed; i++){
                pos += (pos < alvo.getPos()) ? -1 : 1;
                distancia = Utilidades.calcularDistancia(pos, alvo.getPos());
                if (distancia > arma.getAttackRange()){
                    System.out.println("O atirador SE AFASTOU do inimigo e esta na DISTANCIA IDEAL para ATACAR!\n"); Utilidades.esperar(200);
                    chegou = true;
                    break;
                }
            }
        }
        
        if (chegou){
            atacar(alvo);
            return;
        }
        else{
            System.out.printf("O atirador chegou a uma distancia de %d metros do monstro!\n", Utilidades.calcularDistancia(pos, alvo.getPos())); Utilidades.esperar(200);
        }
    }

    /*
     * A habilidade especial do atirador é de atirar 2 projéteis a mais em um turno
     * Em contrapartida, recebe uma redução na porcentagem de precisão
     * Mas também recebe um aumento em seu dano
     */
    @Override
    protected void usarHabilidadeEspecial(Personagem alvo){
        int qtdAtque = arma.getAttackSpeed() + 2;
        int contador = 0;

        System.out.printf("%s atirara %d projeteis neste turno!\n\n", nome, qtdAtque); Utilidades.esperar(200);

        for (int i = 0; i < qtdAtque; i++){

            double chanceAcerto = Math.min(0.9, (precisao + sorte) * 0.9);

            if (Math.random() < chanceAcerto){

                if (Math.random() > alvo.getDodgeChance()){
                    contador++;

                    int distancia = Utilidades.calcularDistancia(pos, alvo.getPos());

                    alvo.receberDano(forca * arma.getDano() * distancia/5 * 1.1);
                    System.out.println("ISSO! O nosso atirador ACERTOU o inimigo!"); Utilidades.esperar(200);
                }
                else{
                    System.out.println("NAO! O inimigo ESQUIVOU do projetil do nosso atirador!"); Utilidades.esperar(200);
                }
            }
            else
                System.out.println("NAO! O atirador ERROU o projetil!"); Utilidades.esperar(200);
        }

        System.out.printf("\nO heroi acertou %d projeteis de %d projeteis atirados!", contador, qtdAtque); Utilidades.esperar(200);
    }

    /*
     * Método que aumenta a precisão do atirador a cada nível de XP par alcançado
     */
    @Override
    protected void melhorarAtributoUnico(){
        precisao += 0.05;
        if (precisao > 0.6) precisao = 0.6;
    }
}
