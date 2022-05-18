/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpkg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author movie
 */
public class SignUpFormController implements Initializable {

    @FXML
    private TextField fullNameTextField;
    @FXML
    private TextField UserIDTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField usertypeTextField;
    @FXML
    private Label passwordAlertLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public int checkUniqueID(String inputUserID){
        int found=0;
        ObjectInputStream ois=null;
        try {
            User u;
            ois = new ObjectInputStream(new FileInputStream("User.bin"));
            while(true){
            u = (User) ois.readObject();
            if(u.getUserID().equals(inputUserID)){
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
    public int checkValidpass(String inputPass){
        int valid =0;
        if(inputPass.length()<4){
            valid++;
        }
        return valid;
    }
    @FXML
    private void SignUpOnClick(ActionEvent event) {
        if(checkUniqueID (UserIDTextField.getText())>0 ){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Sorry! Your User ID must be Unique!");
            a.showAndWait();
        }
        else if(  checkValidpass(passwordTextField.getText())>0 ){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Sorry! Your Password must be 4 characters long!");
            a.showAndWait();
        }
        else if(UserIDTextField.getText().equals("") || passwordTextField.getText().equals("") || 
                fullNameTextField.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Sorry! Please Fillup the Required Fields.");
            a.showAndWait();
        }
        else{
        File f = null;
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try {
          
            f = new File("User.bin");
            if(f.exists()) {
                fos = new FileOutputStream(f,true);
                oos=new AppendableObjectOutputStream(fos);
            }
            else{
                fos=new FileOutputStream(f);
                oos=new ObjectOutputStream(fos);
            }
            //User u;
            User u= new Client(UserIDTextField.getText(), passwordTextField.getText(),usertypeTextField.getText(), fullNameTextField.getText());
            
            oos.writeObject(u);
           
            } catch (IOException ex) {
            Logger.getLogger(SignUpFormController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(oos != null) oos.close();
                } catch (IOException ex) {
                Logger.getLogger(SignUpFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Success! Your SignUp is Done.");
        a.showAndWait();
        UserIDTextField.clear();
        passwordTextField.clear();
        fullNameTextField.clear();
            /*File f = null;
            FileWriter fw = null;

        try {

            f = new File("UserSignUpCredential.txt");      

            if(f.exists()) fw = new FileWriter(f,true);
            else fw = new FileWriter(f);
           
            fw.write(
            	UserIDTextField.getText()+","
                +passwordTextField.getText()+","	
                +usertypeTextField.getText()+"\n"	
            );           
            
        } catch (IOException ex) {
            Logger.getLogger(SignUpFormController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(fw != null) fw.close();
            } catch (IOException ex) {
                Logger.getLogger(SignUpFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
            //emailTextField.clear();
            //phoneNumberTextField.clear();
        }
        
    }

    @FXML
    private void checkPasswordLength(ActionEvent event) {
        String inputPass = passwordTextField.getText();
        if(inputPass.length()<4){
            passwordAlertLabel.setText("Your Password must be 4 charaters long!");
        }
        else{
            passwordAlertLabel.setText("Your Password is OK");
        }
    }
    
}
