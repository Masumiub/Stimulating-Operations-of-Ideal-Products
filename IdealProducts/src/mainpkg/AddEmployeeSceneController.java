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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author movie
 */
public class AddEmployeeSceneController implements Initializable {

    @FXML
    private TextField userIDTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField userType;
    @FXML
    private TextField deliuserIDTextField;
    @FXML
    private TextField delipasswordTextField;
    @FXML
    private TextField deliuserType;
    @FXML
    private TextField fullNameTextField;
    @FXML
    private TextField delifullNameTextField;
    @FXML
    private TextField gduserIDTextField;
    @FXML
    private TextField gdpasswordTextField;
    @FXML
    private TextField gduserType;
    @FXML
    private TextField gdfullNameTextField;
    @FXML
    private TextField opuserIDTextField;
    @FXML
    private TextField oppasswordTextField;
    @FXML
    private TextField opuserType;
    @FXML
    private TextField opfullNameTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

@FXML
    private void addUserOnClick(ActionEvent event) {
        
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
            User u= new Admin(userIDTextField.getText(), passwordTextField.getText(),userType.getText(), fullNameTextField.getText());
            
            oos.writeObject(u);
           
            } catch (IOException ex) {
            Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(oos != null) oos.close();
                } catch (IOException ex) {
                Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    
}

    @FXML
    private void addDeliveryManUserOnClick(ActionEvent event) {
        
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
            User u= new DeliveryMan(deliuserIDTextField.getText(), delipasswordTextField.getText(),deliuserType.getText(), delifullNameTextField.getText());
            
            oos.writeObject(u);
           
            } catch (IOException ex) {
            Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(oos != null) oos.close();
                } catch (IOException ex) {
                Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void addGraphicDesignerUserOnClick(ActionEvent event) {
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
            User u= new GraphicDesigner(gduserIDTextField.getText(), gdpasswordTextField.getText(),gduserType.getText(), gdfullNameTextField.getText());
            
            oos.writeObject(u);
           
            } catch (IOException ex) {
            Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(oos != null) oos.close();
                } catch (IOException ex) {
                Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void addOpManagerUserOnClick(ActionEvent event) {
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
            User u= new OperationManager(opuserIDTextField.getText(), oppasswordTextField.getText(),opuserType.getText(), opfullNameTextField.getText());
            
            oos.writeObject(u);
           
            } catch (IOException ex) {
            Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(oos != null) oos.close();
                } catch (IOException ex) {
                Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
