package com.nurse.schedule.mapper.auth;

public class AuthHistoryRow {
    private Long AUTH_SEQ;
    private String USER_NM;
    private String PHONE_NUM;
    private String AUTH_CODE;
    private String TX_ID;
    private String AUTH_STATUS;

    public AuthHistoryRow() {
    }

    public AuthHistoryRow(Long AUTH_SEQ, String USER_NM, String PHONE_NUM, String AUTH_CODE, String TX_ID, String AUTH_STATUS) {
        this.AUTH_SEQ = AUTH_SEQ;
        this.USER_NM = USER_NM;
        this.PHONE_NUM = PHONE_NUM;
        this.AUTH_CODE = AUTH_CODE;
        this.TX_ID = TX_ID;
        this.AUTH_STATUS = AUTH_STATUS;
    }

    public Long getAUTH_SEQ() {
        return AUTH_SEQ;
    }

    public void setAUTH_SEQ(Long AUTH_SEQ) {
        this.AUTH_SEQ = AUTH_SEQ;
    }

    public String getUSER_NM() {
        return USER_NM;
    }

    public void setUSER_NM(String USER_NM) {
        this.USER_NM = USER_NM;
    }

    public String getPHONE_NUM() {
        return PHONE_NUM;
    }

    public void setPHONE_NUM(String PHONE_NUM) {
        this.PHONE_NUM = PHONE_NUM;
    }

    public String getAUTH_CODE() {
        return AUTH_CODE;
    }

    public void setAUTH_CODE(String AUTH_CODE) {
        this.AUTH_CODE = AUTH_CODE;
    }

    public String getTX_ID() {
        return TX_ID;
    }

    public void setTX_ID(String TX_ID) {
        this.TX_ID = TX_ID;
    }

    public String getAUTH_STATUS() {
        return AUTH_STATUS;
    }

    public void setAUTH_STATUS(String AUTH_STATUS) {
        this.AUTH_STATUS = AUTH_STATUS;
    }
}

