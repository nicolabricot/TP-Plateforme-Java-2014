/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pom;

/**
 *
 * @author Valoo
 */
public class NewClass {
    
    public NewClass(){
        
    }
    
    public static void main(String[] args) {
        POM pom = new POM(1);
        pom.setMysteriousNumber();
        System.out.println(pom.getMysteriousNumber());
        pom.games();
    }
    
}
