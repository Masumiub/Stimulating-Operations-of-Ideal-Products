/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpkg;

import java.io.Serializable;

public class NewAssignedGraphicDesigner implements Serializable{
    private String purchasedCardID;
    private String clientFirstName;
    private String clientLastName;
    private String deliveryDate;
    private String deliveryAddress;
    private String clientCustomText;
    private String assignedDesignerName;

    public NewAssignedGraphicDesigner(String purchasedCardID, String clientFirstName, String clientLastName, String deliveryDate, String deliveryAddress, String clientCustomText, String assignedDesignerName) {
        this.purchasedCardID = purchasedCardID;
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.deliveryDate = deliveryDate;
        this.deliveryAddress = deliveryAddress;
        this.clientCustomText = clientCustomText;
        this.assignedDesignerName = assignedDesignerName;
    }

    public String getPurchasedCardID() {
        return purchasedCardID;
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

    public String getClientCustomText() {
        return clientCustomText;
    }

    public String getAssignedDesignerName() {
        return assignedDesignerName;
    }

    public void setPurchasedCardID(String purchasedCardID) {
        this.purchasedCardID = purchasedCardID;
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

    public void setClientCustomText(String clientCustomText) {
        this.clientCustomText = clientCustomText;
    }

    public void setAssignedDesignerName(String assignedDesignerName) {
        this.assignedDesignerName = assignedDesignerName;
    }

    @Override
    public String toString() {
        return "NewAssignedGraphicDesigner{" + "purchasedCardID=" + purchasedCardID + ", clientFirstName=" + clientFirstName + ", clientLastName=" + clientLastName + ", deliveryDate=" + deliveryDate + ", deliveryAddress=" + deliveryAddress + ", clientCustomText=" + clientCustomText + ", assignedDesignerName=" + assignedDesignerName + '}';
    }

    
    
    
}
