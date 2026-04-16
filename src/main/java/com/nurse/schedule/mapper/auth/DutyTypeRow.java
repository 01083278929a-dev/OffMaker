package com.nurse.schedule.mapper.auth;

import java.time.LocalTime;

public class DutyTypeRow {
    private Long DUTY_TYPE_SEQ;
    private Long USER_SEQ;
    private String DUTY_NM;
    private LocalTime START_TIME;
    private LocalTime END_TIME;
    private String COLOR_CODE;
    private String DUTY_CATEGORY;
    private String OFF_DEDUCT_YN;
    private String USE_YN;

    public DutyTypeRow() {
    }

    public DutyTypeRow(Long DUTY_TYPE_SEQ, Long USER_SEQ, String DUTY_NM, LocalTime START_TIME, LocalTime END_TIME,
                       String COLOR_CODE, String DUTY_CATEGORY, String OFF_DEDUCT_YN, String USE_YN) {
        this.DUTY_TYPE_SEQ = DUTY_TYPE_SEQ;
        this.USER_SEQ = USER_SEQ;
        this.DUTY_NM = DUTY_NM;
        this.START_TIME = START_TIME;
        this.END_TIME = END_TIME;
        this.COLOR_CODE = COLOR_CODE;
        this.DUTY_CATEGORY = DUTY_CATEGORY;
        this.OFF_DEDUCT_YN = OFF_DEDUCT_YN;
        this.USE_YN = USE_YN;
    }

    public Long getDUTY_TYPE_SEQ() {
        return DUTY_TYPE_SEQ;
    }

    public void setDUTY_TYPE_SEQ(Long DUTY_TYPE_SEQ) {
        this.DUTY_TYPE_SEQ = DUTY_TYPE_SEQ;
    }

    public Long getUSER_SEQ() {
        return USER_SEQ;
    }

    public void setUSER_SEQ(Long USER_SEQ) {
        this.USER_SEQ = USER_SEQ;
    }

    public String getDUTY_NM() {
        return DUTY_NM;
    }

    public void setDUTY_NM(String DUTY_NM) {
        this.DUTY_NM = DUTY_NM;
    }

    public LocalTime getSTART_TIME() {
        return START_TIME;
    }

    public void setSTART_TIME(LocalTime START_TIME) {
        this.START_TIME = START_TIME;
    }

    public LocalTime getEND_TIME() {
        return END_TIME;
    }

    public void setEND_TIME(LocalTime END_TIME) {
        this.END_TIME = END_TIME;
    }

    public String getCOLOR_CODE() {
        return COLOR_CODE;
    }

    public void setCOLOR_CODE(String COLOR_CODE) {
        this.COLOR_CODE = COLOR_CODE;
    }

    public String getDUTY_CATEGORY() {
        return DUTY_CATEGORY;
    }

    public void setDUTY_CATEGORY(String DUTY_CATEGORY) {
        this.DUTY_CATEGORY = DUTY_CATEGORY;
    }

    public String getOFF_DEDUCT_YN() {
        return OFF_DEDUCT_YN;
    }

    public void setOFF_DEDUCT_YN(String OFF_DEDUCT_YN) {
        this.OFF_DEDUCT_YN = OFF_DEDUCT_YN;
    }

    public String getUSE_YN() {
        return USE_YN;
    }

    public void setUSE_YN(String USE_YN) {
        this.USE_YN = USE_YN;
    }
}

