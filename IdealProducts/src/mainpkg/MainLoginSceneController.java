/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpkg;

import java.io.IOException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author movie
 */
public class MainLoginSceneController implements Initializable {

    @FXML
    private TextField UserIDTextField;
    @FXML
    private TextField PasswordTextField;
    @FXML
    private ComboBox<String> userTypeComboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userTypeComboBox.getItems().addAll("Client", "Operation Manager", "Graphic Designer", "Delivery Man", "Admin");
        userTypeComboBox.setValue("Client");
    }    

    @FXML
    private void SignInOnClick(ActionEvent event) throws IOException {
        User u = User.verifylogin(UserIDTextField.getText(), PasswordTextField.getText(), userTypeComboBox.getSelectionModel().getSelectedItem());
        
        if(u==null){
            Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Sorry! Wrong UserID/Password. Please Try Again!");
                    a.showAndWait();
                    UserIDTextField.clear();
                    PasswordTextField.clear();
        }
        else{
             if(u instanceof Admin){
                 FXMLLoader loader3 = new FXMLLoader();
                 loader3.setLocation(getClass().getResource("AdminDashBoard.fxml"));
                 Parent homeScene3 = loader3.load();
                 Scene homepage3 = new Scene(homeScene3);
                 AdminDashBoardController controller = loader3.getController();
                 //System.out.println(u.getUserID());
                 controller.initData(u);
                 Stage window3 = (Stage)((Node)event.getSource()).getScene().getWindow();
                 window3.setScene(homepage3);
                 window3.show();
             }
             if(u instanceof DeliveryMan){
                 FXMLLoader loader3 = new FXMLLoader();
                 loader3.setLocation(getClass().getResource("DeliveryManDashBoard.fxml"));
                 Parent homeScene3 = loader3.load();
                 Scene homepage3 = new Scene(homeScene3);
                 DeliveryManDashBoardController controller = loader3.getController();
                 //System.out.println(u.getUserID());
                 controller.initData(u);
                 Stage window3 = (Stage)((Node)event.getSource()).getScene().getWindow();
                 window3.setScene(homepage3);
                 window3.show();
             }
             if(u instanceof GraphicDesigner){
                 FXMLLoader loader3 = new FXMLLoader();
                 loader3.setLocation(getClass().getResource("Graphic Designer.fxml"));
                 Parent homeScene3 = loader3.load();
                 Scene homepage3 = new Scene(homeScene3);
                 GraphicDesignerController controller = loader3.getController();
                 //System.out.println(u.getUserFullName());
                 controller.initData(u);
                 Stage window3 = (Stage)((Node)event.getSource()).getScene().getWindow();
                 window3.setScene(homepage3);
                 window3.show();
             }
             if(u instanceof OperationManager){
                 FXMLLoader loader3 = new FXMLLoader();
                 loader3.setLocation(getClass().getResource("OperationManagerDashBoard.fxml"));
                 Parent homeScene3 = loader3.load();
                 Scene homepage3 = new Scene(homeScene3);
                 OperationManagerDashBoardController controller = loader3.getController();
                 //System.out.println(u.getUserFullName());
                 controller.initData(u);
                 Stage window3 = (Stage)((Node)event.getSource()).getScene().getWindow();
                 window3.setScene(homepage3);
                 window3.show();
             }
             if(u instanceof Client){
                 FXMLLoader loader3 = new FXMLLoader();
                 loader3.setLocation(getClass().getResource("ClientDashBoardScene.fxml"));
                 Parent homeScene3 = loader3.load();
                 Scene homepage3 = new Scene(homeScene3);
                 ClientDashBoardSceneController controller = loader3.getController();
                 //System.out.println(u.getUserFullName());
                 controller.initData(u);
                 Stage window3 = (Stage)((Node)event.getSource()).getScene().getWindow();
                 window3.setScene(homepage3);
                 window3.show();
             }
        }
    }

    private void ViewClientDashBoardOnClick(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("ClientDashBoardScene.fxml"));
        Scene scene2 = new Scene(scene2Parent);
            
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene2);
        window.show();
    }

    private void ViewOPManagerDashBoardOnClick(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("OperationManagerDashBoard.fxml"));
        Scene scene2 = new Scene(scene2Parent);
            
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene2);
        window.show();
    }

    private void ViewDesignerDashBoardOnClick(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("Graphic Designer.fxml"));
        Scene scene2 = new Scene(scene2Parent);
              
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene2);
        window.show();
    }

    private void ViewDeliveryManDashBoardOnClick(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("DeliveryManDashBoard.fxml"));
        Scene scene2 = new Scene(scene2Parent);
       
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene2);
        window.show();
    }

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
    private void showCompanyHistoryOnClick(ActionEvent event) throws IOException {
        Parent meetParent = FXMLLoader.load(getClass().getResource("CompanyHistory.fxml"));
        Scene meetingViewScene = new Scene(meetParent);
        Stage newWindow  = new Stage();
        
        newWindow.setTitle("CompanyHistory");
        newWindow.setScene(meetingViewScene);
        newWindow.show();
    }
    
}
