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

public class FacilityDef {    
    
    private String mCode,mServiceName,mAddress,mZipCode,mCity,mState,mServices1;
    private String mNPI,mPhone,mFax,mFederalTaxId,mServices,mTaxonomy,mCLIA;

    public void setcode(String d){this.mCode=d;}
    public void setservicename(String d){this.mServiceName=d;}
    public void setaddress(String d){this.mAddress=d;}
    public void setzipcode(String d){this.mZipCode=d;}
    public void setcity(String d){this.mCity=d;}
    public void setstate(String d){this.mState=d;}
    public void setnpi(String d){this.mNPI=d;}
    public void setphone(String d){this.mPhone=d;}
    public void setfax(String d){this.mFax=d;}
    public void setfederaltaxid(String d){this.mFederalTaxId=d;}
    public void setservices(String d){this.mServices=d;}
    public void setservices1(String d){this.mServices1=d;}
    public void setTaxonomy(String d){this.mTaxonomy=d;}
    public void setCLIA(String d){this.mCLIA=d;}
   
    public String getcode(){return this.mCode;} 
    public String getservicename(){return this.mServiceName;}
    public String getaddress(){return this.mAddress;}
    public String getzipcode(){return this.mZipCode;}
    public String getcity(){return this.mCity;}
    public String getstate(){return this.mState;}
    public String getnpi(){return this.mNPI;}
    public String getphone(){return this.mPhone;}
    public String getfax(){return this.mFax;}
    public String getfederaltaxid(){return this.mFederalTaxId;}
    public String getservices(){return this.mServices;}
    public String getTaxonomy(){return this.mTaxonomy;}
    public String getCLIA(){return this.mCLIA;}
    public String getservices1(){return this.mServices1;}
    
}
