import java.util.Random;

public class CorvoRei extends Monstro{
    
    public CorvoRei(String nome, int nivelDificuldade, int pos, Arma arma){
        super(nome, nivelDificuldade, pos, arma);
        this.pontosDeVidaMax = 60 + (nivelDificuldade - 1) * 15;
        this.pontosDeVida = this.pontosDeVidaMax;
        this.protecao = 0.3 + (nivelDificuldade - 1) * 0.05;
        this.forca = 10 + nivelDificuldade;
        this.moveSpeed = 12;
        this.xpConcedido = 20 + (nivelDificuldade * 15);
        this.sorte = 0.15 + (nivelDificuldade * 0.03);
    }

    @Override
    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.getPos());
        double chance = Math.random();

        System.out.printf("\nO CORVO REI esta a %d metro(s) do %s e ira ", distancia, Utilidades.verificarClasse(alvo));

        if (distancia <= 1){
            System.out.println("SE MOVER!\n"); Utilidades.esperar(200);
            mover(alvo);
        }
        else{
            if (chance < 0.4){
                System.out.println("SE MOVER!\n"); Utilidades.esperar(200);
                mover(alvo);
            }
            else{
                System.out.println("ATACAR!\n"); Utilidades.esperar(200);
                atacar(alvo);
            }
        }
    }

    @Override
    public void atacar(Personagem alvo){
        Random r = new Random();
        String[] corvejo = {"CROAC!", "CRAC!", "CRO!"};

        int contador = 0;

        for (int i = 0; i < arma.getAttackSpeed(); i++){
            System.out.print(corvejo[r.nextInt(3)] + " ");
            Utilidades.esperar(200);

            if (Math.random() > alvo.getDodgeChance()){
                contador++;
                alvo.receberDano(forca);
            }
        }

        System.out.printf("\n\n%s acertou %d de %d ataques dados!\n", nome, contador, arma.getAttackSpeed()); Utilidades.esperar(200);
    }

    @Override
    public void mover(Personagem alvo){
        if (Math.random() < 0.5)
            pos += moveSpeed;
        else
            pos -= moveSpeed;
        
        System.out.printf("%s, o CORVO REI, agora esta a %d metros do nosso guerreiro!\n", nome, Utilidades.calcularDistancia(pos, alvo.pos)); Utilidades.esperar(200);
    }
}
