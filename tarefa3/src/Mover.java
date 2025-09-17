public class Mover implements AcaoDeCombate {

    @Override
    public void executar(Combatente usuario, Combatente alvo){

        boolean chegou = false;

        if (usuario.getArma().getTipo() == "Corpo a Corpo"){
            int direcao = usuario.getPos() < alvo.getPos() ? 1 : -1;

            for (int i = 0; i < usuario.getMoveSpeed(); i++){
                usuario.setPos(usuario.getPos() + direcao);

                if (Utilidades.calcularDistancia(usuario.getPos(), alvo.getPos()) == usuario.getArma().getAttackRange()){
                    chegou = true;
                    System.out.printf("%s ALNCANCOU %s e ira ATACAR!\n", usuario.getNome(), Utilidades.verificarClasse(alvo));
                    break;
                }
            }
        }
        else{

            boolean afastando = Utilidades.calcularDistancia(usuario.getPos(), alvo.getPos()) < usuario.getArma().getAttackRange();
            int direcao;

            if (afastando)
                direcao = usuario.getPos() < alvo.getPos() ? -1 : 1;
            else
                direcao = usuario.getPos() < alvo.getPos() ? 1 : -1;

            for (int i = 0; i < usuario.getMoveSpeed(); i++){
                usuario.setPos(usuario.getPos() + direcao);
                if (Utilidades.calcularDistancia(usuario.getPos(), alvo.getPos()) == usuario.getArma().getAttackRange()){
                    chegou = true;
                    System.out.printf("%s chegou a uma distancia ideial e ira ATACAR %s!\n", usuario.getNome(), Utilidades.verificarClasse(alvo)); Utilidades.esperar();
                    break;
                }
            }

            if (chegou){
                AtaqueComum ataque = new AtaqueComum();
                ataque.executar(usuario, alvo);
            }
            else{
                int distancia = Utilidades.calcularDistancia(usuario.getPos(), alvo.getPos());
                System.out.printf("%s se movimentou e agora esta a %d metros de %s, %s!\n", usuario.getNome(), distancia, alvo.getNome(), Utilidades.verificarClasse(alvo)); Utilidades.esperar();
            }
        }
    }
}
