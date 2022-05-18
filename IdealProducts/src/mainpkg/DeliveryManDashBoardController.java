/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpkg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author movie
 */
public class DeliveryManDashBoardController implements Initializable {

    @FXML
    private TableView<NewAssignedDeliveryMan> tableView;
    @FXML
    private TableColumn<NewAssignedDeliveryMan, String> cardIDCol;
    @FXML
    private TableColumn<NewAssignedDeliveryMan, String> firstNameCol;
    @FXML
    private TableColumn<NewAssignedDeliveryMan, String> lastNameCol;
    @FXML
    private TableColumn<NewAssignedDeliveryMan, String> deliveryDateCol;
    @FXML
    private TableColumn<NewAssignedDeliveryMan, String> deliveryAddressCol;
    @FXML
    private TableColumn<NewAssignedDeliveryMan, String> customStatusCol;
    @FXML
    private TableColumn<NewAssignedDeliveryMan, String> deliveryManNameCol;
    @FXML
    private TextField postTextField;
    @FXML
    private Label userIDLabel;
    @FXML
    private TextField deliveryManNameTextField;
    @FXML
    private DatePicker workingDateTextField;
    @FXML
    private ComboBox attendanceStatusComboBox;
    @FXML
    private TableView<ReivewedLeaveApplication> leaveApplicationTableView;
    @FXML
    private TableColumn<ReivewedLeaveApplication, String> empNameCol;
    @FXML
    private TableColumn<ReivewedLeaveApplication, String> postCol;
    @FXML
    private TableColumn<ReivewedLeaveApplication, String> leaveStartDateCol;
    @FXML
    private TableColumn<ReivewedLeaveApplication, String> leaveEndDateCol;
    @FXML
    private TableColumn<ReivewedLeaveApplication, String> detailsCol;
    @FXML
    private TableColumn<ReivewedLeaveApplication, String> statusCol;
    @FXML
    private RadioButton clearRadioBtn;
    @FXML
    private RadioButton notClearRadioBtn;
    @FXML
    private ComboBox deliveryStatusComboBox;
    @FXML
    private Label cardIDLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label deliveryDatelabel;
    @FXML
    private Label deliveryBylabel;
    @FXML
    private Label paymentStatusLabel;
    
    private ToggleGroup tg;
    /**
     * Initializes the controller class.
     */
    DeliveryMan loggedUser;
    public void initData(User u){
        loggedUser = (DeliveryMan)u;
        //userIDLabel.setText(inputUserID);
        userIDLabel.setText(loggedUser.getUserID());
        deliveryManNameTextField.setText(loggedUser.getUserFullName());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tg = new ToggleGroup();
        clearRadioBtn.setToggleGroup(tg);
        notClearRadioBtn.setToggleGroup(tg);
        
        clearRadioBtn.setSelected(true);
        paymentStatusLabel.setText("Clear");
        
        LoadAssignedDeliveryTaskList();
        cardIDCol.setCellValueFactory(new PropertyValueFactory<NewAssignedDeliveryMan, String>("cardID"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<NewAssignedDeliveryMan, String>("clientFirstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<NewAssignedDeliveryMan, String>("clientLastName"));
        deliveryDateCol.setCellValueFactory(new PropertyValueFactory<NewAssignedDeliveryMan, String>("deliveryDate"));
        deliveryAddressCol.setCellValueFactory(new PropertyValueFactory<NewAssignedDeliveryMan, String>("deliveryAddress"));
        customStatusCol.setCellValueFactory(new PropertyValueFactory<NewAssignedDeliveryMan, String>("customizationStatus"));
        deliveryManNameCol.setCellValueFactory(new PropertyValueFactory<NewAssignedDeliveryMan, String>("deliveryManName"));
        
        attendanceStatusComboBox.getItems().addAll("Present", "Absent");
        attendanceStatusComboBox.setValue("Present");
        
        LoadReivewedLeaveApplication();
        empNameCol.setCellValueFactory(new PropertyValueFactory<ReivewedLeaveApplication, String>("employeeName"));
        postCol.setCellValueFactory(new PropertyValueFactory<ReivewedLeaveApplication, String>("post"));
        leaveStartDateCol.setCellValueFactory(new PropertyValueFactory<ReivewedLeaveApplication, String>("leaveStartDate"));
        leaveEndDateCol.setCellValueFactory(new PropertyValueFactory<ReivewedLeaveApplication, String>("leaveEndDate"));
        detailsCol.setCellValueFactory(new PropertyValueFactory<ReivewedLeaveApplication, String>("details"));
        statusCol.setCellValueFactory(new PropertyValueFactory<ReivewedLeaveApplication, String>("approvalStatus"));
        
        deliveryStatusComboBox.getItems().addAll("Delivered", "Not Delivered");
        deliveryStatusComboBox.setValue("Delivered");
        
    }    
    
    /*public void initData(String inputUserID){
        userIDLabel.setText(inputUserID);
    }*/
    private void LoadReivewedLeaveApplication(){
        ObjectInputStream ois=null;
        try {
            ReivewedLeaveApplication app;
            ois = new ObjectInputStream(new FileInputStream("reviewedLeaveApplicationDList.bin"));
            while(true){
            app = (ReivewedLeaveApplication) ois.readObject();
            leaveApplicationTableView.getItems().add(0, app);
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
    private void LoadAssignedDeliveryTaskList(){
        ObjectInputStream ois=null;
        try {
            NewAssignedDeliveryMan d;
            ois = new ObjectInputStream(new FileInputStream("assignedDeliveryManList.bin"));
            while(true){
            d = (NewAssignedDeliveryMan) ois.readObject();
            tableView.getItems().add(0, d);
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
    private void SignOutOnClick(ActionEvent event) throws IOException {
        Parent client2Parent = FXMLLoader.load(getClass().getResource("MainLoginScene.fxml"));
        Scene home = new Scene(client2Parent);     
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(home);
        window.show();
    }

    @FXML
    private void applyForLeaveOnClick(ActionEvent event) throws IOException {
        Parent leaveApplicationParent = FXMLLoader.load(getClass().getResource("LeaveApplication.fxml"));
        Scene leaveApplicationViewScene = new Scene(leaveApplicationParent);
        Stage newWindow  = new Stage();
        
        newWindow.setTitle("Leave Application Form For Delivery Man");
        newWindow.setScene(leaveApplicationViewScene);
        newWindow.show();
    }
    
    private int isAttendanceAlreadySubmitted(String workingDate){
        int found = 0;
        
        ObjectInputStream ois=null;
        try {
            AttendaceOfDeliveryMan a;
            ois = new ObjectInputStream(new FileInputStream("attendanceListofDeliveryMan.bin"));
            while(true){
            a = (AttendaceOfDeliveryMan) ois.readObject();
            if(a.getWorkingDate().equals(workingDate)){
                found++;
                break;
            }
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
        return found;
    }
    @FXML
    private void submitAttendanceOnClick(ActionEvent event) {
        if(isAttendanceAlreadySubmitted(workingDateTextField.getValue().toString())>0){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Sorry! Your Attendance for today is already submitted!");
            a.showAndWait();
        }
        else{
        File f = null;
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try {
          
            f = new File("attendanceListofDeliveryMan.bin");
            if(f.exists()) {
                fos = new FileOutputStream(f,true);
                oos=new AppendableObjectOutputStream(fos);
            }
            else{
                fos=new FileOutputStream(f);
                oos=new ObjectOutputStream(fos);
            }
            
            AttendaceOfDeliveryMan newAttendance = new AttendaceOfDeliveryMan(
               deliveryManNameTextField.getText(),
               postTextField.getText(), 
               workingDateTextField.getValue().toString(),
               (String)attendanceStatusComboBox.getSelectionModel().getSelectedItem()
               );
            
            oos.writeObject(newAttendance);
            //deliveryManNameTextField.clear();
            
            
            } catch (IOException ex) {
            Logger.getLogger(OperationManagerDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(oos != null) oos.close();
                } catch (IOException ex) {
                Logger.getLogger(OperationManagerDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
        
        Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Thanks! Your Attendance for today is submitted!");
            a.showAndWait();
      }
    }

    

    @FXML
    private void getDeliveryInfoOnClick(ActionEvent event) {
        cardIDLabel.setText(tableView.getSelectionModel().getSelectedItem().getCardID());
        firstNameLabel.setText(tableView.getSelectionModel().getSelectedItem().getClientFirstName());
        lastNameLabel.setText(tableView.getSelectionModel().getSelectedItem().getClientLastName());
        deliveryDatelabel.setText(tableView.getSelectionModel().getSelectedItem().getDeliveryDate());
        deliveryBylabel.setText(tableView.getSelectionModel().getSelectedItem().getDeliveryManName());
        deliveryStatusComboBox.getSelectionModel().getSelectedItem();
        
    }
    
    public int isDelivered(String cardID){
        int found=0;
        ObjectInputStream ois=null;
        try {
            DeliveredCard c;
            ois = new ObjectInputStream(new FileInputStream("deliveredCardList.bin"));
            while(true){
            c = (DeliveredCard) ois.readObject();
            if(c.getDeliveredCardID().equals(cardID)){
                found++;
                break;
            }
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
        return found;
    }
    @FXML
    private void deliveryConfirmationOnClick(ActionEvent event) {
        /*boolean isConfirmed  = loggedUser.ConfirmDelivery(cardIDLabel.getText(), firstNameLabel.getText(), lastNameLabel.getText(), deliveryDatelabel.getText(),
                 deliveryBylabel.getText(), paymentStatusLabel.getText(), (String)deliveryStatusComboBox.getSelectionModel().getSelectedItem());
        
        if(isConfirmed){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Thanks! Your New Delivered Card was Added to the File.");
            a.showAndWait();
        }
        else{
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Sorry! Your New Delivered Card is unable to Add to the File.");
            a.showAndWait();
        }*/
        if( isDelivered(cardIDLabel.getText())>0 ){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Sorry!This Card is already delivered!");
            a.showAndWait();
        }
        else{
        File f = null;
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try {
          
            f = new File("deliveredCardList.bin");
            if(f.exists()) {
                fos = new FileOutputStream(f,true);
                oos=new AppendableObjectOutputStream(fos);
            }
            else{
                fos=new FileOutputStream(f);
                oos=new ObjectOutputStream(fos);
            }
            
            DeliveredCard newDeliveredCard = new DeliveredCard(
               cardIDLabel.getText(), firstNameLabel.getText(), lastNameLabel.getText(), deliveryDatelabel.getText(),
                 deliveryBylabel.getText(), paymentStatusLabel.getText(), (String)deliveryStatusComboBox.getSelectionModel().getSelectedItem() 
               );
            
            oos.writeObject(newDeliveredCard);
          
            } catch (IOException ex) {
            Logger.getLogger(OperationManagerDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(oos != null) oos.close();
                } catch (IOException ex) {
                Logger.getLogger(OperationManagerDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      Alert a = new Alert(Alert.AlertType.INFORMATION);
      a.setContentText("Thanks!This Card is added delivered card list!");
      a.showAndWait();
      }
    }

    @FXML
    private void radioButtonOnClick(ActionEvent event) {
        if(clearRadioBtn.isSelected()){
            paymentStatusLabel.setText("Clear");
        }
        else if(notClearRadioBtn.isSelected()){
            paymentStatusLabel.setText("Not Clear");
        }
    }

    @FXML
    private void showLeaveApplicationformOnClick(ActionEvent event) throws IOException {
        Parent leaveApplicationParent = FXMLLoader.load(getClass().getResource("LeaveApplication.fxml"));
        Scene leaveApplicationViewScene = new Scene(leaveApplicationParent);
        Stage newWindow  = new Stage();
        
        newWindow.setTitle("Leave Application Form For Delivery Man");
        newWindow.setScene(leaveApplicationViewScene);
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
