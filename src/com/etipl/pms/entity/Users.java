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

public class Users {
    private String mUname;
    private String mUser;
    private String mPassword;
    private String mClinic;
    private String mCategory;

    public void setUname(String un){ this.mUname=un;}
    public void setUser(String u){this.mUser=u;}
    public void setPassword(String pwd){ this.mPassword=pwd;}
    public void setClinic(String clin){ this.mClinic=clin;}
    public void setCategory(String cat){ this.mCategory=cat;}

    public String getUname(){return this.mUname;}
    public String getUser(){return this.mUser;}
    public String getPassword(){return this.mPassword;}
    public String getClinic(){return this.mClinic;}
    public String getCategory(){return this.mCategory;}    
}
