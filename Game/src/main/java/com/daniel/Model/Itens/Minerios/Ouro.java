package com.daniel.Model.Itens.Minerios;

import com.daniel.Model.Itens.Minerio;

public class Ouro extends Minerio {
    public Ouro(){
        super("/com.daniel.Images/Itens/Minerios/Ouro.png", "Minério de Ouro", 1, 80, "Minério Bruto de Ouro");
    }

    public Ouro(int quant) {
        super("/com.daniel.Images/Itens/Minerios/Ouro.png", "Minério de Ouro", quant, 80, "Minério Bruto de Ouro");
    }
}