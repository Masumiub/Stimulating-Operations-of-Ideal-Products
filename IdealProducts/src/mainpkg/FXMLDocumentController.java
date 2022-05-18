/* Main Project File
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpkg;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
//import java.time.Month;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author movie
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField UserIDTextField;
    @FXML
    private TextField PasswordTextField;
    @FXML
    private ComboBox<String> userTypeComboBox;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userTypeComboBox.getItems().addAll("Client", "Operation Manager", "Graphic Designer", "Delivery Man", "Admin");
        userTypeComboBox.setValue("Client");
    }    

    @FXML
    private void SignInOnClick(ActionEvent event) {
        File f = null;
        //FileReader fw = null;
        Scanner sc; 
        String str; 
        String[] tokens;
        String inputUserID = UserIDTextField.getText();
        String inputPassword = PasswordTextField.getText();
        String inputUserType = userTypeComboBox.getSelectionModel().getSelectedItem();
        int count=0;
        try {
            f = new File("UserSignUpCredential.txt");
            sc = new Scanner(f);
            if(f.exists()){
                
                while(sc.hasNextLine()){
                    str=sc.nextLine();
                    tokens = str.split(",");
                    if(tokens[0].equals(inputUserID) && tokens[1].equals(inputPassword) && tokens[2].equals(inputUserType)){
                        //code to change scene
                        count++;
                        if(inputUserType.equals("Client")){
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("ClientDashBoardScene.fxml"));
                            Parent homeScene2 = loader.load();
                            Scene homepage2 = new Scene(homeScene2);
                            ClientDashBoardSceneController controller = loader.getController();
//                            controller.initData(inputUserID);
                            Stage window2 = (Stage)((Node)event.getSource()).getScene().getWindow();
                            window2.setScene(homepage2);
                            window2.show();
                        }
                        else if(inputUserType.equals("Operation Manager")){
                            FXMLLoader loader2 = new FXMLLoader();
                            loader2.setLocation(getClass().getResource("OperationManagerDashBoard.fxml"));
                            Parent homeScene2 = loader2.load();
                            Scene homepage2 = new Scene(homeScene2);
                            OperationManagerDashBoardController controller = loader2.getController();
  //                          controller.initData(inputUserID);
                            Stage window2 = (Stage)((Node)event.getSource()).getScene().getWindow();
                            window2.setScene(homepage2);
                            window2.show();
                        }
                        else if(inputUserType.equals("Graphic Designer")){
                            FXMLLoader loader2 = new FXMLLoader();
                            loader2.setLocation(getClass().getResource("Graphic Designer.fxml"));
                            Parent homeScene2 = loader2.load();
                            Scene homepage2 = new Scene(homeScene2);
                            GraphicDesignerController controller = loader2.getController();
    //                        controller.initData(inputUserID);
                            Stage window2 = (Stage)((Node)event.getSource()).getScene().getWindow();
                            window2.setScene(homepage2);
                            window2.show();
                        }
                        else if(inputUserType.equals("Delivery Man")){
                            FXMLLoader loader2 = new FXMLLoader();
                            loader2.setLocation(getClass().getResource("DeliveryManDashBoard.fxml"));
                            Parent homeScene2 = loader2.load();
                            Scene homepage2 = new Scene(homeScene2);
                            DeliveryManDashBoardController controller = loader2.getController();
      //                      controller.initData(inputUserID);
                            Stage window2 = (Stage)((Node)event.getSource()).getScene().getWindow();
                            window2.setScene(homepage2);
                            window2.show();
                        }
                        else{
                            FXMLLoader loader2 = new FXMLLoader();
                            loader2.setLocation(getClass().getResource("AdminDashBoard.fxml"));
                            Parent homeScene2 = loader2.load();
                            Scene homepage2 = new Scene(homeScene2);
                            AdminDashBoardController controller = loader2.getController();
        //                    controller.initData(inputUserID);
                            Stage window2 = (Stage)((Node)event.getSource()).getScene().getWindow();
                            window2.setScene(homepage2);
                            window2.show();
                        }
                        
                    } 
                }
                if(count==0){
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Sorry! Wrong UserID/Password. Please Try Again!");
                    a.showAndWait();
                    UserIDTextField.clear();
                    PasswordTextField.clear();
                }
            }    
        } 
        catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {
        }
    }

    @FXML
    private void ViewClientDashBoardOnClick(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("ClientDashBoardScene.fxml"));
        Scene scene2 = new Scene(scene2Parent);
            
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene2);
        window.show();
    }

    @FXML
    private void ViewOPManagerDashBoardOnClick(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("OperationManagerDashBoard.fxml"));
        Scene scene2 = new Scene(scene2Parent);
            
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene2);
        window.show();
    }

    @FXML
    private void ViewDesignerDashBoardOnClick(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("Graphic Designer.fxml"));
        Scene scene2 = new Scene(scene2Parent);
              
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene2);
        window.show();
    }

    @FXML
    private void ViewDeliveryManDashBoardOnClick(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("DeliveryManDashBoard.fxml"));
        Scene scene2 = new Scene(scene2Parent);
       
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene2);
        window.show();
    }

    @FXML
    private void ViewAdminDashBoardOnClick(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("AdminDashBoard.fxml"));
        Scene scene2 = new Scene(scene2Parent);
            
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene2);
        window.show();
    }

    @FXML
    private void SignUpOnClick(ActionEvent event) throws IOException {
        Parent signUpFormParent = FXMLLoader.load(getClass().getResource("signUpForm.fxml"));
        Scene signUpFormViewScene = new Scene(signUpFormParent);
        Stage newWindow  = new Stage();
        
        newWindow.setTitle("SignUp Form For Client");
        newWindow.setScene(signUpFormViewScene);
        newWindow.show();
    }
    
}
