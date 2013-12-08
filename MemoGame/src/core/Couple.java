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
    private final int ID;

    public Couple(int ID) {
        this.ID = ID;
        this.left = new Card(ID);
        this.right = new Card(ID);
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
