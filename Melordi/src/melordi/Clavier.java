/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package melordi;



import javafx.scene.Parent;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Valoo
 */
public class Clavier extends Parent {
    
    public Clavier(){
        
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
        
        Touche[] touches = new Touche[]{
            new Touche("U",50,20,60),
            new Touche("I",128,20,62),
            new Touche("O",206,20,64),
            new Touche("P",284,20,65),
            new Touche("J",75,98,67),
            new Touche("K",153,98,69),
            new Touche("L",231,98,71),
            new Touche("M",309,98,72)
        };
        
        for (Touche touche: touches){
            this.getChildren().add(touche);
                    
        }
    }
}
