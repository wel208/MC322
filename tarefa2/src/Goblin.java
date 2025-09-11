public class Goblin extends Monstro{
    
    public Goblin(String nome, int nivel, int pos, Arma arma){
        super(nome, nivel, pos, arma);
    }

    @Override
    public void ambientarMonstro(Heroi heroi){
        System.out.println("\nDepois de um bom tempo em seu percurso, " + heroi.nome + " comeca a ouvir alguns grunidos vindos de dentro da neblina"); Utilidades.esperar(2000);
        System.out.println("Ele logo entende o que aquilo significa: GOBLIN!"); Utilidades.esperar(2000);
        System.out.println("Uma verde criatura humanoide estranha. Um monstro baixo e rapido."); Utilidades.esperar(2000);
        System.out.println(nome + " entao aparece de tras de uma arvore, um goblin muito conhecido pelo reino de Lumenholt por suas frequentes aparicoes."); Utilidades.esperar(2000);

        System.out.print("\nNao muito forte, mas bem rapido, ataca apenas corpo a corpo"); Utilidades.esperar(2000);
        System.out.printf("\n%s, O GOBLIN, no nivel %d de dificuldade, possui:", nome, nivelDificuldade); Utilidades.esperar(2000);
        System.out.printf("\n%d PONTOS DE VIDA;", pontosDeVida); Utilidades.esperar(2000);
        System.out.printf("\n%.0f PONTOS DE FORCA;", forca); Utilidades.esperar(2000);
        System.out.printf("\nE, ao morrer, concedera %d PONTOS DE EXPERIENCIA ao heroi\n", xpConcedido); Utilidades.esperar(2000);

        System.out.printf("\n%s, entao, se prepara para derrotar %s!\n", heroi.nome, nome); Utilidades.esperar(1500);
    }

    @Override
    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);
        double chance = Math.random();

        System.out.printf("\nO GOBLIN esta a %d metros do nosso heroi e ira ", distancia);

        // 10% de chance de usar Ataque Furtivo
        if (chance < 0.1 && distancia <= arma.attackRange) {
            ataqueFurtivo(alvo);
            return;
        }
        // 10% de chance de usar Golpe Atordoante
        else if (chance < 0.2 && distancia <= arma.attackRange) {
            golpeAtordoante(alvo);
            return;
        }

        if (distancia <= arma.attackRange){
            System.out.println("ATACA-LO!\n"); Utilidades.esperar(1500);
            atacar(alvo);
        }
        else{
            System.out.println("CORRER NA DIRECAO DELE!\n"); Utilidades.esperar(1500);
            mover(alvo);
        }
    }

    public void atacar(Personagem alvo){
        int contador = 0;
        for (int i = 0; i < arma.attackSpeed; i++){
            if (Math.random() > alvo.sorte){
                contador++;
                if (Math.random() < criticalChance){
                    alvo.receberDano(forca * 1.3);
                    System.out.println("NAO! O goblin ACERTOU um ATAQUE CRITICO no nosso heroi!"); Utilidades.esperar(1500);
                }
                else{
                    alvo.receberDano(forca);
                    System.out.println("NAO! O goblin ACERTOU um golpe no heroi!"); Utilidades.esperar(1500);
                }
            }
            else
                System.out.println("UFA! O heroi ESQUIVOU do ataque do goblin!"); Utilidades.esperar(1500);
        }
        System.out.printf("\nO goblin acertou %d dos %d ataques dados!\n", contador, arma.attackSpeed); Utilidades.esperar(1500);
    }

    public void mover(Personagem alvo){
        boolean chegou = false;

        if (pos < alvo.pos)
            for (int i = 0; i < moveSpeed; i ++){
                pos++;
                if (Utilidades.calcularDistancia(pos, alvo.pos) == arma.attackRange){
                    System.out.println("O GOBLIN ALCANCOU O HEROI E IRA ATACAR!\n"); Utilidades.esperar(1500);
                    chegou = true;
                    atacar(alvo);
                    break;
                }
            }
        else
            for (int i = 0; i < moveSpeed; i ++){
                pos--;
                if (Utilidades.calcularDistancia(pos, alvo.pos) == arma.attackRange){
                    System.out.println("O GOBLIN ALCANCOU O HEROI E IRA ATACAR!\n"); Utilidades.esperar(1500);
                    chegou = true;
                    atacar(alvo);
                    break;
                }
            }
        
        if (!chegou){
            Utilidades.esperar(1500);
            System.out.println("O goblin ainda nao alcancou o nosso heroi. ");
            System.out.printf("Ele esta a %d metros de distância.", Utilidades.calcularDistancia(pos, alvo.pos)); Utilidades.esperar(1500);
        }
    }

    // Habilidade especial: Ataque Furtivo (com sangramento)
    public void ataqueFurtivo(Personagem alvo){
        System.out.println("\nO goblin se move rapidamente pelas sombras e tenta um ATAQUE FURTIVO!"); Utilidades.esperar(1500);
        if (Math.random() > alvo.sorte * 0.8){
            double dano = forca * 1.5;
            alvo.receberDano(dano);
            System.out.println("O ataque pega o herói de surpresa!"); Utilidades.esperar(1500);

            // Aplica sangramento por 2 turnos
            alvo.aplicarStatus("Sangramento", 2);
            System.out.println("O herói está SANGRANDO e perderá vida a cada turno!"); Utilidades.esperar(1500);
        } else {
            System.out.println("O herói percebe o movimento e evita o ataque furtivo!"); Utilidades.esperar(1500);
        }
    }

    // Habilidade especial: Golpe Atordoante
    public void golpeAtordoante(Personagem alvo){
        System.out.println("\nO goblin mira um golpe rápido para atordoar o herói!"); Utilidades.esperar(1500);
        if (Math.random() > alvo.sorte){
            alvo.receberDano(forca * 1.1);
            alvo.aplicarStatus("Atordoado", 1);
            System.out.println("O herói foi ATORDOADO e perderá o próximo turno!"); Utilidades.esperar(1500);
        } else {
            System.out.println("O herói bloqueia o golpe atordoante!"); Utilidades.esperar(1500);
        }
    }
}
