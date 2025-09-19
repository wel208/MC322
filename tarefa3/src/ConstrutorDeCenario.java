import java.util.ArrayList;
import java.util.List;

public class ConstrutorDeCenario implements GeradorDeFases {
    
    //Métodos
    public List<Fase> gerar(TipoCenario cenario, int nivel, int n){
        List<Fase> fases = new ArrayList<>();

        for (int i = 0; i < n; i++){
            fases.add(new Fases(Utilidades.criarListaDeMonstro(cenario, n, nivel), cenario));
        }

        return fases;
    }
}
