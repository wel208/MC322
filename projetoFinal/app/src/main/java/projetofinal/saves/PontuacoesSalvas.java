package projetofinal.saves;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

@XmlRootElement(name = "pontuacoes")
@XmlAccessorType(XmlAccessType.FIELD)
public class PontuacoesSalvas {

    @XmlElements({@XmlElement(name = "rank", type=JogadorAuxiliar.class)})
    private List<JogadorAuxiliar> rank = new ArrayList<>();

    public void atualizarRank(JogadorAuxiliar jogador){
        JogadorAuxiliar existente = null;

        if (rank.size() == 0){
            rank.add(jogador);
            salvarRank();
            return;
        }

        // Verifica se o nome do jogador já está no ranking
        for (JogadorAuxiliar i : rank){
            if (jogador.getNome().equals(i.getNome())){
                existente = i;
                break;
            }
        }

        // Se estiver, atualiza a lista e remove os dados antigos
        if (existente != null){
            if (jogador.getPontuacao() > existente.getPontuacao()){
                rank.add(jogador);
                rank.remove(existente);
            }
        }

        // Se o jogador não está na lista, adiciona nela
        else
            rank.add(jogador);

        // Organiza o ranking com todos os jogadores
        rank.sort(Comparator.comparing(JogadorAuxiliar::getPontuacao).reversed());

        // Caso haja mais de 10 pessoas no ranking, remove a última
        if (rank.size() > 10)
            rank.removeLast();

        salvarRank();
    }

    public void carregarRank(){

        File arquivo = new File("saves/pontuacoes.xml");

        if (!arquivo.exists()) {
            return; 
        }

        try{
            JAXBContext contexto = JAXBContext.newInstance(PontuacoesSalvas.class);
            Unmarshaller unmarshaller = contexto.createUnmarshaller();

            PontuacoesSalvas pontuacaoCarregada = (PontuacoesSalvas) unmarshaller.unmarshal(new File("saves/pontuacoes.xml"));

            setRank(pontuacaoCarregada.rank);
        } 
        catch (JAXBException e) {
            e.printStackTrace();
            return;
        }
    }

    private void salvarRank() {
        try{
            // cria a pasta "saves" se não existir
            File dir = new File("saves");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            JAXBContext contexto = JAXBContext.newInstance(PontuacoesSalvas.class);
            Marshaller marshaller = contexto.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(this, new File("saves/pontuacoes.xml"));
        }
        catch (JAXBException e){
            e.printStackTrace();
            System.out.println("Erro ao salvar as pontuacoes.");
        }
    }

    public List<JogadorAuxiliar> getRank() { return rank; }

    private void setRank(List<JogadorAuxiliar> rank){ this.rank = rank; }
}
