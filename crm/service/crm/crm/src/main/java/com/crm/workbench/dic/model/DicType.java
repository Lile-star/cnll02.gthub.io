package com.crm.workbench.dic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DicType {
    private String code;

    private String name;

    private String description;

    private List<DicValue> dicValueList=new ArrayList<>();

}