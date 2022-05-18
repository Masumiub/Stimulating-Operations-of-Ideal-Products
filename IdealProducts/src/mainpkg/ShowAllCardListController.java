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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author movie
 */
public class ShowAllCardListController implements Initializable {

    @FXML
    private TableView<Card> tableView;
    @FXML
    private TableColumn<Card, Integer> CardIDcol;
    @FXML
    private TableColumn<Card, String> CardTypeCol;
    @FXML
    private TableColumn<Card, Float> CardPriceCol;
    @FXML
    private TableColumn<Card, String> DesignNameCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CardIDcol.setCellValueFactory(new PropertyValueFactory<Card, Integer>("cardID"));
        CardTypeCol.setCellValueFactory(new PropertyValueFactory<Card, String>("cardType"));
        CardPriceCol.setCellValueFactory(new PropertyValueFactory<Card, Float>("price"));
        DesignNameCol.setCellValueFactory(new PropertyValueFactory<Card, String>("cardDesignerName"));
        
        loadCardlist();
    }    
    public void loadCardlist(){
        ObjectInputStream ois=null;
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
    }
    
}
