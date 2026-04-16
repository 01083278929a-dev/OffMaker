package com.nurse.schedule.mapper.auth;

import java.time.LocalDate;

public class UserInfoRow {
    private Long USER_SEQ;
    private String USER_NM;
    private String PHONE_NUM;
    private String USER_CI;
    private String USER_DI;
    private LocalDate BIRTH_DATE;
    private String GENDER;

    public UserInfoRow() {
    }

    public UserInfoRow(Long USER_SEQ, String USER_NM, String PHONE_NUM, String USER_CI, String USER_DI, LocalDate BIRTH_DATE, String GENDER) {
        this.USER_SEQ = USER_SEQ;
        this.USER_NM = USER_NM;
        this.PHONE_NUM = PHONE_NUM;
        this.USER_CI = USER_CI;
        this.USER_DI = USER_DI;
        this.BIRTH_DATE = BIRTH_DATE;
        this.GENDER = GENDER;
    }

    public Long getUSER_SEQ() {
        return USER_SEQ;
    }

    public void setUSER_SEQ(Long USER_SEQ) {
        this.USER_SEQ = USER_SEQ;
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

    public String getUSER_CI() {
        return USER_CI;
    }

    public void setUSER_CI(String USER_CI) {
        this.USER_CI = USER_CI;
    }

    public String getUSER_DI() {
        return USER_DI;
    }

    public void setUSER_DI(String USER_DI) {
        this.USER_DI = USER_DI;
    }

    public LocalDate getBIRTH_DATE() {
        return BIRTH_DATE;
    }

    public void setBIRTH_DATE(LocalDate BIRTH_DATE) {
        this.BIRTH_DATE = BIRTH_DATE;
    }

    public String getGENDER() {
        return GENDER;
    }

    public void setGENDER(String GENDER) {
        this.GENDER = GENDER;
    }
}

