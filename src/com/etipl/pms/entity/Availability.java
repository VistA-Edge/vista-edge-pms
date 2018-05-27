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

public class Availability {

private String mAvailableUniqueCode="";
private String mDoctorCode="";
private String mDate="";
private String mFromTime="";
private String mToTime="";
private String mSlotSize="";
private String mOverbooking="";
private String mRemarks="";
private String mStatus="";

public void setAvailableUniqueCode(String auc){ this.mAvailableUniqueCode=auc;}
public void setDoctorCode(String dc){this.mDoctorCode=dc;}
public void setDate(String d){this.mDate=d;}
public void setFromTime(String ftime){this.mFromTime=ftime;}
public void setToTime(String ttime){this.mToTime=ttime;}
public void setSlotSize(String ss){this.mSlotSize=ss;}
public void setOverbooking(String ob){this.mOverbooking=ob;}
public void setRemarks(String re){this.mRemarks=re;}
public void setStatus(String status){this.mStatus=status;}

public String getAvailableUniqueCode(){return this.mAvailableUniqueCode;}
public String getDoctorCode(){return this.mDoctorCode;}
public String getDate(){return this.mDate;}
public String getFromTime(){return this.mFromTime;}
public String getToTime(){return this.mToTime;}
public String getSlotSize(){return this.mSlotSize;}
public String getOverbooking(){return this.mOverbooking;}
public String getRemarks(){return this.mRemarks;}
public String getStatus(){return this.mStatus;}


}
