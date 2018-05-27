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

public class PatientVisitDetails {
    private String mVISITNO;
    private String mVISIT_DATETIME;
    private String mDepartment;
    private String mProvider;
    private String mAppointmentDATETIME;
    private String mTypeofVisit;

    public void setVisitNo(String visitno)     { this.mVISITNO=visitno;    }
    public void setVisit_Datetime(String vdt)  { this.mVISIT_DATETIME=vdt; }
    public void setDepartment(String dpt)      { this.mDepartment=dpt;     }
    public void setProvider(String provider)   { this.mProvider=provider;  }
    public void setAppt_Datetime(String adt)   { this.mAppointmentDATETIME=adt;}
    public void setTypeofVisit(String tov)     { this.mTypeofVisit=tov;    }

    public String getVisitNo()                 { return this.mVISITNO;     }
    public String getVisit_Datetime()          { return this.mVISIT_DATETIME;}
    public String getDepartment()              { return this.mDepartment;  }
    public String getProvider()                { return this.mProvider;    }
    public String getAppt_Datetime()           { return this.mAppointmentDATETIME;}
    public String getTypeofVisit()             { return this.mTypeofVisit; }


}
