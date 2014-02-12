/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Math.random;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author Valoo & Nico
 */
public class Launcher extends Application {

    Group root = new Group();
    public static final String GAMES_FOLDER = "games";
    public static final String GAME_EXTENSION = ".jar";
    private final File folder = new File(Launcher.GAMES_FOLDER);
    private ArrayList<String> games = new ArrayList<String>();
    private final int SCENE_WIDTH = 500;
    private final int SCENE_HEIGHT = 500;
    private final int LEFT_WIDTH = 200;
    private final int RIGHT_WIDTH = 300;

    private void listGames() throws Exception {
        String[] list = folder.list();
        for (String game : list) {
            if (game.endsWith(Launcher.GAME_EXTENSION)) {
                this.games.add(game);
            }
        }
        
        Collections.sort(this.games);

        if (this.games.isEmpty()) {
            throw new IOException("Games folder is empty, no JAR file found");
        }

    }

    private void createButtons() {
        VBox buttonsPane = new VBox(20);
        buttonsPane.setPadding(new Insets(20));

        final BorderPane gamePane = new BorderPane();
        gamePane.setPrefSize(RIGHT_WIDTH, SCENE_HEIGHT);
        gamePane.setPadding(new Insets(20, 30, 20, 30));

        final Rectangle espace_top = new Rectangle(RIGHT_WIDTH - 2 * 30, 10, new Color(1, 1, 1, 0));
        final Rectangle espace_bottom = new Rectangle(RIGHT_WIDTH - 2 * 30, 10, new Color(1, 1, 1, 0));
        final Rectangle underline = new Rectangle(RIGHT_WIDTH - 2 * 30, 3, new Color(0, 0, 0, 0.7));

        for (final String game : this.games) {
            Button button = new Button(Launcher.getGameName(game));
            button.setMinHeight(40);
            button.setMaxWidth(Double.MAX_VALUE);

            buttonsPane.getChildren().add(button);

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String[] command = new String[]{"java", "-jar", folder.getAbsolutePath() + File.separator + game};
                    
                    Text t = new Text("The game is loading, be patient :)");
                    t.setFont(Font.font(Font.getDefault().getName(), FontWeight.BOLD, Font.getDefault().getSize()));
                    t.setFill(Color.BLUE);
                    gamePane.setBottom(t);
                    
                    try {
                        Process process = Runtime.getRuntime().exec(command, null, folder);
                        process.getInputStream().close();
                        process.getErrorStream().close();
                    } catch (IOException ex) {
                        Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
            String loadedSummary;
            try {
                loadedSummary = this.readGameInfo(game);
            } catch (IOException ex) {
                loadedSummary = "Oups, no summary was added with the game. \nYou should try " + Launcher.getGameName(game) + " to make your mind about it.";
            }
            final String summary = loadedSummary;

            button.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    VBox gameContentPane = new VBox();
                    gamePane.getChildren().removeAll(gamePane.getChildren());
                    Text content = new Text(summary);
                    content.setWrappingWidth(RIGHT_WIDTH - 2 * 30);
                    Text title = new Text(Launcher.getGameName(game));
                    title.setFont(Font.font(Font.getDefault().getName(), 20));
                    gameContentPane.getChildren().add(title);
                    gameContentPane.getChildren().add(espace_top);
                    gameContentPane.getChildren().add(underline);
                    gameContentPane.getChildren().add(espace_bottom);
                    gameContentPane.getChildren().add(content);
                    gamePane.setTop(gameContentPane);
                }
            });

            /*
             button.setOnMouseExited(new EventHandler<MouseEvent>() {
             @Override
             public void handle(MouseEvent t) {
             gamePane.getChildren().removeAll(gamePane.getChildren());
             }
             });
             */
        }

        ScrollPane buttonsScrollPane = new ScrollPane();
        buttonsScrollPane.setContent(buttonsPane);
        buttonsScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        buttonsScrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        buttonsScrollPane.setPrefSize(LEFT_WIDTH, SCENE_WIDTH);
        buttonsScrollPane.setStyle("-fx-background-color:transparent;");
        buttonsScrollPane.setFitToWidth(true);

        BorderPane parent = new BorderPane();
        parent.setLeft(buttonsScrollPane);
        parent.setRight(gamePane);
        root.getChildren().add(parent);
    }

    private void createCircles() {
        Group circles = new Group();
        for (int i = 0; i < 20; i++) {
            Circle circle = new Circle(30, Color.BISQUE);
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.BISQUE);
            circle.setStrokeWidth(4);
            circles.getChildren().add(circle);
        }
        root.getChildren().add(circles);

        Timeline timeline = new Timeline();
        for (Node circle : circles.getChildren()) {
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, // set start position at 0
                    new KeyValue(circle.translateXProperty(), random() * 800),
                    new KeyValue(circle.translateYProperty(), random() * 600)),
                    new KeyFrame(new Duration(400000), // set end position at 40s
                    new KeyValue(circle.translateXProperty(), random() * 800),
                    new KeyValue(circle.translateYProperty(), random() * 600)));
        }
        // play 40s of animation
        timeline.play();
    }

    private void createRectangles() {
        Group rectangles = new Group();
        for (int i = 0; i < 20; i++) {
            Rectangle rectangle = new Rectangle(50, 50, Color.LIGHTSKYBLUE);
            rectangle.setStrokeType(StrokeType.OUTSIDE);
            rectangle.setStroke(Color.LIGHTSKYBLUE);
            rectangle.setStrokeWidth(1);
            rectangles.getChildren().add(rectangle);
        }
        root.getChildren().add(rectangles);

        Timeline timeline = new Timeline();
        for (Node circle : rectangles.getChildren()) {
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, // set start position at 0
                    new KeyValue(circle.translateXProperty(), random() * 800),
                    new KeyValue(circle.translateYProperty(), random() * 600)),
                    new KeyFrame(new Duration(40000), // set end position at 40s
                    new KeyValue(circle.translateXProperty(), random() * 800),
                    new KeyValue(circle.translateYProperty(), random() * 600)));
        }
        // play 40s of animation
        timeline.play();
    }

    private String readGameInfo(String game) throws IOException {
        URL file = new URL("jar:file:" + folder.getAbsolutePath() + File.separator + game + "!/res/summary.txt");
        URLConnection connection = file.openConnection();
        BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bf.readLine()) != null) {
            sb.append(line).append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

    private static String getGameName(String game) {
        return game.substring(0, game.length() - Launcher.GAME_EXTENSION.length());
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            this.listGames();
        } catch (Exception ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.createCircles();
        this.createRectangles();
        this.createButtons();

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, Color.ALICEBLUE);

        primaryStage.setTitle("Games board");
        primaryStage.getIcons().add(new Image(Launcher.class.getResourceAsStream("/res/icon.png")));
        primaryStage.setResizable(false);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
