/* ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; ;
; Copyright © 2009 Edgeware Technologies (India) Pvt Ltd ;
; ;
; This source code contains the intellectual property ;
; of its copyright holder(s), and is made available ;
; under a license. If you do not know the terms of ;
; the license, please stop and do not read further. ;
; ;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
*/

package com.etipl.pms.entity;

public class Slot {

    private String mSlotID;
    private String mSlotNo;
    private String mFromTime;
    private String mToTime;
    private String mPatientCode;
    private String mRemarks;
    private String mAvailableCode;
    private String mStatus;
    private String mBookingType;
    private String mEntryTime;
    private String mReferredFrom;
    private String mMDateTime;
    private String mCancelDateTime;
    private String mCancelHRN;
    private String mOperatorID;
    private String mCategory;


    public void setSlotID(String d){ this.mSlotID=d;}
    public void setSlotNo(String d){ this.mSlotNo=d;}
    public void setFromTime(String d){ this.mFromTime=d;}
    public void setToTime(String d){ this.mToTime=d;}
    public void setPatientCode(String d){ this.mPatientCode=d;}
    public void setRemarks(String d){ this.mRemarks=d;}
    public void setAvailableCode(String d){ this.mAvailableCode=d;}
    public void setStatus(String d){ this.mStatus=d;}
    public void setBookingType(String d){ this.mBookingType=d;}
    public void setEntryTime(String d){ this.mEntryTime=d;}
    public void setReferredFrom(String d){ this.mReferredFrom=d;}
    public void setMDateTime(String d){ this.mMDateTime=d;}
    public void setCancelDateTime(String d){ this.mCancelDateTime=d;}
    public void setCancelHRN(String d){ this.mCancelHRN=d;}
    public void setOperatorID(String d){ this.mOperatorID=d;}
    public void setCategory(String d){ this.mCategory=d;}


    public String getSlotID(){return this.mSlotID;}
    public String getSlotNo(){return this.mSlotNo;}
    public String getFromTime(){return this.mFromTime;}
    public String getToTime(){return this.mToTime;}
    public String getPatientCode(){return this.mPatientCode;}
    public String getRemarks(){return this.mRemarks;}
    public String getAvailableCode(){return this.mAvailableCode;}
    public String getStatus(){return this.mStatus;}
    public String getBookingType(){return this.mBookingType;}
    public String getEntryTime(){return this.mEntryTime;}
    public String getReferredFrom(){return this.mReferredFrom;}
    public String getMDateTime(){return this.mMDateTime;}
    public String getCancelDateTime(){return this.mCancelDateTime;}
    public String getCancelHRN(){return this.mCancelHRN;}
    public String getOperatorID(){return this.mOperatorID;}
    public String getCategory(){return this.mCategory;}


}
