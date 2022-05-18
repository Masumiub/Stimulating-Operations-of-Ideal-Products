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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author movie
 */
public class ReviewLeaveApplicationSceneController implements Initializable {
    private LeaveApplyForm selectedApplication;
    @FXML
    private ComboBox approvalStatusComboBox;
    @FXML
    private Label employeeNameLabel;
    @FXML
    private Label postlabel;
    @FXML
    private Label leaveStartDateLabel;
    @FXML
    private Label leaveEndDateLabel;
    @FXML
    private Label detailslabel;

    /**
     * Initializes the controller class.
     */
    public void initData(LeaveApplyForm L){
        selectedApplication = L;
        employeeNameLabel.setText(selectedApplication.getEmployeeFullName());
        postlabel.setText(selectedApplication.getEmployeeDesignation());
        leaveStartDateLabel.setText(selectedApplication.getLeaveStartDate());
        leaveEndDateLabel.setText(selectedApplication.getLeaveEndDate());
        detailslabel.setText(selectedApplication.getDetails());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        approvalStatusComboBox.getItems().addAll("Approved", "Rejected");
        approvalStatusComboBox.setValue("Approved");
    }    

    @FXML
    private void approveLeaveApplicationOnClick(ActionEvent event) throws IOException {
        File f = null;
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try {
          
            f = new File("reviewedLeaveApplicationDList.bin");
            if(f.exists()) {
                fos = new FileOutputStream(f,true);
                oos=new AppendableObjectOutputStream(fos);
            }
            else{
                fos=new FileOutputStream(f);
                oos=new ObjectOutputStream(fos);
            }
            
            ReivewedLeaveApplication newReviewApp = new ReivewedLeaveApplication(
               employeeNameLabel.getText(), postlabel.getText(), leaveStartDateLabel.getText(), leaveEndDateLabel.getText(),
                    detailslabel.getText(),(String)approvalStatusComboBox.getSelectionModel().getSelectedItem()
               );
            
            oos.writeObject(newReviewApp);
            
            
            } catch (IOException ex) {
            Logger.getLogger(ReviewLeaveApplicationSceneController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(oos != null) oos.close();
                } catch (IOException ex) {
                Logger.getLogger(ReviewLeaveApplicationSceneController.class.getName()).log(Level.SEVERE, null, ex);
          }
                
      }
        Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Success! Your Application review is Done!");
                a.showAndWait();
                
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("AdminDashBoard.fxml"));
        Scene home = new Scene(scene2Parent);
            
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(home);
        window.show();
    }

    @FXML
    private void cancelApprovalOnClick(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("AdminDashBoard.fxml"));
        Scene home = new Scene(scene2Parent);
            
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(home);
        window.show();
    }
    
}
