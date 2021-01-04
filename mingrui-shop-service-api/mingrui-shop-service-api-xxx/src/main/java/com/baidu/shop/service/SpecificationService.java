package com.baidu.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.SpecGroupDTO;
import com.baidu.shop.dto.SpecParamDTO;
import com.baidu.shop.entity.SpecGroupEntity;
import com.baidu.shop.entity.SpecParamEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "规格组接口")
public interface SpecificationService {
    @ApiOperation(value = "通过条件查询规格组")

    @GetMapping(value = "specgroup/list")
    Result<List<SpecGroupEntity>> getSepcGroupInfo(SpecGroupDTO specGroupDTO);

    @ApiOperation(value = "新增规格组")
    @PostMapping(value = "/category/save")
    Result<JSONObject> saveSpecGrouo(@RequestBody SpecGroupDTO specGroupDTO);

    @ApiOperation(value = "修改规格组")
    @PutMapping(value = "/category/save")
    Result<JSONObject> updateSpecGrouo(@RequestBody SpecGroupDTO specGroupDTO);


    @ApiOperation(value = "删除规格组")
    @DeleteMapping(value = "/category/delete")
    Result<JSONObject>delSpecGrouo(Integer id);

    @GetMapping(value = "specparam/list")
    Result<List<SpecParamEntity>> getSepcParamInfo(SpecParamDTO specParamDTO);

    @ApiOperation(value = "新增规格参数")
    @PostMapping(value = "/specparam/save")
    Result<JSONObject> saveSpecParam(@RequestBody SpecParamDTO specParamDTO);

    @ApiOperation(value = "修改规格参数")
    @PutMapping(value = "/specparam/save")
    Result<JSONObject> updateSpecParam(@RequestBody SpecParamDTO specParamDTO);

    @ApiOperation(value = "删除规格参数")
    @DeleteMapping(value = "/specparam/delete")
    Result<JSONObject>delSpecParam(Integer id);
}
