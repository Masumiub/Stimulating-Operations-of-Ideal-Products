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

public class MeetingSchedule implements Serializable{
    private String meetingTopic;
    private String meetingDate;
    private String meetingTime;

    public MeetingSchedule(String meetingTopic, String meetingDate, String meetingTime) {
        this.meetingTopic = meetingTopic;
        this.meetingDate = meetingDate;
        this.meetingTime = meetingTime;
    }

    public String getMeetingTopic() {
        return meetingTopic;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public String getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTopic(String meetingTopic) {
        this.meetingTopic = meetingTopic;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }

    @Override
    public String toString() {
        return "MeetingSchedule{" + "meetingTopic=" + meetingTopic + ", meetingDate=" + meetingDate + ", meetingTime=" + meetingTime + '}';
    }
    
    
}
