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

public class Departments {
    private String mcode;
    private String mName;

    public void setCode(String c){this.mcode=c;}
    public void setName(String d){this.mName=d;}

    public String getCode(){return this.mcode;}
    public String getName(){return this.mName;}

}
