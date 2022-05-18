/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpkg;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author movie
 */
public class OperationManagerDashBoardController implements Initializable {

   
    
    private ArrayList<Card> cardArr = new ArrayList<Card>();
    
    @FXML
    private TableView<Purchase> clientOrderTableView;
    @FXML
    private TableColumn<Purchase, String> cardIDcol;
    @FXML
    private TableColumn<Purchase, String> priceCol;
    @FXML
    private TableColumn<Purchase, String> firstNameCol;
    @FXML
    private TableColumn<Purchase, String> lastNameCol;
    @FXML
    private TableColumn<Purchase, String> quantityCol;
    @FXML
    private TableColumn<Purchase, String> deliveryDateCol;
    @FXML
    private TableColumn<Purchase, String> accountNoCol;
    @FXML
    private TableColumn<Purchase, String> deliveryAddressCol;
    @FXML
    private TableColumn<Purchase, String> CustomTextCol;
    @FXML
    private TableColumn<Purchase, String> cardTypeCol;
    
    @FXML
    private Label userIDLabel;
    
    OperationManager loggedUser;
    
    public void initData(User u){
        loggedUser = (OperationManager)u;
        //userIDLabel.setText(inputUserID);
        userIDLabel.setText(loggedUser.getUserID());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        cardIDcol.setCellValueFactory(new PropertyValueFactory<Purchase, String>("purchasedCardID"));
        cardTypeCol.setCellValueFactory(new PropertyValueFactory<Purchase, String>("purchasedCardType"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Purchase, String>("purchasedCardPrice"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Purchase, String>("clientFirstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Purchase, String>("clientLastName"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<Purchase, String>("quantity"));
        deliveryDateCol.setCellValueFactory(new PropertyValueFactory<Purchase, String>("deliveryDate"));
        accountNoCol.setCellValueFactory(new PropertyValueFactory<Purchase, String>("clientAccountNo"));
        deliveryAddressCol.setCellValueFactory(new PropertyValueFactory<Purchase, String>("deliveryAddress"));
        CustomTextCol.setCellValueFactory(new PropertyValueFactory<Purchase, String>("clientCustomText"));
        
        
        LoadPurchasedCardList();
    }    

    @FXML
    private void SignOutOnClick(ActionEvent event) throws IOException {
        Parent client2Parent = FXMLLoader.load(getClass().getResource("MainLoginScene.fxml"));
        Scene home = new Scene(client2Parent);
               
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(home);
        window.show();
    }
    private void LoadPurchasedCardList(){
        ObjectInputStream ois=null;
        try {
            Purchase p;
            ois = new ObjectInputStream(new FileInputStream("purchaseList.bin"));
            while(true){
            p = (Purchase) ois.readObject();
            clientOrderTableView.getItems().add(0, p);
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
    
    /*private void addNewCardOnCilck(ActionEvent event) {
        
        cardArr.add(
               new Card(
               Integer.parseInt(cardIDTextField.getText()), 
               (String)cardTypeComboBox.getSelectionModel().getSelectedItem(),
               Float.parseFloat(cardPriceTextField.getText()), 
               cardDesignerNameTextField.getText()
               )
       );
       
        Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("New card instance is added to the arry list");
                a.showAndWait();

    }*/
       
    
    
    @FXML
    private void savetoTheCardListOnCilck(ActionEvent event) throws IOException {
        Parent addCardParent = FXMLLoader.load(getClass().getResource("AddANewCardScene.fxml"));
        Scene addCardViewScene = new Scene(addCardParent);
        Stage newWindow  = new Stage();
        
        newWindow.setTitle("Add A New Card");
        newWindow.setScene(addCardViewScene);
        newWindow.show();
        /*if(cardIDTextField.getText().equals("") || cardPriceTextField.getText().equals("") || cardDesignerNameTextField.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Sorry! Please fillup the required Fields.");
            a.showAndWait();
        }
        else{
        File f = null;
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try {
          
            f = new File("cardList.bin");
            if(f.exists()) {
                fos = new FileOutputStream(f,true);
                oos=new AppendableObjectOutputStream(fos);
            }
            else{
                fos=new FileOutputStream(f);
                oos=new ObjectOutputStream(fos);
            }
            
            Card newCard = new Card(
               Integer.parseInt(cardIDTextField.getText()), 
               (String)cardTypeComboBox.getSelectionModel().getSelectedItem(),
               Float.parseFloat(cardPriceTextField.getText()), 
               cardDesignerNameTextField.getText()
               );
            
            oos.writeObject(newCard);
            //tableView.getItems().add(newCard);
            cardIDTextField.clear();
            cardPriceTextField.clear();
            cardDesignerNameTextField.clear();
            
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
        a.setContentText("Thanks! Your New Card Added to the File.");
        a.showAndWait();
        }
        /*Card newCard = new Card(
               Integer.parseInt(cardIDTextField.getText()), 
               (String)cardTypeComboBox.getSelectionModel().getSelectedItem(),
               Float.parseFloat(cardPriceTextField.getText()), 
               cardDesignerNameTextField.getText()
               );
        tableView.getItems().add(newCard);
        
        cardIDTextField.setText(null);
        cardTypeComboBox.setValue(null);
        cardPriceTextField.setText(null);
        cardDesignerNameTextField.setText(null);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cardList.bin", true));
            oos.writeObject(newCard);
            oos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
    
    /*public ObservableList<Card> getCard(){
        ObservableList<Card> card = FXCollections.observableArrayList();
        card.add(new Card(111,"Eid", 125.90f, "Masum"));
        card.add(new Card(222,"Ramadan", 175.90f, "Asif"));
        card.add(new Card(33,"Ramadan", 175.90f, "Rahim"));
        return card;
    }*/
    }
    
    @FXML
    private void ShowAllCardsOnClick(ActionEvent event) throws IOException {
        Parent cardListParent = FXMLLoader.load(getClass().getResource("ShowAllCardList.fxml"));
        Scene cardListViewScene = new Scene(cardListParent);
        Stage newWindow  = new Stage();
        
        newWindow.setTitle("Card List");
        newWindow.setScene(cardListViewScene);
        newWindow.show();
        /*ObjectInputStream ois=null;
        try {
            Card c;
            ois = new ObjectInputStream(new FileInputStream("cardList.bin"));
            while(true){
            c = (Card) ois.readObject();
            tableView.getItems().add(c);
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
        //ObservableList<Card> cardList = getCard();
        //tableView.setItems(cardList);
       
        /*ObjectInputStream ois=null;
         try {
            Card c; 
            ois = new ObjectInputStream(new FileInputStream("cardList.bin"));
            //while(true){
                c = (Card)ois.readObject();
                System.out.println(c.toString());
                tableView.getItems().add(c);
                ois.close();
            //}   
        } catch (Exception ex) {
            try {
                if(ois!=null)
                    ois.close();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        } */
    }

    @FXML
    private void AssignADesignerOnClick(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AssignADesigner.fxml"));
        Parent AssignADesigner = loader.load();
    
        
        Scene AssignADesignerScene = new Scene(AssignADesigner);
        

        AssignADesignerController controller = loader.getController();
        controller.initData(clientOrderTableView.getSelectionModel().getSelectedItem());
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(AssignADesignerScene);
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

    @FXML
    private void showFeedbackOnClick(ActionEvent event) throws IOException {
        Parent feedParent = FXMLLoader.load(getClass().getResource("ShowClientFeedbackSecne.fxml"));
        Scene feedViewScene = new Scene(feedParent);
        Stage newWindow  = new Stage();
        
        newWindow.setTitle("Client Feedback");
        newWindow.setScene(feedViewScene);
        newWindow.show();
    }

    
}
