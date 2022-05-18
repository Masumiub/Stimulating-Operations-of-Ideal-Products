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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.event.MenuEvent;

/**
 * FXML Controller class
 *
 * @author movie
 */
public class PurchaseCardController implements Initializable {
    
    private Card selectedCard;
    @FXML
    private Label selectedCardIDTextArea;
    @FXML
    private TextField clientFirstNameTextField;
    @FXML
    private TextField clientLastNameTextField;
    @FXML
    private TextField quantityTextField;
    @FXML
    private DatePicker deliveryDateTextField;
    @FXML
    private TextField accountNoTextField;
    @FXML
    private TextField deliveryAddressTextField;
    @FXML
    private TextField customTextField;
    @FXML
    private Label selectedCardType;
    @FXML
    private Label selectedCardPrice;
    
    
    //* Initializes the controller class.
     
    public void initData(Card c){
        selectedCard = c;
        selectedCardIDTextArea.setText(Integer.toString(selectedCard.getCardID()));
        selectedCardType.setText(selectedCard.getCardType());
        selectedCardPrice.setText(Float.toString(selectedCard.getPrice()));
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @FXML
    private void confirmPurchaseOnClick(ActionEvent event) throws IOException{
        /*if(clientFirstNameTextField.getText().equals("") || clientLastNameTextField.getText().equals("") 
                || quantityTextField.getText().equals("") || deliveryDateTextField.getValue().equals("") 
                || accountNoTextField.getText().equals("") || deliveryAddressTextField.getText().equals("")
                || customTextField.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Sorry! Please Fillup the Required Form!");
            a.showAndWait();
        }
        else{*/
            File f = null;
            FileOutputStream fos=null;
            ObjectOutputStream oos=null;
            try {
            f = new File("purchaseList.bin");
            if(f.exists()) {
                fos = new FileOutputStream(f,true);
                oos=new AppendableObjectOutputStream(fos);
            }
            else{
                fos=new FileOutputStream(f);
                oos=new ObjectOutputStream(fos);
            }
            Purchase newCardPurchase = new Purchase(
                    selectedCardIDTextArea.getText(), selectedCardType.getText(),selectedCardPrice.getText(),
                    clientFirstNameTextField.getText(), clientLastNameTextField.getText(), quantityTextField.getText(),
                    deliveryDateTextField.getValue().toString(), accountNoTextField.getText(), deliveryAddressTextField.getText(),
                    customTextField.getText()
            );
            oos.writeObject(newCardPurchase);
            
            }
            catch (IOException ex) {
            Logger.getLogger(PurchaseCardController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(oos != null) oos.close();
                } catch (IOException ex) {
                Logger.getLogger(PurchaseCardController.class.getName()).log(Level.SEVERE, null, ex);
          }
            
        }
    //}
    }

    @FXML
    private void cancelPurchaseOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ClientDashBoard.fxml"));
        Scene scene2 = new Scene(root);
            
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene2);
        window.show();
    }
    
}
