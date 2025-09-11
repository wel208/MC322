import java.util.ArrayList;
import java.util.List;

public class Fase {
    
    //Atributos
    private final List<Monstro> monstros = new ArrayList<>();      //Array que guarda os monstros presentes na fase
    private String ambiente;
    private int nivel;

    //Construtor
    public Fase(String ambiente, int nivel){
        this.ambiente = ambiente;
        this.nivel = nivel;
    }

    //Getters
    public int getNivel(){  
        return nivel;
    }
    public String getAmbiente(){
        return ambiente;
    }
    public List<Monstro> getMonstros(){
        return monstros;
    }

    //Método que adiciona um monstro ao array de monstros da fase
    protected void adicionarMonstro(Monstro monstro){
        monstros.add(monstro);
    }

}
