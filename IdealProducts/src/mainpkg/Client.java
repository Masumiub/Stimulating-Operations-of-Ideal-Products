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

public class Client extends User implements Serializable{

    public Client(String userID, String password, String userType, String userFullName) {
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
    
    /*
    public boolean SendFeedback(String clientFullName, String feedbackSubmitDate, String feedbackDetails){
    
    File f = null;
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try {
          
            f = new File("clientFeedbackList.bin");
            if(f.exists()) {
                fos = new FileOutputStream(f,true);
                oos=new AppendableObjectOutputStream(fos);
            }
            else{
                fos=new FileOutputStream(f);
                oos=new ObjectOutputStream(fos);
            }
            
            Feedback newFeedback = new Feedback(
               clientFullName, feedbackSubmitDate, feedbackDetails
               );
            
            oos.writeObject(newFeedback);
           //feedbackDetailsTextArea.clear();
           
            } catch (IOException ex) {
            Logger.getLogger(SendFeedbackSceneController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(oos != null) oos.close();
                } catch (IOException ex) {
                Logger.getLogger(SendFeedbackSceneController.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
        return true;
    
    }*/
    /*
    public boolean purchaseACard(,,,,){
            File f = null;
            FileOutputStream fos=null;
            ObjectOutputStream oos=null;
            try {
            f = new File("purchaseList.bin");
            if(f.exists()) {
                fos = new FileOutputStream(f,true);
                oos=new AppendableObjectOutputStream(fos);
            }
            else{
                fos=new FileOutputStream(f);
                oos=new ObjectOutputStream(fos);
            }
            Purchase newCardPurchase = new Purchase(
                    selectedCardIDTextArea.getText(), selectedCardType.getText(),selectedCardPrice.getText(),
                    clientFirstNameTextField.getText(), clientLastNameTextField.getText(), quantityTextField.getText(),
                    deliveryDateTextField.getValue().toString(), accountNoTextField.getText(), deliveryAddressTextField.getText(),
                    customTextField.getText()
            );
            oos.writeObject(newCardPurchase);
            
            }
            catch (IOException ex) {
            Logger.getLogger(CardPurchaseFormController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(oos != null) oos.close();
                } catch (IOException ex) {
                Logger.getLogger(CardPurchaseFormController.class.getName()).log(Level.SEVERE, null, ex);
          }
            
        }
    }
    */
    
}
