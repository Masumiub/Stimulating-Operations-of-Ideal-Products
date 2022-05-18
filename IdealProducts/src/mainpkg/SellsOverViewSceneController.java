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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author movie
 */
public class SellsOverViewSceneController implements Initializable {

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

    @FXML
    private void goBackOnClick(ActionEvent event) throws IOException {
        Parent client2Parent = FXMLLoader.load(getClass().getResource("AdminDashBoard.fxml"));
        Scene home = new Scene(client2Parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(home);
        window.show();
    }
    
}
