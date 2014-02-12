/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import core.Couple;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Nicolas Devenet <nicolas@devenet.info>
 */
public class Card extends javax.swing.JPanel {
    
    public enum STATE {
        HIDDEN, VISIBLE, MATCHED
    }

    private final int value;
    private STATE state;
    private final ImageIcon backImage, headsImage;
    
    private Couple couple;

    /**
     * Creates new form GameBoard
     */
    public Card(int value, Couple couple) {
        this.value = value;
        this.couple = couple;
        this.state = STATE.HIDDEN;
        initComponents();
        this.backImage = new ImageIcon(this.getClass().getClassLoader().getResource("res/back.png"));
        this.headsImage = new ImageIcon(this.getClass().getClassLoader().getResource("res/" + value + ".png"));
        //this.myLabel.setText(Integer.toString(value));
    }
    
    public void updateState(STATE state) {
        if (this.state != STATE.MATCHED) {
            this.state = state;
            this.repaint();
            Logger.getLogger(Card.class.getName()).log(Level.INFO, this+" updated to state "+this.state);
        }
    }
    
    public STATE state() {
        return this.state;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.state != STATE.HIDDEN) {
            g.drawImage(this.headsImage.getImage(), 0, 0, this);
        } else {
            g.drawImage(this.backImage.getImage(), 0, 0, this);
        }
    }

    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder("Card(")
                .append(value)
                .append(")");
        return tmp.toString();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        myLabel = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(100, 100));
        setPreferredSize(new java.awt.Dimension(100, 100));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        myLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(myLabel)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(89, Short.MAX_VALUE)
                .addComponent(myLabel)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        Logger.getLogger(Card.class.getName()).log(Level.INFO, this+" clicked on state "+this.state);
        if (!this.state.equals(STATE.HIDDEN)) { return; }
        if (this.couple.acceptVisibleCard()) {
            this.updateState(STATE.VISIBLE);
            this.couple.checkedCard();
        }
    }//GEN-LAST:event_formMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel myLabel;
    // End of variables declaration//GEN-END:variables
}
