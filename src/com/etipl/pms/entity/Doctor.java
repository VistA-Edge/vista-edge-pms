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

public class Doctor {
private String mDOCTOR_CODE,mDOCTOR_NAME,mSPECIALITY,mCREDENTIALS,mAdd1,mAdd2,mCity;
private String mState,mZip,mEMAIL,mOFFICE,mFAX,mHOME,mCELL,mSSN,mFEDERALTAXID,mMEDICARE;
private String mMEDICAID,mNPI,mUPIN,mBCBS,mTAXONOMY,mProviderType,mRole,mDOJ,mGenSpeciality;
private String mROOM_NO,mFIRST_RATE,mSUBSEQUENT_RATE,mCATEGORY_1,mCATEGORY_2,mREMARKS,mExtra1,mExtra2;
private String mRailRoad,mRailRoadGroup;
private String mMEDICAREGROUP,mMEDICAIDGROUP;
private String mBCBSGROUP;
private String mStateLicence;
private String mDEANO;


public void setRailRoadGroup(String d){ this.mRailRoadGroup=d;}
public void setMEDICAREGROUP(String d){ this.mMEDICAREGROUP=d;}
public void setMEDICAIDGROUP(String d){ this.mMEDICAIDGROUP=d;}
public void setBCBSGROUP(String d){ this.mBCBSGROUP=d;}

public void setDOCTOR_CODE(String d){ this.mDOCTOR_CODE=d;}
public void setDOCTOR_NAME(String d){ this.mDOCTOR_NAME=d;}
public void setCREDENTIALS(String d){ this.mCREDENTIALS=d;}
public void setAdd1(String d){ this.mAdd1=d;}
public void setAdd2(String d){ this.mAdd2=d;}
public void setCity(String d){ this.mCity=d;}
public void setSPECIALITY(String d){ this.mSPECIALITY=d;}
public void setState(String d){ this.mState=d;}
public void setZip(String d){ this.mZip=d;}
public void setEMAIL(String d){ this.mEMAIL=d;}
public void setOFFICE(String d){ this.mOFFICE=d;}
public void setFAX(String d){ this.mFAX=d;}
public void setHOME(String d){ this.mHOME=d;}
public void setCELL(String d){ this.mCELL=d;}
public void setSSN(String d){ this.mSSN=d;}
public void setFEDERALTAXID(String d){ this.mFEDERALTAXID=d;}
public void setMEDICARE(String d){ this.mMEDICARE=d;}
public void setMEDICAID(String d){ this.mMEDICAID=d;}
public void setNPI(String d){ this.mNPI=d;}
public void setUPIN(String d){ this.mUPIN=d;}
public void setBCBS(String d){ this.mBCBS=d;}
public void setTAXONOMY(String d){ this.mTAXONOMY=d;}
public void setProviderType(String d){ this.mProviderType=d;}
public void setRole(String d){ this.mRole=d;}
public void setDOJ(String d){ this.mDOJ=d;}
public void setGenSpeciality(String d){ this.mGenSpeciality=d;}
public void setROOM_NO(String d){ this.mROOM_NO=d;}
public void setFIRST_RATE(String d){ this.mFIRST_RATE=d;}
public void setSUBSEQUENT_RATE(String d){ this.mSUBSEQUENT_RATE=d;}
public void setCATEGORY_1(String d){ this.mCATEGORY_1=d;}
public void setCATEGORY_2(String d){ this.mCATEGORY_2=d;}
public void setREMARKS(String d){ this.mREMARKS=d;}
public void setExtra1(String d){ this.mExtra1=d;}
public void setExtra2(String d){ this.mExtra2=d;}
public void setRailRoad(String d){ this.mRailRoad=d;}

public void setStateLicence(String d){ this.mStateLicence=d;}
public void setDEANO(String d){ this.mDEANO=d;}

public String getDOCTOR_CODE(){return this.mDOCTOR_CODE;}
public String getDOCTOR_NAME(){return this.mDOCTOR_NAME;}
public String getCREDENTIALS(){return this.mCREDENTIALS;}
public String getAdd1(){return this.mAdd1;}
public String getAdd2(){return this.mAdd2;}
public String getSPECIALITY(){return this.mSPECIALITY;}
public String getCity(){return this.mCity;}
public String getState(){return this.mState;}
public String getZip(){return this.mZip;}
public String getEMAIL(){return this.mEMAIL;}
public String getOFFICE(){return this.mOFFICE;}
public String getFAX(){return this.mFAX;}
public String getHOME(){return this.mHOME;}
public String getCELL(){return this.mCELL;}
public String getSSN(){return this.mSSN;}
public String getFEDERALTAXID(){return this.mFEDERALTAXID;}
public String getMEDICARE(){return this.mMEDICARE;}
public String getMEDICAID(){return this.mMEDICAID;}
public String getNPI(){return this.mNPI;}
public String getUPIN(){return this.mUPIN;}
public String getBCBS(){return this.mBCBS;}
public String getTAXONOMY(){return this.mTAXONOMY;}
public String getProviderType(){return this.mProviderType;}
public String getRole(){return this.mRole;}
public String getDOJ(){return this.mDOJ;}
public String getGenSpeciality(){return this.mGenSpeciality;}
public String getROOM_NO(){return this.mROOM_NO;}
public String getFIRST_RATE(){return this.mFIRST_RATE;}
public String getSUBSEQUENT_RATE(){return this.mSUBSEQUENT_RATE;}
public String getCATEGORY_1(){return this.mCATEGORY_1;}
public String getCATEGORY_2(){return this.mCATEGORY_2;}
public String getREMARKS(){return this.mREMARKS;}
public String getExtra1(){return this.mExtra1;}
public String getExtra2(){return this.mExtra2;}
public String getRailRoad(){return this.mRailRoad;}

public String getRailRoadGroup(){return this.mRailRoadGroup;}
public String getMEDICAREGROUP(){return this.mMEDICAREGROUP;}
public String getMEDICAIDGROUP(){return this.mMEDICAIDGROUP;}
public String getBCBSGROUP(){return this.mBCBSGROUP;}

public String getStateLicence(){return this.mStateLicence;}
public String getDEANO(){return this.mDEANO;}


}
