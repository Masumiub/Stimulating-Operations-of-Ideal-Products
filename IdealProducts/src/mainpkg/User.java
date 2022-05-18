/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpkg;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author movie
 */
public abstract class User implements Serializable{
    protected String userID;
    protected String password;
    protected String userType;
    protected String userFullName;

    public User(String userID, String password, String userType, String userFullName) {
        this.userID = userID;
        this.password = password;
        this.userType = userType;
        this.userFullName = userFullName;
    }

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    public abstract void addUser();

    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", password=" + password + ", userType=" + userType + ", userFullName=" + userFullName + '}';
    }
    
    
    public static ArrayList<User> listOfUser(){
        ArrayList<User>uList = new ArrayList<>();
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        
        try {
            f = new File("User.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            User u;
            try{
                while(true){
                    u = (User)ois.readObject();
                    uList.add(u);
                }
            }
            catch(Exception e){}                
        } 
        catch (IOException ex) { } 
        finally {
            try {
                if(ois != null) {
                    ois.close();
                }
            } 
            catch (IOException ex){}
        }    
        return uList;
    }
    public static User verifylogin(String userID, String password, String userType){
        ArrayList<User>uList = User.listOfUser();
        for(User i: uList){
            if(i.getUserID().equals(userID) && i.getPassword().equals(password) && i.getUserType().equals(userType)){
                return i;
            }
        }
        return null;
    }
}
