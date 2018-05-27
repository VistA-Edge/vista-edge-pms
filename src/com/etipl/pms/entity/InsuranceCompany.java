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

public class InsuranceCompany {
    private String mCode;
    private String mType;
    private String mName;
    private String mPhone;
    private String mAddress;
    private String mFax;
    private String mZip;
    private String mCity;
    private String mState;
    private String mContPerson;
    private String mPaymentCode;
    private String mAdjustment;
    private String mDeductible;
    private String mTakeBack;
    private String mWithHold;

    public void setPaymentCode(String s){this.mPaymentCode=s;}
    public void setAdjustment(String s){this.mAdjustment=s;}
    public void setDeductible(String s){this.mDeductible=s;}
    public void setTakeBack(String s){this.mTakeBack=s;}
    public void setWithHold(String s){this.mWithHold=s;}
    
    public void setCode(String c){this.mCode=c;}
    public void setType(String t){this.mType=t;}
    public void setName(String c){this.mName=c;}
    public void setPhone(String c){this.mPhone=c;}
    public void setddress(String c){this.mAddress=c;}
    public void setFax(String f){this.mFax=f;}
    public void setZip(String z){this.mZip=z;}
    public void setCity(String c){this.mCity=c;}
    public void setState(String s){this.mState=s;}
    public void setContPerson(String s){this.mContPerson=s;}

    public String getPaymentCode(){return this.mPaymentCode;}
    public String getAdjustment(){return this.mAdjustment;}
    public String getDeductible(){return this.mDeductible;}
    public String getTakeBack(){return this.mTakeBack;}
    public String getWithHold(){return this.mWithHold;}


    public String getCode(){return this.mCode;}
    public String getType(){return this.mType;}
    public String getName(){return this.mName;}
    public String getPhone(){return this.mPhone;}
    public String getAddress(){return this.mAddress;}
    public String getFax(){return this.mFax;}
    public String getCZip(){return this.mZip;}
    public String getCity(){return this.mCity;}
    public String getState(){return this.mState;}
    public String getContPerson(){return this.mContPerson;}

}
