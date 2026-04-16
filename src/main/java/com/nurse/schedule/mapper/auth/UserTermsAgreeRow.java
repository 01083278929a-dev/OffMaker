package com.nurse.schedule.mapper.auth;

public class UserTermsAgreeRow {
    private Long AGREE_SEQ;
    private Long USER_SEQ;
    private String SERVICE_TERMS_YN;
    private String PRIVACY_POLICY_YN;
    private String MARKETING_RECV_YN;

    public UserTermsAgreeRow() {
    }

    public UserTermsAgreeRow(Long AGREE_SEQ, Long USER_SEQ, String SERVICE_TERMS_YN, String PRIVACY_POLICY_YN, String MARKETING_RECV_YN) {
        this.AGREE_SEQ = AGREE_SEQ;
        this.USER_SEQ = USER_SEQ;
        this.SERVICE_TERMS_YN = SERVICE_TERMS_YN;
        this.PRIVACY_POLICY_YN = PRIVACY_POLICY_YN;
        this.MARKETING_RECV_YN = MARKETING_RECV_YN;
    }

    public Long getAGREE_SEQ() {
        return AGREE_SEQ;
    }

    public void setAGREE_SEQ(Long AGREE_SEQ) {
        this.AGREE_SEQ = AGREE_SEQ;
    }

    public Long getUSER_SEQ() {
        return USER_SEQ;
    }

    public void setUSER_SEQ(Long USER_SEQ) {
        this.USER_SEQ = USER_SEQ;
    }

    public String getSERVICE_TERMS_YN() {
        return SERVICE_TERMS_YN;
    }

    public void setSERVICE_TERMS_YN(String SERVICE_TERMS_YN) {
        this.SERVICE_TERMS_YN = SERVICE_TERMS_YN;
    }

    public String getPRIVACY_POLICY_YN() {
        return PRIVACY_POLICY_YN;
    }

    public void setPRIVACY_POLICY_YN(String PRIVACY_POLICY_YN) {
        this.PRIVACY_POLICY_YN = PRIVACY_POLICY_YN;
    }

    public String getMARKETING_RECV_YN() {
        return MARKETING_RECV_YN;
    }

    public void setMARKETING_RECV_YN(String MARKETING_RECV_YN) {
        this.MARKETING_RECV_YN = MARKETING_RECV_YN;
    }
}

