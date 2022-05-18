
package mainpkg;

import java.io.Serializable;

public class Purchase implements Serializable{
    String purchasedCardID;
    String purchasedCardType;
    String purchasedCardPrice;
    String clientFirstName;
    String clientLastName;
    String quantity;
    String deliveryDate;
    String clientAccountNo;
    String deliveryAddress;
    String clientCustomText;
    //int sellsCardID;
    
    public Purchase(String purchasedCardID, String purchasedCardType, String purchasedCardPrice, String clientFirstName, String clientLastName, String quantity, String deliveryDate, String clientAccountNo, String deliveryAddress, String clientCustomText) {
        this.purchasedCardID = purchasedCardID;
        this.purchasedCardType = purchasedCardType;
        this.purchasedCardPrice = purchasedCardPrice;
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.quantity = quantity;
        this.deliveryDate = deliveryDate;
        this.clientAccountNo = clientAccountNo;
        this.deliveryAddress = deliveryAddress;
        this.clientCustomText = clientCustomText;
    }

    public String getPurchasedCardID() {
        return purchasedCardID;
    }

    public String getPurchasedCardType() {
        return purchasedCardType;
    }

    public String getPurchasedCardPrice() {
        return purchasedCardPrice;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getClientAccountNo() {
        return clientAccountNo;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getClientCustomText() {
        return clientCustomText;
    }

    public void setPurchasedCardID(String purchasedCardID) {
        this.purchasedCardID = purchasedCardID;
    }

    public void setPurchasedCardType(String purchasedCardType) {
        this.purchasedCardType = purchasedCardType;
    }

    public void setPurchasedCardPrice(String purchasedCardPrice) {
        this.purchasedCardPrice = purchasedCardPrice;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setClientAccountNo(String clientAccountNo) {
        this.clientAccountNo = clientAccountNo;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setClientCustomText(String clientCustomText) {
        this.clientCustomText = clientCustomText;
    }
    
}
