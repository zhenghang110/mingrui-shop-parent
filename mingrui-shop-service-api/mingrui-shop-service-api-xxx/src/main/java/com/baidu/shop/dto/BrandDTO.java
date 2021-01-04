package com.baidu.shop.dto;

import com.baidu.shop.base.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baidu.shop.validate.group.MingruiOperation;

import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Table(name = "tb_brand")
public class BrandDTO extends BaseDTO {

   @ApiModelProperty(value = "品牌主键id",example = "1")
   @NotNull(message = "主键不能为空",groups = {MingruiOperation.Update.class})
    private Integer id;

   @ApiModelProperty(value="品牌名称")
   @NotEmpty(message = "品牌名称不能为空",groups = {MingruiOperation.Add.class,MingruiOperation.Update.class})
    private String name;

   @ApiModelProperty(value = "品牌图片")
   private String image;

   @ApiModelProperty(value = "品牌首字母")
    private Character letter;

    @ApiModelProperty(value = "品牌分类信息")
    @NotEmpty(message = "分类信息不能为空",groups = {MingruiOperation.Add.class,MingruiOperation.Update.class})
   private String categories;
}
