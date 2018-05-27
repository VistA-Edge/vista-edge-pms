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

public class Bill {
    private String mBillNo;
    private String mCaseID;
    private String mCPT;
    private String mPOS;
    private String mEMG;
    private String mCPTSerialNo;
    private String mCPTUnits;
    private String mCPTRate;
    private String mCharges;
    private String mCoIns;
    private String mAdjCode;
    private String mAdjAmt;
    private String mBalance;
    private String mICD1;
    private String mICD2;
    private String mICD3;
    private String mICD4;
    private String mICDPointer;
    private String mProviderID;
    private String mPTax;
    private String mM1;
    private String mM2;


    public void setBillNo(String billno)         {      this.mBillNo=billno;            }
    public void setCaseID(String caseid)         {      this.mCaseID=caseid;            }
    public void setCPT(String cpt)               {      this.mCPT=cpt;                  }
    public void setPOS(String pos)               {      this.mPOS=pos;                  }
    public void setEMG(String emg)               {      this.mEMG=emg;                  }
    public void setCPTSerialNo(String cptserialno){     this.mCPTSerialNo=cptserialno;  }
    public void setCPTUnits(String cptunits)      {     this.mCPTUnits=cptunits;        }
    public void setCPTRate(String cptrate){             this.mCPTRate=cptrate;          }
    public void setCharges(String charges){             this.mCharges=charges;          }
    public void setCoIns(String coins){                 this.mCoIns=coins;              }
    public void setAdjCode(String adjcode){             this.mAdjCode=adjcode;          }
    public void setAdjAmt(String adjamt){               this.mAdjAmt=adjamt;            }
    public void setBalance(String balance){             this.mBalance=balance;          }
    public void setICD1(String icd1){                   this.mICD1=icd1;                }
    public void setICD2(String icd2){                   this.mICD2=icd2;                }
    public void setICD3(String icd3){                   this.mICD3=icd3;                }
    public void setICD4(String icd4){                   this.mICD4=icd4;                }
    public void setICDPointer(String icdpointer){       this.mICDPointer=icdpointer;    }

    public void setPTax(String proid){                  this.mPTax=proid;    }

    public void setProviderId(String proid){       this.mProviderID=proid;    }
    public void setM1(String m1){       this.mM1=m1;    }
    public void setM2(String m2){       this.mM2=m2;    }


    public String getBillNo(){                          return this.mBillNo;            }
    public String getCaseID(){                          return this.mCaseID;            }
    public String getCPT(){                             return this.mCPT;               }
    public String getPOS(){                             return this.mPOS;               }
    public String getEMG(){                             return this.mEMG;               }
    public String getCPTSerialNo(){                     return this.mCPTSerialNo;       }
    public String getCPTUnits(){                        return this.mCPTUnits;          }
    public String getCPTRate(){                         return this.mCPTRate;           }
    public String getCharges(){                         return this.mCharges;           }
    public String getCoIns(){                           return this.mCoIns;             }
    public String getAdjCode(){                         return this.mAdjCode;           }
    public String getAdjAmt(){                          return this.mAdjAmt;            }
    public String getBalance(){                         return this.mBalance;           }
    public String getICD1(){                            return this.mICD1;              }
    public String getICD2(){                            return this.mICD2;              }
    public String getICD3(){                            return this.mICD3;              }
    public String getICD4(){                            return this.mICD4;              }
    public String getICDPointer(){                      return this.mICDPointer;        }

    public String getPTax(){                            return this.mPTax;        }
    public String getProviderId(){                      return this.mProviderID;        }
    public String getM1(){                              return this.mM1;        }
    public String getM2(){                              return this.mM2 ;        }
}
