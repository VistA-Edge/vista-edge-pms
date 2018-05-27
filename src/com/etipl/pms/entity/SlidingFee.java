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

public class SlidingFee {
    
    private Integer mFamilySize;
    private String mCodes;
    private String mMinIncome;
    private String mMaxIncome;


    public void setFamilySize(Integer fs){
        this.mFamilySize=fs;
    }
    public void setCodes(String code){
        this.mCodes=code;
    }
    public void setMinIncome(String Min){
        this.mMinIncome=Min;
    }
    public void setMaxIncome(String Max){
        this.mMaxIncome=Max;
    }

    public Integer getFamilySize(){
        return this.mFamilySize;
    }
    public String getCodes(){
        return this.mCodes;
    }
    public String getMinIncome(){
        return this.mMinIncome;
    }
    public String getMaxIncome(){
        return this.mMaxIncome;
    }
}
