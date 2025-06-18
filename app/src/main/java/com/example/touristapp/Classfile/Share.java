package com.example.touristapp.Classfile;

public class Share {

        private String comment;
        private String userName;
        private String date;
        private String location;

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Share() {
            // Default constructor required for calls to DataSnapshot.getValue(UserReaction.class)
        }

        public Share(String comment, String userName, String date) {
            this.comment = comment;
            this.userName = userName;
            this.date = date;
        }

        public String getComment() {
            return comment;
        }

        public String getUserName() {
            return userName;
        }

        public String getDate() {
            return date;
        }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
