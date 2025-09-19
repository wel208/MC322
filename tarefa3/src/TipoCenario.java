public enum TipoCenario {
    //Constantes
    CASTELO ("VAMOS! O castelo esta sendo invadido!"),
    VILAREJO ("Caramba, um vilarejo abandonado... Monstros tomaram conta deste lugar."),
    ACAMPAMENTO ("Estamos num acampamento no meio da floresta. Tenha cuidado, ha monstros la fora.");

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