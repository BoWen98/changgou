package com.bowen.service.goods.service;

import com.bowen.service.goods.api.pojo.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BrandService {

    public List<Brand> findByCategory(Integer categoryid);

    public List<Brand> findAll();

    public List<Brand> findList(Brand brand);

    public Brand findById(Integer id);

    public void saveBrand(Brand brand);

    public void updateBrand(Brand brand);

    public void deleteBrand(Integer id);

    public PageInfo<Brand> findPage(Integer page, Integer size);

    public PageInfo<Brand> findPage(Brand brand, Integer page, Integer size);
}
