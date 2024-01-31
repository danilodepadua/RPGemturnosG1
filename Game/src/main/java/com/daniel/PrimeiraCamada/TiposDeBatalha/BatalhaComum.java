package com.daniel.PrimeiraCamada.TiposDeBatalha;

import com.daniel.PrimeiraCamada.Exceptions.PlayerInexistenteException;
import com.daniel.PrimeiraCamada.Inimigo;
import com.daniel.PrimeiraCamada.TipoBatalha;
import com.daniel.TerceiraCamada.ResultadoController;
import com.daniel.game.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Random;

public class BatalhaComum extends TipoBatalha {
    Inimigo[] inimigos = Main.cidadeAtual.getInimigos();

    @Override
    public void Inicializar() {
        Random rand = new Random();
        inimigo = inimigos[rand.nextInt(0, inimigos.length)];
    }

    @Override
    public void LogicaVitoria() throws IOException, PlayerInexistenteException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("TelaResultado.fxml"));
        Parent root = loader.load();
        ResultadoController resultadoController = loader.getController();
        resultadoController.atualizarValores(this.xpGanho,  this.dinheiroGanho, this.itensDropados);
        Main.ChangeScene(root);
    }

    @Override
    public void Derrota() throws IOException {
        Main.ChangeScene(new FXMLLoader(Main.class.getResource("TelaGameOver.fxml")).load());
    }
}