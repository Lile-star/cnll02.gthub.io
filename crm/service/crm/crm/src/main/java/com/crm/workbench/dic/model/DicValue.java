package com.crm.workbench.dic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DicValue {
    private Integer id;

    private String value;

    private String text;

    private Integer orderNo;

    private String typeCode;


}