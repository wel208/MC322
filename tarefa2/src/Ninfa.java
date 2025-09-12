/*
 * Mulher que se transforma num enorme e forte monstro
 * Muitos pontos de vida e boa proteção
 * Consegue atacar a uma distância <= 3 metros
 * Não é rápida, pouca chance de esquivar de um ataque
 */

public class Ninfa extends Monstro{
    
    //Construtor
    public Ninfa(String nome, int nivel, int pos, Arma arma){
        super(nome, nivel, pos, arma);
        this.pontosDeVidaMax = 100 + (nivel - 1) * 20;
        this.pontosDeVida = this.pontosDeVidaMax;
        this.protecao = 0.4 + (nivel - 1) * 0.01;
        this.forca = 20 + (nivel - 1) * 5;
        this.moveSpeed = 3;
        this.xpConcedido = 30 + (nivel * 15);
        this.sorte = 0.3 + (nivel * 0.02);
    }

    @Override
    public void ambientarMonstro(Heroi heroi){
        System.out.println("\nAo caminhar, " + heroi.nome + " comeca a ouvir um choro distante."); Utilidades.esperar(200);
        System.out.println("Seria a princesa?"); Utilidades.esperar(200);
        System.out.println("Ele vai correndo ate a origem do choro!"); Utilidades.esperar(200);
        System.out.println("Era, sim, uma mulher! Mas nao era a princesa... Ele segue ate a donzela para entender o que esta acontecendo."); Utilidades.esperar(200);
        System.out.println("Ele entao toca em seu ombro e, quando a mulher se vira, ele percebe que nao devia ter baixado tanto a guarda."); Utilidades.esperar(200);
        System.out.println("O seu rosto monstruoso nao escondia, se tratava de uma NINFA DA FLORESTA."); Utilidades.esperar(200);
        System.out.println("Uma criatura sombria que protegia a floresta de Umbraeth atraindo homens e, entao, os matando."); Utilidades.esperar(200);
        System.out.println("Ela faz isso se transformando em uma enorme aberracao."); Utilidades.esperar(200);
        System.out.println("Enquanto ela se transforma, " + heroi.nome + " se afasta."); Utilidades.esperar(200);

        System.out.print("\nA ninfa e um ser muito forte, mas lento, seu ataque pode alcancar ate 3 metros de distancia."); Utilidades.esperar(200);
        System.out.printf("\n%s, A NINFA, no nivel %d de dificuldade, possui:", nome, nivelDificuldade); Utilidades.esperar(200);
        System.out.printf("\n%d PONTOS DE VIDA;", pontosDeVida); Utilidades.esperar(200);
        System.out.printf("\n%.0f PONTOS DE FORCA;", forca); Utilidades.esperar(200);
        System.out.printf("\nE, ao morrer, concedera %d PONTOS DE EXPERIENCIA ao heroi.\n", xpConcedido); Utilidades.esperar(200);

        System.out.printf("\n%s, depois de se afastar do monstro, pensa em como pode derrotar %s.\n", heroi.nome, nome); Utilidades.esperar(200);
    }

    @Override
    public void tomarDecisao(Personagem alvo){
        int distancia = Utilidades.calcularDistancia(pos, alvo.pos);
        double chance = Math.random();

        System.out.printf("\nA NINFA está a %d metros do nosso herói e irá ", distancia);

        // 10% de chance de usar Grito Hipnótico (pode ser usado a qualquer distância)
        if (chance < 0.1) {
            gritoHipnotico(alvo);
            return;
        }
        // 15% de chance de usar Abraço Mortal se estiver no alcance
        else if (chance < 0.25 && distancia <= arma.attackRange) {
            abracoMortal(alvo);
            return;
        }

        if (distancia <= arma.attackRange){
            System.out.print("ATACAR!\n"); Utilidades.esperar(200);
            atacar(alvo);
        }
        else{
            System.out.print("SE APROXIMAR!\n"); Utilidades.esperar(200);
            mover(alvo);
        }
    }
    
    @Override
    public void atacar(Personagem alvo){
        int contador = 0;

        for (int i = 0; i < arma.attackSpeed; i++){
            if (Math.random() > alvo.dodgeChance){
                contador++;
                if (Math.random() < criticalChance){
                    alvo.receberDano(forca * arma.dano * 1.1);
                    System.out.println("\nNAO! A ninfa ACERTOU um ATAQUE CRITICO no nosso heroi!"); Utilidades.esperar(200);
                }
                else{
                    alvo.receberDano(forca * arma.dano);
                    System.out.println("\nNAO! A ninfa ACERTOU um golpe no heroi!"); Utilidades.esperar(200);
                }
            }
            else
                System.out.println("\nUFA! O heroi ESQUIVOU do ataque da ninfa!"); Utilidades.esperar(200);
        }

        System.out.printf("\nA ninfa ACERTOU %d de %d ataque(s) dado(s)!\n", contador, arma.attackSpeed); Utilidades.esperar(200);
    }

    @Override
    public void mover(Personagem alvo){
        boolean chegou = false;

        if (pos < alvo.pos)
            for (int i = 0; i < moveSpeed; i ++){
                pos++;
                if (Utilidades.calcularDistancia(pos, alvo.pos) == arma.attackRange){
                    System.out.println("A NINFA ALCANCOU O HEROI E IRA ATACAR!"); Utilidades.esperar(200);
                    chegou = true;
                    atacar(alvo);
                    break;
                }
            }
        else
            for (int i = 0; i < moveSpeed; i ++){
                pos--;
                if (Utilidades.calcularDistancia(pos, alvo.pos) == arma.attackRange){
                    System.out.println("A NINFA ALCANÇOU O HEROI E IRA ATACAR!"); Utilidades.esperar(200);
                    chegou = true;
                    atacar(alvo);
                    break;
                }
            }
        
        if (!chegou){
            Utilidades.esperar(200);
            System.out.println("\nA NINFA ainda nao alcancou o nosso heroi!");
            System.out.printf("Ela esta a %d metros de %s.\n", Utilidades.calcularDistancia(pos, alvo.pos), alvo.nome); Utilidades.esperar(200);
        }
    }

    // Habilidade especial: Abraço Mortal
    public void abracoMortal(Personagem alvo) {
        System.out.println("\nA Ninfa abre seus braços monstruosos e tenta agarrar o herói em um ABRAÇO MORTAL!");
        Utilidades.esperar(200);

        if (Utilidades.calcularDistancia(pos, alvo.pos) <= arma.attackRange) {
            if (Math.random() > alvo.dodgeChance * 0.8) {
                double dano = forca * arma.dano * 1.8;
                alvo.receberDano(dano);
                System.out.println("A Ninfa esmaga o herói com força brutal!");
                Utilidades.esperar(200);

                alvo.aplicarStatus("Atordoado", 1);
                System.out.println("O herói está ATORDOADO e perderá o próximo turno!");
                Utilidades.esperar(200);
            } else {
                System.out.println("O herói consegue escapar do abraço mortal!");
                Utilidades.esperar(200);
            }
        } else {
            System.out.println("A Ninfa está longe demais para usar o Abraço Mortal!");
            Utilidades.esperar(200);
        }
    }

    // Habilidade especial: Grito Hipnótico
    public void gritoHipnotico(Personagem alvo) {
        System.out.println("\nA Ninfa solta um GRITO HIPNÓTICO que ecoa pela floresta!");
        Utilidades.esperar(200);

        // Chance de acertar o efeito
        if (Math.random() < sorte) {
            // 50% de chance de aplicar Cegueira, 50% de aplicar Defesa Reduzida
            if (Math.random() < 0.5) {
                alvo.aplicarStatus("Cegueira", 2);
                System.out.println("O herói está CEGADO e terá dificuldade para acertar ataques!");
            } else {
                alvo.aplicarStatus("Defesa Reduzida", 2);
                System.out.println("A defesa do herói foi reduzida pelo grito hipnótico!");
            }
            Utilidades.esperar(200);
        } else {
            System.out.println("O herói resiste aos efeitos do grito hipnótico!");
            Utilidades.esperar(200);
        }
    }
}
