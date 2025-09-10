public class ConstrutorDeCenario {
    
    //Métodos
    public static FaseCastelo[] gerarFases(int n){
        FaseCastelo[] fases = new FaseCastelo[n];

        for(int i = 0; i < n; i++){
            fases[i] = new FaseCastelo();
        }

        return fases;
    }
}
