import java.util.Random;

import javax.sound.midi.SysexMessage;

public class CorvoRei extends Monstro{
    
    //Construtor
    public CorvoRei(String nome, int nivel, int pos){
        super(nome, nivel, pos);
        this.attackSpeed = 5 + nivel % 3;
        this.dodgeChance = 0.5 + 0.02 * nivel;
        this.forca = 10;
        this.moveSpeed = 12;
        this.protecao = 10;
        this.pontosDeVida = 20 + 2 * (nivel - 1);
        this.xpConcedido = 30 + 2 * nivel;
    }

    @Override
    public void ambientarMonstro(){

    }

    @Override
    public void statusParcial(){
        System.out.printf("\n%s, O CORVO REI, está com %d PONTOS DE VIDA.", nome, pontosDeVida);
    }

    @Override
    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);
        double chance = Math.random();

        System.out.printf("\nO CORVO REI está a %d metros do %s e irá ", distancia, Utilidades.verificarClasse(alvo));
        
        if (distancia <= 2){
            System.out.print("SE MOVER!");
            mover(alvo);
        }
        else{
            if (chance < 0.5){
                System.out.print("SE MOVER!");
                mover(alvo);
            }
            else{
                System.out.print("ATACAR!");
                atacar(alvo);
            }
        }
    }

    @Override
    public void atacar(Personagem alvo){
        Random r = new Random();
        String[] corvejo = {"CROAC!", "CRAC!", "CRO!"};

        int contador = 0;

        for (int i = 0; i < attackSpeed; i++){

            System.out.print(corvejo[r.nextInt(2)] + " ");
            Utilidades.esperar(300);

            if (Math.random() > alvo.dodgeChance){
                contador++;
                alvo.receberDano(forca);
            }
        }

        System.out.printf("\nOs corvos acertaram %d dos %d ataques dados!", contador, attackSpeed);
        alvo.statusParcial();
    }

    @Override
    public void mover(Personagem alvo){
        if (Math.random() < 0.5)
            pos += moveSpeed;
        else
            pos -= moveSpeed;
        
        System.out.printf("\n%s, o CORVO REI, agora está a %d metros do nosso herói!", nome, Utilidades.calcularDistancia(pos, alvo.pos));
    }
}
