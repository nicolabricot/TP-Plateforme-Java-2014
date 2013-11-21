/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plusmoins;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.text.Text;

/**
 *
 * @author Valoo
 */
public class StartGame extends Parent {

    public StartGame() {

        final Text text = new Text("En attente d'un click");
        SplitMenuButton m = new SplitMenuButton();
        m.relocate(50, 3);
        m.setText("Facile");
        m.getItems().addAll(new MenuItem("Facile"), new MenuItem("Moyen"), new MenuItem("Difficile"));
        m.setOnAction(new EventHandler() {
            @Override    
            public void handle(Event e) {
                System.out.println("Shutdown");
            }

            
        });
        Button button = new Button("Go !");

        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                text.setText("Cliqué : " + System.currentTimeMillis());
            }
        });
        this.getChildren().add(m);
        this.getChildren().add(button);
        this.getChildren().add(text);

    }

}
