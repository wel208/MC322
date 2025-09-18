public enum TipoCenario {
    //Constantes
    CASTELO ("VAMOS! O castelo esta sendo invadido!"),
    ACAMPAMENTO ("Tenha cuidado. Ha monstros la fora."),
    VILAREJO ("Um vilarejo abandonado. Monstros tomaram conta deste lugar.");

    //Atributos
    private final String descricao;

    //Construtor
    TipoCenario(String descricao){
        this.descricao = descricao;
    }

    //Métodos
    public void descreverCenario(){
        System.out.println(descricao);
    }

    public String getDescricao(){
        return descricao;
    }
}