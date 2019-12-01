package com.bowen.service.goods.service.Impl;

import com.bowen.service.goods.api.pojo.Brand;
import com.bowen.service.goods.dao.BrandMapper;
import com.bowen.service.goods.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ProjectName: changgou
 * @Package: com.bowen.service.goods.service.Impl
 * @ClassName: BrandServiceImpl
 * @Author: Bowen
 * @Description: Brang业务层
 * @Date: 2019/12/1 10:45
 * @Version: 1.0.0
 */

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findList(Brand brand) {
        Example example = createExample(brand);
        return brandMapper.selectByExample(example);
    }

    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void saveBrand(Brand brand) {
        brandMapper.insertSelective(brand);
    }

    @Override
    public void updateBrand(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void deleteBrand(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<Brand> findPage(Integer page, Integer size) {
        //分页实现
        /**
         * @Method findPage
         * @Author Bowen
         * @Version 1.0.0
         * @Description
         * @param page 当前页
         * @param size 每页显示多少条
         * @Return com.github.pagehelper.PageInfo<com.bowen.service.goods.api.pojo.Brand>
         * @Exception
         * @Date 2019/12/1 19:30
         */
        PageHelper.startPage(page, size);
        //查询集合
        List<Brand> brands = brandMapper.selectAll();
        //封装PageInfo
        return new PageInfo<Brand>(brands);
    }

    @Override
    public PageInfo<Brand> findPage(Brand brand, Integer page, Integer size) {
        //分页实现
        /**
         * @Method findPage
         * @Author Bowen
         * @Version 1.0.0
         * @Description
         * @param page 当前页
         * @param size 每页显示多少条
         * @Return com.github.pagehelper.PageInfo<com.bowen.service.goods.api.pojo.Brand>
         * @Exception
         * @Date 2019/12/1 19:30
         */
        PageHelper.startPage(page, size);
        //查询
        Example example = createExample(brand);
        List<Brand> brands = brandMapper.selectByExample(example);
        //封装PageInfo
        return new PageInfo<Brand>(brands);
    }


    /**
     * @param brand
     * @Method
     * @Author Bowen
     * @Version 1.0.0
     * @Description 查询条件构建
     * @Return
     * @Exception
     * @Date 2019/12/1 19:25
     */

    public Example createExample(Brand brand) {
        //自定义条件搜索
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();//条件构造器
        if (brand != null) {
            if (StringUtils.isNotEmpty(brand.getName())) {
                criteria.andLike("name", "%" + brand.getName() + "%");
            }
            if (StringUtils.isNotEmpty(brand.getLetter())) {
                criteria.andEqualTo("letter", brand.getLetter());
            }
        }
        return example;
    }

}
