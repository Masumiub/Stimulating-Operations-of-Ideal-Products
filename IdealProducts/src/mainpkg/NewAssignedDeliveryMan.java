/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpkg;

/**
 *
 * @author movie
 */
import java.io.Serializable;

public class NewAssignedDeliveryMan implements Serializable{
    private String cardID;
    private String clientFirstName;
    private String clientLastName;
    private String deliveryDate;
    private String deliveryAddress;
    private String customizationStatus;
    private String deliveryManName;

    public NewAssignedDeliveryMan(String cardID, String clientFirstName, String clientLastName, String deliveryDate, String deliveryAddress, String customizationStatus, String deliveryManName) {
        this.cardID = cardID;
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.deliveryDate = deliveryDate;
        this.deliveryAddress = deliveryAddress;
        this.customizationStatus = customizationStatus;
        this.deliveryManName = deliveryManName;
    }

    public String getCardID() {
        return cardID;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getCustomizationStatus() {
        return customizationStatus;
    }

    public String getDeliveryManName() {
        return deliveryManName;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setCustomizationStatus(String customizationStatus) {
        this.customizationStatus = customizationStatus;
    }

    public void setDeliveryManName(String deliveryManName) {
        this.deliveryManName = deliveryManName;
    }

    @Override
    public String toString() {
        return "NewAssignedDeliveryMan{" + "cardID=" + cardID + ", clientFirstName=" + clientFirstName + ", clientLastName=" + clientLastName + ", deliveryDate=" + deliveryDate + ", deliveryAddress=" + deliveryAddress + ", customizationStatus=" + customizationStatus + ", deliveryManName=" + deliveryManName + '}';
    }

    

    
    
    
}
