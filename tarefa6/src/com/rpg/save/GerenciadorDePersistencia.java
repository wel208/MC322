package com.rpg.save;
import com.rpg.game.*;
import com.rpg.util.Utilidades;
import javax.xml.bind.*;
import java.io.File;

public class GerenciadorDePersistencia {

    //Métodos
    public static void salvarBatalha(Batalha b, String nomeBatalha){
        try{
            // cria a pasta "saves" se não existir
            File dir = new File("saves");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            JAXBContext contexto = JAXBContext.newInstance(Batalha.class);
            Marshaller marshaller = contexto.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(b, new File("saves/" + nomeBatalha + ".xml"));

            System.out.println("\nJogo salvo com sucesso!"); Utilidades.esperar();
        }
        catch (JAXBException e){
            e.printStackTrace();
            System.out.println("Erro ao salvar o jogo.");
        }
    }

    public static Batalha carregarBatalha(String nomeBatalha){
        try{
            JAXBContext contexto = JAXBContext.newInstance(Batalha.class);
            Unmarshaller unmarshaller = contexto.createUnmarshaller();

            Batalha batalhaCarregada = (Batalha) unmarshaller.unmarshal(new File("saves/" + nomeBatalha + ".xml"));

            System.out.println("\nJogo carregado com sucesso!"); Utilidades.esperar();
            return batalhaCarregada;
        }
        catch (JAXBException e){
            e.printStackTrace();
            return null;
        }
    }
}
