package com.baidu.shop.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tb_spec_group")
public class SpecGroupEntity {

    @Id
    private Integer id;

    private Integer cid;

    private String name;

}
