/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author Valériane JEAN 3A IR
 */
public class Game {

    public int counter;
    public int mysteriousNumber;
    public int userNumber;
    public String level;
    public boolean win;

    public Game(String level) {
        this.userNumber = 0;
        this.level = level;
        this.setMysteriousNumber();
        this.win = false;
        this.counter = 0;

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
    
  

    public String inGame() {
        if (this.level.equals("Facile") && this.counter <= 4 && this.userNumber < this.mysteriousNumber) {
            counter++;
            int tmp = 5 - counter;
            return "C'est plus ! Attention il vous reste : " + tmp + " coups.";
        }
        if (this.level.equals("Facile") && this.counter <= 4 && this.userNumber > this.mysteriousNumber) {
            counter++;
            int tmp = 5 - counter;
            return "C'est moins ! Attention il vous reste : " + tmp + " coups.";
        }
        if (this.level.equals("Moyen") && this.counter <= 9 && this.userNumber < this.mysteriousNumber) {
            counter++;
            int tmp = 10 - counter;
            return "C'est plus ! Attention il vous reste : " + tmp + " coups.";
        }
        if (this.level.equals("Moyen") && counter <= 9 && userNumber > mysteriousNumber) {
            counter++;
            int tmp = 10 - counter;
            return "C'est moins ! Attention il vous reste : " + tmp + " coups.";
        }
        if (this.level.equals("Difficile") && counter <= 9 && userNumber < mysteriousNumber) {
            counter++;
            int tmp = 10 - counter;
            return "C'est plus ! Attention il vous reste : " + tmp + " coups.";
        }
        if (this.level.equals("Difficile") && counter <= 9 && userNumber > mysteriousNumber) {
            counter++;
            int tmp = 10 - counter;
            return "C'est moins ! Attention il vous reste : " + tmp + " coups.";
        }
        return "Oh tu as perdu ! ";
    }

    public void setwin() {
        if (!this.win ) {
            this.win = true;
        } else {
            this.win = false;
        }
    }

    
}
