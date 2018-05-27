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

public class PaymentPostingDef {

    private String mReceiptDate="",mPayorType="",mPaymentMode="",mCheckNo="",mECFANo="",mDescription="";
    private String mPaymentAmt="",mReceiptCode="",mReceiptNo="",mReceiptId="",mInsurance="",mPaymentCode="",mAdjCode="";
    private String mWithHoldCode="",mDeductibleCode="",mTakeBackCode="",mCoPayCode="",mPayerName="",mPayorId="";
    private String mPatientName,mPatientSex="",mPatientDOB="",mClaimId="",mServDate="",mProc="",mCharges="",mBalance="" ;
    private String mAddress1="",mAddress2="",mCity="",mState="",mZip="",mPhone="",mGurantorname="",mGurantorSex="",mGurantorDOB="";
    private String mRelation="",mProvider="",mAllowedCode="",mp1="",mp2="",mp3="",mid="",mReceiptDate1="";

    public void setReceiptDate(String d){this.mReceiptDate=d;}
    public void setReceiptDate1(String d){this.mReceiptDate1=d;}
    public void setPayorType(String d){this.mPayorType=d;}
    public void setPaymentMode(String d){this.mPaymentMode=d;}
    public void setCheckNo(String d){this.mCheckNo=d;}
    public void setECFANo(String d){this.mECFANo=d;}
    public void setDescription(String d){this.mDescription=d;}
    public void setPaymentAmt(String d){this.mPaymentAmt=d;}
    public void setReceiptCode(String d){this.mReceiptCode=d;}
    public void setReceiptNo(String d){this.mReceiptNo=d;}
    public void setReceiptId(String d){this.mReceiptId=d;}    
    public void setInsurance(String d){this.mInsurance=d;}
    public void setPaymentCode(String d){this.mPaymentCode=d;}
    public void setAdjCode(String d){this.mAdjCode=d;}
    public void setWithHoldCode(String d){this.mWithHoldCode=d;}
    public void setDeductibleCode(String d){this.mDeductibleCode=d;}
    public void setTakeBackCode(String d){this.mTakeBackCode=d;}
    public void setCoPayCode(String d){this.mCoPayCode=d;}
    public void setPayerName(String d){this.mPayerName=d;}
    public void setPayorId(String d){this.mPayorId=d;}
    public void setPatientName(String d){this.mPatientName=d;}
    public void setPatientSex(String d){this.mPatientSex=d;}
    public void setPatientDOB(String d){this.mPatientDOB=d;}
    public void setClaimId(String d){this.mClaimId=d;}
    public void setServDate(String d){this.mServDate=d;}
    public void setProc(String d){this.mProc=d;}
    public void setCharges(String d){this.mCharges=d;}
    public void setBalance(String d){this.mBalance=d;}
    public void setAddress1(String d){this.mAddress1=d;}
    public void setAddress2(String d){this.mAddress2=d;}
    public void setCity(String d){this.mCity=d;}
    public void setState(String d){this.mState=d;}
    public void setZip(String d){this.mZip=d;}
    public void setPhone(String d){this.mPhone=d;}
    public void setGurantorname(String d){this.mGurantorname=d;}
    public void setGurantorSex(String d){this.mGurantorSex=d;}
    public void setGurantorDOB(String d){this.mGurantorDOB=d;}
    public void setRelation(String d){this.mRelation=d;}
    public void setProvider(String d){this.mProvider=d;}
    public void setAllowedCode(String d){this.mAllowedCode=d;}
    public void setp1(String d){this.mp1=d;}
    public void setp2(String d){this.mp2=d;}
    public void setp3(String d){this.mp3=d;}
    public void setid(String d){this.mid=d;}
   // public void setReceiptId(String d){this.mReceiptId=d;}

    public String getReceiptDate(){return this.mReceiptDate;}
    public String getReceiptDate1(){return this.mReceiptDate1;}
    public String getPayorType(){return this.mPayorType;}
    public String getPaymentMode(){return this.mPaymentMode;}
    public String getCheckNo(){return this.mCheckNo;}
    public String getECFANo(){return this.mECFANo;}
    public String getDescription(){return this.mDescription;}
    public String getPaymentAmt(){return this.mPaymentAmt;}
    public String getReceiptCode(){return this.mReceiptCode;}
    public String getReceiptNo(){return this.mReceiptNo;}
    public String getReceiptId(){return this.mReceiptId;}    
    public String getInsurance(){return this.mInsurance;}
    public String getPaymentCode(){return this.mPaymentCode;}
    public String getAdjCode(){return this.mAdjCode;}
    public String getWithHoldCode(){return this.mWithHoldCode;}
    public String getDeductibleCode(){return this.mDeductibleCode;}
    public String getTakeBackCode(){return this.mTakeBackCode;}
    public String getCoPayCode(){return this.mCoPayCode;}
    public String getPayerName(){return this.mPayerName;}
    public String getPayorId(){return this.mPayorId;}
    public String getPatientName(){return this.mPatientName;}
    public String getPatientSex(){return this.mPatientSex;}
    public String getPatientDOB(){return this.mPatientDOB;}
    public String getClaimId(){return this.mClaimId;}
    public String getServDate(){return this.mServDate;}
    public String getProc(){return this.mProc;}
    public String getCharges(){return this.mCharges;}
    public String getBalance(){return this.mBalance;}
    public String getAddress1(){return this.mAddress1;}
    public String getAddress2(){return this.mAddress2;}
    public String getCity(){return this.mCity;}
    public String getState(){return this.mState;}
    public String getZip(){return this.mZip;}
    public String getPhone(){return this.mPhone;}
    public String getGurantorname(){return this.mGurantorname;}
    public String getGurantorSex(){return this.mGurantorSex;}
    public String getGurantorDOB(){return this.mGurantorDOB;}
    public String getRelation(){return this.mRelation;}
    public String getProvider(){return this.mProvider;}
    public String getAllowedCode(){return this.mAllowedCode;}
    public String getp1(){return this.mp1;}
    public String getp2(){return this.mp2;}
    public String getp3(){return this.mp3;}
    public String getid(){return this.mid;}
   // public String getReceiptId(){return this.mReceiptId;}



}
