package com.baidu.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.BaseApiService;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.BrandDTO;
import com.baidu.shop.entity.BrandEntity;
import com.baidu.shop.entity.CategoryBrandEntity;
import com.baidu.shop.mapper.BrandMapper;
import com.baidu.shop.mapper.CategoryBrandMapper;
import com.baidu.shop.service.BrandService;
import com.baidu.shop.utils.BaiduBeanUtil;
import com.baidu.shop.utils.PinyinUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BrandMapperImpl extends BaseApiService implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Resource
    private CategoryBrandMapper categoryBrandMapper;

    @Override
    public Result<JSONObject> deleteBrandId(Integer id) {
        brandMapper.deleteByPrimaryKey(id);

        this.deleteCategoryBrandId(id);

        return this.setResultSuccess();
    }

    @Override
    public Result<JSONObject> editBrandInfo(BrandDTO brandDTO) {
        BrandEntity brandEntity = BaiduBeanUtil.copyProperties(brandDTO, BrandEntity.class);
        //给首字母赋值
        brandEntity.setLetter(PinyinUtil.getUpperCase(String.valueOf(brandEntity.getName().toCharArray()[0]),false).toCharArray()[0]);
        brandMapper.updateByPrimaryKeySelective(brandEntity);

        //删除中间表
       this.deleteCategoryBrandId(brandEntity.getId());

        ArrayList<CategoryBrandEntity> list  = new ArrayList<>();
        String categories = brandDTO.getCategories();

        if (StringUtils.isEmpty(brandDTO.getCategories())) return this.setResultSuccess();

        if (categories.contains(",")){
            String[] splitArr = categories.split(",");
            for (String s: splitArr) {
                CategoryBrandEntity categoryBrandEntity = new CategoryBrandEntity();
                categoryBrandEntity.setCategoryId(Integer.valueOf(s));//转换成Intger类型
                categoryBrandEntity.setBrandId(brandEntity.getId());
                list.add(categoryBrandEntity);

            }
            categoryBrandMapper.insertList(list);
            }else{
                CategoryBrandEntity categoryBrandEntity = new CategoryBrandEntity();
                categoryBrandEntity.setBrandId(brandEntity.getId());
                categoryBrandEntity.setCategoryId(Integer.valueOf(categories));
                list.add(categoryBrandEntity);
                categoryBrandMapper.insertSelective(categoryBrandEntity);
            }
            return this.setResultSuccess();
    }



    @Override
    public Result<JSONObject> save(BrandDTO brandDTO) {


        BrandEntity brandEntity = BaiduBeanUtil.copyProperties(brandDTO, BrandEntity.class);

        brandEntity.setLetter(PinyinUtil.getUpperCase(String.valueOf(brandEntity.getName().toCharArray()[0]),false).toCharArray()[0]);


        brandMapper.insertSelective(brandEntity);

        //维护中间表数据
        String categories = brandDTO.getCategories();
        if (StringUtils.isEmpty(brandDTO.getCategories()))return setResultError("");
        ArrayList<CategoryBrandEntity> list  = new ArrayList<>();


        if(categories.contains(",")){
            String[] splitArr = categories.split(",");//得到数组

            for (String s: splitArr){
                CategoryBrandEntity categoryBrandEntity = new CategoryBrandEntity();
                categoryBrandEntity.setBrandId(brandEntity.getId());
                categoryBrandEntity.setCategoryId(Integer.valueOf(s));
                list.add(categoryBrandEntity);

            }
            categoryBrandMapper.insertList(list);
        }else{
            CategoryBrandEntity categoryBrandEntity = new CategoryBrandEntity();
            categoryBrandEntity.setBrandId(brandEntity.getId());
            categoryBrandEntity.setCategoryId(Integer.valueOf(categories));
            list.add(categoryBrandEntity);
            categoryBrandMapper.insertSelective(categoryBrandEntity);
        }
        return this.setResultSuccess();
    }



    @Override
    public Result<PageInfo<BrandEntity>> getList(BrandDTO brandDTO) {


        PageHelper.startPage(brandDTO.getPage(),brandDTO.getRows());

        if(!StringUtils.isEmpty(brandDTO.getSort())) PageHelper.orderBy(brandDTO.getOrderBy());


        BrandEntity brandEntity = BaiduBeanUtil.copyProperties(brandDTO,BrandEntity.class);

        Example example = new Example(BrandEntity.class);
        example.createCriteria().andLike("name","%" + brandEntity.getName() + "%");

        List<BrandEntity> brandEntities = brandMapper.selectByExample(example);
        PageInfo<BrandEntity> pageInfo = new PageInfo<>(brandEntities);

        return this.setResultSuccess(pageInfo);

    }

/*
    private void insertcategoryBrandList(String categories,Integer id){
        if (StringUtils.isEmpty(categories)) throw new RuntimeException("分类信息不能为空");
        ArrayList<CategoryBrandEntity> list  = new ArrayList<>();


        if(categories.contains(",")){
            String[] splitArr = categories.split(",");//得到数组

            for (String s: splitArr){
                CategoryBrandEntity categoryBrandEntity = new CategoryBrandEntity();
                categoryBrandEntity.setBrandId(id);
                categoryBrandEntity.setCategoryId(Integer.valueOf(s));
                list.add(categoryBrandEntity);

            }
            categoryBrandMapper.insertList(list);
        }else{
            CategoryBrandEntity categoryBrandEntity = new CategoryBrandEntity();
            categoryBrandEntity.setBrandId(id);
            categoryBrandEntity.setCategoryId(Integer.valueOf(categories));
            list.add(categoryBrandEntity);
            categoryBrandMapper.insertSelective(categoryBrandEntity);
        }
    }
*/


    private void deleteCategoryBrandId(Integer brandId){

        Example example = new Example(CategoryBrandEntity.class);
        example.createCriteria().andEqualTo("brandId",brandId);
        categoryBrandMapper.deleteByExample(example);

    }

}