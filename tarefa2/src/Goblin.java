public class Goblin extends Monstro{
    
    public Goblin(String nome, int nivel, int pos, Arma arma){
        super(nome, nivel, pos, arma);
        this.pontosDeVidaMax = 40 + (nivel - 1) * 8;
        this.pontosDeVida = this.pontosDeVidaMax;
        this.protecao = 0.2 + (nivel - 1) * 0.02;
        this.forca = 25 + (nivel - 1) * 2;
        this.moveSpeed = 7;
        this.xpConcedido = 10 + (nivel * 8);
        this.sorte = 0.25 + (nivel * 0.01);
        this.dodgeChance = 0.1 + (nivel * 0.01);
    }

    @Override
    public void ambientarMonstro(Heroi heroi){
        System.out.println("\nDepois de um bom tempo em seu percurso, " + heroi.nome + " comeca a ouvir alguns grunidos vindos de dentro da neblina"); Utilidades.esperar(200);
        System.out.println("Ele logo entende o que aquilo significa: GOBLIN!"); Utilidades.esperar(200);
        System.out.println("Uma verde criatura humanoide estranha. Um monstro baixo e rapido."); Utilidades.esperar(200);
        System.out.println(nome + " entao aparece de tras de uma arvore, um goblin muito conhecido pelo reino de Lumenholt por suas frequentes aparicoes."); Utilidades.esperar(200);

        System.out.print("\nNao muito forte, mas bem rapido, ataca apenas corpo a corpo"); Utilidades.esperar(200);
        System.out.printf("\n%s, O GOBLIN, no nivel %d de dificuldade, possui:", nome, nivelDificuldade); Utilidades.esperar(200);
        System.out.printf("\n%d PONTOS DE VIDA;", pontosDeVida); Utilidades.esperar(200);
        System.out.printf("\n%.0f PONTOS DE FORCA;", forca); Utilidades.esperar(200);
        System.out.printf("\nE, ao morrer, concedera %d PONTOS DE EXPERIENCIA ao heroi\n", xpConcedido); Utilidades.esperar(200);

        System.out.printf("\n%s, entao, se prepara para derrotar %s!\n", heroi.nome, nome); Utilidades.esperar(200);
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
            System.out.println("ATACA-LO!\n"); Utilidades.esperar(200);
            atacar(alvo);
        }
        else{
            System.out.println("CORRER NA DIRECAO DELE!\n"); Utilidades.esperar(200);
            mover(alvo);
        }
    }

    public void atacar(Personagem alvo){
        int contador = 0;
        for (int i = 0; i < arma.attackSpeed; i++){
            if (Math.random() > alvo.dodgeChance){
                contador++;
                if (Math.random() < criticalChance){
                    alvo.receberDano(forca * arma.dano * 1.3);
                    System.out.println("NAO! O goblin ACERTOU um ATAQUE CRITICO no nosso heroi!"); Utilidades.esperar(200);
                }
                else{
                    alvo.receberDano(forca * arma.dano);
                    System.out.println("NAO! O goblin ACERTOU um golpe no heroi!"); Utilidades.esperar(200);
                }
            }
            else
                System.out.println("UFA! O heroi ESQUIVOU do ataque do goblin!"); Utilidades.esperar(200);
        }
        System.out.printf("\nO goblin acertou %d dos %d ataques dados!\n", contador, arma.attackSpeed); Utilidades.esperar(200);
    }

    public void mover(Personagem alvo){
        boolean chegou = false;

        if (pos < alvo.pos)
            for (int i = 0; i < moveSpeed; i ++){
                pos++;
                if (Utilidades.calcularDistancia(pos, alvo.pos) == arma.attackRange){
                    System.out.println("O GOBLIN ALCANCOU O HEROI E IRA ATACAR!\n"); Utilidades.esperar(200);
                    chegou = true;
                    atacar(alvo);
                    break;
                }
            }
        else
            for (int i = 0; i < moveSpeed; i ++){
                pos--;
                if (Utilidades.calcularDistancia(pos, alvo.pos) == arma.attackRange){
                    System.out.println("O GOBLIN ALCANCOU O HEROI E IRA ATACAR!\n"); Utilidades.esperar(200);
                    chegou = true;
                    atacar(alvo);
                    break;
                }
            }
        
        if (!chegou){
            Utilidades.esperar(200);
            System.out.println("O goblin ainda nao alcancou o nosso heroi. ");
            System.out.printf("Ele esta a %d metros de distância.", Utilidades.calcularDistancia(pos, alvo.pos)); Utilidades.esperar(200);
        }
    }

    // Habilidade especial: Ataque Furtivo (com sangramento)
    public void ataqueFurtivo(Personagem alvo){
        System.out.println("\nO goblin se move rapidamente pelas sombras e tenta um ATAQUE FURTIVO!"); Utilidades.esperar(200);
        if (Math.random() > (alvo.dodgeChance) * 0.8){
            double dano = forca * 1.5;
            alvo.receberDano(dano);
            System.out.println("O ataque pega o herói de surpresa!"); Utilidades.esperar(200);

            // Aplica sangramento por 2 turnos
            alvo.aplicarStatus("Sangramento", 2);
            System.out.println("O herói está SANGRANDO e perderá vida a cada turno!"); Utilidades.esperar(200);
        } else {
            System.out.println("O herói percebe o movimento e evita o ataque furtivo!"); Utilidades.esperar(200);
        }
    }

    // Habilidade especial: Golpe Atordoante
    public void golpeAtordoante(Personagem alvo){
        System.out.println("\nO goblin mira um golpe rápido para atordoar o herói!"); Utilidades.esperar(200);
        if (Math.random() > alvo.dodgeChance){
            alvo.receberDano(forca * arma.dano * 1.1);
            alvo.aplicarStatus("Atordoado", 1);
            System.out.println("O herói foi ATORDOADO e perderá o próximo turno!"); Utilidades.esperar(200);
        } else {
            System.out.println("O herói bloqueia o golpe atordoante!"); Utilidades.esperar(200);
        }
    }
}
