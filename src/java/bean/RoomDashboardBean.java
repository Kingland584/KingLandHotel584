/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author mohd azrin
 */
public class RoomDashboardBean implements java.io.Serializable{
    public String roomID;
    public String roomNum;
    public String roomStatus;
    public String roomType;

    public RoomDashboardBean() {
    }

    public RoomDashboardBean(String roomID, String roomNum, String roomStatus, String roomType) {
        this.roomID = roomID;
        this.roomNum = roomNum;
        this.roomStatus = roomStatus;
        this.roomType = roomType;
    }

    public String getRoomID() {
        return roomID;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
    
}
