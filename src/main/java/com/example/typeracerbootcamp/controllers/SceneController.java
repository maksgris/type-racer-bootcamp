package com.example.typeracerbootcamp.controllers;

import com.example.typeracerbootcamp.Links.FileLink;
import com.example.typeracerbootcamp.Links.ServerLink;
import com.example.typeracerbootcamp.Source;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
    public static ServerLink link;

    @FXML
    private MediaView mediaView;

    @FXML
    private Slider soundSlider;

    @FXML
    Label myLabel;

    @FXML
    Label LabelNick;
    static Label static_LabelNick;

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
        if(link==null){
            link = new ServerLink();
            FileLink file = new FileLink("temp.txt",true);
            file.read();
            try {
                if (file.out()[0].equals(null) && file.out()[1].equals(null)) {
                    System.out.println("[DEBUG] file values are null...");
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
                } else {
                    System.out.println("[DEBUG] file values are not null!");
                    String temp = link.logUser(file.out()[0], file.out()[1]);
                    Source.injectNick(temp);
                    SceneController.setnick(temp);
                }
            }catch (RuntimeException ex){
                System.out.println("[DEBUG] file exception: " + ex.getMessage());
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
        }else{
            System.out.println("[DEBUG] allegedly full success!");
            link.onlinePlayController();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        static_LabelNick = LabelNick;
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
        setnick("someone that should log in");
    }
    @FXML
    public static void setnick(String nick){
        static_LabelNick.setText("Welcome "+nick +"!");
        System.out.println("[DEBUG] Nick set of LabelNick to " + nick);
    }
}