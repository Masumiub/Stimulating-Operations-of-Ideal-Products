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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author movie
 */
public class GraphicDesignerController implements Initializable {

    @FXML
    private Label userIDLabel;
    @FXML
    private TableView<NewAssignedGraphicDesigner> tableView;
   
    @FXML
    private TableColumn<NewAssignedGraphicDesigner, String> cardIDCol;
    @FXML
    private TableColumn<NewAssignedGraphicDesigner, String> firstNameCol;
    @FXML
    private TableColumn<NewAssignedGraphicDesigner, String> lastNameCol;
    @FXML
    private TableColumn<NewAssignedGraphicDesigner, String> deliveryDateCol;
    @FXML
    private TableColumn<NewAssignedGraphicDesigner, String> deliveryAddressCol;
    @FXML
    private TableColumn<NewAssignedGraphicDesigner, String> customTextCol;
    @FXML
    private TableColumn<NewAssignedGraphicDesigner, String> designerNameCol;
    
    GraphicDesigner loggedUser;
    /**
     * Initializes the controller class.
     */
    
    public void initData(User u){
        loggedUser = (GraphicDesigner)u;
        //userIDLabel.setText(inputUserID);
        userIDLabel.setText(loggedUser.getUserID());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        cardIDCol.setCellValueFactory(new PropertyValueFactory<NewAssignedGraphicDesigner, String>("purchasedCardID"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<NewAssignedGraphicDesigner, String>("clientFirstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<NewAssignedGraphicDesigner, String>("clientLastName"));
        deliveryDateCol.setCellValueFactory(new PropertyValueFactory<NewAssignedGraphicDesigner, String>("deliveryDate"));
        deliveryAddressCol.setCellValueFactory(new PropertyValueFactory<NewAssignedGraphicDesigner, String>("deliveryAddress"));
        customTextCol.setCellValueFactory(new PropertyValueFactory<NewAssignedGraphicDesigner, String>("clientCustomText"));
        designerNameCol.setCellValueFactory(new PropertyValueFactory<NewAssignedGraphicDesigner, String>("assignedDesignerName"));
        
        LoadNewDesignerAssignedTaskList();
    }    

    /*public void initData(String inputUserID){
        userIDLabel.setText(inputUserID);
    }*/
    @FXML
    private void SignOutOnClick(ActionEvent event) throws IOException {
        
        Parent client2Parent = FXMLLoader.load(getClass().getResource("MainLoginScene.fxml"));
        Scene home = new Scene(client2Parent);
             
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(home);
        window.show();
    }
    /*private void meetingScheduleList(){
        ObjectInputStream ois=null;
        try {
            MeetingSchedule meeting;
            ois = new ObjectInputStream(new FileInputStream("meetingSchedulelist.bin"));
            while(true){
            meeting = (MeetingSchedule) ois.readObject();
            meetScheduleTableView.getItems().add(0, meeting);
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
    */
    private void LoadNewDesignerAssignedTaskList(){
        ObjectInputStream ois=null;
        try {
            NewAssignedGraphicDesigner newdesignerTask;
            ois = new ObjectInputStream(new FileInputStream("assignedGraphicDesignerList.bin"));
            while(true){
            newdesignerTask = (NewAssignedGraphicDesigner) ois.readObject();
            //if(newdesignerTask.getAssignedDesignerName().equals(loggedUser.getUserFullName())){
                tableView.getItems().add(0, newdesignerTask);
            //}
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
    private void makeWorkScheduleOnClick(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("WorkScheduleScene.fxml"));
            Parent homeScene3 = loader.load();
            Scene homepage3 = new Scene(homeScene3);
            WorkScheduleSceneController controller = loader.getController();
                 //System.out.println(u.getUserFullName());
            controller.initData(loggedUser);
            Stage window3 = (Stage)((Node)event.getSource()).getScene().getWindow();
            window3.setScene(homepage3);
            window3.show();
        /*Parent workScheduleParent = FXMLLoader.load(getClass().getResource("WorkScheduleScene.fxml"));
        Scene workScheduleViewScene = new Scene(workScheduleParent);
        Stage newWindow  = new Stage();
        
        newWindow.setTitle("Work Schedule Form For Designer");
        newWindow.setScene(workScheduleViewScene);
        newWindow.show();*/
    }

    @FXML
    private void assignDeliveryManOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AssignDeliveryManScene.fxml"));
        Parent AssignADeliveryMan = loader.load();
    
        
        Scene AssignAdeliverymanScene = new Scene(AssignADeliveryMan);
        
        //access the controller
        AssignDeliveryManSceneController controller = loader.getController();
        controller.initData(tableView.getSelectionModel().getSelectedItem(), loggedUser);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(AssignAdeliverymanScene);
        window.show();
    }

    @FXML
    private void showMeetingScheduleOnClick(ActionEvent event) throws IOException {
        Parent meetParent = FXMLLoader.load(getClass().getResource("MeetingScheduleScene.fxml"));
        Scene meetingViewScene = new Scene(meetParent);
        Stage newWindow  = new Stage();
        
        newWindow.setTitle("Meeting Schedule");
        newWindow.setScene(meetingViewScene);
        newWindow.show();
    }

    @FXML
    private void showMeetingOnClick(ActionEvent event) throws IOException {
        Parent meetParent = FXMLLoader.load(getClass().getResource("MeetingScheduleScene.fxml"));
        Scene meetingViewScene = new Scene(meetParent);
        Stage newWindow  = new Stage();
        
        newWindow.setTitle("Meeting Schedule");
        newWindow.setScene(meetingViewScene);
        newWindow.show();
    }

    @FXML
    private void showFAQsOnClick(ActionEvent event) throws IOException {
        Parent meetParent = FXMLLoader.load(getClass().getResource("ShowFAQsScene.fxml"));
        Scene meetingViewScene = new Scene(meetParent);
        Stage newWindow  = new Stage();
        
        newWindow.setTitle("FAQs");
        newWindow.setScene(meetingViewScene);
        newWindow.show();
    }

    @FXML
    private void closeAppOnClick(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }
    
}
