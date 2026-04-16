package com.nurse.schedule.mapper.auth;

public class VacationMgmtRow {
    private Long VAC_SEQ;
    private Long USER_SEQ;
    private String BASE_YEAR;
    private Double TOTAL_DAYS;
    private Double USED_DAYS;
    private Double REMAIN_DAYS;

    public VacationMgmtRow() {
    }

    public VacationMgmtRow(Long VAC_SEQ, Long USER_SEQ, String BASE_YEAR, Double TOTAL_DAYS, Double USED_DAYS, Double REMAIN_DAYS) {
        this.VAC_SEQ = VAC_SEQ;
        this.USER_SEQ = USER_SEQ;
        this.BASE_YEAR = BASE_YEAR;
        this.TOTAL_DAYS = TOTAL_DAYS;
        this.USED_DAYS = USED_DAYS;
        this.REMAIN_DAYS = REMAIN_DAYS;
    }

    public Long getVAC_SEQ() {
        return VAC_SEQ;
    }

    public void setVAC_SEQ(Long VAC_SEQ) {
        this.VAC_SEQ = VAC_SEQ;
    }

    public Long getUSER_SEQ() {
        return USER_SEQ;
    }

    public void setUSER_SEQ(Long USER_SEQ) {
        this.USER_SEQ = USER_SEQ;
    }

    public String getBASE_YEAR() {
        return BASE_YEAR;
    }

    public void setBASE_YEAR(String BASE_YEAR) {
        this.BASE_YEAR = BASE_YEAR;
    }

    public Double getTOTAL_DAYS() {
        return TOTAL_DAYS;
    }

    public void setTOTAL_DAYS(Double TOTAL_DAYS) {
        this.TOTAL_DAYS = TOTAL_DAYS;
    }

    public Double getUSED_DAYS() {
        return USED_DAYS;
    }

    public void setUSED_DAYS(Double USED_DAYS) {
        this.USED_DAYS = USED_DAYS;
    }

    public Double getREMAIN_DAYS() {
        return REMAIN_DAYS;
    }

    public void setREMAIN_DAYS(Double REMAIN_DAYS) {
        this.REMAIN_DAYS = REMAIN_DAYS;
    }
}

