package com.baidu.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.BaseApiService;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.SpecGroupDTO;
import com.baidu.shop.dto.SpecParamDTO;
import com.baidu.shop.entity.SpecGroupEntity;
import com.baidu.shop.entity.SpecParamEntity;
import com.baidu.shop.mapper.SpecGroupMapper;
import com.baidu.shop.mapper.SpecParamMapper;
import com.baidu.shop.service.SpecificationService;
import com.baidu.shop.utils.BaiduBeanUtil;
import com.baidu.shop.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SpecGroupServiceImpl extends BaseApiService implements SpecificationService {

    @Autowired
    private SpecGroupMapper specGroupMapper;

    @Autowired
    private SpecParamMapper specParamMapper;

    @Override
    public Result<List<SpecGroupEntity>> getSepcGroupInfo(SpecGroupDTO specGroupDTO) {

        Example example = new Example(SpecGroupEntity.class);

        if (ObjectUtil.isNotNull(specGroupDTO.getId()));
        example.createCriteria().andEqualTo("cid", BaiduBeanUtil.copyProperties(specGroupDTO, SpecGroupEntity.class).getCid());
        List<SpecGroupEntity> specGroupEntities = specGroupMapper.selectByExample(example);


        return this.setResultSuccess(specGroupEntities);
    }

    @Transactional
    @Override
    public Result<JSONObject> saveSpecGrouo(SpecGroupDTO specGroupDTO) {

        specGroupMapper.insertSelective(BaiduBeanUtil.copyProperties(specGroupDTO,SpecGroupEntity.class));

        return this.setResultSuccess();
    }

    @Transactional
    @Override
    public Result<JSONObject> updateSpecGrouo(SpecGroupDTO specGroupDTO) {
        specGroupMapper.updateByPrimaryKeySelective(BaiduBeanUtil.copyProperties(specGroupDTO,SpecGroupEntity.class));
        return this.setResultSuccess();
    }

    @Transactional
    @Override
    public Result<JSONObject> delSpecGrouo(Integer id) {

        //删除规格组之前需要先判断一下当前规格组下是否有规格参数
        SpecParamEntity specParamEntity = new SpecParamEntity();
        Example example = new Example(SpecParamEntity.class);
        example.createCriteria().andEqualTo("id",specParamEntity.getGroupId());
        List<SpecParamEntity> specParamEntities = specParamMapper.selectByExample(example);
        if (specParamEntities.size()<=1) return this.setResultError("当前规格组下有规格参数不能删除");

        specGroupMapper.deleteByPrimaryKey(id);

        return this.setResultSuccess();
    }

    @Override
    public Result<List<SpecParamEntity>> getSepcParamInfo(SpecParamDTO specParamDTO) {

        SpecParamEntity specParamEntity = BaiduBeanUtil.copyProperties(specParamDTO, SpecParamEntity.class);
        Example example = new Example(SpecParamEntity.class);
        example.createCriteria().andEqualTo("groupId",specParamEntity.getGroupId());

        List<SpecParamEntity> specParamEntities = specParamMapper.selectByExample(example);
      /*  Example example = new Example(SpecParamEntity.class);

        example.createCriteria().andEqualTo("groupId",BaiduBeanUtil.copyProperties(specParamDTO,SpecParamEntity.class).getGroupId());
        List<SpecParamEntity> specParamEntities = specParamMapper.selectByExample(example);*/

        return this.setResultSuccess(specParamEntities);
    }

    @Override
    public Result<JSONObject> saveSpecParam(SpecParamDTO specParamDTO) {
        specParamMapper.insertSelective(BaiduBeanUtil.copyProperties(specParamDTO,SpecParamEntity.class));
        return this.setResultSuccess();
    }

    @Override
    public Result<JSONObject> updateSpecParam(SpecParamDTO specParamDTO) {
        specParamMapper.updateByPrimaryKeySelective(BaiduBeanUtil.copyProperties(specParamDTO,SpecParamEntity.class));
        return this.setResultSuccess();
    }

    @Override
    public Result<JSONObject> delSpecParam(Integer id) {


        specParamMapper.deleteByPrimaryKey(id);

        return this.setResultSuccess();
    }
}