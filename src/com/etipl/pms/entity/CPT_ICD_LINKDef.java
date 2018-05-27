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

public class CPT_ICD_LINKDef {
    private String mProcedureCode,mDescription,mNotes;
    private String mDiagnosisCode;
    
    public void setProcedureCode(String pc){this.mProcedureCode=pc;}
    public void setDescription(String pc){this.mDescription=pc;}
    public void setNotes(String pc){this.mNotes=pc;}
    public void setDiagnosisCode(String dc){this.mDiagnosisCode=dc;}

    public String getProcedureCode(){return this.mProcedureCode;}
    public String getDescription(){return this.mDescription;}
    public String getNotes(){return this.mNotes;}
    public String getDiagnosisCode(){return this.mDiagnosisCode;}

}
