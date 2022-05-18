/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpkg;

import java.io.Serializable;

public class Card implements Serializable{
     int cardID;
     String cardType;
     float price;
     String cardDesignerName;

    public Card(int cardID, String cardType, float price, String cardDesignerName) {
        this.cardID = cardID;
        this.cardType = cardType;
        this.price = price;
        this.cardDesignerName = cardDesignerName;
    }

    public int getCardID() {
        return cardID;
    }

    public String getCardType() {
        return cardType;
    }

    public float getPrice() {
        return price;
    }

    public String getCardDesignerName() {
        return cardDesignerName;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setCardDesignerName(String cardDesignerName) {
        this.cardDesignerName = cardDesignerName;
    }

    @Override
    public String toString() {
        return "Card{" + "cardID=" + cardID + ", cardType=" + cardType + ", price=" + price + ", cardDesignerName=" + cardDesignerName + '}';
    }
     
     

}
