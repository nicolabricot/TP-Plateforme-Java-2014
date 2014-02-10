/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher;

import java.io.File;
import java.io.IOException;
import static java.lang.Math.random;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Valoo
 */
public class Launcher extends Application {

    Group root = new Group();

    public void createButton() {
        
        FlowPane pane = new FlowPane(Orientation.VERTICAL);
        pane.setPadding(new Insets(20));
        pane.setVgap(20);
        pane.setHgap(20);
       
        Label label = new Label("Jeux disponible :");
        pane.getChildren().add(label);
        
        final File projet = new File("./projet");       
        final String[] listeFichier = projet.list();
        
        for (int i = 0; i < listeFichier.length; i++) {
            if (listeFichier[i].endsWith(".jar")) {
                final String fichier = listeFichier[i];
                
                Button button = new Button(listeFichier[i].substring(0, listeFichier[i].length() - 4));
                button.setMinSize(100, 50);
                
                pane.getChildren().add(button);

                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String[] command = new String[]{"java", "-jar", "../projet/" + fichier};
                        try {
                            Process process = Runtime.getRuntime().exec(command, null, projet);
                            process.getInputStream().close();
                            process.getErrorStream().close();
                        } catch (IOException ex) {
                            System.out.println(ex);
                        }
                    }
                }
                );
            }
        }
        root.getChildren().add(pane);
    }

    public void createCircles() {
        Group circles = new Group();
        for (int i = 0; i < 20; i++) {
            Circle circle = new Circle(10, Color.BISQUE);
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.BISQUE);
            circle.setStrokeWidth(4);
            circles.getChildren().add(circle);
        }
        root.getChildren().add(circles);       

        Timeline timeline = new Timeline();
        for (Node circle : circles.getChildren() ) {
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, // set start position at 0
                            new KeyValue(circle.translateXProperty(), random() * 800),
                            new KeyValue(circle.translateYProperty(), random() * 600)
                    ),
                    new KeyFrame(new Duration(400000), // set end position at 40s
                            new KeyValue(circle.translateXProperty(), random() * 800),
                            new KeyValue(circle.translateYProperty(), random() * 600)
                    )
            );
        }
        // play 40s of animation
        timeline.play();
    }
    
    public void createRectangles(){
        Group rectangles = new Group();
        for (int i = 0; i < 20; i++) {
            Rectangle rectangle = new Rectangle(5, 10, Color.BROWN);
            rectangle.setStrokeType(StrokeType.OUTSIDE);
            rectangle.setStroke(Color.BROWN);
            rectangle.setStrokeWidth(4);
            rectangles.getChildren().add(rectangle);
        }
        root.getChildren().add(rectangles);
        
         Timeline timeline = new Timeline();
        for (Node circle : rectangles.getChildren() ) {
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, // set start position at 0
                            new KeyValue(circle.translateXProperty(), random() * 800),
                            new KeyValue(circle.translateYProperty(), random() * 600)
                    ),
                    new KeyFrame(new Duration(40000), // set end position at 40s
                            new KeyValue(circle.translateXProperty(), random() * 800),
                            new KeyValue(circle.translateYProperty(), random() * 600)
                    )
            );
        }
        // play 40s of animation
        timeline.play();
    }

    @Override
    public void start(Stage primaryStage) {

        this.createCircles();
        this.createRectangles();
        this.createButton();
        Scene scene = new Scene(root, 500, 500, Color.ALICEBLUE);

        primaryStage.setTitle("Hello World!");
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
