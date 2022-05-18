/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpkg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author movie
 */
public class ShowFAQsSceneController implements Initializable {

    @FXML
    private TextArea faqsTextArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
  
    /*public void loadFile() throws FileNotFoundException{
        faqsTextArea.clear();
        File f = null;
        Scanner sc; 
        String str; 
        //String[] tokens;
        try {
            f = new File("FAQs.txt");
            sc = new Scanner(f);
            if(f.exists()){
                while(sc.hasNextLine()){
                    str=sc.nextLine();
                    faqsTextArea.setText(str);
                }
               
            }
        } 
        catch (IOException ex) {
            Logger.getLogger(ShowFAQsSceneController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {
        }
    //}*/

    @FXML
    private void ShowFAQsOnClick(ActionEvent event) {
        faqsTextArea.clear();
        File f = null;
        //Scanner sc; 
        //String str; 
        //String[] tokens;
        try {
            f = new File("FAQs.txt");
            /*sc = new Scanner(f);
            if(f.exists()){
                faqsTextArea.setText();
                while(sc.hasNextLine()){
                    str=sc.nextLine();
                    
                }
               
            }*/
            Scanner sc = new Scanner(f);
                String str="";
                while(sc.hasNextLine()){
                    str+=sc.nextLine()+"\n";
                }
                faqsTextArea.setText(str);
        } 
        catch (IOException ex) {
            Logger.getLogger(ShowFAQsSceneController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {
        }
    }
    
}
