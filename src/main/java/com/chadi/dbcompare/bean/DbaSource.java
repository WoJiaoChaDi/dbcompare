package com.chadi.dbcompare.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DbaSource extends DbBaseObj{
    private String owner;
    private String name;
    private String type;
    private String line;
    private String text;
}
