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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author movie
 */
public class AdminDashBoardController implements Initializable {
    
    Admin loggedUser;
    @FXML
    private Label userIDLabel;
    @FXML
    private TextField meetingTopicTextField;
    @FXML
    private DatePicker meetingDateTextField;
    @FXML
    private TextField meetingTimeTextField;
    private TableView<LeaveApplyForm> leaveApplicationTableView;
    private TableColumn<LeaveApplyForm, String> employeeNameCol;
    private TableColumn<LeaveApplyForm, String> postCol;
    private TableColumn<LeaveApplyForm, String> applicationTopicCol;
    private TableColumn<LeaveApplyForm, String> startingDateCol;
    private TableColumn<LeaveApplyForm, String> endingDateCol;
    private TableColumn<LeaveApplyForm, String> detailsCol;
    private TextField userIDTextField;
    private TextField passwordTextField;
    private TextField userType;
    private TextField deliuserIDTextField;
    private TextField delipasswordTextField;
    private TextField deliuserType;
    private TextField fullNameTextField;
    private TextField delifullNameTextField;
    private TextField gduserIDTextField;
    private TextField gdpasswordTextField;
    private TextField gduserType;
    private TextField gdfullNameTextField;
    private TextField opuserIDTextField;
    private TextField oppasswordTextField;
    private TextField opuserType;
    private TextField opfullNameTextField;
    @FXML
    private MenuItem signOutMenuItem;
    @FXML
    private Label statusLabel;
    @FXML
    private PieChart pieChart;
    @FXML
    private Button loadPieChartButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        /*employeeNameCol.setCellValueFactory(new PropertyValueFactory<LeaveApplyForm, String>("employeeFullName"));
        postCol.setCellValueFactory(new PropertyValueFactory<LeaveApplyForm, String>("employeeDesignation"));
        applicationTopicCol.setCellValueFactory(new PropertyValueFactory<LeaveApplyForm, String>("applicationSubject"));
        startingDateCol.setCellValueFactory(new PropertyValueFactory<LeaveApplyForm, String>("leaveStartDate"));
        endingDateCol.setCellValueFactory(new PropertyValueFactory<LeaveApplyForm, String>("leaveEndDate"));
        detailsCol.setCellValueFactory(new PropertyValueFactory<LeaveApplyForm, String>("details"));*/
        
        //loadLeaveApplicationList();
    }    
    
    public void initData(User u){
        loggedUser = (Admin)u;
        //userIDLabel.setText(inputUserID);
        userIDLabel.setText(loggedUser.getUserID());
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
    private void SignOutOnClick(ActionEvent event) throws IOException {
        Parent client2Parent = FXMLLoader.load(getClass().getResource("MainLoginScene.fxml"));
        Scene home = new Scene(client2Parent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(home);
        window.show();
    }

    @FXML
    private void setScheduleForMeetingOnClick(ActionEvent event) {
        if(meetingTopicTextField.getText().equals("") || meetingDateTextField.getValue().equals("") 
                ||meetingTimeTextField.getText().equals("")){
            
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Sorry! Please fillup the required Form! ");
            a.showAndWait();
        }
        else{
            
            /*
            boolean isMeetingScheduleAdded = loggedUser.makeMeetingSchedule(meetingTopicTextField.getText(), 
               meetingDateTextField.getValue().toString(),
               meetingTimeTextField.getText());
            
            if(isMeetingScheduleAdded){
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Thanks! Your New Meeting Schedule is Added.");
                a.showAndWait();
            }
            else{
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Sorry! Please fillup the required Form! ");
                a.showAndWait();
            }
            */
            File f = null;
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try {
          
            f = new File("meetingSchedulelist.bin");
            if(f.exists()) {
                fos = new FileOutputStream(f,true);
                oos=new AppendableObjectOutputStream(fos);
            }
            else{
                fos=new FileOutputStream(f);
                oos=new ObjectOutputStream(fos);
            }
            
            MeetingSchedule newMeetingSchedule = new MeetingSchedule(
               meetingTopicTextField.getText(), 
               meetingDateTextField.getValue().toString(),
               meetingTimeTextField.getText()     
               );
            
            oos.writeObject(newMeetingSchedule);
            meetingTopicTextField.clear();
            meetingTimeTextField.clear();
            
            } catch (IOException ex) {
            Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(oos != null) oos.close();
                } catch (IOException ex) {
                Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Thanks! Your New Meeting Schedule is Added.");
        a.showAndWait();
        }
        
    }

    @FXML
    private void reviewApplicationOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ShowEmployeeLeaveApplicationScene.fxml"));
        Parent reviewApplicationViewParent = loader.load();
    
        Scene reviewAppLeaveViewScene = new Scene(reviewApplicationViewParent);

        //ReviewLeaveApplicationSceneController controller = loader.getController();
        //controller.initData(leaveApplicationTableView.getSelectionModel().getSelectedItem());
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(reviewAppLeaveViewScene);
        window.show();
    }

    @FXML
    private void showSalaryOnClick(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("SalaryOfEmployeeScene.fxml"));
        Scene home = new Scene(scene2Parent);
            
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(home);
        window.show();
    }

    private void addUserOnClick(ActionEvent event) {
        
        File f = null;
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try {
          
            f = new File("User.bin");
            if(f.exists()) {
                fos = new FileOutputStream(f,true);
                oos=new AppendableObjectOutputStream(fos);
            }
            else{
                fos=new FileOutputStream(f);
                oos=new ObjectOutputStream(fos);
            }
            //User u;
            User u= new Admin(userIDTextField.getText(), passwordTextField.getText(),userType.getText(), fullNameTextField.getText());
            
            oos.writeObject(u);
           
            } catch (IOException ex) {
            Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(oos != null) oos.close();
                } catch (IOException ex) {
                Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    
}

    private void addDeliveryManUserOnClick(ActionEvent event) {
        
        File f = null;
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try {
          
            f = new File("User.bin");
            if(f.exists()) {
                fos = new FileOutputStream(f,true);
                oos=new AppendableObjectOutputStream(fos);
            }
            else{
                fos=new FileOutputStream(f);
                oos=new ObjectOutputStream(fos);
            }
            //User u;
            User u= new DeliveryMan(deliuserIDTextField.getText(), delipasswordTextField.getText(),deliuserType.getText(), delifullNameTextField.getText());
            
            oos.writeObject(u);
           
            } catch (IOException ex) {
            Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(oos != null) oos.close();
                } catch (IOException ex) {
                Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void addGraphicDesignerUserOnClick(ActionEvent event) {
        File f = null;
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try {
          
            f = new File("User.bin");
            if(f.exists()) {
                fos = new FileOutputStream(f,true);
                oos=new AppendableObjectOutputStream(fos);
            }
            else{
                fos=new FileOutputStream(f);
                oos=new ObjectOutputStream(fos);
            }
            //User u;
            User u= new GraphicDesigner(gduserIDTextField.getText(), gdpasswordTextField.getText(),gduserType.getText(), gdfullNameTextField.getText());
            
            oos.writeObject(u);
           
            } catch (IOException ex) {
            Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(oos != null) oos.close();
                } catch (IOException ex) {
                Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void addOpManagerUserOnClick(ActionEvent event) {
        File f = null;
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try {
          
            f = new File("User.bin");
            if(f.exists()) {
                fos = new FileOutputStream(f,true);
                oos=new AppendableObjectOutputStream(fos);
            }
            else{
                fos=new FileOutputStream(f);
                oos=new ObjectOutputStream(fos);
            }
            //User u;
            User u= new OperationManager(opuserIDTextField.getText(), oppasswordTextField.getText(),opuserType.getText(), opfullNameTextField.getText());
            
            oos.writeObject(u);
           
            } catch (IOException ex) {
            Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(oos != null) oos.close();
                } catch (IOException ex) {
                Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void showSellsOverViewOnClick(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("SellsOverViewScene.fxml"));
        Scene home = new Scene(scene2Parent);
            
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(home);
        window.show();
    }

    private void SignOutFromAdminOnClick(ActionEvent event) throws IOException {
        System.out.println("Signing out");
        /*Parent root = FXMLLoader.load(getClass().getResource("SalaryOfEmployeeScene.fxml"));
        Scene home = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(home);
        window.show();*/
        
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

    public int countCardsSells(){
        int count=0;
        ObjectInputStream ois=null;
        try {
            Purchase p;
            ois = new ObjectInputStream(new FileInputStream("purchaseList.bin"));
            while(true){
            p = (Purchase) ois.readObject();
            if(p.getPurchasedCardType().equals("Eid Card"))
                 count++;
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
        return count;
    }
    public int countCardsSells2(){
        int count=0;
        ObjectInputStream ois=null;
        try {
            Purchase p;
            ois = new ObjectInputStream(new FileInputStream("purchaseList.bin"));
            while(true){
            p = (Purchase) ois.readObject();
            if(p.getPurchasedCardType().equals("Ramadan Card"))
                 count++;
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
        return count;
    }
    public int countCardsSells3(){
        int count=0;
        ObjectInputStream ois=null;
        try {
            Purchase p;
            ois = new ObjectInputStream(new FileInputStream("purchaseList.bin"));
            while(true){
            p = (Purchase) ois.readObject();
            if(p.getPurchasedCardType().equals("Wedding Card"))
                 count++;
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
        return count;
    }
    public int countCardsSells4(){
        int count=0;
        ObjectInputStream ois=null;
        try {
            Purchase p;
            ois = new ObjectInputStream(new FileInputStream("purchaseList.bin"));
            while(true){
            p = (Purchase) ois.readObject();
            if(p.getPurchasedCardType().equals("Puja Card"))
                 count++;
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
        return count;
    }
    @FXML
    private void loadPieChartButtonOnClick(ActionEvent event) {
        int EidCardSell = countCardsSells();
        int RamadanCardSell = countCardsSells2();
        int WeddingCardSell = countCardsSells3();
        int PujaCardSell = countCardsSells4();
        ObservableList <PieChart.Data> list = FXCollections.observableArrayList(
            new PieChart.Data("Eid Card",EidCardSell),
            new PieChart.Data("Ramadan Card",RamadanCardSell),
            new PieChart.Data("Wedding Card",WeddingCardSell),
            new PieChart.Data("Puja Card",PujaCardSell)
        );
        
        pieChart.setData(list);
        
        for(PieChart.Data data: pieChart.getData()){
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    statusLabel.setText(String.valueOf(data.getPieValue()));
                    
                }
            }
            );
        }
    }
}
