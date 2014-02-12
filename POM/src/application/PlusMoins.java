package application;

import core.Game;
import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuBuilder;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuItemBuilder;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Valériane JEAN 3A IR
 */
public class PlusMoins extends Application {

    private static final double WIDTH = 450, HEIGHT = 450;
    private Timeline animation;

    private void init(Stage primaryStage) {
        final Group root = new Group();
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        Group layer1 = new Group();
        for (int i = 0; i < 15; i++) {
            Circle circle = new Circle(200, Color.web("white", 0.05f));
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.web("white", 0.2f));
            circle.setStrokeWidth(4f);
            layer1.getChildren().add(circle);
        }
        // create second list of circles
        Group layer2 = new Group();
        for (int i = 0; i < 20; i++) {
            Circle circle = new Circle(70, Color.web("white", 0.05f));
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.web("white", 0.1f));
            circle.setStrokeWidth(2f);
            layer2.getChildren().add(circle);
        }
        // create third list of circles
        Group layer3 = new Group();
        for (int i = 0; i < 10; i++) {
            Circle circle = new Circle(150, Color.web("white", 0.05f));
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.web("white", 0.16f));
            circle.setStrokeWidth(4f);
            layer3.getChildren().add(circle);
        }
        // Set a blur effect on each layer
        layer1.setEffect(new BoxBlur(30, 30, 3));
        layer2.setEffect(new BoxBlur(2, 2, 2));
        layer3.setEffect(new BoxBlur(10, 10, 3));
        // create a rectangle size of window with colored gradient
        Rectangle colors = new Rectangle(WIDTH, HEIGHT,
                new LinearGradient(0f, 1f, 1f, 0f, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#f8bd55")),
                        new Stop(0.14f, Color.web("#c0fe56")),
                        new Stop(0.28f, Color.web("#5dfbc1")),
                        new Stop(0.43f, Color.web("#64c2f8")),
                        new Stop(0.57f, Color.web("#be4af7")),
                        new Stop(0.71f, Color.web("#ed5fc2")),
                        new Stop(0.85f, Color.web("#ef504c")),
                        new Stop(1, Color.web("#f2660f")))
        );
        colors.setBlendMode(BlendMode.OVERLAY);
        // create main content
        Group group = new Group(
                new Rectangle(WIDTH, HEIGHT, Color.BLACK),
                layer1,
                layer2,
                layer3,
                colors
        );
        Rectangle clip = new Rectangle(WIDTH, HEIGHT);
        clip.setSmooth(false);
        group.setClip(clip);
        root.getChildren().add(group);
        // create list of all circles
        List<Node> allCircles = new ArrayList<Node>();
        allCircles.addAll(layer1.getChildren());
        allCircles.addAll(layer2.getChildren());
        allCircles.addAll(layer3.getChildren());
        // Create a animation to randomly move every circle in allCircles
        animation = new Timeline();
        for (Node circle : allCircles) {
            animation.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, // set start position at 0s
                            new KeyValue(circle.translateXProperty(), random() * WIDTH),
                            new KeyValue(circle.translateYProperty(), random() * HEIGHT)
                    ),
                    new KeyFrame(new Duration(40000), // set end position at 40s
                            new KeyValue(circle.translateXProperty(), random() * WIDTH),
                            new KeyValue(circle.translateYProperty(), random() * HEIGHT)
                    )
            );
        }
        animation.setAutoReverse(true);
        animation.setCycleCount(Animation.INDEFINITE);

        MenuBar menuBar = new MenuBar();

        MenuItem menu111 = MenuItemBuilder.create().text("Facile").build();
        MenuItem menu112 = MenuItemBuilder.create().text("Moyen").build();
        MenuItem menu113 = MenuItemBuilder.create().text("Difficile").build();
        Menu menu11 = MenuBuilder.create().text("New Game...").items(menu111, menu112, menu113).build();
        MenuItem menu12 = MenuItemBuilder.create().text("Exit").build();
        Menu menu1 = MenuBuilder.create().text("Menu").items(menu11, menu12).build();
        menuBar.getMenus().addAll(menu1);
        root.getChildren().addAll(menuBar);

        menu111.setOnAction(new EventHandler<ActionEvent>() {
            private Text textIndic;
            private Text textSol;
            private TextField tf;
            private Button buttonP;
            private Button buttonM;
            private Button buttonOK;

            @Override
            public void handle(ActionEvent t) {
                final Game game = new Game("Facile");

                this.textIndic = new Text("Choisi un nombre :");
                this.textIndic.relocate(75, 180);
                this.textIndic.setScaleX(1.25);
                this.textIndic.setScaleY(1.25);
                this.textSol = new Text("");
                this.textSol.relocate(150, 270);
                this.textSol.setScaleX(1.25);
                this.textSol.setScaleY(1.25);
                this.tf = new TextField();
                this.tf.setText("0");
                this.tf.relocate(110, 210);
                this.buttonP = new Button("+");
                this.buttonP.relocate(70, 210);
                this.buttonM = new Button("-");
                this.buttonM.relocate(260, 210);
                this.buttonOK = new Button("OK ?");
                this.buttonOK.relocate(150, 250);

                //actions des boutons  
                this.buttonP.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {
                        if (game.userNumber <= 9) {
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

                            root.getChildren().remove(tf);
                            root.getChildren().remove(textIndic);
                            root.getChildren().remove(buttonM);
                            root.getChildren().remove(buttonOK);
                            root.getChildren().remove(buttonP);
                            root.getChildren().remove(textSol);
                            game.setwin();

                        } else {
                            if (game.counter == 1) {
                                textSol.setText("Oh ... Tu as perdu !");
                            }
                            textSol.setText(game.inGame());

                        }
                    }
                });

                root.getChildren().add(this.tf);
                root.getChildren().add(this.textSol);
                root.getChildren().add(this.textIndic);
                root.getChildren().add(this.buttonP);
                root.getChildren().add(this.buttonM);
                root.getChildren().add(this.buttonOK);
            }
        });

        menu112.setOnAction(new EventHandler<ActionEvent>() {
            private Text textIndic;
            private Text textSol = new Text();
            private TextField tf;
            private Button buttonP;
            private Button buttonM;
            private Button buttonOK;

            @Override
            public void handle(ActionEvent t) {
                final Game game = new Game("Facile");

                this.textIndic = new Text("Choisi un nombre :");
                this.textIndic.relocate(75, 180);
                this.textIndic.setScaleX(1.25);
                this.textIndic.setScaleY(1.25);
                this.textSol.setText("Aller c'est parti !");
                this.textSol.relocate(150, 270);
                this.textSol.setScaleX(1.25);
                this.textSol.setScaleY(1.25);
                this.tf = new TextField();
                this.tf.setText("0");
                this.tf.relocate(110, 210);
                this.buttonP = new Button("+");
                this.buttonP.relocate(70, 210);
                this.buttonM = new Button("-");
                this.buttonM.relocate(260, 210);
                this.buttonOK = new Button("OK ?");
                this.buttonOK.relocate(150, 250);

                //actions des boutons  
                this.buttonP.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {

                        if (game.userNumber <= 99) {
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

                            //root.getChildren().remove(tf);
                            root.getChildren().remove(textIndic);
                            root.getChildren().remove(buttonM);
                            root.getChildren().remove(buttonOK);
                            root.getChildren().remove(buttonP);
                            root.getChildren().remove(textSol);
                            game.setwin();

                        } else {
                            if (game.counter <= 1) {
                                textSol.setText("Oh ... Tu as perdu !");

                            }
                            textSol.setText(game.inGame());

                        }
                    }
                });

                root.getChildren().add(this.tf);
                root.getChildren().add(this.textSol);
                root.getChildren().add(this.textIndic);
                root.getChildren().add(this.buttonP);
                root.getChildren().add(this.buttonM);
                root.getChildren().add(this.buttonOK);
            }
        });

        menu113.setOnAction(new EventHandler<ActionEvent>() {
            private Text textIndic;
            private Text textSol;
            private TextField tf;
            private Button buttonP;
            private Button buttonM;
            private Button buttonOK;

            @Override
            public void handle(ActionEvent t) {
                final Game game = new Game("Facile");

                this.textIndic = new Text("Choisi un nombre :");
                this.textIndic.relocate(75, 180);
                this.textIndic.setScaleX(1.25);
                this.textIndic.setScaleY(1.25);
                this.textSol = new Text("");
                this.textSol.relocate(150, 270);
                this.textSol.setScaleX(1.25);
                this.textSol.setScaleY(1.25);
                this.tf = new TextField();
                this.tf.setText("0");
                this.tf.relocate(110, 210);
                this.buttonP = new Button("+");
                this.buttonP.relocate(70, 210);
                this.buttonM = new Button("-");
                this.buttonM.relocate(260, 210);
                this.buttonOK = new Button("OK ?");
                this.buttonOK.relocate(150, 250);

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

                            root.getChildren().remove(tf);
                            root.getChildren().remove(textIndic);
                            root.getChildren().remove(buttonM);
                            root.getChildren().remove(buttonOK);
                            root.getChildren().remove(buttonP);
                            root.getChildren().remove(textSol);
                            game.setwin();

                        } else {
                            if (game.inGame() == "Oh tu as perdu ! ") {
                                root.getChildren().remove(tf);
                                root.getChildren().remove(textIndic);
                                root.getChildren().remove(buttonM);
                                root.getChildren().remove(buttonOK);
                                root.getChildren().remove(buttonP);
                            }
                            textSol.setText(game.inGame());

                        }
                    }
                });

                root.getChildren().add(this.tf);
                root.getChildren().add(this.textSol);
                root.getChildren().add(this.textIndic);
                root.getChildren().add(this.buttonP);
                root.getChildren().add(this.buttonM);
                root.getChildren().add(this.buttonOK);
            }
        });

        menu12.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                System.exit(0);
            }
        });
    }

    @Override
    public void stop() {
        animation.stop();
    }

    public void play() {
        animation.play();
    }

    public double getSampleWidth() {
        return 495;
    }

    public double getSampleHeight() {
        return 480;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
        play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
