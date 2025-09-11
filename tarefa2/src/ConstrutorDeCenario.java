import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class ConstrutorDeCenario {
    
    //Métodos
    public static List<Fase> gerarFases(int nFases){
        List<Fase> fases = new ArrayList<>();
        String[] ambientes = {"Castelo", "Vilarejo Destruído", "Acampamento na Floresta"};
        Random r = new Random();
        
        for (int i = 1; i <= nFases; i++){
            Fase f = new Fase(ambientes[r.nextInt(ambientes.length)], i);
            String ambiente = ambientes[r.nextInt(ambientes.length)];

            int numMonstros = r.nextInt(2, 5); //Cada fase terá entre 2 e 4 monstros

            for (int j = 0; j < numMonstros; j++) {
                Monstro monstro = Utilidades.criarMonstro(ambiente, i);
                f.adicionarMonstro(monstro);
            }
            
            fases.add(f);
        }

        return fases;
    }
}
