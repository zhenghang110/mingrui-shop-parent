package com.baidu.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.BrandDTO;
import com.baidu.shop.entity.BrandEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags = "品牌接口")
public interface BrandService {

    @ApiOperation(value = "获取品牌信息")
    @GetMapping(value = "/brand/list")
    public Result<PageInfo<BrandEntity>> getList(BrandDTO brandDTO);

    @ApiOperation(value = "品牌新增")
    @PostMapping(value = "/brand/save")
    public Result<JSONObject>save(@RequestBody BrandDTO brandDTO);

    @ApiOperation(value = "品牌修改")
    @PutMapping(value="/brand/save")
    public Result<JSONObject> editBrandInfo(@RequestBody BrandDTO brandDTO);

    @ApiOperation(value = "删除品牌信息")
    @DeleteMapping("/brand/delete")
   public Result<JSONObject> deleteBrandId(Integer id);

}
