/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import gui.Card;
import gui.Game;

/**
 *
 * @author Nicolas Devenet <nicolas@devenet.info>
 */
public class Memo {

    public enum LEVEL {
        Easy, Normal, Hard, Expert
    }

    public static boolean isLevel(LEVEL level) {
        for (LEVEL l : LEVEL.values()) {
            if (l.equals(level)) {
                return true;
            }
        }
        return false;
    }
    private final int rows;
    private final int cols;
    private Couple[] couples;
    private Card[] cards;
    private int currentVisibleCards = 0;
    private final int maximumVisibleCards = 2;
    private Game gameBoard;
    private int couplesFound = 0;

    public Memo(LEVEL level, Game gameBoard) {
        this.gameBoard = gameBoard;
        switch (level) {
            case Normal:
                this.rows = 4;
                this.cols = 5;
                break;

            case Hard:
                this.rows = 6;
                this.cols = 6;
                break;

            case Expert:
                this.rows = 6;
                this.cols = 8;
                break;

            case Easy:
            default:
                this.rows = 2;
                this.cols = 3;
                break;
        }

        // create couple with two "same" cards
        this.couples = new Couple[this.numberCouples()];
        for (int i = 0; i < this.numberCouples(); i++) {
            this.couples[i] = new Couple(i + 1, this);
        }
        // put cards in random position
        int numberCards = this.numberCards();
        this.cards = new Card[numberCards];
        for (Couple c : this.couples) {
            int left_position = (int) (Math.random() * (numberCards));
            int right_position = (int) (Math.random() * (numberCards));
            while (this.cards[left_position] != null) {
                left_position = (int) (Math.random() * (numberCards));
            }
            this.cards[left_position] = c.left();
            while (this.cards[right_position] != null) {
                right_position = (int) (Math.random() * (numberCards));
            }
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

    public boolean acceptVisibleCard() {
        if (this.currentVisibleCards < this.maximumVisibleCards) {
            this.currentVisibleCards++;
            return true;
        }
        // we need to hidden again the two visible cards
        for (Card c : this.cards) {
            c.updateState(Card.STATE.HIDDEN);
        }
        this.currentVisibleCards = 0;
        return false;
    }

    public void coupleFound() {
        this.currentVisibleCards = 0;
        this.couplesFound++;
        this.isGameEnded();
    }

    private void isGameEnded() {
        if (this.couplesFound == this.numberCouples()) {
            this.gameBoard.gameIsEnded();
        }
    }
    
    public void cardHitten() {
        this.gameBoard.cardHitten();
    }
}
