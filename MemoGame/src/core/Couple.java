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
public class Couple {

    private Card left;
    private Card right;
    private Memo game;

    public Couple(int value, Memo game) {
        this.game = game;
        this.left = new Card(value, this);
        this.right = new Card(value, this);
    }
  
    public void checkedCard() {
        if (this.left.state().equals(Card.STATE.VISIBLE) && this.right.state().equals(Card.STATE.VISIBLE)) {
            // both card are visible, we have found a couple :)
            this.left.updateState(Card.STATE.MATCHED);
            this.right.updateState(Card.STATE.MATCHED);
            this.game.coupleFound();
        }
    }
    
    public boolean acceptVisibleCard() {
        return this.game.acceptVisibleCard();
    }

    public Card left() {
        return this.left;
    }

    public Card right() {
        return this.right;
    }
}
