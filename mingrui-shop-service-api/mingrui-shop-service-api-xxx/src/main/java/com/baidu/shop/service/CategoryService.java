package com.baidu.shop.service;

import com.baidu.shop.base.Result;
import com.baidu.shop.entity.CategoryEntity;
import com.baidu.shop.validate.group.MingruiOperation;
import com.google.gson.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "商品分类接口") // 声明接口
public interface CategoryService {

    @ApiOperation(value = "通过pid查询商品分类")
    @GetMapping(value = "category/list")
    Result<List<CategoryEntity>> getCategoryByPid(Integer pid);

    @ApiOperation(value = "通过id删除分类")
    @DeleteMapping(value = "/category/del")
    Result<JsonObject> deleteCategoryById(Integer id);

    //@RequestBody 接收前台传递过来的参数(参数必须得是string类型的字符串[json]) "{}" {}
    @ApiOperation(value = "更新")
    @PutMapping(value = "/category/updateCat")
    Result<JsonObject> editCategoryById(@Validated({MingruiOperation.Update.class}) @RequestBody CategoryEntity categoryEntity);

    @ApiOperation(value = "新增")
    @PostMapping(value = "/category/add")
    Result<JsonObject> addCategoryById(@Validated({MingruiOperation.Add.class})@RequestBody CategoryEntity categoryEntity);

    @ApiOperation(value = "通过品牌id查询商品分类")
    @GetMapping(value = "/category/brand")
    public Result<List<CategoryEntity>> getByBrand(Integer brandId);
}
