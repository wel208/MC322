package projetofinal.game;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import projetofinal.Blocos.*;

public class GeradorDeBlocos{
    private int rotacoes = 0;
    private List<Bloco> bag = new ArrayList<>();

    public GeradorDeBlocos() {
        recarregarBag();
    }

    public void recarregarBag() {
        bag.clear();
        bag.add(new I());
        bag.add(new O());
        bag.add(new T());
        bag.add(new S());
        bag.add(new Z());
        bag.add(new J());
        bag.add(new L());
        Collections.shuffle(bag);
    }

    public Bloco proximoBloco() {
        if (bag.isEmpty()){
            rotacoes++;
            recarregarBag();
        }

        return bag.remove(0);
    }

    public int getRotacoes() {return rotacoes;}
}