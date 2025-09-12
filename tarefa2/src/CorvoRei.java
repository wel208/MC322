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
    public void ambientarMonstro(Heroi heroi){
        System.out.println("\nApos certo tempo caminhando, o nosso heroi percebe que o barulho dos corvos esta cada vez mais alto."); Utilidades.esperar(200);
        System.out.println("Ate que chega o momento em que " + nome + " aparece, o Corvo Rei."); Utilidades.esperar(200);
        System.out.println("Um corvo com penas que refletem luz de forma que parece ter pedacos de ouro em suas asas."); Utilidades.esperar(200);
        System.out.println("Ele, cercado de sua horda de corvos, se mostra pronto para impedir que o heroi complete seu objetivo."); Utilidades.esperar(200);
        System.out.println("Com um simples corvejo, pode fazer com que seus capangas executem suas vontades."); Utilidades.esperar(200);
        System.out.println("Porem ele tem um problema: ele nao pode fazer isso enquanto voa."); Utilidades.esperar(200);
        System.out.println("Com " + nome + " morto, sua horda nao conseguira seguir em combate."); Utilidades.esperar(200);
        System.out.println("O heroi entao se prepara para a batalha."); Utilidades.esperar(200);

        System.out.printf("\nEste e um oponente agil, consegue se movimentar rapidamente de um local para outro."); Utilidades.esperar(200);
        System.out.printf("\nAlem de conseguir esquivar de ataques inimigos com certa facilidade"); Utilidades.esperar(200);
        System.out.printf("\n%s, O CORVO REI, que esta no nivel %d, possui:", nome, nivelDificuldade); Utilidades.esperar(200);
        System.out.printf("\n%d PONTOS DE VIDA;", pontosDeVida); Utilidades.esperar(200);
        System.out.printf("\nSeus capangas tem %.0f PONTOS DE FORCA;", forca); Utilidades.esperar(200);
        System.out.printf("\nE, ao morrer, concedera %d PONTOS DE EXPERIENCIA ao heroi\n", xpConcedido); Utilidades.esperar(200);

        System.out.println("\nAgora, tomado pela raiva, o nosso heroi comeca a sua batalha!"); Utilidades.esperar(200);
    }

    @Override
    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);
        double chance = Math.random();

        System.out.printf("\nO CORVO REI esta a %d metro(s) do %s e ira ", distancia, Utilidades.verificarClasse(alvo));

        if (chance < 0.1) {
            chuvaDeCorvos(alvo);
            return;
        }
        else if (chance < 0.2) {
            vooRasante(alvo);
            return;
        }

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

        for (int i = 0; i < arma.attackSpeed; i++){
            System.out.print(corvejo[r.nextInt(3)] + " ");
            Utilidades.esperar(200);

            if (Math.random() > alvo.dodgeChance){
                contador++;
                alvo.receberDano(forca);
            }
        }

        System.out.printf("\n\nOs corvos acertaram %d dos %d ataques dados!\n", contador, arma.attackSpeed); Utilidades.esperar(200);
    }

    @Override
    public void mover(Personagem alvo){
        if (Math.random() < 0.5)
            pos += moveSpeed;
        else
            pos -= moveSpeed;
        
        System.out.printf("%s, o CORVO REI, agora esta a %d metros do nosso guerreiro!\n", nome, Utilidades.calcularDistancia(pos, alvo.pos)); Utilidades.esperar(200);
    }

    // Habilidade especial: Chuva de Corvos (com cegueira)
    public void chuvaDeCorvos(Personagem alvo){
        System.out.println("\nO CORVO REI solta um grito ensurdecedor e uma CHUVA DE CORVOS ataca o herói!"); Utilidades.esperar(200);
        int ataques = arma.attackSpeed + 2;
        int acertos = 0;

        for (int i = 0; i < ataques; i++){
            if (Math.random() > alvo.dodgeChance){
                acertos++;
                alvo.receberDano(forca * arma.dano * 0.8);
            }
        }

        System.out.printf("Os corvos acertaram %d de %d ataques!\n", acertos, ataques); Utilidades.esperar(200);

        alvo.aplicarStatus("Cegueira", 1);
        System.out.println("O herói está CEGADO e terá dificuldade para acertar ataques no próximo turno!"); Utilidades.esperar(200);
    }

    // Habilidade especial: Voo Rasante (com aumento de esquiva)
    public void vooRasante(Personagem alvo){
        System.out.println("\nO CORVO REI mergulha em um VOO RASANTE contra o herói!"); Utilidades.esperar(200);

        pos = alvo.pos + 1;

        if (Math.random() > alvo.dodgeChance){
            alvo.receberDano(forca * arma.dano * 1.2);
            System.out.println("O ataque acerta em cheio!"); Utilidades.esperar(200);
        } else {
            System.out.println("O herói esquiva do voo rasante!"); Utilidades.esperar(200);
        }

        this.aplicarStatus("Esquiva Aumentada", 1);
        System.out.println("O CORVO REI está mais difícil de acertar após o voo rasante!"); Utilidades.esperar(200);
    }
}
