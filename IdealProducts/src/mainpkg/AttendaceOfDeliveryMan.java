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

public class AttendaceOfDeliveryMan implements Serializable{
    private String fullname;
    private String post;
    private String workingDate;
    private String attendanceStatus;

    public AttendaceOfDeliveryMan(String fullname, String post, String workingDate, String attendanceStatus) {
        this.fullname = fullname;
        this.post = post;
        this.workingDate = workingDate;
        this.attendanceStatus = attendanceStatus;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPost() {
        return post;
    }

    public String getWorkingDate() {
        return workingDate;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setWorkingDate(String workingDate) {
        this.workingDate = workingDate;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    @Override
    public String toString() {
        return "AttendaceOfDeliveryMan{" + "fullname=" + fullname + ", post=" + post + ", workingDate=" + workingDate + ", attendanceStatus=" + attendanceStatus + '}';
    }
    
           
}
