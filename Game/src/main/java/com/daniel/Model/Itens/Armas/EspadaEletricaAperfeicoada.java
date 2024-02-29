package com.daniel.Model.Itens.Armas;

import com.daniel.Model.Itens.Arma;
import com.daniel.Model.Magias.TiposElementais;

public class EspadaEletricaAperfeicoada extends Arma {
    public EspadaEletricaAperfeicoada() {
        super("/com.daniel.Images/Armas/w_sword_roman_holly.png", "Espada super elétrica", 1, 1000, "Concede aumento de 50 no ataque mágico", 0, 50, TiposElementais.Eletrico);
    }

    public EspadaEletricaAperfeicoada(int quant) {
        super("/com.daniel.Images/Armas/w_sword_roman_holly.png", "Espada super elétrica", quant, 1000, "Concede aumento de 50 no ataque mágico", 0, 50, TiposElementais.Eletrico);
    }
}