/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpkg;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author movie
 */
public class MeetingScheduleSceneController implements Initializable {

    @FXML
    private TableView<MeetingSchedule> meetScheduleTableView;
    @FXML
    private TableColumn<MeetingSchedule, String> meetingTopicCol;
    @FXML
    private TableColumn<MeetingSchedule, String> meetingDateCol;
    @FXML
    private TableColumn<MeetingSchedule, String> meetingTimeCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        meetingTopicCol.setCellValueFactory(new PropertyValueFactory<MeetingSchedule, String>("meetingTopic"));
        meetingDateCol.setCellValueFactory(new PropertyValueFactory<MeetingSchedule, String>("meetingDate"));
        meetingTimeCol.setCellValueFactory(new PropertyValueFactory<MeetingSchedule, String>("meetingTime"));
        meetingScheduleList();
    }    
    private void meetingScheduleList(){
        ObjectInputStream ois=null;
        try {
            MeetingSchedule meeting;
            ois = new ObjectInputStream(new FileInputStream("meetingSchedulelist.bin"));
            while(true){
            meeting = (MeetingSchedule) ois.readObject();
            meetScheduleTableView.getItems().add(0, meeting);
            }   
        }catch (Exception ex) {
            try {
                if(ois!=null)
                    ois.close();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        }
    }
}
