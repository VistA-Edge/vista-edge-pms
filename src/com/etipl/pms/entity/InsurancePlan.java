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

public class InsurancePlan {
   
    private String company,plan_type,group_no,plan_name,policy_type,source,effective_date,expiry_date,
            relation_holder,insured_id,date_of_sign,sof_ins;

    private String ins_name,ins_dob,ins_sex,ins_add,ins_zipcode,ins_city,ins_state,ins_phone,ins_empname,
            o_ins_name,o_ins_dob,o_ins_sex,o_ins_group_no,o_emp_name,o_plan_name,any_other;
    
    
    
    public void setcomp(String d){this.company=d;}
    public void setpt(String d){this.plan_type=d;}
    public void setgn(String d){this.group_no=d;}
    public void setpn(String d){this.plan_name=d;}
    public void setpoty(String d){this.policy_type=d;}
    public void setsource(String d){this.source=d;}
    public void seteffdate(String d){this.effective_date=d;}
    public void setexpdate(String d){this.expiry_date=d;}
    public void setrh(String d){this.relation_holder=d;}
    public void setinsid(String d){this.insured_id=d;}
    public void setdsign(String d){this.date_of_sign=d;}
    public void setsof(boolean d){
                if (d==true)
                        this.sof_ins="Y";
                else
                    this.sof_ins="N";
            }
            public void setiname(String d){this.ins_name=d;}
            public void setidob(String d){this.ins_dob=d;}
            public void setisex(String d){this.ins_sex=d;}
            public void setiadd(String d){this.ins_add=d;}
            public void setizcode(String d){this.ins_zipcode=d;}
            public void seticity(String d){this.ins_city=d;}
            public void setistate(String d){this.ins_state=d;}
            public void setiphone(String d){this.ins_phone=d;}
            public void setiename(String d){this.ins_empname=d;}
            
            public void setoiname(String d){this.o_ins_name=d;}
            public void setoidob(String d){this.o_ins_dob=d;}
            public void setoisex(String d){this.o_ins_sex=d;}
            public void setogno(String d){this.o_ins_group_no=d;}
            public void setoename(String d){this.o_emp_name=d;}
            public void setopname(String d){this.o_plan_name=d;}
            public void setanyother(boolean d){
                if (d==true)
                        this.any_other="Y";
                else
                    this.any_other="N";
            }
    
    
    public String getcomp(){return this.company;}
    public String getpt(){return this.plan_type;}
    public String getgn(){return this.group_no;}
    public String getpn(){return this.plan_name;}
    public String getpoty(){return this.policy_type;}
    public String getsource(){return this.source;}
    public String geteffdate(){return this.effective_date;}
    public String getexpdate(){return this.expiry_date;}
    public String getrh(){return this.relation_holder;}
    public String getinsid(){return this.insured_id;}
    public String getdsign(){return this.date_of_sign;}
    public String getsof(){return this.sof_ins;}
    //_________________________
            public String getiname(){return this.ins_name;}
            public String getidob(){return this.ins_dob;}
            public String getisex(){return this.ins_sex;}
            public String getiadd(){return this.ins_add;}
            public String getizcode(){return this.ins_zipcode;}
            public String geticity(){return this.ins_city;}
            public String getistate(){return this.ins_state;}
            public String getiphone(){return this.ins_phone;}
            public String getiename(){return this.ins_empname;}
            
            public String getoiname(){return this.o_ins_name;}
            public String getoidob(){return this.o_ins_dob;}
            public String getoisex(){return this.o_ins_sex;}
            public String getogno(){return this.o_ins_group_no;}
            public String getoename(){return this.o_emp_name;}
            public String getopname(){return this.o_plan_name;}
            public String getanyother(){return this.any_other;}
    
    
    /** Creates a new instance of InsurancePlan */
    public InsurancePlan() {
        
    }
    
}
