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

public class LinkCPTwithICD {
    private String mBillNo;
    private String mCPT;
    private String mChargeCode;
    private String mICD;
    private String mSource;
    private String mStatus;
    private String mRecordDateTime;
    private String mSNo;

    public void setBillNo(String billno)         {      this.mBillNo=billno;                      }
    public void setCPT(String cpt)               {      this.mCPT=cpt;                            }
    public void setChargeCode(String chargecode) {      this.mChargeCode=chargecode;              }
    public void setICD(String icd)               {      this.mICD=icd;                            }
    public void setSource(String source)               {this.mSource=source;                      }
    public void setStatus(String status)               {this.mStatus=status;                      }
    public void setRecordDateTime(String recorddatetime){this.mRecordDateTime=recorddatetime;     }
    public void setSNo(String SNo)               {      this.mSNo=SNo;                            }


    public String getBillNo()                    {      return this.mBillNo;                      }
    public String getCPT()                       {      return this.mCPT;                         }
    public String getChargeCode()                {      return this.mChargeCode;                  }
    public String getICD()                       {      return this.mICD;                         }
    public String getSource()                    {      return this.mSource;                      }
    public String getRecordDateTime()            {      return this.mRecordDateTime;              }
    public String getSNo()                       {      return this.mSNo;                         }


}
