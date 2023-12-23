package com.daniel.game;

import com.daniel.PrimeiraCamada.Save.SaveManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;


public class Main extends Application {
    static Stage CurrentStage;
    public static SaveManager saveManager = new SaveManager();


    public static void ChangeScene(FXMLLoader Root){
        try {
            boolean isFull = CurrentStage.isFullScreen();
            Scene scene = new Scene(Root.load(), CurrentStage.getMaxWidth(), CurrentStage.getHeight());
            CurrentStage.setScene(scene);
            CurrentStage.setFullScreen(isFull);
        }
        catch(IOException ignored){
            System.out.println(ignored);
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        CurrentStage = stage;
        FXMLLoader root = new FXMLLoader(Main.class.getResource("MainScene.fxml"));
        stage.setHeight(400);
        stage.setWidth(600);
        Scene scene = new Scene(root.load(),CurrentStage.getMaxWidth(), CurrentStage.getHeight());
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.setScene(scene);
        stage.setTitle("teste");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
