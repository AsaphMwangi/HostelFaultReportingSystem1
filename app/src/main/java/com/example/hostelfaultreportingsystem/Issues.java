package com.example.hostelfaultreportingsystem;

public class Issues {
    public Issues() {
    }

    String IssueTitle, HostelName, RoomNo, StudentEmail,ImageAttached,Status;

    public Issues(String issueTitle, String hostelName, String roomNo, String studentEmail, String imageAttached, String status) {
        IssueTitle = issueTitle;
        HostelName = hostelName;
        RoomNo = roomNo;
        StudentEmail = studentEmail;
        ImageAttached=imageAttached;
        Status =status;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getImageAttached() {
        return ImageAttached;
    }

    public void setImageAttached(String imageAttached) {
        ImageAttached = imageAttached;
    }

    public String getIssueTitle() {
        return IssueTitle;
    }

    public void setIssueTitle(String issueTitle) {
        IssueTitle = issueTitle;
    }

    public String getHostelName() {
        return HostelName;
    }

    public void setHostelName(String hostelName) {
        HostelName = hostelName;
    }

    public String getRoomNo() {
        return RoomNo;
    }

    public void setRoomNo(String roomNo) {
        RoomNo = roomNo;
    }

    public String getStudentEmail() {
        return StudentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        StudentEmail = studentEmail;
    }
}