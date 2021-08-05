/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brmusicplayer;

import java.util.Random;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import data_structures.Doublet;
import data_structures.DoubletListing;
import data_structures.Nodet;
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
import java.io.FileFilter;
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
import javafx.stage.DirectoryChooser;

public class MainFXMLController {

    /**FXML Attributes*/

    @FXML // fx:id="gridPaneButtons"
    private GridPane gridPaneButtons; // Value injected by FXMLLoader

    @FXML // fx:id="sliderProgress"
    private Slider sliderProgress; // Value injected by FXMLLoader

    @FXML // fx:id="labelProgress"
    private Label labelProgress; // Value injected by FXMLLoader

    @FXML // fx:id="labelDuration"
    private Label labelDuration; // Value injected by FXMLLoader

    @FXML // fx:id="imageViewPlayPause"
    private ImageView imageViewPlayPause; // Value injected by FXMLLoader

    @FXML // fx:id="sliderVolume"
    private Slider sliderVolume; // Value injected by FXMLLoader

    @FXML // fx:id="imageViewMuteUnmute"
    private ImageView imageViewMuteUnmute; // Value injected by FXMLLoader
    
    @FXML // fx:id="labelSongsCount"
    private Label labelSongsCount; // Value injected by FXMLLoade

    @FXML // fx:id="sliderRate"
    private Slider sliderRate; // Value injected by FXMLLoader

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
 
    //Private Attributes
    private final Random colorRandom;
    private long actualLenght;
    private MediaPlayer mediaPlayer;
    private final Image playImage;
    private final Image pauseImage;
    private final Image muteImage;
    private final Image unmuteImage;
    private int index;
    
    //Data Stuctures
    private final DoubletListing<String,String> listingMusic;
    private Nodet<Doublet<String,String>> actualNodet;
    
    
    
    public MainFXMLController(){
        colorRandom = new Random(System.currentTimeMillis());
        listingMusic = new DoubletListing<>();
        playImage = new Image(BRMusicPlayer.class.getResourceAsStream( "../resources/images/play-button.png" ));
        pauseImage = new Image(BRMusicPlayer.class.getResourceAsStream( "../resources/images/pause.png" ));
        muteImage = new Image(BRMusicPlayer.class.getResourceAsStream( "../resources/images/muted.png" ));
        unmuteImage = new Image(BRMusicPlayer.class.getResourceAsStream( "../resources/images/volume.png" ));
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        sliderVolume.valueProperty().addListener((Observable observable) -> {
            mediaPlayer.setVolume(sliderVolume.getValue()/100);
        });
        
        sliderRate.valueProperty().addListener((Observable observable) -> {
            mediaPlayer.setRate(sliderRate.getValue());
        });
    }
    
    @FXML
    public void openFiles(MouseEvent event){
        
        if(event.getButton() == MouseButton.PRIMARY){
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Music Files");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Music Files (*.mp3) (*.flac)", "*.mp3","*.flac");
            fileChooser.getExtensionFilters().add(extFilter);
            List<File> openedFiles = fileChooser.showOpenMultipleDialog(null);
            listingMusic.clear();
            if(openedFiles != null){

                if(!openedFiles.isEmpty()){
                    openedFiles.forEach(file -> {
                        listingMusic.addToBack(file.getAbsolutePath(),file.toURI().toString());
                    });

                    index = 1;
                    actualNodet = listingMusic.getFrontNodet();
                    loadMetadata();
                    playMusic();
                }
            }
            
            openedFiles = null;
            fileChooser = null;
            extFilter = null;
            
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
            actualLenght = audioFile.getAudioHeader().getTrackLength();
            
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
            
            Artwork artWork = tag.getFirstArtwork();
            BufferedImage resizedImage = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = resizedImage.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage((BufferedImage)artWork.getImage(), 0, 0,500,500,null);
            g2.dispose();
            
            Image coverArt = SwingFXUtils.toFXImage((resizedImage), null);
            setColors(coverArt);
            
            imageViewCoverArt.setImage(coverArt);
            
            String listCount = index+"/"+listingMusic.getSize();
            labelSongsCount.setText(listCount);
            
            g2 = null;
            readFile = null;
            listCount = null;
            audioFile = null;
            tag = null;
            title = null;
            artist = null;
            album = null;
            genre = null;
            titleTooltip = null;
            artistTooltip = null;
            albumTooltip = null;
            genreTooltip = null;
            artWork = null;
            resizedImage = null;
            coverArt = null;
        } catch (CannotReadException | IOException | TagException | ReadOnlyFileException | InvalidAudioFrameException ex) {
            System.out.println("brmusicplayer.MainFXMLController.loadMetadata(): "+ex.getMessage());
        }
    }
    
    public void cleanMediaPlayer(){
        if(mediaPlayer != null){
            if(mediaPlayer.getStatus().equals(Status.PLAYING)
                    || mediaPlayer.getStatus().equals(Status.PAUSED)){
                mediaPlayer.stop();
            }
            
            mediaPlayer.dispose();
            
            mediaPlayer = null;
            System.gc();
        }
    }
    
    public void setMediaPlayerEvents(){
        if(mediaPlayer != null){
            mediaPlayer.currentTimeProperty().addListener((ObservableValue<? extends javafx.util.Duration> observable, javafx.util.Duration oldValue, javafx.util.Duration newValue) -> {
                Double currentTime = newValue.toSeconds();
                sliderProgress.setValue(currentTime);
                labelProgress.setText(secondsToString(currentTime.longValue()));

                if(currentTime.longValue() == actualLenght)
                    playNext();
            
                currentTime = null;
            });

            sliderVolume.setValue(mediaPlayer.getVolume()*100);
            sliderRate.setValue(mediaPlayer.getRate());
            mediaPlayer.setOnReady(() -> {

                Duration total = new Duration(actualLenght*1000);
                Double seconds = total.toSeconds();
                sliderProgress.setMax(seconds);
                labelDuration.setText(secondsToString(seconds.longValue()));
                seconds = null;
                total = null;
            });
        }
    }
    
    public void playMusic(){
        
        cleanMediaPlayer();
        
        Media media = new Media(actualNodet.getValue().getSecondElement());
        mediaPlayer = new MediaPlayer(media);
        setMediaPlayerEvents();
        mediaPlayer.play();
        imageViewPlayPause.setImage(pauseImage);
        imageViewMuteUnmute.setImage(muteImage);
        media = null;
    }
    
    @FXML
    public void onMouseSlider(){
        mediaPlayer.seek(javafx.util.Duration.seconds(sliderProgress.getValue()));
    }
    
    @FXML
    public void stopButton(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY){
            if(mediaPlayer != null){
                mediaPlayer.stop();
                imageViewPlayPause.setImage(playImage);
                imageViewMuteUnmute.setImage(muteImage);
            }
        }
    }
    @FXML
    public void playPauseButton(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY){
            if(mediaPlayer != null){
                if(mediaPlayer.getStatus().equals(Status.PAUSED)){
                    imageViewPlayPause.setImage(pauseImage);
                    imageViewMuteUnmute.setImage(unmuteImage);
                    mediaPlayer.play();
                }
                else if(mediaPlayer.getStatus().equals(Status.PLAYING)){
                    imageViewPlayPause.setImage(playImage);
                    imageViewMuteUnmute.setImage(muteImage);
                    mediaPlayer.pause();
                }
            }
        }
    }
    
    @FXML
    public void nextButton(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY){
            playNext();
        }
    }
    
    private void playNext(){
    if(actualNodet.getNext() != null){
            index++;
            actualNodet = actualNodet.getNext();
            loadMetadata();
            playMusic();
        }
    }
    
    @FXML
    public void previousButton(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY){
            if(actualNodet.getPrevious()!= null){
                index--;
                actualNodet = actualNodet.getPrevious();
                loadMetadata();
                playMusic();
            }
        }
    }
    
    @FXML
    public void openDirectory(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY){
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Choose Directory Music Files");
            File directory = directoryChooser.showDialog(null);
            listingMusic.clear();
            if(directory != null){
                FileFilter musicFilter = (File file) -> {
                    return file.getName().endsWith(".mp3") || file.getName().endsWith(".flac") || file.getName().endsWith(".wma") || file.getName().endsWith(".m4a");
                };
                File[] files = directory.listFiles(musicFilter);
                if(files.length > 0){
                    
                    for(File file: files)
                        listingMusic.addToBack(file.getAbsolutePath(),file.toURI().toString());
                    
                    actualNodet = listingMusic.getFrontNodet();
                    loadMetadata();
                    playMusic();
                }
                
                files = null;
                
                musicFilter = null;
            }
            
            directoryChooser = null;
            directory = null;
            
        }
        
        
    }
    
    @FXML
    public void volumeDownButton(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY){
            if(mediaPlayer != null){
                if(!mediaPlayer.getStatus().equals(Status.STOPPED)){
                    if(sliderVolume.getValue() - 10 > 0)
                        sliderVolume.setValue(sliderVolume.getValue() - 10);
                    else
                        sliderVolume.setValue(0);
                }
            }
        }
    }
    
    @FXML
    public void volumeUpButton(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY){
            if(mediaPlayer != null){
                if(!mediaPlayer.getStatus().equals(Status.STOPPED)){
                    if(sliderVolume.getValue() + 10 < 100)
                        sliderVolume.setValue(sliderVolume.getValue() + 10);
                    else
                        sliderVolume.setValue(100);
                }
            }
        }
    }
    
    @FXML
    public void rateDownButton(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY){
            if(mediaPlayer != null){
                if(!mediaPlayer.getStatus().equals(Status.STOPPED)){
                    if(sliderRate.getValue() - 0.1 > 0)
                        sliderRate.setValue(sliderRate.getValue() - 0.1);
                    else
                        sliderRate.setValue(0);
                }
            }
        }
    }
    
    @FXML
    public void rateNormalState(MouseEvent event){
        if(event.getButton() == MouseButton.SECONDARY){
            sliderRate.setValue(1);
        }
    }
    
    @FXML
    public void rateUpButton(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY){
            if(mediaPlayer != null){
                if(!mediaPlayer.getStatus().equals(Status.STOPPED)){
                    if(sliderRate.getValue() + 0.1 < 2)
                        sliderRate.setValue(sliderRate.getValue() + 0.1);
                    else
                        sliderRate.setValue(2);
                }
            }
        }
    }
    
    @FXML
    public void equalizerButton(MouseEvent event){
       if(event.getButton() == MouseButton.PRIMARY){
           if(mediaPlayer != null){
                if(!mediaPlayer.getStatus().equals(Status.STOPPED)){
                    
                }
           }
       }
    }

    @FXML
    public void muteUnmuteButton(MouseEvent event){
       if(event.getButton() == MouseButton.PRIMARY){
           if(event.getButton() == MouseButton.PRIMARY){
            if(mediaPlayer != null){
                if(mediaPlayer.isMute()){
                    imageViewMuteUnmute.setImage(muteImage);
                    mediaPlayer.setMute(false);
                }
                else{
                    imageViewMuteUnmute.setImage(unmuteImage);
                    mediaPlayer.setMute(true);
                }
            }
        }
       }
    }
    
    public void setColors(Image image) {
        int maximum = 0;
        Color firstColor = Color.BLACK;
        Color secondColor, thirdColor, firstFontColor, secondFontColor;
        DoubletListing<Integer, Color> listingColorCount = new DoubletListing<>();

        for (int i = 0; i < image.getWidth(); i += 20) {
            for (int j = 0; j < image.getHeight(); j += 20) {
                Color color = image.getPixelReader().getColor(i, j);
                Doublet<Integer, Color> doublet = listingColorCount.getDoubletForSecondElement(color);

                if (doublet != null) {//exist
                    int number = doublet.getFirstElement() + 1;
                    doublet.setFirstElement(number);
                    if (number > maximum) {
                        firstColor = color;
                        maximum = number;
                    }
                } else {
                    listingColorCount.addToBack(1, color);
                }
                color = null;
                doublet = null;
            }
        }

        secondColor = firstColor;
        
        if(listingColorCount.getSize() > 1){
            while(secondColor.equals(firstColor)){
                int position = colorRandom.nextInt((int)listingColorCount.getSize()-1);
                secondColor =  listingColorCount.get((long)position).getSecondElement();
            }
        }
        
        thirdColor = secondColor;
        if(listingColorCount.getSize() > 2){
            while(thirdColor.equals(firstColor) || thirdColor.equals(secondColor) ){
                int position = colorRandom.nextInt((int)listingColorCount.getSize()-1);
                thirdColor =  listingColorCount.get((long)position).getSecondElement();
            }
        }
        
        listingColorCount.clear();
        firstFontColor = (firstColor.getRed() > 0.5) ? Color.BLACK : Color.WHITE;
        secondFontColor = (secondColor.getRed() > 0.5) ? Color.BLACK : Color.WHITE;
        
        labelTitle.setTextFill(secondFontColor);
        labelAlbum.setTextFill(secondFontColor);
        labelArtist.setTextFill(secondFontColor);
        labelGenre.setTextFill(secondFontColor);
        
        labelDuration.setTextFill(firstFontColor);
        labelProgress.setTextFill(firstFontColor);
        labelSongsCount.setTextFill(firstFontColor);
     
        Stop stop1 = new Stop(0, secondColor);
        Stop stop2 = new Stop(0.4, thirdColor);
        Stop stop3 = new Stop(1, firstColor);
        
        LinearGradient mainLinearGradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stop1,stop2,stop3);

        BackgroundFill bf = new BackgroundFill(mainLinearGradient,CornerRadii.EMPTY,Insets.EMPTY);
        Background background = new Background(bf);
        paneImage.setBackground(background);
        String hex = toHexString(firstColor);
        gridPaneButtons.setStyle("-fx-background-color: "+hex+";");

        stop1 = stop2 = stop3 = null;
        mainLinearGradient = null;
        bf  = null;
        background  = null;
        hex = null;
        firstColor = secondColor = thirdColor = secondFontColor = firstFontColor = null;

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


