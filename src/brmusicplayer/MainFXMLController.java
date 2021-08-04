/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brmusicplayer;

import com.sun.glass.events.MouseEvent;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import data_structures.Doublet;
import data_structures.DoubletListing;
import data_structures.Listing;
import java.io.File;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.FileChooser;

public class MainFXMLController {


    @FXML // fx:id="gridPaneMain"
    private GridPane gridPaneMain; // Value injected by FXMLLoader

    @FXML // fx:id="gridPaneButtons"
    private GridPane gridPaneButtons; // Value injected by FXMLLoader

    @FXML // fx:id="gridPaneInformationProgress"
    private GridPane gridPaneInformationProgress; // Value injected by FXMLLoader

    @FXML // fx:id="gridPaneProgress"
    private ColumnConstraints gridPaneProgress; // Value injected by FXMLLoader

    @FXML // fx:id="sliderProgress"
    private Slider sliderProgress; // Value injected by FXMLLoader

    @FXML // fx:id="labelProgress"
    private Label labelProgress; // Value injected by FXMLLoader

    @FXML // fx:id="labelDuration"
    private Label labelDuration; // Value injected by FXMLLoader

    @FXML // fx:id="gridPaneControls"
    private GridPane gridPaneControls; // Value injected by FXMLLoader

    @FXML // fx:id="gridPaneInformation"
    private GridPane gridPaneInformation; // Value injected by FXMLLoader

    @FXML // fx:id="paneImage"
    private Pane paneImage; // Value injected by FXMLLoader

    @FXML // fx:id="imageViewCoverArt"
    private ImageView imageViewCoverArt; // Value injected by FXMLLoader

    @FXML // fx:id="anchorPaneTags"
    private AnchorPane anchorPaneTags; // Value injected by FXMLLoader

    @FXML // fx:id="labelTitle"
    private Label labelTitle; // Value injected by FXMLLoader

    @FXML // fx:id="labelArtist"
    private Label labelArtist; // Value injected by FXMLLoader

    @FXML // fx:id="labelAlbum"
    private Label labelAlbum; // Value injected by FXMLLoader

    @FXML // fx:id="labelGenre"
    private Label labelGenre; // Value injected by FXMLLoader
    
    @FXML // fx:id="buttonOpenFile"
    private Button buttonOpenFile; // Value injected by FXMLLoader

    
    //Colors
    private Color firstColor;
    private Color secondColor;
    private Color thirdColor;
    private Color fourthColor;
    private Color fontColor;
    
    private final Random colorRandom;
    
    private MediaPlayer mediaPlayer;
    
    //Data Stuctures
    private Listing<String> listingMusic;
    
    
    public MainFXMLController(){
        firstColor = new Color(0,0,0,1.0);
        secondColor = new Color(0,0,0,1.0);
        thirdColor = new Color(0,0,0,1.0);
        fourthColor = new Color(0,0,0,1.0);
        fontColor = new Color(0,0,0,1.0);
        colorRandom = new Random(System.currentTimeMillis());
    }
    

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert gridPaneMain != null : "fx:id=\"gridPaneMain\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert gridPaneButtons != null : "fx:id=\"gridPaneButtons\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert gridPaneInformationProgress != null : "fx:id=\"gridPaneInformationProgress\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert gridPaneProgress != null : "fx:id=\"gridPaneProgress\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert sliderProgress != null : "fx:id=\"sliderProgress\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert labelProgress != null : "fx:id=\"labelProgress\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert labelDuration != null : "fx:id=\"labelDuration\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert gridPaneControls != null : "fx:id=\"gridPaneControls\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert buttonOpenFile != null : "fx:id=\"buttonOpenFile\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert gridPaneInformation != null : "fx:id=\"gridPaneInformation\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert paneImage != null : "fx:id=\"paneImage\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert imageViewCoverArt != null : "fx:id=\"imageViewCoverArt\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert anchorPaneTags != null : "fx:id=\"anchorPaneTags\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert labelTitle != null : "fx:id=\"labelTitle\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert labelArtist != null : "fx:id=\"labelArtist\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert labelAlbum != null : "fx:id=\"labelAlbum\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert labelGenre != null : "fx:id=\"labelGenre\" was not injected: check your FXML file 'MainFXML.fxml'.";


        Image image = new Image(BRMusicPlayer.class.getResourceAsStream( "../resources/images/siu.jpg" ));
        imageViewCoverArt.setImage(image);
        setColors(image);
    }
    
    @FXML
    private void openFile(javafx.scene.input.MouseEvent evt){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        String path = file.toURI().toString();
        if(path != null){
            Media m = new Media(path);
            mediaPlayer = new MediaPlayer(m);
            mediaPlayer.play();
        }
    }
    
    public void setColors(Image image) {

        int maximum = 0;
        DoubletListing<Integer, Color> list = new DoubletListing<>();

        for (int i = 0; i < image.getWidth(); i += 12) {
            for (int j = 0; j < image.getHeight(); j += 12) {
                Color color = image.getPixelReader().getColor(i, j);
                Doublet<Integer, Color> doublet = list.getDoubletForSecondElement(color);

                if (doublet != null) {//exist
                    int number = doublet.getFirstElement() + 1;
                    doublet.setFirstElement(number);
                    if (number > maximum) {
                        firstColor = color;
                        maximum = number;
                    }
                } else {
                    list.addToBack(0, color);
                }
            }
        }

        secondColor = firstColor;
        
        if(list.getSize() > 1){
            while(secondColor.equals(firstColor)){
                int position = colorRandom.nextInt((int)list.getSize()-1);
                secondColor =  list.get((long)position).getSecondElement();
            }
        }
        
       thirdColor = secondColor;
        if(list.getSize() > 2){
            while(thirdColor.equals(firstColor) || thirdColor.equals(secondColor) ){
                int position = colorRandom.nextInt((int)list.getSize()-1);
                thirdColor =  list.get((long)position).getSecondElement();
            }
        }
        
        fourthColor = thirdColor;
        if(list.getSize() > 3){
            while(fourthColor.equals(firstColor) || fourthColor.equals(secondColor) || fourthColor.equals(thirdColor) ){
                int position = colorRandom.nextInt((int)list.getSize()-1); 
                fourthColor = list.get((long)position).getSecondElement();
            }
        }
        
        double red = firstColor.getRed();
        fontColor = (red >= 155) ? Color.BLACK : Color.WHITE;
        
        list.clear();
        list = null;
       
        
       labelTitle.setTextFill(fontColor);
       labelAlbum.setTextFill(fontColor);
       labelArtist.setTextFill(fontColor);
       labelGenre.setTextFill(fontColor);
       labelDuration.setTextFill(fontColor);
       labelProgress.setTextFill(fontColor);

       Stop[] stops = new Stop[] { new Stop(0, firstColor), new Stop(0.4, secondColor), new Stop(0.8, thirdColor), new Stop(1, fourthColor)};
       //Stop[] stops1 = new Stop[] { new Stop(0, fourthColor), new Stop(1, thirdColor)};
       LinearGradient mainLinearGradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
       //LinearGradient nonMainLinearGradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops1);
       
       BackgroundFill bf = new BackgroundFill(mainLinearGradient,CornerRadii.EMPTY,Insets.EMPTY);
       Background background = new Background(bf);
       paneImage.setBackground(background);
       anchorPaneTags.setBackground(background);
       
       /*
       BackgroundFill bf1 = new BackgroundFill(nonMainLinearGradient,CornerRadii.EMPTY,Insets.EMPTY);
       Background background1 = new Background(bf1);
       
       gridPaneControls.setBackground(background1);
       gridPaneInformationProgress.setBackground(background1);
       sliderProgress.setBackground(background1);
        
       */
       
       gridPaneButtons.setStyle("-fx-background-color: "+toHexString(fourthColor)+";");
       
       mainLinearGradient = null;
       //nonMainLinearGradient = null;
       
       stops = null;
       
       bf  = null;
       background  = null;

       
       System.gc();
    }
    private String format(double val) {
        String in = Integer.toHexString((int) Math.round(val * 255));
        return in.length() == 1 ? "0" + in : in;
    }
    public String toHexString(Color value) {
        return "#" + (format(value.getRed()) + format(value.getGreen()) + format(value.getBlue()) + format(value.getOpacity()))
                .toUpperCase();
    }
    
}


