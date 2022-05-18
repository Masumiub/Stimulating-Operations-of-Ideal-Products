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

public class OperationManager extends User implements Serializable{

    public OperationManager(String userID, String password, String userType, String userFullName) {
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
    /*
    
    public boolean AssignADesigner(String cardID, String clientFirstName, String clientLastName, String deliveryDate, String address, 
    String customText, String designerName){
        File f = null;
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try {
            f = new File("assignedGraphicDesignerList.bin");
            if(f.exists()) {
                fos = new FileOutputStream(f,true);
                oos=new AppendableObjectOutputStream(fos);
            }
            else{
                fos=new FileOutputStream(f);
                oos=new ObjectOutputStream(fos);
            }
            NewAssignedGraphicDesigner newDesigner = new NewAssignedGraphicDesigner(
                    cardID,
                    clientFirstName, clientLastName,
                    deliveryDate,  address,
                    customText, designerName
            );
            oos.writeObject(newDesigner);
            
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
    }
    */
    
    /*
    public boolean addANewCard(){
    
    }
    */
}
