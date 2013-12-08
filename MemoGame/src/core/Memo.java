/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import gui.Card;

/**
 *
 * @author Nicolas Devenet <nicolas@devenet.info>
 */
public class Memo {

    public enum LEVEL {
        Novice, Normal, Intermediary, Expert
    }
    private final LEVEL level;
    private final int rows;
    private final int cols;
    private Couple[] couples;
    private Card[] cards;

    public Memo(LEVEL level) throws Exception {
        switch (level) {
            case Novice:
                this.rows = 2;
                this.cols = 3;
                break;
                
            case Normal:
                this.rows = 4;
                this.cols = 5;
                break;
                
            case Intermediary:
                this.rows = 6;
                this.cols = 6;
                break;
                
            case Expert:
                this.rows = 6;
                this.cols = 8;
                break;
                
            default:
                throw new Exception("Unrecognized level");
        }
        this.level = level;

        // create couple with two "same" cards
        this.couples = new Couple[this.numberCouples()];
        for (int i = 0; i < this.numberCouples(); i++) {
            this.couples[i] = new Couple(i + 1);
        }
        // put cards in random position
        int numberCards = this.numberCards();
        this.cards = new Card[numberCards];
        for (Couple c : this.couples) {
            int left_position = (int) (Math.random() * (numberCards));
            int right_position = (int) (Math.random() * (numberCards));
            while (this.cards[left_position] != null) { left_position = (int) (Math.random() * (numberCards)); }
            this.cards[left_position] = c.left();
            while (this.cards[right_position] != null) { right_position = (int) (Math.random() * (numberCards)); }
            this.cards[right_position] = c.right();
        }
    }

    public int rows() {
        return this.rows;
    }

    public int cols() {
        return this.cols;
    }

    public final int numberCards() {
        return this.rows * this.cols;
    }

    public final int numberCouples() {
        return this.numberCards() / 2;
    }
    
    public Card[] cards() {
        return this.cards;
    }
}
