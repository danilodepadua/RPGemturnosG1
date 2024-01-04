package com.daniel.PrimeiraCamada.Itens.Pocoes;

import com.daniel.PrimeiraCamada.Entidades.Player;
import com.daniel.PrimeiraCamada.Exceptions.PlayerInexistenteException;
import com.daniel.PrimeiraCamada.Interfaces.IConsumable;
import com.daniel.PrimeiraCamada.Itens.Item;
import com.daniel.PrimeiraCamada.Personagem;
import com.daniel.PrimeiraCamada.PersonagemLuta;

public class PocaoCura extends Item implements IConsumable {

    public PocaoCura() {
        this.imagem = "/com.daniel.Images/Itens/pt1.png";
        this.nome = "Poção de cura";
        this.descricao = "Poção que concede recuperação de vida";
        this.preço = 50; // Preço inicial
    }

    @Override
    public void Consumir(PersonagemLuta p){
        p.RecuperarVida(100);
    }
    @Override
    public void Consumir() throws PlayerInexistenteException {
        Player.getPlayer().RecuperarVida(100);
    }
}
