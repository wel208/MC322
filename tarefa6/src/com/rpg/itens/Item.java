package com.rpg.itens;
import javax.xml.bind.annotation.*;
@XmlSeeAlso({Arma.class})
public interface Item {
    String getNome();
}
