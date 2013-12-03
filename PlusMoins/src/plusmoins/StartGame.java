/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plusmoins;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.text.Text;

/**
 *
 * @author Valériane JEAN
 *          3A IR
 */
public class StartGame extends Parent {

    public StartGame() {

        final Text text = new Text("Choisi ton niveau de difficulté :");
        text.relocate(0, 5);
        final SplitMenuButton m = new SplitMenuButton();
        m.relocate(170, 5);

        MenuItem item1 = new MenuItem("Facile");
        MenuItem item2 = new MenuItem("Moyen");
        MenuItem item3 = new MenuItem("Difficile");

        m.getItems().addAll(item1, item2, item3);

        item1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                m.setText("Facile");
            }
        });

        item2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                m.setText("Moyen");
            }
        });
        
        item3.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                m.setText("Difficile");
            }
        });
        
        Button button = new Button("Go !");
        button.relocate(270, 5);

        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                Game game = new Game(m.getText());
                getChildren().add(game);

            }
        });
        this.getChildren().add(m);
        this.getChildren().add(button);
        this.getChildren().add(text);

    }

}
