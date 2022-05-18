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
public class AssignADesignerController implements Initializable {
    private Purchase selectedCard;
    @FXML
    private Label cardIDLabel;
    @FXML
    private Label firstnameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label deliveryDateLabel;
    @FXML
    private Label deliveryAddressLabel;
    @FXML
    private Label clientCustomText;
    @FXML
    private TextField designerNameTextField;
    @FXML
    private TableView<User> designerNameTableView;
    @FXML
    private TableColumn<User, String> designerNameCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        designerNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("userFullName"));
        LoadDesignerName();
    }    

    public void LoadDesignerName(){
        ObjectInputStream ois=null;
        try {
            User designer;
            ois = new ObjectInputStream(new FileInputStream("User.bin"));
            while(true){
            designer = (User) ois.readObject();
            if(designer.getUserType().equals("Graphic Designer"))
                 designerNameTableView.getItems().add(0, designer);
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
    void initData(Purchase p) {
        selectedCard = p;
        cardIDLabel.setText(selectedCard.getPurchasedCardID());
        firstnameLabel.setText(selectedCard.getClientFirstName());
        lastNameLabel.setText(selectedCard.getClientLastName());
        deliveryDateLabel.setText(selectedCard.getDeliveryDate());
        deliveryAddressLabel.setText(selectedCard.getDeliveryAddress());
        clientCustomText.setText(selectedCard.getClientCustomText());
    }
    //public int isAlreadyAssignedtoADesigner(){}
    @FXML
    private void confirmAssignDesignerOnClick(ActionEvent event) throws IOException {
        if(designerNameTextField.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Sorry! Please Assign a Graphic Designer Name.");
            a.showAndWait();
        }
        else{
        File f = null;
            FileOutputStream fos=null;
            ObjectOutputStream oos=null;
            try {
            f = new File("assignedGraphicDesignerList.bin");
            if(f.exists()) {
                fos = new FileOutputStream(f,true);
                oos=new AppendableObjectOutputStream(fos);
            }
            else{
                fos=new FileOutputStream(f);
                oos=new ObjectOutputStream(fos);
            }
            NewAssignedGraphicDesigner newDesigner = new NewAssignedGraphicDesigner(
                    cardIDLabel.getText(),
                    firstnameLabel.getText(), lastNameLabel.getText(),
                    deliveryDateLabel.getText(),  deliveryAddressLabel.getText(),
                    clientCustomText.getText(), designerNameTextField.getText()
            );
            oos.writeObject(newDesigner);
            
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
        a.setContentText("Thanks! Assigning Graphic Designer was Successful.");
        a.showAndWait();
        
        
        Parent client2Parent = FXMLLoader.load(getClass().getResource("OperationManagerDashBoard.fxml"));
        Scene home = new Scene(client2Parent);   
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(home);
        window.show();
    }
    }

    @FXML
    private void cancelConfirmAssignDesignerOnClick(ActionEvent event) throws IOException {
        Parent client2Parent = FXMLLoader.load(getClass().getResource("OperationManagerDashBoard.fxml"));
        Scene home = new Scene(client2Parent);   
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(home);
        window.show();
    }

    @FXML
    private void selectDesignerOnClick(ActionEvent event) {
        designerNameTextField.setText(designerNameTableView.getSelectionModel().getSelectedItem().getUserFullName());
    }
    
}
