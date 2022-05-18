
package mainpkg;

import java.io.Serializable;

public class DeliveredCard implements Serializable{
    private String deliveredCardID;
    private String clientFirstName;
    private String clientLastName;
    private String deliverydate;
    private String deliveredBy;
    private String paymentStatus;
    private String deliveryStatus;

    public DeliveredCard(String deliveredCardID, String clientFirstName, String clientLastName, String deliverydate, String deliveredBy, String paymentStatus, String deliveryStatus) {
        this.deliveredCardID = deliveredCardID;
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.deliverydate = deliverydate;
        this.deliveredBy = deliveredBy;
        this.paymentStatus = paymentStatus;
        this.deliveryStatus = deliveryStatus;
    }

    public String getDeliveredCardID() {
        return deliveredCardID;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public String getDeliverydate() {
        return deliverydate;
    }

    public String getDeliveredBy() {
        return deliveredBy;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveredCardID(String deliveredCardID) {
        this.deliveredCardID = deliveredCardID;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public void setDeliverydate(String deliverydate) {
        this.deliverydate = deliverydate;
    }

    public void setDeliveredBy(String deliveredBy) {
        this.deliveredBy = deliveredBy;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    @Override
    public String toString() {
        return "DeliveredCard{" + "deliveredCardID=" + deliveredCardID + ", clientFirstName=" + clientFirstName + ", clientLastName=" + clientLastName + ", deliverydate=" + deliverydate + ", deliveredBy=" + deliveredBy + ", paymentStatus=" + paymentStatus + ", deliveryStatus=" + deliveryStatus + '}';
    }
    
    
}
