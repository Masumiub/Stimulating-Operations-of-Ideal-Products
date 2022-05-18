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
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeliveryMan extends User implements Serializable{
    
    //private String emailID;
    //private String phoneNo;
    
    public DeliveryMan(String userID, String password, String userType, String userFullName) {
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
    
    /*public static boolean ConfirmDelivery(String cardID, String clientFirstName, String clientLastName, String deliveryDate,
    String deliveredBy, String paymentStatus, String deliveryStatus){
        File f = null;
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try {
          
            f = new File("deliveredCardList.bin");
            if(f.exists()) {
                fos = new FileOutputStream(f,true);
                oos=new AppendableObjectOutputStream(fos);
            }
            else{
                fos=new FileOutputStream(f);
                oos=new ObjectOutputStream(fos);
            }
            
            DeliveredCard newDeliveredCard = new DeliveredCard(
               cardID, clientFirstName, clientLastName, deliveryDate,
                 deliveredBy, paymentStatus, deliveryStatus 
               );
            
            oos.writeObject(newDeliveredCard);
          
            } catch (IOException ex) {
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
    public boolean attendance(,,,,){
           File f = null;
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try {
          
            f = new File("attendanceListofDeliveryMan.bin");
            if(f.exists()) {
                fos = new FileOutputStream(f,true);
                oos=new AppendableObjectOutputStream(fos);
            }
            else{
                fos=new FileOutputStream(f);
                oos=new ObjectOutputStream(fos);
            }
            
            AttendaceOfDeliveryMan newAttendance = new AttendaceOfDeliveryMan(
               deliveryManNameTextField.getText(),
               postTextField.getText(), 
               workingDateTextField.getValue().toString(),
               (String)attendanceStatusComboBox.getSelectionModel().getSelectedItem()
               );
            
            oos.writeObject(newAttendance);
            //deliveryManNameTextField.clear();
            
            
            } catch (IOException ex) {
            Logger.getLogger(OperationManagerDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(oos != null) oos.close();
                } catch (IOException ex) {
                Logger.getLogger(OperationManagerDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    }
    */
    
    /*
    public boolean ApplyforLeave(){
        File f = null;
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try {
          
            f = new File("leaveApplicationOFDeliveryManlist.bin");
            if(f.exists()) {
                fos = new FileOutputStream(f,true);
                oos=new AppendableObjectOutputStream(fos);
            }
            else{
                fos=new FileOutputStream(f);
                oos=new ObjectOutputStream(fos);
            }
            
            LeaveApplyForm newLeaveApplication = new LeaveApplyForm(
               employeeNameTextField.getText(), 
               designationTextField.getText(),
               applicationSubjectTextField.getText(), 
               leaveStartDateTextField.getValue().toString(),
               leaveEndDateTextField.getValue().toString(),
               detailsTextField.getText()     
               );
            
            oos.writeObject(newLeaveApplication);
           
            } catch (IOException ex) {
            Logger.getLogger(LeaveApplicationController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(oos != null) oos.close();
                } catch (IOException ex) {
                Logger.getLogger(LeaveApplicationController.class.getName()).log(Level.SEVERE, null, ex);
          }
      } 
    }
    */
}
