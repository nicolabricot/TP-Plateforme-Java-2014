/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package melordi;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

/**
 *
 * @author Valoo
 */
public class Instruments {
    
    private Synthesizer synthetiseur;
    private MidiChannel canal;
    
    public int volume = 100;
    
    public Instruments(){
        try{
            //récupération, ouverture et obtention du canal
            synthetiseur = MidiSystem.getSynthesizer();
            synthetiseur.open();
        }catch (MidiUnavailableException ex){
            Logger.getLogger(Instruments.class.getName()).log(Level.SEVERE,null,ex);
        }
        canal = synthetiseur.getChannels()[0];
        
        //initialisation de l'instrument 0 = piano
        canal.programChange(0);
    }
    
    public void noteOn(int note){
        canal.noteOn(note, volume);
    }
    
    public void noteOff(int note){
        canal.noteOff(note);
    }
    
    public void setInstrument(int instrument){
        canal.programChange(instrument);
    }
}
