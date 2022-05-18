/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpkg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author movie
 */
public class GraphicDesigner extends User implements Serializable{

    public GraphicDesigner(String userID, String password, String userType, String userFullName) {
        super(userID, password, userType, userFullName);
    }
        @Override
    public void addUser() {
        //int num1 = 4;
        //int num2 = 5;
        // res = num1+num2;
        File f = null;
        FileOutputStream fos = null;      
        ObjectOutputStream oos = null;
        
        try {
            f = new File("User.bin");
            if(f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);               
            }
            oos.writeObject(this);

        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(oos != null) oos.close();
            } catch (IOException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
    /*public static boolean AssignDeliveryMan(){
            File f = null;
            FileOutputStream fos=null;
            ObjectOutputStream oos=null;
            try {
            f = new File("assignedDeliveryManList.bin");
            if(f.exists()) {
                fos = new FileOutputStream(f,true);
                oos=new AppendableObjectOutputStream(fos);
            }
            else{
                fos=new FileOutputStream(f);
                oos=new ObjectOutputStream(fos);
            }
            NewAssignedDeliveryMan newDeliveryMan = new NewAssignedDeliveryMan(
                    cardIDLabel.getText(),
                    firstNameLabel.getText(), lastNameLabel.getText(),
                    deliveryDateLabel.getText(),  deliveryAddressLabel.getText(),
                    customizationStatusComboBox.getSelectionModel().getSelectedItem(), deliveryManNameTextField.getText()
            );
            oos.writeObject(newDeliveryMan);
            
            }
            catch (IOException ex) {
            Logger.getLogger(OperationManagerDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(oos != null) oos.close();
                } catch (IOException ex) {
                Logger.getLogger(OperationManagerDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
          }
               
        }
        return true;
        
    }*/
    
    /*
    public void showMeetingList(){
    }
    */
    
    
    /*public static boolean AddWorkSchedule(String workingCardID, String myWorkingDate, String deiveryDate, String details){
        File f = null;
        FileWriter fw = null;

        try {
        f = new File("WorkScheduleListofDesigner.txt");      

            if(f.exists()) fw = new FileWriter(f,true);
            else fw = new FileWriter(f);
           
            fw.write(
            	workingCardID+","
                +myWorkingDate+","
                +deiveryDate+","
                +details+"\n"	
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
        return true;
    }*/
}