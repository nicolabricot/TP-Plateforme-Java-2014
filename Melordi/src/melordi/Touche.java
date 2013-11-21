/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package melordi;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Valoo
 */
public class Touche extends Parent {
    
    public String lettre;
    private int positionX = 0;
    private int positionY = 0;
    private int note = 0;
    private Instruments instrument;
    
    Rectangle fond;
    Text lettreTouche;
    
    public Touche(String l, int posX, int posY, int n,Instruments inst){
        this.lettre =  new String(l);
        this.positionX = posX;
        this.positionY = posY;
        this.note = n;
        this.instrument = inst;
        
        fond = new Rectangle(75,75,Color.WHITE);
        fond.setArcHeight(10);
        fond.setArcWidth(10);
        this.getChildren().add(fond);//ajout du rectangle de fond de la touche
        
        lettreTouche = new Text(lettre);
        lettreTouche.setFont(new Font(25));
        lettreTouche.setFill(Color.GREY);
        lettreTouche.setX(25);
        lettreTouche.setY(45);
        this.getChildren().add(lettreTouche);//ajout de la lettre de la touche
        
        this.setTranslateX(positionX);//positionnement de la touche sur le clavier
        this.setTranslateY(positionY);
        
        this.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
            fond.setFill(Color.LIGHTGREY);
            }
        });
        
        this.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
            fond.setFill(Color.WHITE);
            }
        });
        
        this.setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                appuyer();
            }
        });
        this.setOnMouseReleased(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                relacher();
            }
        });

    }
    
    public void appuyer(){
        fond.setFill(Color.DARKGREY);
        this.setTranslateY(this.positionY+2);
        this.instrument.noteOn(note);
    }
    
    public void relacher(){
         fond.setFill(Color.WHITE);
        this.setTranslateY(this.positionY);
        this.instrument.noteOff(note);
    }
}
