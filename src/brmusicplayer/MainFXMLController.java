/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brmusicplayer;

import data_structures.ColorCount;
import java.util.Random;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import data_structures.Doublet;
import data_structures.DoubletListing;
import data_structures.Mode;
import data_structures.Nodet;
import data_structures.PriorityQueue;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.images.Artwork;
import java.io.File;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class MainFXMLController {

    @FXML // fx:id="gridPaneMain"
    private GridPane gridPaneMain; // Value injected by FXMLLoader

    @FXML // fx:id="gridPaneButtons"
    private GridPane gridPaneButtons; // Value injected by FXMLLoader

    @FXML // fx:id="gridPaneInformationProgress"
    private GridPane gridPaneInformationProgress; // Value injected by FXMLLoader

    @FXML // fx:id="sliderProgress"
    private Slider sliderProgress; // Value injected by FXMLLoader

    @FXML // fx:id="labelProgress"
    private Label labelProgress; // Value injected by FXMLLoader

    @FXML // fx:id="labelDuration"
    private Label labelDuration; // Value injected by FXMLLoader

    @FXML // fx:id="gridPaneControls"
    private GridPane gridPaneControls; // Value injected by FXMLLoader

    @FXML // fx:id="imageViewOpenFiles"
    private ImageView imageViewOpenFiles; // Value injected by FXMLLoader

    @FXML // fx:id="imageViewPlayPause"
    private ImageView imageViewPlayPause; // Value injected by FXMLLoader

    @FXML // fx:id="imageViewStop"
    private ImageView imageViewStop; // Value injected by FXMLLoader

    @FXML // fx:id="imageViewPrevious"
    private ImageView imageViewPrevious; // Value injected by FXMLLoader

    @FXML // fx:id="imageViewNext"
    private ImageView imageViewNext; // Value injected by FXMLLoader

    @FXML // fx:id="imageViewEqualizer"
    private ImageView imageViewEqualizer; // Value injected by FXMLLoader

    @FXML // fx:id="imageViewOpenDirectory"
    private ImageView imageViewOpenDirectory; // Value injected by FXMLLoader

    @FXML // fx:id="imageViewVoumeUp"
    private ImageView imageViewVoumeUp; // Value injected by FXMLLoader

    @FXML // fx:id="sliderVolume"
    private Slider sliderVolume; // Value injected by FXMLLoader

    @FXML // fx:id="gridPaneInformation"
    private GridPane gridPaneInformation; // Value injected by FXMLLoader

    @FXML // fx:id="paneImage"
    private Pane paneImage; // Value injected by FXMLLoader

    @FXML // fx:id="imageViewCoverArt"
    private ImageView imageViewCoverArt; // Value injected by FXMLLoader

    @FXML // fx:id="labelTitle"
    private Label labelTitle; // Value injected by FXMLLoader

    @FXML // fx:id="labelGenre"
    private Label labelGenre; // Value injected by FXMLLoader

    @FXML // fx:id="labelAlbum"
    private Label labelAlbum; // Value injected by FXMLLoader

    @FXML // fx:id="labelArtist"
    private Label labelArtist; // Value injected by FXMLLoader
    
    
    //Colors
    private Color firstColor;
    private Color secondColor;
    private Color thirdColor;
    private Color fontColor;
    
    private final Random colorRandom;
    private int actualLenght;
    private MediaPlayer mediaPlayer;
    private FileChooser fileChooser;
    
    //Data Stuctures
    private DoubletListing<String,String> listingMusic;
    private Nodet<Doublet<String,String>> actualNodet;
    
    
    public MainFXMLController(){
        firstColor = new Color(0,0,0,1.0);
        secondColor = new Color(0,0,0,1.0);
        thirdColor = new Color(0,0,0,1.0);
        fontColor = new Color(0,0,0,1.0);
        colorRandom = new Random(System.currentTimeMillis());
        listingMusic = new DoubletListing<>();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Music Files");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Music Files (*.mp3)", "*.mp3");
        fileChooser.getExtensionFilters().add(extFilter);
    }
    

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        sliderVolume.valueProperty().addListener((Observable observable) -> {
            mediaPlayer.setVolume(sliderVolume.getValue()/100);
        });
    }
    
    @FXML
    private void openFiles(MouseEvent event){
        
        if(event.getButton() == MouseButton.PRIMARY){
        
            List<File> files = fileChooser.showOpenMultipleDialog(null);
            listingMusic.clear();
            if(files != null){

                if(!files.isEmpty()){
                    files.forEach(file -> {
                        listingMusic.addToBack(file.getAbsolutePath(),file.toURI().toString());
                    });

                    actualNodet = listingMusic.getFrontNodet();
                    loadMetadata();
                    openMusic();
                }

            }
            files = null;
        }
        
    }
    
    private void loadMetadata(){
        try {
            File readFile = new File(actualNodet.getValue().getFirstElement());
            AudioFile audioFile = AudioFileIO.read(readFile);
            Tag tag = audioFile.getTag();
            String title = tag.getFirst(FieldKey.TITLE);
            String artist = tag.getFirst(FieldKey.ARTIST);
            String album = tag.getFirst(FieldKey.ALBUM);
            String genre = tag.getFirst(FieldKey.GENRE);
            actualLenght = audioFile.getAudioHeader().getTrackLength()*1000;
            
            Tooltip titleTooltip = new Tooltip("Title: "+title);
            Tooltip artistTooltip = new Tooltip("Artist: "+artist);
            Tooltip albumTooltip = new Tooltip("Album: "+album);
            Tooltip genreTooltip = new Tooltip("Genre: "+genre);
            
            labelTitle.setText(title);
            labelTitle.setTooltip(titleTooltip);
            labelArtist.setText(artist);
            labelArtist.setTooltip(artistTooltip);
            labelAlbum.setText(album);
            labelAlbum.setTooltip(albumTooltip);
            labelGenre.setText(genre);
            labelGenre.setTooltip(genreTooltip);
            
            //Get the artwork from the mp3 file:
            Artwork artWork = tag.getFirstArtwork();
            BufferedImage resizedImage = new BufferedImage(600,600,BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = resizedImage.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage((BufferedImage)artWork.getImage(), 0, 0,600,600,null);
            g2.dispose();
            Image coverArt = SwingFXUtils.toFXImage((resizedImage), null);
            setColors(coverArt);
            
            imageViewCoverArt.setImage(coverArt);
            readFile = null;
            coverArt = null;
            titleTooltip = null;
            artistTooltip = null;
            albumTooltip = null;
            genreTooltip = null;
        } catch (CannotReadException | IOException | TagException | ReadOnlyFileException | InvalidAudioFrameException ex) {
            System.out.println("brmusicplayer.MainFXMLController.loadMetadata(): "+ex.getMessage());
        }
    }
    
    public void openMusic(){
        
        if(mediaPlayer != null){
            if(mediaPlayer.getStatus().equals(Status.PLAYING)
                    || mediaPlayer.getStatus().equals(Status.PAUSED)){
                mediaPlayer.stop();
            }
            
            mediaPlayer.dispose();
            
            mediaPlayer = null;
            System.gc();
        }
        
        Media media = new Media(actualNodet.getValue().getSecondElement());
        mediaPlayer = new MediaPlayer(media);
        
        mediaPlayer.currentTimeProperty().addListener((ObservableValue<? extends javafx.util.Duration> observable, javafx.util.Duration oldValue, javafx.util.Duration newValue) -> {
            Double currentTime = newValue.toSeconds();
            sliderProgress.setValue(currentTime);
            labelProgress.setText(secondsToString(currentTime.longValue()));
            currentTime = null;
        });
        
        sliderVolume.setValue(mediaPlayer.getVolume()*100);
        
        
        

        mediaPlayer.setOnReady(() -> {
            
            Duration total = new Duration(actualLenght);
            Double seconds = total.toSeconds();
            sliderProgress.setMax(seconds);
            labelDuration.setText(secondsToString(seconds.longValue()));
            seconds = null;
        });
        

        mediaPlayer.play();
        media = null;
        
    }
    @FXML
    public void onMouseSlider(){
        mediaPlayer.seek(javafx.util.Duration.seconds(sliderProgress.getValue()));
    }
    
    @FXML
    public void stopButton(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY){
            mediaPlayer.stop();
        }
    }
    @FXML
    public void playPauseButton(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY){
            mediaPlayer.play();
        }
    }
    
    @FXML
    public void nextButton(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY){
            if(actualNodet.getNext() != null){
                actualNodet = actualNodet.getNext();
                loadMetadata();
                openMusic();
            }
        }
    }
    
    @FXML
    public void previousButton(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY){
            if(actualNodet.getPrevious()!= null){
                actualNodet = actualNodet.getPrevious();
                loadMetadata();
                openMusic();
            }
        }
    }
    
    @FXML
    public void openDirectory(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY){
            
        }
    }
    
    @FXML
    public void volumeDownButton(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY){
            if(sliderVolume.getValue() - 10 > 0){
                sliderVolume.setValue(sliderVolume.getValue() - 10);
            }else{
                sliderVolume.setValue(0);
            }
        }
    }
    
    @FXML
    public void volumeUpButton(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY){
            if(sliderVolume.getValue() + 10 < 100){
                sliderVolume.setValue(sliderVolume.getValue() + 10);
            }else{
                sliderVolume.setValue(100);
            }
        }
    }
    
    @FXML
    public void equalizerButton(MouseEvent event){
       if(event.getButton() == MouseButton.PRIMARY){
           
       }
    }

    
    public void setColors(Image image) {

        int maximum = 0;
        DoubletListing<Integer, Color> list = new DoubletListing<>();

        for (int i = 0; i < image.getWidth(); i += 30) {
            for (int j = 0; j < image.getHeight(); j += 30) {
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
                    list.addToBack(1, color);
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
        
        list.clear();
        fontColor = (firstColor.getRed() > 0.5) ? Color.BLACK : Color.WHITE;
        list = null;
        
       labelTitle.setTextFill(fontColor);
       labelAlbum.setTextFill(fontColor);
       labelArtist.setTextFill(fontColor);
       labelGenre.setTextFill(fontColor);
       labelDuration.setTextFill(fontColor);
       labelProgress.setTextFill(fontColor);

       Stop[] stops = new Stop[] { new Stop(0, secondColor), new Stop(0.4, thirdColor),new Stop(1, firstColor)};
       LinearGradient mainLinearGradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
       
       BackgroundFill bf = new BackgroundFill(mainLinearGradient,CornerRadii.EMPTY,Insets.EMPTY);
       Background background = new Background(bf);
       paneImage.setBackground(background);
       gridPaneButtons.setStyle("-fx-background-color: "+toHexString(firstColor)+";");
       
       mainLinearGradient = null;
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

    private String secondsToString(long seconds) {
        long absSeconds = Math.abs(seconds);
        String positive = String.format(
                "%02d:%02d:%02d",
                absSeconds / 3600,
                (absSeconds % 3600) / 60,
                absSeconds % 60);
        return seconds < 0 ? "-" + positive : positive;
    }
    
    
}


