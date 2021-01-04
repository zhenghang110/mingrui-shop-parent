package com.baidu.shop.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tb_spec_param")
public class SpecParamEntity {
    @Id
    private Integer id;

    private Integer cid;

    private Integer groupId;

    private String name;

    @Column(name = "`numeric`")//mysql关键字需要加1前面引号`
    private Boolean numeric;

    private String unit;

    private Boolean generic;

    private Boolean searching;

    private String segments;
}
