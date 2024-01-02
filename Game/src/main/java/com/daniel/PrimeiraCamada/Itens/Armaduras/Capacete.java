package com.daniel.PrimeiraCamada.Itens.Armaduras;

import com.daniel.PrimeiraCamada.Entidades.Player;
import com.daniel.PrimeiraCamada.Exceptions.PlayerInexistenteException;
import com.daniel.PrimeiraCamada.Interfaces.IEquipable;
import com.daniel.PrimeiraCamada.Itens.Armadura;

public class Capacete extends Armadura implements IEquipable {
    protected boolean equiparCapacete;
    public Capacete(){
        this.aumentoDefesaFisica = 0;
        this.aumentoDefesaMagica = 0;
    }
    @Override
    public void equipar() throws PlayerInexistenteException {
        Player.getPlayer().desequiparCapacete();
        Player.getPlayer().equiparCapacete(this);
        this.equiparCapacete = true;
    }

    @Override
    public void desequipar() throws PlayerInexistenteException {
        Player.getPlayer().desequiparCapacete();
        System.out.println("Esse capacete não está equipado.");
    }

}