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

public class Feedback implements Serializable{
    private String clientFullName;
    private String feedbackDate;
    private String feedbackDetails;

    public Feedback(String clientFullName, String feedbackDate, String feedbackDetails) {
        this.clientFullName = clientFullName;
        this.feedbackDate = feedbackDate;
        this.feedbackDetails = feedbackDetails;
    }

    public String getClientFullName() {
        return clientFullName;
    }

    public String getFeedbackDate() {
        return feedbackDate;
    }

    public String getFeedbackDetails() {
        return feedbackDetails;
    }

    public void setClientFullName(String clientFullName) {
        this.clientFullName = clientFullName;
    }

    public void setFeedbackDate(String feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public void setFeedbackDetails(String feedbackDetails) {
        this.feedbackDetails = feedbackDetails;
    }

    @Override
    public String toString() {
        return "Feedback{" + "clientFullName=" + clientFullName + ", feedbackDate=" + feedbackDate + ", feedbackDetails=" + feedbackDetails + '}';
    }
    
    
}
