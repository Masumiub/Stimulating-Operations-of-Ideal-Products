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
import javafx.scene.control.ComboBox;
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


public class ClientDashBoardSceneController implements Initializable {

    @FXML
    private Label userIDLabel;
    @FXML
    private ComboBox<String> cardTypeComboBox;
    @FXML
    private TableView<Card> tableView;
    @FXML
    private TableColumn<Card, Integer> cardID;
    @FXML
    private TableColumn<Card, String> cardType;
    @FXML
    private TableColumn<Card, Float> cardPrice;
    @FXML
    private TableColumn<Card, String> DesignNameCol;

    Client loggedUser;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cardTypeComboBox.getItems().addAll("Eid Card", "Ramadan Card", "Wedding Card", "Puja Card");
        cardTypeComboBox.setValue("Eid Card");
        

        cardID.setCellValueFactory(new PropertyValueFactory<Card, Integer>("cardID"));
        cardType.setCellValueFactory(new PropertyValueFactory<Card, String>("cardType"));
        cardPrice.setCellValueFactory(new PropertyValueFactory<Card, Float>("price"));
        DesignNameCol.setCellValueFactory(new PropertyValueFactory<Card, String>("cardDesignerName"));
    }    

    
    public void initData(User u){
        loggedUser = (Client)u;
        //userIDLabel.setText(inputUserID);
        userIDLabel.setText(loggedUser.getUserID());
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
    private void SearchCardOnClick(ActionEvent event) {
        ObjectInputStream ois=null;
         try {
            Card c; 
            ois = new ObjectInputStream(new FileInputStream("cardList.bin"));
           
            while(true){
                c = (Card) ois.readObject();
                String selectedcardtype = c.getCardType();
                if(selectedcardtype.equals((String)cardTypeComboBox.getSelectionModel().getSelectedItem())){
                    tableView.getItems().add(0, c);
                }
            }
            
            
        } catch (Exception ex) {
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
    private void changeScene(ActionEvent event) throws IOException { //select card to purchase
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CardPurchaseForm.fxml"));
        Parent purchaseViewParent = loader.load();
    
        Scene purchaseViewScene = new Scene(purchaseViewParent);

        CardPurchaseFormController controller = loader.getController();
        controller.initData(tableView.getSelectionModel().getSelectedItem());
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(purchaseViewScene);
        window.show();
    }

    @FXML
    private void sendFeedbackOnClick(ActionEvent event) throws IOException {
        Parent feedbackParent = FXMLLoader.load(getClass().getResource("SendFeedbackScene.fxml"));
        Scene sendfeedbackParentViewScene = new Scene(feedbackParent);
        Stage newWindow  = new Stage();
        
        newWindow.setTitle("Send Feedback");
        newWindow.setScene(sendfeedbackParentViewScene);
        newWindow.show();
    }

    @FXML
    private void closeAppOnClick(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }
    
}
