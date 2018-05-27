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

public class CMS_CPTS {

    private String mDID,mCLAIMID,mServiceFromDate,mServiceToDate,mPOS,mEMG,mCPT,mModifier1;
    private String mModifier2,mModifier3,mModifier4,mDiagnosisPointer,mCharges,mUnits,mEPSDT,mQuality,mProviderID;

    public void setDID(String d){ this.mDID=d;}
    public void setCLAIMID(String d){ this.mCLAIMID=d;}
    public void setServiceFromDate(String d){ this.mServiceFromDate=d;}
    public void setServiceToDate(String d){ this.mServiceToDate=d;}
    public void setPOS(String d){ this.mPOS=d;}
    public void setEMG(String d){ this.mEMG=d;}
    public void setCPT(String d){ this.mCPT=d;}
    public void setModifier1(String d){ this.mModifier1=d;}
    public void setModifier2(String d){ this.mModifier2=d;}
    public void setModifier3(String d){ this.mModifier3=d;}
    public void setModifier4(String d){ this.mModifier4=d;}
    public void setDiagnosisPointer(String d){ this.mDiagnosisPointer=d;}
    public void setCharges(String d){ this.mCharges=d;}
    public void setUnits(String d){ this.mUnits=d;}
    public void setEPSDT(String d){ this.mEPSDT=d;}
    public void setQuality(String d){ this.mQuality=d;}
    public void setProviderID(String d){ this.mProviderID=d;}

    public String getDID(){return this.mDID;}
    public String getCLAIMID(){return this.mCLAIMID;}
    public String getServiceFromDate(){return this.mServiceFromDate;}
    public String getServiceToDate(){return this.mServiceToDate;}
    public String getPOS(){return this.mPOS;}
    public String getEMG(){return this.mEMG;}
    public String getCPT(){return this.mCPT;}
    public String getModifier1(){return this.mModifier1;}
    public String getModifier2(){return this.mModifier2;}
    public String getModifier3(){return this.mModifier3;}
    public String getModifier4(){return this.mModifier4;}
    public String getDiagnosisPointer(){return this.mDiagnosisPointer;}
    public String getCharges(){return this.mCharges;}
    public String getUnits(){return this.mUnits;}
    public String getEPSDT(){return this.mEPSDT;}
    public String getQuality(){return this.mQuality;}
    public String getProviderID(){return this.mProviderID;}

}
