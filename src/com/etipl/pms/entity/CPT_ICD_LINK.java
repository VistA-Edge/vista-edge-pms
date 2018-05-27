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

public class CPT_ICD_LINK {
    private String mProcedureCode;
    private String mDiagnosisCode;

    public void setProcdedureCode(String pc){this.mProcedureCode=pc;}
    public void setDiagnosisCode(String dc){this.mDiagnosisCode=dc;}

    public String getProcdeureCode(){return this.mProcedureCode;}
    public String getDiagnosisCode(){return this.mDiagnosisCode;}

}
