package com.daniel.SegundaCamada;

import com.daniel.game.Main;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IceAnimation extends AnimationsAttack{
    public Timeline INICIAR(ImageView View) {
        System.out.println("Iniciou Preparação");
        Image[] img = new Image[29];
        for(int i = 0; i<29; i++)
        {
            img[i] = new Image(Main.class.getResource("/com.daniel.Images/Ice/Ice" +(i+1)+".png").toString());
        }
        System.out.println("Finalizou Preparação");
        System.out.println("Frames: " + img.length);
        return Play(View, 50, img);
    }
}
