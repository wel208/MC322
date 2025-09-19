public class AjudaExterna implements Evento {

    public boolean verificarGatilho(Combatente heroi, Combatente monstro){
        if (heroi.getPontosDeVida() < 0.3 * heroi.getPontosDeVidaMax() &&
            monstro.getPontosDeVida() > 0.5 * monstro.getPontosDeVidaMax() &&
            Math.random() < 0.4){
                System.out.println("\nQUE OTIMO! Um arqueiro aliado apareceu para ajudar nosso heroi!");
                return true;
            }
        else
            return false;
    }

    public void executar(Combatente alvo){
        double dano = alvo.receberDano(100);
        System.out.printf("\n%s, %s, recebeu %d de dano!\n",alvo.getNome(), Utilidades.verificarClasse(alvo), dano);
    }
}
