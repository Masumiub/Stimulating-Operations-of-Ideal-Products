/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpkg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author movie
 */
public class SendFeedbackSceneController implements Initializable {

    @FXML
    private DatePicker feedbackSubmitDateField;
    @FXML
    private TextArea feedbackDetailsTextArea;
    @FXML
    private TextField clientFullNameTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void submitFeedbackOnClick(ActionEvent event) {
        if(clientFullNameTextField.getText().equals("") || feedbackSubmitDateField.getValue().equals("") || 
                feedbackDetailsTextArea.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Sorry! Please fillup required fields.");
            a.showAndWait();
        }
        else{
            /*
            model class method call
            boolean isFeedbackSent = loggedUser.SendFeedback(clientFullNameTextField.getText(), feedbackSubmitDateField.getValue().toString(), feedbackDetailsTextArea.getText());
            
            if(isFeedbackSent){
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("Thanks for your valuable feedback.");
                    a.showAndWait();
            }
            else{
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Sorry! Please fillup required fields.");
                a.showAndWait();
            }
            */
        File f = null;
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try {
          
            f = new File("clientFeedbackList.bin");
            if(f.exists()) {
                fos = new FileOutputStream(f,true);
                oos=new AppendableObjectOutputStream(fos);
            }
            else{
                fos=new FileOutputStream(f);
                oos=new ObjectOutputStream(fos);
            }
            
            Feedback newFeedback = new Feedback(
               clientFullNameTextField.getText(), feedbackSubmitDateField.getValue().toString(), feedbackDetailsTextArea.getText()
               );
            
            oos.writeObject(newFeedback);
           feedbackDetailsTextArea.clear();
           
            } catch (IOException ex) {
            Logger.getLogger(SendFeedbackSceneController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(oos != null) oos.close();
                } catch (IOException ex) {
                Logger.getLogger(SendFeedbackSceneController.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Thanks for your valuable feedback.");
        a.showAndWait();
    }
    }
    
}
