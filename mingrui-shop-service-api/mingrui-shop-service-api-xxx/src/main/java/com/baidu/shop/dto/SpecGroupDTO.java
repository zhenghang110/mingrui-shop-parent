package com.baidu.shop.dto;

import com.baidu.shop.base.BaseDTO;
import com.baidu.shop.validate.group.MingruiOperation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "规格组数据DTO")
public class SpecGroupDTO extends BaseDTO {

    @NotNull(message = "主键不能为空",groups = {MingruiOperation.Update.class})
    @ApiModelProperty(value = "主键",example = "1")
    private Integer id;

    @ApiModelProperty(value = "分类id",example = "1")
    @NotNull(message = "分类id不能为空",groups = {MingruiOperation.Add.class})
    private Integer cid;

    @ApiModelProperty(value = "规格组名称")
    @NotEmpty(message = "规格组名称不能为空",groups = {MingruiOperation.Add.class})
    private String name;


}
