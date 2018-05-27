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

public class ChargeCode {
    private String mCategory;
    private String mChargeCode;
    private String mChargeCodeDes;
    private String mPrice;
    private String mCPTCode;
    private String mCPTDes;
    private String mModifier;
    private String mDefaultTOS;
    private String mDefaultPOS;
    private String mComments;
    

    public void setCategory(String category)         {      this.mCategory=category;            }
    public void setChargeCode(String chargeCode)         {      this.mChargeCode=chargeCode;            }
    public void setChargeCodeDes(String chargecodedes)   {      this.mChargeCodeDes=chargecodedes;}
    public void setPrice(String price)               {      this.mPrice=price;                  }
    public void setCPTCode(String cptcode)               {      this.mCPTCode=cptcode;                  }
    public void setCPTDes(String cptdes){     this.mCPTDes=cptdes;  }
    public void setModifier(String modifier)      {     this.mModifier=modifier;        }
    public void setDefaultTOS(String defaulttos){             this.mDefaultTOS=defaulttos;          }
    public void setDefaultPOS(String defaultpos){             this.mDefaultPOS=defaultpos;          }
    public void setComments(String comments){             this.mComments=comments;          }
    
    public String getCategory(){                          return this.mCategory;            }
    public String getChargeCode(){                          return this.mChargeCode;            }
    public String getChargeCodeDes(){                             return this.mChargeCodeDes;               }
    public String getPrice(){                             return this.mPrice;               }
    public String getCPTCode(){                             return this.mCPTCode;               }
    public String getCPTDes(){                               return this.mCPTDes;       }
    public String getModifier(){                        return this.mModifier;          }
    public String getDefaultTOS(){                         return this.mDefaultTOS;           }
    public String getDefaultPOS(){                         return this.mDefaultPOS;}
    public String getComments(){                         return this.mComments; }
}

