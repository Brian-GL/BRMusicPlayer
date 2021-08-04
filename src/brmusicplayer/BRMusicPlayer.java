/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brmusicplayer;

import java.awt.Toolkit;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

/**
 *
 * @author LENOVO
 */
public class BRMusicPlayer extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainFXML.fxml"));
        stage.setTitle("BR Music Player");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMinHeight(720);
        stage.setMinWidth(1280);
        stage.setMaximized(true);
        stage.getIcons().add(new Image(BRMusicPlayer.class.getResourceAsStream( "../resources/images/BR_Music_Player.png" ))); 
        stage.show();
    }
    
}
