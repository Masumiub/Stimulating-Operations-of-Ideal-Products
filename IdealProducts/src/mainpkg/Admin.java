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

/**
 *
 * @author movie
 */
public class Admin extends User implements Serializable{

    public Admin(String userID, String password, String userType, String userFullName) {
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

    /*@Override
    public String toString() {
        return super.toString();
    }*/

    @Override
    public String toString() {
        return "Admin{" + '}';
    }
    
    /*
    public boolean makeMeetingSchedule(String meetingTopic, String dateOfMeeting, String meetingTime){
        File f = null;
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try {
          
            f = new File("meetingSchedulelist.bin");
            if(f.exists()) {
                fos = new FileOutputStream(f,true);
                oos=new AppendableObjectOutputStream(fos);
            }
            else{
                fos=new FileOutputStream(f);
                oos=new ObjectOutputStream(fos);
            }
            
            MeetingSchedule newMeetingSchedule = new MeetingSchedule(
               meetingTopic, 
               dateOfMeeting,
               meetingTime     
               );
            
            oos.writeObject(newMeetingSchedule);
           
            
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
    
    /*
    public void show piechartOFpopular typeCards(){
    
    }
    */
    /*
    public void boolean reviewLeaveApplication(String employeeName, String post, String leaveStartDate, String leaveEndDate, 
    String details, String approveStatus){
        File f = null;
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try {
          
            f = new File("reviewedLeaveApplicationDList.bin");
            if(f.exists()) {
                fos = new FileOutputStream(f,true);
                oos=new AppendableObjectOutputStream(fos);
            }
            else{
                fos=new FileOutputStream(f);
                oos=new ObjectOutputStream(fos);
            }
            
            ReivewedLeaveApplication newReviewApp = new ReivewedLeaveApplication(
               employeeName, post, leaveStartDate, leaveEndDate,
                    details,approveStatus
               );
            
            oos.writeObject(newReviewApp);
            
            
            } catch (IOException ex) {
            Logger.getLogger(ReviewLeaveApplicationSceneController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(oos != null) oos.close();
                } catch (IOException ex) {
                Logger.getLogger(ReviewLeaveApplicationSceneController.class.getName()).log(Level.SEVERE, null, ex);
          }
                
      }
    return true;
    }
    
    */
    
    /*
    public boolean updateSalary(){
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
    */
}
