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

public class Visit {
private String mSLOT_ID,mARRIVAL_TIME,mSEEN_TIME,mEND_TIME,mNEXT_APPOINTMENT,mAPPOINTMENT_ID;
private String mREMARKS,mREFERRED_FROM,mVISIT_DATE,mSTATUS,mVISITNO;

public void setSLOT_ID(String d){ this.mSLOT_ID=d;}
    public void setARRIVAL_TIME(String d){ this.mARRIVAL_TIME=d;}
    public void setSEEN_TIME(String d){ this.mSEEN_TIME=d;}
    public void setEND_TIME(String d){ this.mEND_TIME=d;}
    public void setNEXT_APPOINTMENT(String d){ this.mNEXT_APPOINTMENT=d;}
    public void setAPPOINTMENT_ID(String d){ this.mAPPOINTMENT_ID=d;}
    public void setREMARKS(String d){ this.mREMARKS=d;}
    public void setREFERRED_FROM(String d){ this.mREFERRED_FROM=d;}
    public void setVISIT_DATE(String d){ this.mVISIT_DATE=d;}
    public void setSTATUS(String d){ this.mSTATUS=d;}
    public void setVISITNO(String d){ this.mVISITNO=d;}

    public String getSLOT_ID(){return this.mSLOT_ID;}
    public String getARRIVAL_TIME(){return this.mARRIVAL_TIME;}
    public String getSEEN_TIME(){return this.mSEEN_TIME;}
    public String getEND_TIME(){return this.mEND_TIME;}
    public String getNEXT_APPOINTMENT(){return this.mNEXT_APPOINTMENT;}
    public String getAPPOINTMENT_ID(){return this.mAPPOINTMENT_ID;}
    public String getREMARKS(){return this.mREMARKS;}
    public String getREFERRED_FROM(){return this.mREFERRED_FROM;}
    public String geVISIT_DATE(){return this.mVISIT_DATE;}
    public String getSTATUS(){return this.mSTATUS;}
    public String getVISITNO(){return this.mVISITNO;}

}
