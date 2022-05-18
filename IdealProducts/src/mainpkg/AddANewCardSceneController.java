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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author movie
 */
public class AddANewCardSceneController implements Initializable {

    @FXML
    private TextField cardIDTextField;
    @FXML
    private TextField cardPriceTextField;
    @FXML
    private ComboBox<String> cardTypeComboBox;
    //private TextField cardDesignerNameTextField;
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
        cardTypeComboBox.getItems().addAll("Eid Card", "Ramadan Card", "Wedding Card", "Puja Card");
        cardTypeComboBox.setValue("Eid Card");
        
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
    public int checkUniqueID(int inputCardID){
        int found=0;
        ObjectInputStream ois=null;
        try {
            Card c;
            ois = new ObjectInputStream(new FileInputStream("cardList.bin"));
            while(true){
            c = (Card) ois.readObject();
            if(c.cardID==inputCardID){
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
    private void savetoTheCardListOnCilck(ActionEvent event) {
        if(checkUniqueID(Integer.parseInt(cardIDTextField.getText()))>0){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Sorry! Your Card ID must be Unique!");
            a.showAndWait();
        }
        else if(cardIDTextField.getText().equals("") || cardPriceTextField.getText().equals("") || designerNameTextField.getText().equals("")){
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
               designerNameTextField.getText()
               );
            
            oos.writeObject(newCard);
           
            cardIDTextField.clear();
            cardPriceTextField.clear();
            designerNameTextField.clear();
            
            } catch (IOException ex) {
            Logger.getLogger(AddANewCardSceneController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(oos != null) oos.close();
                } catch (IOException ex) {
                Logger.getLogger(AddANewCardSceneController.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Thanks! Your New Card Added to the File.");
        a.showAndWait();
        }
    }

    @FXML
    private void selectDesignerOnClick(ActionEvent event) {
        designerNameTextField.setText(designerNameTableView.getSelectionModel().getSelectedItem().getUserFullName());
    }
    
}
