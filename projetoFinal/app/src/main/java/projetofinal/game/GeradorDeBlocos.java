package projetofinal.game;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import projetofinal.Blocos.*;

public class GeradorDeBlocos{
    private int rotacoes = 0;
    private List<Bloco> bag1 = new ArrayList<>();
    private List<Bloco> bag2 = new ArrayList<>();
    private List<Bloco> next = new ArrayList<>();

    public GeradorDeBlocos() {
        recarregarBag(bag1);
        recarregarBag(bag2);
        for(int i = 0; i<4; i++)
            next.add(proximoBloco_next());
    }

    public void recarregarBag(List<Bloco> bag) {
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

    public Bloco proximoBloco_next() {
        if(bag1.size() <= bag2.size()){
            if (bag1.isEmpty()){
                rotacoes++;
                recarregarBag(bag1);
                return bag2.remove(0);
            }
            else
                return bag1.remove(0);
        }else{
            if (bag2.isEmpty()){
                rotacoes++;
                recarregarBag(bag2);
                return bag1.remove(0);
            }
            else
                return bag2.remove(0);
        } 
    }

    public Bloco proximoBloco(){
        next.add(proximoBloco_next());
        return next.remove(0);
    }

    public List<Bloco> getNextList() {return next; }
    public int getRotacoes() { return rotacoes; }
}