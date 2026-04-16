package com.nurse.schedule.mapper.auth;

public class UserAccountRow {
    private Long ACC_SEQ;
    private Long USER_SEQ;
    private String LOGIN_ID;
    private String LOGIN_PW;

    public UserAccountRow() {
    }

    public UserAccountRow(Long ACC_SEQ, Long USER_SEQ, String LOGIN_ID, String LOGIN_PW) {
        this.ACC_SEQ = ACC_SEQ;
        this.USER_SEQ = USER_SEQ;
        this.LOGIN_ID = LOGIN_ID;
        this.LOGIN_PW = LOGIN_PW;
    }

    public Long getACC_SEQ() {
        return ACC_SEQ;
    }

    public void setACC_SEQ(Long ACC_SEQ) {
        this.ACC_SEQ = ACC_SEQ;
    }

    public Long getUSER_SEQ() {
        return USER_SEQ;
    }

    public void setUSER_SEQ(Long USER_SEQ) {
        this.USER_SEQ = USER_SEQ;
    }

    public String getLOGIN_ID() {
        return LOGIN_ID;
    }

    public void setLOGIN_ID(String LOGIN_ID) {
        this.LOGIN_ID = LOGIN_ID;
    }

    public String getLOGIN_PW() {
        return LOGIN_PW;
    }

    public void setLOGIN_PW(String LOGIN_PW) {
        this.LOGIN_PW = LOGIN_PW;
    }
}

