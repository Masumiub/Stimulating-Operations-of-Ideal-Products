/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpkg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author movie
 */
public class WorkScheduleSceneController implements Initializable {

    @FXML
    private TextField wokingCardIDTextField;
    @FXML
    private DatePicker myWorkingDate;
    @FXML
    private DatePicker clientDeliveryDate;
    @FXML
    private TextField designDetailsTextField;

    GraphicDesigner loggedUser;
    
    public void initData(User u){
        loggedUser = (GraphicDesigner)u;
        System.out.println(loggedUser.getUserFullName());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void setWorkScheduleOnClick(ActionEvent event) {
        if(wokingCardIDTextField.getText().equals("") || myWorkingDate.getValue().equals("") || 
                clientDeliveryDate.getValue().equals("") || designDetailsTextField.getText().equals("")
                ){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Sorry. Please fillup all required fields.");
            a.showAndWait();
        }
        else{ 
            /*
            boolean isAddWorkScheduleAdded = loggedUser.AddWorkSchedule(wokingCardIDTextField.getText(), myWorkingDate.getValue()
            clientDeliveryDate.getValue(), designDetailsTextField.getText());
            
            if(isAddWorkScheduleAdded){
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Success! Your Work Schedule is Added.");
                a.showAndWait();
            }else{
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Sorry. Please fillup all required fields.");
                a.showAndWait();
            */
            File f = null;
            FileWriter fw = null;

        try {
        f = new File("WorkScheduleListofDesigner.txt");      

            if(f.exists()) fw = new FileWriter(f,true);
            else fw = new FileWriter(f);
           
            fw.write(
            	wokingCardIDTextField.getText()+","
                +myWorkingDate.getValue()+","
                +clientDeliveryDate.getValue()+","
                +designDetailsTextField.getText()+"\n"	
            );           
            
        } catch (IOException ex) {
            Logger.getLogger(SignUpFormController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(fw != null) fw.close();
            } catch (IOException ex) {
                Logger.getLogger(SignUpFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Success! Your Work Schedule is Added.");
            a.showAndWait();
        }
        
    }

    @FXML
    private void goBackOnClick(ActionEvent event) throws IOException {
        Parent client2Parent = FXMLLoader.load(getClass().getResource("Graphic Designer.fxml"));
        Scene home = new Scene(client2Parent);    
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(home);
        window.show();
    }
    
}
