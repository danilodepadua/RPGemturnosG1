package com.daniel.Model.Dados.CidadeRepositorio.Cidades;

import com.daniel.Model.Dados.CidadeRepositorio.Cidade;
import com.daniel.Model.Exceptions.PlayerInexistenteException;
import com.daniel.Model.Dados.Entidades.Inimigos.Inimigo;
import com.daniel.Model.Dados.Entidades.Inimigos.Viloes.*;
import com.daniel.Model.Dados.Itens.Armaduras.Capacetes.CapaceteMalha;
import com.daniel.Model.Dados.Itens.Armaduras.Peitorais.PeitoralFerro;
import com.daniel.Model.Dados.Itens.Armas.*;
import com.daniel.Model.Dados.Itens.Pocoes.Cerveja;
import com.daniel.Model.Dados.Quests.*;

import java.util.ArrayList;

public class BatalhaDePedraveira extends Cidade {
    public BatalhaDePedraveira() throws PlayerInexistenteException{
        super("PEDRAVEIRA", "/com.daniel.Images/Fundos/BatalhaDePedraveira.jpg", "/com.daniel.Images/Fundos/FundoBatalhaBosque.jpg");
        this.musicPath = "/com.daniel.audios/msc_pedraveira.wav";

        this.inimigos = new Inimigo[]{new InimigoSlimeDeLuzNv3(), new InimigoSlimeDeRaioNv3(), new InimigoSlimeDeTerraNv3(), new InimigoSlimeDeVentoNv3(), new InimigoCobraGigante(), new InimigoCorvoGigante()};

        this.itens.add(new Katana());
        this.itens.add(new KatanaGelo());
        this.itens.add(new KatanaFogo());
        this.itens.add(new PeitoralFerro());
        this.itens.add(new EspadaEletricaAperfeicoada());
        this.itens.add(new EspadaAperfeiçoada());
        this.itens.add(new CapaceteMalha());
        this.itens.add(new Cerveja());

        this.quests.add(new QuestSlimeDeLuzNv3());
        this.quests.add(new QuestSlimeDeVentoNv3());
        this.quests.add(new QuestSlimeDeRaioNv3());
        this.quests.add(new QuestSlimeDeTerraNv3());
        this.quests.add(new QuestCobraGigante());
        this.quests.add(new QuestCorvoGigante());


        this.dialogoAtivo = true;
        this.dialogoCutscene = "Pedraveira, a cidade eternamente marcada pelas cicatrizes de uma batalha antiga entre humanos e demônios, " +
                "onde as ruas silenciosas contam histórias de coragem e resistência entre escombros e sombras.";
        ajustarBotoes();


    }

    @Override
    public void ajustarBotoes() throws PlayerInexistenteException {
        this.botoes = new ArrayList<>();
        this.botoes.add(criarBotaoViajar());
        this.botoes.add(criarBotaoCacar());
        this.botoes.add(criarBotaoLoja());
        this.botoes.add(criarBotaoQuest());
        this.botoes.add(criarBotaoTaverna());
        this.botoes.add(criarBotaoSalvar());

    }
}
