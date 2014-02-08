/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Valoo
 */
public class Launcher extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                File projet = new File("./projet");
                String[] listeFichier = projet.list();
                for (int i = 0; i < listeFichier.length; i++) {
                    if (listeFichier[i].endsWith(".jar")) {

                        System.out.println(listeFichier[i]);// on choisit la sous chaine - les 5 derniers caracteres ".java" 
                    }
                }
                String[] command = new String[]{"java", "-jar", "../projet/PlusMoins.jar"};
                System.out.println("erreur 1 !");
                try {
                    System.out.println("erreur 2 !");
                    Process process = Runtime.getRuntime().exec(command, null, projet);
                    System.out.println("erreur 3 !");
                    process.getInputStream().close();
                    System.out.println("erreur 4 !");
                    process.getErrorStream().close();
                    System.out.println("erreur 5 !");
                } catch (IOException ex) {
                    System.out.println("erreur 6 !");
                }
                
            }

        }
        );

        StackPane root = new StackPane();

        root.getChildren()
                .add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle(
                "Hello World!");
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
