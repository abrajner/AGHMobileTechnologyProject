package com.example.aghmobiletechnologyproject.model;

import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

@Table(name = "tables")
public class TableClass extends SugarRecord {

    @Column(name = "table_name")
    private String name;

    @Column(name = "id")
    private Integer id;

    public TableClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFieldId() {
        return id;
    }

    public void setFieldId(Integer id) {
        this.id = id;
    }
}