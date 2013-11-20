/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plusmoins;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Valériane JEAN
 *          3A IR
 */
public class PlusMoins extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       
        Group root = new Group();
        Scene scene = new Scene(root, 500,300,Color.CORAL);
        
        Circle circle = new Circle();
        circle.setCenterX(200);
        circle.setCenterY(100);
        circle.setRadius(20);
        circle.setFill(Color.BEIGE);
        
        Rectangle rect = new Rectangle();
        rect.setX(190);
        rect.setY(105);
        rect.setWidth(20);
        rect.setHeight(80);
        rect.setFill(Color.RED);
        
        
        root.getChildren().add(circle);
        root.getChildren().add(rect);
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
