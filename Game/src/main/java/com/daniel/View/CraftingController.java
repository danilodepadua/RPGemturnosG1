package com.daniel.View;

import com.daniel.Model.Dados.AudioPlayer;
import com.daniel.Controller.CraftingFachada;
import com.daniel.Model.Dados.Entidades.Player;
import com.daniel.Model.Exceptions.PlayerInexistenteException;
import com.daniel.Model.Dados.Itens.Item;
import com.daniel.game.Main;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.daniel.View.Utilidades.*;

public class CraftingController implements Initializable {

    @FXML
    private AnchorPane boxMensagem;

    @FXML
    private Button btnCriar;

    @FXML
    private Button btnSlot1;

    @FXML
    private Button btnSlot2;

    @FXML
    private Button btnSlot3;

    @FXML
    private Button btnVoltar;

    @FXML
    private GridPane gridItensPlayer;

    @FXML
    private ImageView imgItem;

    @FXML
    private AnchorPane panePrincipal;

    @FXML
    private Text txtMensagem;

    @FXML
    private Text txtNomeItem;

    @FXML
    private Text txtProbSucesso;

    @FXML
    private Text txtQuantidade;
    private Item itemSelecionado;
    private Item itemSelecionado2;
    private CraftingFachada craftingFachada;
    private AudioPlayer audioPlayer = new AudioPlayer();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        definirBackground(panePrincipal, "/com.daniel.Images/Fundos/Fornalha.jpg");
        contornarBotaoVoltarLoja(btnVoltar);
        configurarBotoes(btnCriar);
        this.craftingFachada = CraftingFachada.getInstance();
        try {
            atualizarInterface();
        } catch (PlayerInexistenteException e) {
            throw new RuntimeException(e);
        }

    }

    private void atualizarInterface() throws PlayerInexistenteException {
        gridItensPlayer.getChildren().clear();
        int coluna = 0;
        int linha = 0;
        for (Item item : Player.getPlayer().getInventario().getItens()){
            if (item != null){
                Button botao = new Button();
                ImageView imageView = new ImageView();
                imageView.setImage(item.getImage());
                imageView.setFitWidth(30);
                imageView.setFitHeight(30);
                botao.setGraphic(imageView);
                botao.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
                botao.setStyle("-fx-background-color:  #241811;-fx-border-width: 1;-fx-border-color: #eccb7e");
                configurarBotoes(botao);
                gridItensPlayer.add(botao, coluna, linha);
                coluna++;
                if (coluna >= 5){
                    coluna=0;
                    linha++;
                }
                botao.setOnAction(event -> {
                    if (itemSelecionado != null && itemSelecionado2 !=null){
                        limparTela();  //Se os slots nao tao preenchidos, limpa a tela quando clicar novamente
                    }else {
                        if (itemSelecionado == null){
                            itemSelecionado = item;
                            itemSelecionado(itemSelecionado, btnSlot1);
                        }else {
                            if (itemSelecionado.getNome().equals(item.getNome())){
                                if (itemSelecionado.getQuant() > 1){ //Fazer com que o player nao coloque dois itens juntos
                                    itemSelecionado2 = item;
                                    itemSelecionado(itemSelecionado2, btnSlot2);
                                }
                            }else {
                                itemSelecionado2 = item;
                                itemSelecionado(itemSelecionado2, btnSlot2);
                            }
                        }
                        if (itemSelecionado != null && itemSelecionado2 != null){
                            try {
                                updateCraft();
                            } catch (PlayerInexistenteException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
                btnCriar.setOnAction(event -> {
                    try {
                        Player.getPlayer().getInventario().adicionarItem(criarCraft());
                        Player.getPlayer().getInventario().removerItem(itemSelecionado);
                        Player.getPlayer().getInventario().removerItem(itemSelecionado2);
                        atualizarInterface(); //Chamando de forma recursiva para atualizar a tela a partir do clique
                        limparTela();

                    } catch (PlayerInexistenteException e) {
                        throw new RuntimeException(e);
                    }

                });

            }
        }
    }
    private ImageView criarImageCraft(Item item){
        ImageView view = new ImageView();
        view.setImage(item.getImage());
        view.setFitHeight(35);
        view.setFitWidth(35);

        btnCriar.setDisable(false);
        return view;
    }
    private Item updateCraft() throws PlayerInexistenteException {
        Item resultado = craftingFachada.criarBarraFerro(itemSelecionado, itemSelecionado2, 0.3);

        if (resultado != null) {
            btnSlot3.setGraphic(criarImageCraft(resultado));
            editarTexto();
            return resultado;
        }


        resultado = craftingFachada.criarPocaoVidaGrande(itemSelecionado, itemSelecionado2, 0.3);

        if (resultado != null) {
            btnSlot3.setGraphic(criarImageCraft(resultado));
            editarTexto();
            return resultado;
        }

        resultado = craftingFachada.criarPocaoManaGrande(itemSelecionado, itemSelecionado2, 0.5);

        if (resultado != null) {
            btnSlot3.setGraphic(criarImageCraft(resultado));
            editarTexto();
            return resultado;
        }

        resultado = craftingFachada.criarFerroAperfeicoado(itemSelecionado, itemSelecionado2, 0.5);

        if (resultado != null) {
            btnSlot3.setGraphic(criarImageCraft(resultado));
            editarTexto();
            return resultado;
        }

        resultado = craftingFachada.criarEspadaAperfeicoada(itemSelecionado, itemSelecionado2, 0.6);

        if (resultado != null) {
            btnSlot3.setGraphic(criarImageCraft(resultado));
            editarTexto();
            return resultado;
        }

        resultado = craftingFachada.criarEspadaGeloAperfeicoada(itemSelecionado, itemSelecionado2, 0.4);

        if (resultado != null) {
            btnSlot3.setGraphic(criarImageCraft(resultado));
            editarTexto();
            return resultado;
        }

        resultado = craftingFachada.criarEspadaEscuridaoAperfeicoada(itemSelecionado, itemSelecionado2, 0.7);

        if (resultado != null) {
            btnSlot3.setGraphic(criarImageCraft(resultado));
            editarTexto();
            return resultado;
        }

        resultado = craftingFachada.criarEspadaFogoAperfeicoada(itemSelecionado, itemSelecionado2,0.6);

        if (resultado != null) {
            btnSlot3.setGraphic(criarImageCraft(resultado));
            editarTexto();
            return resultado;
        }

        resultado = craftingFachada.criarEspadaEletricaAperfeicoada(itemSelecionado, itemSelecionado2,0.5);

        if (resultado != null) {
            btnSlot3.setGraphic(criarImageCraft(resultado));
            editarTexto();
            return resultado;
        }

        resultado = craftingFachada.criarBarraFogo(itemSelecionado, itemSelecionado2, 0.6);

        if (resultado != null) {
            btnSlot3.setGraphic(criarImageCraft(resultado));
            editarTexto();
            return resultado;
        }
        resultado = craftingFachada.criarBarraGelo(itemSelecionado, itemSelecionado2, 0.35);

        if (resultado != null) {
            btnSlot3.setGraphic(criarImageCraft(resultado));
            editarTexto();
            return resultado;
        }
        resultado = craftingFachada.criarBarraEscuridao(itemSelecionado, itemSelecionado2, 0.4);

        if (resultado != null) {
            btnSlot3.setGraphic(criarImageCraft(resultado));
            editarTexto();
            return resultado;
        }
        resultado = craftingFachada.criarBarraOuro(itemSelecionado, itemSelecionado2, 0.6);

        if (resultado != null) {
            btnSlot3.setGraphic(criarImageCraft(resultado));
            editarTexto();
            return resultado;
        }
        // Se chegou até aqui, nenhum resultado foi encontrado
        return null;
    }



    private Item criarCraft() throws PlayerInexistenteException {
        Item craftResult = updateCraft();
        if (craftResult != null) {

            double random =Math.random();
            if (calcularChance() > random) {
                mostrarResultado("Item Forjado!");
                audioPlayer.play("/com.daniel.audios/som_craft.wav", false);
                return craftResult;
            }else {
                mostrarResultado("Falhou");
            }
        }
        return null;
    }
    private double calcularChance() throws PlayerInexistenteException {
        int inteligencia = Player.getPlayer().getInteligence();
        double chance = craftingFachada.getChanceBase();
        double sucesso = chance + (inteligencia * 0.01);
        if( sucesso >= 1){
            sucesso=1;
        }
        return sucesso;
    }
    private void editarTexto() throws PlayerInexistenteException {
        String porcentagemFormatada = String.format("%.0f%%", calcularChance() * 100);
        txtProbSucesso.setText(porcentagemFormatada);
    }
    private void limparTela(){
        itemSelecionado = null;
        itemSelecionado2 = null;
        btnSlot1.setGraphic(null);
        btnSlot2.setGraphic(null);
        btnSlot3.setGraphic(null);
        btnCriar.setDisable(true);
        txtProbSucesso.setText("");
        imgItem.setImage(null);
        txtQuantidade.setText("");
        txtNomeItem.setText("");
    }

    private void itemSelecionado(Item item, Button button) {
        ImageView view = new ImageView();
        view.setImage(item.getImage());
        view.setFitHeight(35);
        view.setFitWidth(35);
        button.setGraphic(view);
        imgItem.setImage(item.getImage());
        txtQuantidade.setText(""+item.getQuant());
        txtNomeItem.setText(item.getNome());

    }

    @FXML
    void onClickVoltar(ActionEvent event) throws IOException {
        Main.ChangeScene(new FXMLLoader(Main.class.getResource("TelaCidade.fxml")).load());
    }
    private void mostrarResultado(String mensagem) {
        txtMensagem.setText(mensagem);

        // Animação de fade-in
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), boxMensagem);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        // Animação de fade-out
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), boxMensagem);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setDelay(Duration.seconds(2));

        // Combinação das animações
        SequentialTransition sequence = new SequentialTransition(fadeIn, fadeOut);

        // Inicia a sequência de animação
        sequence.play();
    }
}