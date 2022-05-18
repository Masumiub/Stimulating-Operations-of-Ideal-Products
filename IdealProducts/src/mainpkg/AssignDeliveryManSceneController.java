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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author movie
 */
public class AssignDeliveryManSceneController implements Initializable {
    private NewAssignedGraphicDesigner assignedDeliveryMan;
    @FXML
    private TextField deliveryManNameTextField;
    @FXML
    private ComboBox<String> customizationStatusComboBox;
    @FXML
    private Label cardIDLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label deliveryDateLabel;
    @FXML
    private Label deliveryAddressLabel;
    @FXML
    private TableView<User> deliverymanTableView;
    @FXML
    private TableColumn<User, String> deliveryManNameCol;
    /**
     * Initializes the controller class.
     */
    GraphicDesigner loggedUser;
    
    public void initData(NewAssignedGraphicDesigner d, User u){
        assignedDeliveryMan = d;
        cardIDLabel.setText(assignedDeliveryMan.getPurchasedCardID());
        firstNameLabel.setText(assignedDeliveryMan.getClientFirstName());
        lastNameLabel.setText(assignedDeliveryMan.getClientLastName());
        deliveryDateLabel.setText(assignedDeliveryMan.getDeliveryDate());
        deliveryAddressLabel.setText(assignedDeliveryMan.getDeliveryAddress());
        
        loggedUser = (GraphicDesigner)u;
        //System.out.println(loggedUser.getUserFullName());
    }
    
    public void LoadDeliveryManName(){
        ObjectInputStream ois=null;
        try {
            User deliveryMan;
            ois = new ObjectInputStream(new FileInputStream("User.bin"));
            while(true){
            deliveryMan = (User) ois.readObject();
            if(deliveryMan.getUserType().equals("Delivery Man"))
                 deliverymanTableView.getItems().add(0, deliveryMan);
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customizationStatusComboBox.getItems().addAll("Customize Done", "Not Completed");
        customizationStatusComboBox.setValue("Customize Done");
        deliveryManNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("userFullName"));
        LoadDeliveryManName();
    }    
    
    public int isDeliveryManAlreadyAssigned(String CardID){
        int found= 0 ;
        ObjectInputStream ois=null;
        try {
            NewAssignedDeliveryMan d;
            ois = new ObjectInputStream(new FileInputStream("assignedDeliveryManList.bin"));
            while(true){
            d = (NewAssignedDeliveryMan) ois.readObject();
            if(d.getCardID().equals(CardID)){
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
    private void confirmAssignDeliveryManOnClick(ActionEvent event) throws IOException {
        /*if(isDeliveryManAlreadyAssigned(cardIDLabel.getText())>0){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Sorry! This Card is already assigned to Delivery Man.");
            a.showAndWait();
        }*/
        if(deliveryManNameTextField.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Sorry! Please Assign a Delivery Man Name.");
            a.showAndWait();
        }
        else{
            
            //int isAssigned = loggedUser.;
     
        File f = null;
            FileOutputStream fos=null;
            ObjectOutputStream oos=null;
            try {
            f = new File("assignedDeliveryManList.bin");
            if(f.exists()) {
                fos = new FileOutputStream(f,true);
                oos=new AppendableObjectOutputStream(fos);
            }
            else{
                fos=new FileOutputStream(f);
                oos=new ObjectOutputStream(fos);
            }
            NewAssignedDeliveryMan newDeliveryMan = new NewAssignedDeliveryMan(
                    cardIDLabel.getText(),
                    firstNameLabel.getText(), lastNameLabel.getText(),
                    deliveryDateLabel.getText(),  deliveryAddressLabel.getText(),
                    customizationStatusComboBox.getSelectionModel().getSelectedItem(), deliveryManNameTextField.getText()
            );
            oos.writeObject(newDeliveryMan);
            
            }
            catch (IOException ex) {
            Logger.getLogger(OperationManagerDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(oos != null) oos.close();
                } catch (IOException ex) {
                Logger.getLogger(OperationManagerDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
          }
               
        }
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Thanks! Assigning DeliveryMan was Successful.");
        a.showAndWait();
        
        Parent client2Parent = FXMLLoader.load(getClass().getResource("Graphic Designer.fxml"));
        Scene home = new Scene(client2Parent);      
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(home);
        window.show();
    }
    }

    @FXML
    private void cancelAssigningOnClick(ActionEvent event) throws IOException {
        Parent client2Parent = FXMLLoader.load(getClass().getResource("Graphic Designer.fxml"));
        Scene home = new Scene(client2Parent);
               
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(home);
        window.show();
    }

    @FXML
    private void selectDeliveryManOnClick(ActionEvent event) {
        deliveryManNameTextField.setText(deliverymanTableView.getSelectionModel().getSelectedItem().getUserFullName());
    }
    
}
