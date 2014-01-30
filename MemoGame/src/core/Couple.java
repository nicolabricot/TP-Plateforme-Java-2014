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
    private final int value;

    public Couple(int value) {
        this.value = value;
        this.left = new Card(value);
        this.right = new Card(value);
    }

    public boolean matched(Card left, Card right) {
        return (left.equals(this.left) && right.equals(this.right)) || (left.equals(this.right) && right.equals(this.left));
    }

    public Card left() {
        return this.left;
    }

    public Card right() {
        return this.right;
    }
}
