/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpkg;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author movie
 */
public class ShowEmployeeLeaveApplicationSceneController implements Initializable {

    @FXML
    private TableView<LeaveApplyForm> leaveApplicationTableView;
    @FXML
    private TableColumn<LeaveApplyForm, String> employeeNameCol;
    @FXML
    private TableColumn<LeaveApplyForm, String> postCol;
    @FXML
    private TableColumn<LeaveApplyForm, String> applicationTopicCol;
    @FXML
    private TableColumn<LeaveApplyForm, String> startingDateCol;
    @FXML
    private TableColumn<LeaveApplyForm, String> endingDateCol;
    @FXML
    private TableColumn<LeaveApplyForm, String> detailsCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        employeeNameCol.setCellValueFactory(new PropertyValueFactory<LeaveApplyForm, String>("employeeFullName"));
        postCol.setCellValueFactory(new PropertyValueFactory<LeaveApplyForm, String>("employeeDesignation"));
        applicationTopicCol.setCellValueFactory(new PropertyValueFactory<LeaveApplyForm, String>("applicationSubject"));
        startingDateCol.setCellValueFactory(new PropertyValueFactory<LeaveApplyForm, String>("leaveStartDate"));
        endingDateCol.setCellValueFactory(new PropertyValueFactory<LeaveApplyForm, String>("leaveEndDate"));
        detailsCol.setCellValueFactory(new PropertyValueFactory<LeaveApplyForm, String>("details"));
        loadLeaveApplicationList();
    }    
    
    private void loadLeaveApplicationList(){
        ObjectInputStream ois=null;
        try {
            LeaveApplyForm leaveApplication;
            ois = new ObjectInputStream(new FileInputStream("leaveApplicationOFDeliveryManlist.bin"));
            while(true){
            leaveApplication = (LeaveApplyForm) ois.readObject();
            leaveApplicationTableView.getItems().add(leaveApplication);
            }   
        }catch (Exception ex) {
            try {
                if(ois!=null)
                    ois.close();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        }
    }

    @FXML
    private void reviewApplicationOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ReviewLeaveApplicationScene.fxml"));
        Parent reviewApplicationViewParent = loader.load();
    
        Scene reviewAppLeaveViewScene = new Scene(reviewApplicationViewParent);

        ReviewLeaveApplicationSceneController controller = loader.getController();
        controller.initData(leaveApplicationTableView.getSelectionModel().getSelectedItem());
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(reviewAppLeaveViewScene);
        window.show();
    }

    @FXML
    private void goBacktoMainSceneOnClick(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("AdminDashBoard.fxml"));
        Scene home = new Scene(scene2Parent);   
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(home);
        window.show();
    }
    
}
