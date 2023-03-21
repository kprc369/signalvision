package com.semtle.psj.test.Vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class ProductVo {
    private String companyNo;      //회사 번호
    private String barcode;        //바코드 번호
    private String status;            //상태값


}
