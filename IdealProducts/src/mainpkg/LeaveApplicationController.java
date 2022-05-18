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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author movie
 */
public class LeaveApplicationController implements Initializable {

    @FXML
    private TextField employeeNameTextField;
    @FXML
    private TextField designationTextField;
    @FXML
    private TextField applicationSubjectTextField;
    @FXML
    private DatePicker leaveStartDateTextField;
    @FXML
    private TextField detailsTextField;
    @FXML
    private DatePicker leaveEndDateTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void applyForLeaveOnClick(ActionEvent event) throws IOException {
        if(employeeNameTextField.getText().equals("") || applicationSubjectTextField.getText().equals("")
                || leaveStartDateTextField.getValue().equals("") || leaveEndDateTextField.getValue().equals("") ||
                detailsTextField.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Sorry. Please fillup the required form for leave application!");
            a.showAndWait();
            
        }
        else{
        File f = null;
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try {
          
            f = new File("leaveApplicationOFDeliveryManlist.bin");
            if(f.exists()) {
                fos = new FileOutputStream(f,true);
                oos=new AppendableObjectOutputStream(fos);
            }
            else{
                fos=new FileOutputStream(f);
                oos=new ObjectOutputStream(fos);
            }
            
            LeaveApplyForm newLeaveApplication = new LeaveApplyForm(
               employeeNameTextField.getText(), 
               designationTextField.getText(),
               applicationSubjectTextField.getText(), 
               leaveStartDateTextField.getValue().toString(),
               leaveEndDateTextField.getValue().toString(),
               detailsTextField.getText()     
               );
            
            oos.writeObject(newLeaveApplication);
           
            } catch (IOException ex) {
            Logger.getLogger(LeaveApplicationController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(oos != null) oos.close();
                } catch (IOException ex) {
                Logger.getLogger(LeaveApplicationController.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Thanks! Your Leave Application is Submitted. Please wait for Approval.");
        a.showAndWait();
        
    }
    }
}
