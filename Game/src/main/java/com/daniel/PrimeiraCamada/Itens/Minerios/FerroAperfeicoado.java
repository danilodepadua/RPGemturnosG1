package com.daniel.PrimeiraCamada.Itens.Minerios;

import com.daniel.PrimeiraCamada.Itens.Item;

public class FerroAperfeicoado extends Item {
    public FerroAperfeicoado(){
        super("/com.daniel.Images/Itens/Minerios/FerroAperfeicoado.png", "Ferro aperfeiçoado", 1, 50, "Ferro aperfeiçoado e brilhante");
    }

    public FerroAperfeicoado(int quant) {
        super("/com.daniel.Images/Itens/Minerios/FerroAperfeicoado.png", "Ferro aperfeiçoado", quant, 50, "Ferro aperfeiçoado e brilhante");
    }
}