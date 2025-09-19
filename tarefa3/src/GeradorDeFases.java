import java.util.List;
public interface GeradorDeFases {
    List<Fase> gerar(TipoCenario cenario, int nivel, int quantidadeDeFases);
}
