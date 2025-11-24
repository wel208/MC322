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

    @XmlElements({@XmlElement(name = "rankVitorias", type=JogadorAuxiliar.class)})
    private List<JogadorAuxiliar> rankVitorias = new ArrayList<>();
    @XmlElements({@XmlElement(name = "rankPontuacao", type=JogadorAuxiliar.class)})
    private List<JogadorAuxiliar> rankPontuacao = new ArrayList<>();
    @XmlElements({@XmlElement(name = "jogadores", type=JogadorAuxiliar.class)})
    private List<JogadorAuxiliar> jogadores = new ArrayList<>();

    public void atualizarRank(JogadorAuxiliar jogador, boolean venceu){
        boolean jogadorExistente = false;

        for (JogadorAuxiliar j : jogadores){
            if (jogador.getNome().equals(j.getNome())){
                jogadorExistente = true;
                if (venceu) j.aumentarVitorias();
                if (jogador.getPontuacao() > j.getPontuacao()){
                    j.setPontuacao(jogador.getPontuacao());
                    j.setNLinhas(jogador.getNLinhas());
                    j.setNivel(jogador.getNivel());
                }
                break;
            }
        }

        if (!jogadorExistente){
            jogadores.add(jogador);
            if (venceu) jogador.aumentarVitorias();
        }

        jogadores.sort(Comparator.comparing(JogadorAuxiliar::getPontuacao).reversed());
        
        rankPontuacao = new ArrayList<>(jogadores);
        rankVitorias = new ArrayList<>(jogadores);

        rankVitorias.sort(Comparator.comparing(JogadorAuxiliar::getVitorias).reversed());

        while (rankPontuacao.size() > 10)
            rankPontuacao.removeLast();

        while (rankVitorias.size() > 10) 
            rankVitorias.removeLast();

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

            setJogadores(pontuacaoCarregada.jogadores);
            setRankPontuacao(pontuacaoCarregada.rankPontuacao);
            setRankVitorias(pontuacaoCarregada.rankVitorias);
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
        }
    }

    public List<JogadorAuxiliar> getRankPontuacao() { return rankPontuacao; }
    public List<JogadorAuxiliar> getRankVitorias() { return rankVitorias; }

    private void setRankPontuacao(List<JogadorAuxiliar> rank) { rankPontuacao = rank; }
    private void setRankVitorias(List<JogadorAuxiliar> rank) { rankVitorias = rank; }
    private void setJogadores(List<JogadorAuxiliar> jogadores) { this.jogadores = jogadores; }
}
