/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pom;

import static java.lang.Math.random;
import java.util.Scanner;

/**
 *
 * @author Valoo
 */
public class POM {
    
    protected int mysteriousNumber;
    protected int userChoicNumber;
    protected int level;
    protected int compteur;
    protected boolean win;

    public POM(int level){
        this.level = level;
        this.win = false;
        this.compteur = 1;
    }
    //method for mysterious number's creation
    public void setMysteriousNumber(){
        switch(this.level){
            case 0:
                this.mysteriousNumber = (int) (Math.random() * 10 );
                break;
            case 1:
                this.mysteriousNumber = (int) (Math.random() * 100 );
                break;
            case 2:
                this.mysteriousNumber = (int) (Math.random() * 1000 );
                break;
            default:
                System.out.println("I'm sorry but the system seems to have some problems");
        }            
    }
    
    public void games(){
        Scanner sc = new Scanner(System.in);
        while(!win && this.compteur < 5){
            System.out.println("Veuillez saisir un nombre :");
            int nbUser = sc.nextInt();
            if(nbUser == this.mysteriousNumber){
             this.win = true;
                System.out.println("Bravo vous avez trouvé le nombre mystère en "+this.compteur);
            }
            else if(nbUser < this.mysteriousNumber)
                System.out.println("C'est plus !");
            else System.out.println("C'est Moins !");
            
            this.compteur ++ ;
        }
        System.out.println("Désolé ! Vous n'avez pas trouvé à temps le nombre mystère.");
    }
    
    // access to mysterious number 
    public int getMysteriousNumber(){
        return this.mysteriousNumber;        
    }
    
    public int getUserChoicNumber() {
        return userChoicNumber;
    }

    public void setUserChoicNumber(int userChoicNumber) {
        this.userChoicNumber = userChoicNumber;
    }
}
