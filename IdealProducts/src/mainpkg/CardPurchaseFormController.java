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
import java.util.Random;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author movie
 */
public class CardPurchaseFormController implements Initializable {

    private Card selectedCard;
    @FXML
    private Label selectedCardType;
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
    private Label selectedCardIDTextArea;
    @FXML
    private Label selectedCardPrice;
    @FXML
    private RadioButton bkashRadioBtn;
    @FXML
    private RadioButton rokectRadioBtn;
    @FXML
    private RadioButton nogodRadioBtn;
    @FXML
    private RadioButton cashOnDeliveryRadioBtn;
    
    private ToggleGroup tg;
    
    private int sellsID;
    /**
     * Initializes the controller class.
     */
    public void initData(Card c){
        selectedCard = c;
        selectedCardIDTextArea.setText(Integer.toString(selectedCard.getCardID()));
        selectedCardType.setText(selectedCard.getCardType());
        selectedCardPrice.setText(Float.toString(selectedCard.getPrice()));   
    }
    
    public int createUniqueSellsID(){
        Random random = new Random(System. nanoTime()); 
        int sellsID = random. nextInt(10000000);
        return sellsID;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tg = new ToggleGroup();
        bkashRadioBtn.setToggleGroup(tg);
        rokectRadioBtn.setToggleGroup(tg);
        nogodRadioBtn.setToggleGroup(tg);
        cashOnDeliveryRadioBtn.setToggleGroup(tg);
    }    

    @FXML
    private void confirmPurchaseOnClick(ActionEvent event) throws IOException{
        if(clientFirstNameTextField.getText().equals("") || clientLastNameTextField.getText().equals("") 
                || quantityTextField.getText().equals("") || deliveryDateTextField.getValue().equals("") 
                || accountNoTextField.getText().equals("") || deliveryAddressTextField.getText().equals("")
                || customTextField.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Sorry! Please Fillup the Required Form!");
            a.showAndWait();
        }
        else{
            int quantityOfCard = Integer.parseInt(quantityTextField.getText());
            float priceofCard = Float.parseFloat(selectedCardPrice.getText());
            float payableAmount =  quantityOfCard*priceofCard;
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
            Logger.getLogger(CardPurchaseFormController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(oos != null) oos.close();
                } catch (IOException ex) {
                Logger.getLogger(CardPurchaseFormController.class.getName()).log(Level.SEVERE, null, ex);
          }
            
        }
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Thank you for your Purchase! Your Card will be delivered soon! Your Payable Amount is: "+payableAmount +" Taka");
            a.showAndWait();
            
            Parent root = FXMLLoader.load(getClass().getResource("ClientDashBoardScene.fxml"));
            Scene scene2 = new Scene(root);   
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene2);
            window.show();
    }
    }
    @FXML
    private void cancelPurchaseOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ClientDashBoardScene.fxml"));
        Scene scene2 = new Scene(root);   
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }
    
}
