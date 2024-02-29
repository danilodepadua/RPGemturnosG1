package com.daniel.Model.Itens.Armas;

import com.daniel.Model.Itens.Arma;
import com.daniel.Model.Magias.TiposElementais;

public class EspadaGeloAperfeicoada extends Arma {
    public EspadaGeloAperfeicoada() {
        super("/com.daniel.Images/Armas/w_sword_roman_diamond.png", "Espada glacial", 1, 1500, "Concede aumento de 60 no ataque mágico", 0, 60, TiposElementais.Gelo);
    }

    public EspadaGeloAperfeicoada(int quant) {
        super("/com.daniel.Images/Armas/w_sword_roman_diamond.png", "Espada glacial", quant, 1500, "Concede aumento de 60 no ataque mágico", 0, 60, TiposElementais.Gelo);
    }
}