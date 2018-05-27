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

public class LinkCPTwithChargeCode {
    private String mBillNo;    
    private String mCPT;
    private String mChargeCode;
    private String mRecordDateTime;
    private String mPOS;
    private String mTOS;
    private String mM1;
    
    public void setBillNo(String billno)         {      this.mBillNo=billno;            }
    public void setCPT(String cpt)               {      this.mCPT=cpt;                  }
    public void setChargeCode(String chargecode) {      this.mChargeCode=chargecode;    }
    public void setRecordDateTime(String recorddatetime){this.mRecordDateTime=recorddatetime;}
    public void setPOS(String pos)               {      this.mPOS=pos;                  }
    public void setTOS(String tos)               {      this.mTOS=tos;                  }
    public void setM1(String m1)                 {      this.mM1=m1;                    }
    

    public String getBillNo()                    {      return this.mBillNo;            }
    public String getCPT()                       {      return this.mCPT;               }
    public String getChargeCode()                {      return this.mChargeCode;        }
    public String getRecordDateTime()            {      return this.mRecordDateTime;    }
    public String getPOS()                       {      return this.mPOS;               }
    public String getTOS()                       {      return this.mTOS;               }
    public String getM1()                        {      return this.mM1;                }

}
