/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plusmoins;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 * @author Valériane JEAN 3A IR
 */
public class Game extends Parent {

    private int counter;
    private int mysteriousNumber;
    protected int userNumber;
    protected TextField tf;
    protected Button buttonP;
    protected Button buttonM;
    protected Button buttonOK;
    protected String level;
    protected boolean win;
    protected Text textIndic;
    protected Text textSol;

    public Game(String level) {
        this.userNumber = 0;
        this.level = level;
        this.setMysteriousNumber();
        this.win = false;
        this.counter = 0;
        this.addButton();
        // this.games();
    }

    public void setMysteriousNumber() {
        switch (this.level) {
            case "Facile":
                this.mysteriousNumber = (int) (Math.random() * 10);
                System.out.println(this.mysteriousNumber);
                break;
            case "Moyen":
                this.mysteriousNumber = (int) (Math.random() * 100);
                System.out.println(this.mysteriousNumber);
                break;
            case "Difficile":
                this.mysteriousNumber = (int) (Math.random() * 1000);
                System.out.println(this.mysteriousNumber);
                break;
            default:
                System.out.println("I'm sorry but the system seems to have some problems");
        }
    }

    public void addButton() {

        this.textIndic = new Text("Veillez saisir un nombre");
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
        this.getChildren().add(this.tf);
        this.getChildren().add(this.textSol);
        this.getChildren().add(this.textIndic);
        this.getChildren().add(this.buttonP);
        this.getChildren().add(this.buttonM);
        this.getChildren().add(this.buttonOK);

        this.buttonP.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if (level == "Facile" && userNumber <= 9) {
                    userNumber++;
                    tf.setText(Integer.toString(userNumber));
                }
                if (level == "Moyen" && userNumber <= 99) {
                    userNumber++;
                    tf.setText(Integer.toString(userNumber));
                }
                if (level == "Difficile" && userNumber <= 999) {
                    userNumber++;
                    tf.setText(Integer.toString(userNumber));
                }

            }
        });
        this.buttonM.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if (userNumber >= 1) {
                    userNumber--;
                    tf.setText(Integer.toString(userNumber));
                }

            }
        });
        this.tf.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                tf.setText(Integer.toString(userNumber));
            }
        });
        this.buttonOK.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if (userNumber == mysteriousNumber) {
                    textSol.setText("Bravo ! Vous avez trouvé le nombre mystere");
                    getChildren().remove(tf);
                    getChildren().remove(textIndic);
                    getChildren().remove(buttonM);
                    getChildren().remove(buttonOK);
                    getChildren().remove(buttonP);
                    setwin();
                    
                } else {
                    if (level == "Facile" && counter <= 4) {
                        counter ++;
                        int tmp = 5 - counter;
                        textSol.setText("Non ce n'est pas ca recommencez ! Attention il vous reste : " + tmp + " coups." );
                    }
                    if (level == "Moyen" && counter <= 9) {
                        counter ++;
                        int tmp = 10 - counter;
                        textSol.setText("Non ce n'est pas ca recommencez ! Attention il vous reste : " + tmp + " coups." );
                    }
                    if (level == "Difficile" && counter <= 9) {
                        counter ++;
                        int tmp = 10 - counter;
                        textSol.setText("Non ce n'est pas ca recommencez ! Attention il vous reste : " + tmp + " coups." );
                    }

                }
            }
        });
    }

    public void setwin() {
        if (this.win == true) {
            this.win = false;
        } else {
            this.win = true;
            try {
                        Thread.sleep(30000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    getChildren().remove(textSol);
        }
    }

    public void games() {
        Scanner sc = new Scanner(System.in);
        while (!win && this.counter < 5) {
            int nbUser = sc.nextInt();
            if (nbUser == this.mysteriousNumber) {
                this.win = true;
                this.textSol.setText("Bravo vous avez trouvé le nombre mystère en " + this.counter);
            } else if (nbUser < this.mysteriousNumber) {
                this.textSol.setText("C'est plus !");
            } else {
                this.textSol.setText("C'est Moins !");
            }

            this.counter++;
        }
        System.out.println("Désolé ! Vous n'avez pas trouvé à temps le nombre mystère.");
    }
}
