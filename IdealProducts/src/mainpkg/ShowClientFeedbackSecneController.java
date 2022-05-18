/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpkg;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author movie
 */
public class ShowClientFeedbackSecneController implements Initializable {

    @FXML
    private TextArea outputTextArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loadFeedbackOnClick(ActionEvent event) {
        
        outputTextArea.setText("");
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        
        try {
            f = new File("clientFeedbackList.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Feedback newFeed;
            try{
                outputTextArea.setText("");
                while(true){
                    newFeed = (Feedback)ois.readObject();
                    outputTextArea.appendText(newFeed.toString());
                }
            }
            catch(Exception e){
                
            }    
                    
        } catch (IOException ex) { } 
        finally {
            try {
                if(ois != null) ois.close();
            } catch (IOException ex) { }
        }
    }
    
}
