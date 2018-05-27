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

public class BillAmt {
    private String mBillNo;
    private String mCaseID;
    private String mBillDate;
    private String mTotalCPTCount;
    private String mInsuranceCarrier;
    private String mTotalCPTUnits;
    private String mTotalCharges;
    private String mTotalCoIns;
    private String mTotalAdjAmt;
    private String mTotalBalance;
    private String mDeductible;
    private String mOutofPocket;
    private String mBalancetobeClaimed;
    private String mAmountPaidByPatient;
    private String mModeofPayment;
    private String mAmountDue;
    private String mBankName;
    private String mModeofPaymentNo;
    private String mChequeDate;
    private String mICD1;
    private String mICD2;
    private String mICD3;
    private String mICD4;
 
    public void setBillNo(String billno)         {      this.mBillNo=billno;            }
    public void setCaseID(String caseid)         {      this.mCaseID=caseid;            }
    public void setBillDate(String billdate)       {      this.mBillDate=billdate;        }
    public void setTotalCPTCount(String totalcptcount){ this.mTotalCPTCount=totalcptcount;}
    public void setInsuranceCarrier(String insurancecarrier){this.mInsuranceCarrier=insurancecarrier;}
    public void setTotalCPTUnits(String totalcptunits){ this.mTotalCPTUnits=totalcptunits;}
    public void setTotalCharges(String totalcharges){   this.mTotalCharges=totalcharges;  }
    public void setTotalCoIns(String totalcoins){       this.mTotalCoIns=totalcoins;      }
    public void setTotalAdjAmt(String totaladjamt){     this.mTotalAdjAmt=totaladjamt;    }
    public void setTotalBalance(String totalbalance){   this.mTotalBalance=totalbalance;  }
    public void setDeductible(String deductible){       this.mDeductible=deductible;      }
    public void setOutofPocket(String outofpocket){     this.mOutofPocket=outofpocket;    }
    public void setBalancetobeClaimed(String balancetobeclaimed){this.mBalancetobeClaimed=balancetobeclaimed;}
    public void setAmountPaidByPatient(String amountpaidbypatient){this.mAmountPaidByPatient=amountpaidbypatient;}
    public void setModeofPayment(String modeofpayment){ this.mModeofPayment=modeofpayment;}
    public void setAmountDue(String amountdue){         this.mAmountDue=amountdue;        }
    public void setBankName(String bankname){           this.mBankName=bankname;          }
    public void setModeofPaymentNo(String modeofpaymentno){this.mModeofPaymentNo=modeofpaymentno;}
    public void setChequeDate(String chequedate){       this.mChequeDate=chequedate;      }
    public void setICD1(String icd1){                   this.mICD1=icd1;                  }
    public void setICD2(String icd2){                   this.mICD2=icd2;                  }
    public void setICD3(String icd3){                   this.mICD3=icd3;                  }
    public void setICD4(String icd4){                   this.mICD4=icd4;                  }


    public String getBillNo(){                          return this.mBillNo;              }
    public String getCaseID(){                          return this.mCaseID;              }
    public String getBillDate(){                        return this.mBillDate;            }
    public String getTotalCPTCount(){                   return this.mTotalCPTCount;       }
    public String getInsuranceCarrier(){                return this.mInsuranceCarrier;    }
    public String getTotalCPTUnits(){                   return this.mTotalCPTUnits;       }
    public String getTotalCharges(){                    return this.mTotalCharges;        }
    public String getTotalCoIns(){                      return this.mTotalCoIns;          }
    public String getTotalAdjAmt(){                     return this.mTotalAdjAmt;         }
    public String getTotalBalance(){                    return this.mTotalBalance;        }
    public String getDeductible(){                      return this.mDeductible;          }
    public String getOutofPocket(){                     return this.mOutofPocket;         }
    public String getBalancetobeClaimed(){              return this.mBalancetobeClaimed;  }
    public String getAmountPaidByPatient(){             return this.mAmountPaidByPatient; }
    public String getModeofPayment(){                   return this.mModeofPayment;       }
    public String getAmountDue(){                       return this.mAmountDue;           }
    public String getBankName(){                        return this.mBankName;            }
    public String getModeofPaymentNo(){                 return this.mModeofPaymentNo;     }
    public String getChequeDate(){                       return this.mChequeDate;         }
    public String getICD1(){                            return this.mICD1;                }
    public String getICD2(){                            return this.mICD2;                }
    public String getICD3(){                            return this.mICD3;                }
    public String getICD4(){                            return this.mICD4;                }
}
