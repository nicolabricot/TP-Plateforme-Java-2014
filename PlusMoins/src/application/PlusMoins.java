package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Valériane JEAN
 *          3A IR
 */
public class PlusMoins extends Application {
    
    

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox();
        InterfGame start = new InterfGame();

        root.getChildren().add(start);
        Scene scene = new Scene(root, 500, 300);
        stage.setScene(scene);
        stage.show();
    }
}
