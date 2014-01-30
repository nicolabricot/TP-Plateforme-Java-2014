/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import core.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 * @author Valériane JEAN 3A IR
 */
public class InterfGame extends Parent {

    public InterfGame() {

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

            protected TextField tf;
            protected Button buttonP;
            protected Button buttonM;
            protected Button buttonOK;
            protected Text textIndic;
            protected Text textSol;

            @Override
            public void handle(ActionEvent e) {

                //initialisation du jeu
                final Game game = new Game(m.getText());

                //initialisation des boutons
                this.textIndic = new Text("Choisi un nombre");
                this.textIndic.relocate(150, 25);
                this.textSol = new Text("");
                this.textSol.relocate(150, 170);
                this.tf = new TextField();
                this.tf.setText("0");
                this.tf.relocate(150, 80);
                this.buttonP = new Button("+");
                this.buttonP.relocate(150, 50);
                this.buttonM = new Button("-");
                this.buttonM.relocate(150, 110);
                this.buttonOK = new Button("OK ?");
                this.buttonOK.relocate(150, 140);

                //actions des boutons  
                this.buttonP.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {
                        if (game.level.equals("Facile") && game.userNumber <= 9) {
                            game.userNumber++;
                            tf.setText(Integer.toString(game.userNumber));
                        }
                        if (game.level.equals("Moyen") && game.userNumber <= 99) {
                            game.userNumber++;
                            tf.setText(Integer.toString(game.userNumber));
                        }
                        if (game.level.equals("Difficile") && game.userNumber <= 999) {
                            game.userNumber++;
                            tf.setText(Integer.toString(game.userNumber));
                        }
                    }
                });
                this.buttonM.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent e) {
                        if (game.userNumber >= 1) {
                            game.userNumber--;
                            tf.setText(Integer.toString(game.userNumber));
                        }

                    }
                });
                this.tf.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent e) {
                        game.userNumber = Integer.parseInt(tf.getText());
                    }
                });
                this.buttonOK.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent e) {
                        if (game.userNumber == game.mysteriousNumber) {
                            textSol.setText("Bravo ! Vous avez trouvé le nombre mystere");
                            
                            getChildren().remove(tf);
                            getChildren().remove(textIndic);
                            getChildren().remove(buttonM);
                            getChildren().remove(buttonOK);
                            getChildren().remove(buttonP);
                            getChildren().remove(textSol);
                            game.setwin();

                        } else {
                            textSol.setText(game.inGame());

                        }
                    }
                });

                
                getChildren().add(this.tf);
                getChildren().add(this.textSol);
                getChildren().add(this.textIndic);
                getChildren().add(this.buttonP);
                getChildren().add(this.buttonM);
                getChildren().add(this.buttonOK);

            }
        });
        this.getChildren().add(m);
        this.getChildren().add(button);
        this.getChildren().add(text);

    }

}
