/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core;



import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.effect.Reflection;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Valériane JEAN
 *          3A IR
 */
public class Clavier extends Parent {
    
    private Touche[] touches;
    private Instruments instrument;
    
    public Clavier(Instruments inst){
        
        this.instrument = inst;
        
        Rectangle fond = new Rectangle();
        fond.setWidth(400);
        fond.setHeight(200);
        fond.setArcHeight(30);
        fond.setArcWidth(30);
        fond.setFill(
                new LinearGradient(0f, 0f, 0f, 1f, true, CycleMethod.NO_CYCLE, 
                        new Stop[]{
                            new Stop(0,Color.web("#333333")),
                            new Stop(1,Color.web("#000000"))
                        }
                )
        );
        Reflection r = new Reflection();
        r.setFraction(0.25);
        r.setBottomOpacity(0);
        r.setTopOpacity(0.5);
        fond.setEffect(r);
        
        this.setTranslateX(50);
        this.setTranslateY(250);
        this.getChildren().add(fond);
        
        this.touches = new Touche[]{
            new Touche("Do - u",50,20,60, this.instrument),
            new Touche("Re - i",128,20,62, this.instrument),
            new Touche("Mi - o",206,20,64, this.instrument),
            new Touche("Fa - p",284,20,65, this.instrument),
            new Touche("Sol - j",75,98,67, this.instrument),
            new Touche("La - k",153,98,69, this.instrument),
            new Touche("Si - l",231,98,71, this.instrument),
            new Touche("Do - m",309,98,72, this.instrument)
        };
        
        for (Touche touche: touches){
            this.getChildren().add(touche);                   
        }
        
        this.setOnKeyPressed(new EventHandler<KeyEvent>(){
            public void handle(KeyEvent ke){
                for (Touche touche: touches){
                    if( touche.lettre.equals( ke.getText().toUpperCase() ) )
                        touche.appuyer();
                }
            }
        });
        this.setOnKeyReleased(new EventHandler<KeyEvent>(){
            public void handle(KeyEvent ke){
                for (Touche touche: touches){
                    if(touche.lettre.equals( ke.getText().toUpperCase() ) )
                        touche.relacher();
                }
            }
        });
    }
}
