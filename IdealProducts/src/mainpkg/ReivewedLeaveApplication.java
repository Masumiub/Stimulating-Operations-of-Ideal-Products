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

public class ReivewedLeaveApplication implements Serializable{
    private String employeeName;
    private String post;
    private String leaveStartDate;
    private String leaveEndDate;
    private String details;
    private String approvalStatus;

    public ReivewedLeaveApplication(String employeeName, String post, String leaveStartDate, String leaveEndDate, String details, String approvalStatus) {
        this.employeeName = employeeName;
        this.post = post;
        this.leaveStartDate = leaveStartDate;
        this.leaveEndDate = leaveEndDate;
        this.details = details;
        this.approvalStatus = approvalStatus;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getPost() {
        return post;
    }

    public String getLeaveStartDate() {
        return leaveStartDate;
    }

    public String getLeaveEndDate() {
        return leaveEndDate;
    }

    public String getDetails() {
        return details;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setLeaveStartDate(String leaveStartDate) {
        this.leaveStartDate = leaveStartDate;
    }

    public void setLeaveEndDate(String leaveEndDate) {
        this.leaveEndDate = leaveEndDate;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    @Override
    public String toString() {
        return "ReivewedLeaveApplication{" + "employeeName=" + employeeName + ", post=" + post + ", leaveStartDate=" + leaveStartDate + ", leaveEndDate=" + leaveEndDate + ", details=" + details + ", approvalStatus=" + approvalStatus + '}';
    }
    
    
}
