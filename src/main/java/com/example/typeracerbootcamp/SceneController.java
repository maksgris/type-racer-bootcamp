package com.example.typeracerbootcamp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.application.HostServices;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SceneController implements Initializable {
    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    private FXMLLoader fxmlLoader;
    private GameController gameController;
    private GameController controller;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private GameController finalController;
    private int time;
    static double volume = 0.1;
    static boolean muted = false;

    @FXML
    private MediaView mediaView;

    @FXML
    private Slider soundSlider;

    @FXML
    Label myLabel;

    @FXML
    Button muteButton;

    public void StartGame(ActionEvent e)  throws IOException  {
        fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/typeracerbootcamp/InGame.fxml"));
        controller = fxmlLoader.getController();
        fxmlLoader.setController(controller = new GameController());
        root = fxmlLoader.load();
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root, 600,400);
        String css = Objects.requireNonNull(this.getClass().getResource("/com/example/typeracerbootcamp/application.css")).toExternalForm();
        scene.getStylesheets().add(css);
        controller.load(true);
        finalController = controller;

        //  Media Player

        if (mediaView.getMediaPlayer() != null)
            mediaView.getMediaPlayer().stop();
        try {
            String fileName = getClass().getResource("/Redline.mp3").toURI().toString();
            Media media = new Media(fileName);
            MediaPlayer player = new MediaPlayer(media);
            mediaView.setMediaPlayer(player);
            mediaView.getMediaPlayer().seek(mediaView.getMediaPlayer().getStartTime());
            mediaView.getMediaPlayer().setVolume(volume);
            mediaView.getMediaPlayer().play();

        } catch (URISyntaxException exception) {
            exception.printStackTrace();
        }

        time = 15;
        Runnable timer = new Runnable() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    time--;
                    finalController.updateTimer(time);
                    System.out.println("[DEBUG] timer runing... " + time);
                    if(time<=0) {
                        System.out.println("[DEBUG] horay! " + time + " <= 0");
                        EndGame();
                    }
                });
                }
            };
        Thread thread = new Thread(timer);
        thread.setDaemon(true);
        executorService.scheduleAtFixedRate(thread,0,1, TimeUnit.SECONDS);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if(time>0){
                    if (e.getCode() == KeyCode.ENTER) {
                        System.out.println("[DEBUG] ENTER HIT");
                        finalController.onEnter(e.getCode().toString());
                    }
                    else if(e.getCode().toString().length()==1) {
                        System.out.println("[DEBUG] LETTER HIT " + e.getText());
                        finalController.onLetterPress(e.getText());
                    }
                    else if(e.getCode() == KeyCode.BACK_SPACE)
                        finalController.delchar();
                    System.out.println(e.getCode());
                }
                if(e.getCode()==KeyCode.ESCAPE){
                    try{
                        mediaView.getMediaPlayer().stop();
                        executorService.shutdown();
                        finalController.endgamelistener();
                    }catch (Exception exception){
                        exception.printStackTrace();
                    }
                }
            }
        });

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                mediaView.getMediaPlayer().stop();
                executorService.shutdown();
                System.out.println("[DEBUG] Close request init.");
                System.out.println("[DEBUG] thread closed");
            }
        });
        stage.setScene(scene);
        stage.show();
    }
    public void OpenLink() {
        Source source = new Source();
        source.getHostServices().showDocument("https://github.com/maksgris/type-racer-bootcamp");
    }
    public void SetSound() {
        volume = soundSlider.getValue();
        mediaView.getMediaPlayer().setVolume(volume);
        System.out.println("[DEBUG] SetSound() in SceneController, volume = " + volume);
        muted = false;
        setUnmutePicture();
    }
    public void setMutePicture() {
        ImageView mute = new ImageView(new Image("mute.png"));
        mute.setFitHeight(35);
        mute.setPreserveRatio(true);
        muteButton.setGraphic(mute);
    }
    public void setUnmutePicture() {
        ImageView unmute = new ImageView(new Image("unmute.png"));
        unmute.setFitHeight(35);
        unmute.setPreserveRatio(true);
        muteButton.setGraphic(unmute);
    }
    public void Mute() {
        if(muted) {
            setUnmutePicture();
            mediaView.getMediaPlayer().setVolume(volume);
            System.out.println("[DEBUG] Mute() in SceneController, volume = " + volume);
            muted = !muted;
        } else {
            setMutePicture();
            mediaView.getMediaPlayer().setVolume(0);
            System.out.println("[DEBUG] Mute() in SceneController, volume = 0");
            muted = !muted;
        }
    }
    public void ExitGame() {
        Platform.exit();
    }
    public void EndGame() {
        System.out.println("[DEBUG] EndGame() in SceneController");
        executorService.shutdown();
        finalController.endGame();
        stage.setScene(scene);
        stage.show();
    }
    public void StartGameOnline(ActionEvent e) throws IOException {
        FXMLLoader popup = new FXMLLoader(getClass().getResource("/com/example/typeracerbootcamp/LoginAlert.fxml"));
        OnlineGamePopup control = popup.getController();
        Parent rot = popup.load();
        Stage stg = new Stage();
        stg.setTitle("Competitive Type Racing");
        Image icon = new Image("file:src/main/java/images/image.png");
        stg.getIcons().add(icon);
        Scene scn = new Scene(rot);
        stg.setScene(scn);
        stg.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            String fileName = getClass().getResource("/MainMenu.mp3").toURI().toString();
            Media media = new Media(fileName);
            MediaPlayer player = new MediaPlayer(media);
            mediaView.setMediaPlayer(player);
            mediaView.getMediaPlayer().seek(mediaView.getMediaPlayer().getStartTime());
            mediaView.getMediaPlayer().setVolume(volume);
            mediaView.getMediaPlayer().play();
        } catch (URISyntaxException exception) {
            exception.printStackTrace();
        }
    }
}
