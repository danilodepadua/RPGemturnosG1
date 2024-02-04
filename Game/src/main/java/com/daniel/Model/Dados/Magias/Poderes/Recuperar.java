package com.daniel.Model.Dados.Magias.Poderes;

import com.daniel.Model.Interfaces.IEffects;
import com.daniel.Model.BatalhaDeTurnos.PersonagemLuta;
import com.daniel.Model.Dados.Magias.Animation.CleanceAnimation;
import com.daniel.Model.Dados.Magias.Magia;
import com.daniel.Model.Dados.Magias.TiposElementais;

public class Recuperar extends Magia implements IEffects {
    public Recuperar(){
        super("Restaurata", 30, 0, TiposElementais.NaoElemental, new CleanceAnimation(), false);
    }

    @Override
    public void aplicarEfeito(PersonagemLuta alvo) {
        System.out.println("Alvo: " + alvo.getNome());
        this.mensagem.add(alvo.descegar());
        this.mensagem.add(alvo.dessilenciar());
        this.mensagem.add(alvo.Desenvenenar());
    }
}