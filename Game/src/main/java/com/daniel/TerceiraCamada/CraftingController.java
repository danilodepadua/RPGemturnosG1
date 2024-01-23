package com.daniel.TerceiraCamada;

import com.daniel.PrimeiraCamada.Entidades.Player;
import com.daniel.PrimeiraCamada.Exceptions.PlayerInexistenteException;
import com.daniel.PrimeiraCamada.Itens.Item;
import com.daniel.PrimeiraCamada.Itens.Minerios.BarraFerro;
import com.daniel.PrimeiraCamada.Itens.Minerios.Ferro;
import com.daniel.game.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.daniel.TerceiraCamada.Utilidades.configurarBotoes;
import static com.daniel.TerceiraCamada.Utilidades.definirBackground;

public class CraftingController implements Initializable {
    @FXML
    private Button btnSlot1;

    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnSlot2;
    @FXML
    private Button btnSlot3;
    @FXML
    private GridPane gridItensPlayer;
    @FXML
    private AnchorPane panePrincipal;

    @FXML
    private Button btnCriar;

    private Item itemSelecionado;
    private Item itemSelecionado2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        definirBackground(panePrincipal, "/com.daniel.Images/Fundos/Fornalha.jpg");
        try {
            atualizarInterface();
        } catch (PlayerInexistenteException e) {
            throw new RuntimeException(e);
        }

    }

    private void atualizarInterface() throws PlayerInexistenteException {
        gridItensPlayer.getChildren().clear();
        int j =0;
        for (int i = 0; i < Player.getPlayer().getInventario().getItens().length; i++) {
            if (Player.getPlayer().getInventario().getItens()[i] != null) {
                Button b = new Button();
                ImageView image = new ImageView();
                image.setImage(Player.getPlayer().getInventario().getItens()[i].getImage());

                image.setFitWidth(40);
                image.setFitHeight(40);
                gridItensPlayer.add(b, j % 10, j / 10);
                b.prefWidthProperty().bind(gridItensPlayer.prefWidthProperty().divide(gridItensPlayer.getColumnCount()));
                b.prefHeightProperty().bind(gridItensPlayer.prefHeightProperty().divide(gridItensPlayer.getRowCount()));
                // Defina a cor de fundo do botão, bordas arredondadas e tamanho mínimo do botão
                b.setStyle("-fx-background-color: #241811; -fx-min-width: 60; -fx-min-height: 60;-fx-background-insets: 0; -fx-background-radius: 0;-fx-border-width: 1; -fx-focus-traversable: false; -fx-border-color:  #eccb7e");

                image.setPreserveRatio(true);
                b.setGraphic(image);

                configurarBotoes(b);
                int finalI = i;
                b.setOnAction(event -> {
                    try {
                        if (itemSelecionado == null) {
                            itemSelecionado = Player.getPlayer().getInventario().getItens()[finalI];
                            itemSelecionado(itemSelecionado, btnSlot1);
                        } else if (itemSelecionado2 == null) {
                            itemSelecionado2 = Player.getPlayer().getInventario().getItens()[finalI];
                            if (!itemSelecionado2.equals(itemSelecionado)){
                            itemSelecionado(itemSelecionado2, btnSlot2);
                            }else {
                                if (itemSelecionado2.getQuant() >1){
                                    itemSelecionado(itemSelecionado2, btnSlot2);
                                }
                            }
                        } else if (itemSelecionado != null || itemSelecionado2 != null) {
                            itemSelecionado = null;
                            itemSelecionado2 = null;
                        }
                    } catch (PlayerInexistenteException e) {
                        throw new RuntimeException(e);
                    }
                });
                btnCriar.setOnAction(event -> {
                    try {
                        Player.getPlayer().getInventario().adicionarItem(updateCraft());
                        Player.getPlayer().getInventario().RemoverItem(itemSelecionado);
                        Player.getPlayer().getInventario().RemoverItem(itemSelecionado2);
                        atualizarInterface();
                        // Limpa os slots e desabilita o botão de criação
                        itemSelecionado = null;
                        itemSelecionado2 = null;
                        btnCriar.setDisable(true);
                        btnSlot1.setGraphic(null);
                        btnSlot2.setGraphic(null);
                        btnSlot3.setGraphic(null);
                    } catch (PlayerInexistenteException e) {
                        throw new RuntimeException(e);
                    }
                });
                j++;
            }
        }
    }
    private Item updateCraft() throws PlayerInexistenteException {
        if (itemSelecionado != null && itemSelecionado2 != null) {
            // Verifica se pelo menos um dos itens selecionados é um minério de ferro (ou sua lógica específica)
            if (itemSelecionado instanceof Ferro && itemSelecionado2 instanceof Ferro) {
                btnCriar.setDisable(false);
                BarraFerro barraFerro = new BarraFerro();
                btnSlot3.setGraphic(new ImageView(barraFerro.getImage()));
                return barraFerro;
            }
        } else {
            // Se não houver um par de itens selecionados, limpe o slot3
            btnSlot3.setGraphic(null);
            btnCriar.setDisable(true);
        }
        return null;
    }


    private void itemSelecionado(Item item, Button button) {
        ImageView view = new ImageView();
        view.setImage(item.getImage());
        view.setFitHeight(35);
        view.setFitWidth(35);
        button.setGraphic(view);

        try {
            // Atualiza o craft sempre que um item é selecionado
            updateCraft();
        } catch (PlayerInexistenteException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onClickVoltar(ActionEvent event) throws IOException {
        Main.ChangeScene(new FXMLLoader(Main.class.getResource("TelaCidade.fxml")).load());
    }
}
