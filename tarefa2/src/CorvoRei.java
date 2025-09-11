import java.util.Random;

public class CorvoRei extends Monstro{
    
    public CorvoRei(String nome, int nivel, int pos, Arma arma){
        super(nome, nivel, pos, arma);
    }

    @Override
    public void ambientarMonstro(Heroi heroi){
        System.out.println("\nApos certo tempo caminhando, o nosso heroi percebe que o barulho dos corvos esta cada vez mais alto."); Utilidades.esperar(2000);
        System.out.println("Ate que chega o momento em que " + nome + " aparece, o Corvo Rei."); Utilidades.esperar(2000);
        System.out.println("Um corvo com penas que refletem luz de forma que parece ter pedacos de ouro em suas asas."); Utilidades.esperar(2000);
        System.out.println("Ele, cercado de sua horda de corvos, se mostra pronto para impedir que o heroi complete seu objetivo."); Utilidades.esperar(2000);
        System.out.println("Com um simples corvejo, pode fazer com que seus capangas executem suas vontades."); Utilidades.esperar(2000);
        System.out.println("Porem ele tem um problema: ele nao pode fazer isso enquanto voa."); Utilidades.esperar(2000);
        System.out.println("Com " + nome + " morto, sua horda nao conseguira seguir em combate."); Utilidades.esperar(2000);
        System.out.println("O heroi entao se prepara para a batalha."); Utilidades.esperar(2000);

        System.out.printf("\nEste e um oponente agil, consegue se movimentar rapidamente de um local para outro."); Utilidades.esperar(2000);
        System.out.printf("\nAlem de conseguir esquivar de ataques inimigos com certa facilidade"); Utilidades.esperar(2000);
        System.out.printf("\n%s, O CORVO REI, que esta no nivel %d, possui:", nome, nivelDificuldade); Utilidades.esperar(2000);
        System.out.printf("\n%d PONTOS DE VIDA;", pontosDeVida); Utilidades.esperar(1500);
        System.out.printf("\nSeus capangas tem %.0f PONTOS DE FORCA;", forca); Utilidades.esperar(1500);
        System.out.printf("\nE, ao morrer, concedera %d PONTOS DE EXPERIENCIA ao heroi\n", xpConcedido); Utilidades.esperar(1500);

        System.out.println("\nAgora, tomado pela raiva, o nosso heroi comeca a sua batalha!"); Utilidades.esperar(2000);
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
            System.out.println("SE MOVER!\n"); Utilidades.esperar(1500);
            mover(alvo);
        }
        else{
            if (chance < 0.4){
                System.out.println("SE MOVER!\n"); Utilidades.esperar(1500);
                mover(alvo);
            }
            else{
                System.out.println("ATACAR!\n"); Utilidades.esperar(1500);
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
            System.out.print(corvejo[r.nextInt(3)] + " ");
            Utilidades.esperar(500);

            if (Math.random() > alvo.dodgeChance){
                contador++;
                alvo.receberDano(forca);
            }
        }

        System.out.printf("\n\nOs corvos acertaram %d dos %d ataques dados!\n", contador, attackSpeed); Utilidades.esperar(1500);
    }

    @Override
    public void mover(Personagem alvo){
        if (Math.random() < 0.5)
            pos += moveSpeed;
        else
            pos -= moveSpeed;
        
        System.out.printf("%s, o CORVO REI, agora esta a %d metros do nosso guerreiro!\n", nome, Utilidades.calcularDistancia(pos, alvo.pos)); Utilidades.esperar(1500);
    }

    // Habilidade especial: Chuva de Corvos (com cegueira)
    public void chuvaDeCorvos(Personagem alvo){
        System.out.println("\nO CORVO REI solta um grito ensurdecedor e uma CHUVA DE CORVOS ataca o herói!"); Utilidades.esperar(2000);
        int ataques = attackSpeed + 2;
        int acertos = 0;

        for (int i = 0; i < ataques; i++){
            if (Math.random() > alvo.dodgeChance){
                acertos++;
                alvo.receberDano(forca * 0.8);
            }
        }

        System.out.printf("Os corvos acertaram %d de %d ataques!\n", acertos, ataques); Utilidades.esperar(1500);

        alvo.aplicarStatus("Cegueira", 1);
        System.out.println("O herói está CEGADO e terá dificuldade para acertar ataques no próximo turno!"); Utilidades.esperar(1500);
    }

    // Habilidade especial: Voo Rasante (com aumento de esquiva)
    public void vooRasante(Personagem alvo){
        System.out.println("\nO CORVO REI mergulha em um VOO RASANTE contra o herói!"); Utilidades.esperar(2000);

        if (pos < alvo.pos)
            pos = alvo.pos - 1;
        else
            pos = alvo.pos + 1;

        if (Math.random() > alvo.dodgeChance){
            alvo.receberDano(forca * 1.2);
            System.out.println("O ataque acerta em cheio!"); Utilidades.esperar(1500);
        } else {
            System.out.println("O herói esquiva do voo rasante!"); Utilidades.esperar(1500);
        }

        this.aplicarStatus("Esquiva Aumentada", 1);
        System.out.println("O CORVO REI está mais difícil de acertar após o voo rasante!"); Utilidades.esperar(1500);
    }
}
