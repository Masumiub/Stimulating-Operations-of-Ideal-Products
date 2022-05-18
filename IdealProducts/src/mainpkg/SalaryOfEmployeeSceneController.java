/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpkg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author movie
 */
public class SalaryOfEmployeeSceneController implements Initializable {

    @FXML
    private TextArea showSalaryTextArea;
    @FXML
    private CheckBox updateSalaryCheckBox;
    @FXML
    private TextArea updateSalaryTextArea;
    
    private ArrayList<String> fileTypeList, fileTypeList2; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fileTypeList = new ArrayList<String>();
        fileTypeList.add("*.txt");
        fileTypeList.add("*.doc");
        fileTypeList.add("*.docx");
        fileTypeList.add("*.TXT");
        fileTypeList.add("*.DOC");
        fileTypeList.add("*.DOCX");

        fileTypeList2 = new ArrayList<String>();
        fileTypeList2.add("*.*");
    }    

    @FXML
    private void loadSalaryFileOnClick(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", fileTypeList));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("All files", fileTypeList2));
        File f = fc.showOpenDialog(null);
        if(f != null){
            try {
                Scanner sc = new Scanner(f);
                String str="";
                while(sc.hasNextLine()){
                    str+=sc.nextLine()+"\n";
                }
                showSalaryTextArea.setText(str);
            } catch (FileNotFoundException ex) {
                //Logger.getLogger(FileChooserViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void updateEditSalaryFileOnCheckBox(ActionEvent event) {
        if(updateSalaryCheckBox.isSelected()){
            updateSalaryTextArea.setEditable(true);
            updateSalaryTextArea.setText(showSalaryTextArea.getText());
        }
        else{
            updateSalaryTextArea.setEditable(false);
            updateSalaryTextArea.setText(null);
        }
    }
    @FXML
    private void updateSalaryFileOnClick(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        File f = fc.showSaveDialog(null);
        FileWriter fw = null;
        fw = new FileWriter(f);
        try {
            //f = new File("Emp.txt");      
            if(f.exists()) fw = new FileWriter(f,true);
            else fw = new FileWriter(f);
           
            fw.write(updateSalaryTextArea.getText());           
            
        } catch (IOException ex) {
            Logger.getLogger(SalaryOfEmployeeSceneController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(fw != null) fw.close();
            } catch (IOException ex) {
                Logger.getLogger(SalaryOfEmployeeSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void goBackOnClick(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("AdminDashBoard.fxml"));
        Scene home = new Scene(scene2Parent);
            
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(home);
        window.show();
    }

    
    
}
