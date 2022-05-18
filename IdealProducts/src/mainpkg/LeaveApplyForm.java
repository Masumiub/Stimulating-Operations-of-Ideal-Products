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

public class LeaveApplyForm implements Serializable{
    private String employeeFullName;
    private String employeeDesignation;
    private String applicationSubject;
    private String leaveStartDate;
    private String leaveEndDate;
    private String details;

    public LeaveApplyForm(String employeeFullName, String employeeDesignation, String applicationSubject, String leaveStartDate, String leaveEndDate, String details) {
        this.employeeFullName = employeeFullName;
        this.employeeDesignation = employeeDesignation;
        this.applicationSubject = applicationSubject;
        this.leaveStartDate = leaveStartDate;
        this.leaveEndDate = leaveEndDate;
        this.details = details;
    }

    public String getEmployeeFullName() {
        return employeeFullName;
    }

    public String getEmployeeDesignation() {
        return employeeDesignation;
    }

    public String getApplicationSubject() {
        return applicationSubject;
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

    public void setEmployeeFullName(String employeeFullName) {
        this.employeeFullName = employeeFullName;
    }

    public void setEmployeeDesignation(String employeeDesignation) {
        this.employeeDesignation = employeeDesignation;
    }

    public void setApplicationSubject(String applicationSubject) {
        this.applicationSubject = applicationSubject;
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

    @Override
    public String toString() {
        return "LeaveApplyForm{" + "employeeFullName=" + employeeFullName + ", employeeDesignation=" + employeeDesignation + ", applicationSubject=" + applicationSubject + ", leaveStartDate=" + leaveStartDate + ", leaveEndDate=" + leaveEndDate + ", details=" + details + '}';
    }
    
    
}
