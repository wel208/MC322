import java.util.Random;
import java.util.List;

public class CorvoRei extends Monstro {

    private double precisao;
    private List<Arma> armasPossiveis = List.of(new GarrasCorvo(), new PenasCorvo());
    
    public CorvoRei(String nome, int nivelDificuldade, int pos, Arma arma){
        super(nome, nivelDificuldade, pos, arma);
        this.pontosDeVidaMax = 60 + (nivelDificuldade - 1) * 15;
        this.pontosDeVida = this.pontosDeVidaMax;
        this.protecao = 0.3 + (nivelDificuldade - 1) * 0.05;
        this.forca = 10 + nivelDificuldade;
        this.moveSpeed = 12;
        this.xpConcedido = 20 + (nivelDificuldade * 15);
        this.sorte = 0.15 + (nivelDificuldade * 0.03);
        this.acoes = List.of(new Mover(), new AtaqueComum());
    }

    @Override
    public void escolherAcao(Combatente alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.getPos());
        double chance = Math.random();

        System.out.printf("\nO CORVO REI esta a %d metro(s) do %s e ira ", distancia, Utilidades.verificarClasse(alvo));

        if (distancia <= 1){
            System.out.println("SE MOVER!\n"); Utilidades.esperar();
            acoes.get(0).executar(this, alvo);
        }
        else{
            if (chance < 0.4){
                System.out.println("SE MOVER!\n"); Utilidades.esperar();
                acoes.get(0).executar(this, alvo);
            }
            else{
                System.out.println("ATACAR!\n"); Utilidades.esperar();

                Random r = new Random();
                setArma(armasPossiveis.get(r.nextInt(2))); //Chance de utilizar suas penas ou suas garras

                System.out.printf("\n%s, %s, ira utilizar %s!\n\n", nome, Utilidades.verificarClasse(this), arma.getNome());

                acoes.get(1).executar(this, alvo);

                if (arma.getTipo() == "Corpo a Corpo"){
                    setPos(alvo.getPos() + r.nextInt(1, 5));
                }
            }
        }
    }

    public double getAtributoUnico(){
        return precisao;
    }
}
