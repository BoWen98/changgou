package com.bowen.service.goods.service.Impl;

import com.bowen.service.goods.api.pojo.Album;
import com.bowen.service.goods.api.pojo.Brand;
import com.bowen.service.goods.dao.AlbumMapper;
import com.bowen.service.goods.service.AlbumService;
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
 * @ClassName: AlbumServiceImpl
 * @Author: Bowen
 * @Description: 相册业务层
 * @Date: 2019/12/2 14:53
 * @Version: 1.0.0
 */
@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public List<Album> findAll() {
        return albumMapper.selectAll();
    }

    @Override
    public List<Album> findList(Album album) {
        return albumMapper.select(album);
    }

    @Override
    public Album findById(Long id) {
        return albumMapper.selectByPrimaryKey(id);
    }

    @Override
    public void saveBrand(Album album) {
        albumMapper.insertSelective(album);
    }

    @Override
    public void updateBrand(Album album) {
        albumMapper.updateByPrimaryKeySelective(album);
    }

    @Override
    public void deleteBrand(Long id) {
        albumMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<Album> findPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Album> albums = albumMapper.selectAll();
        return new PageInfo<Album>(albums);
    }

    @Override
    public PageInfo<Album> findPage(Album album, Integer page, Integer size) {
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
        Example example = createExample(album);
        List<Album> albums = albumMapper.selectByExample(example);
        //封装PageInfo
        return new PageInfo<Album>(albums);
    }


    /**
     * @param album
     * @Method
     * @Author Bowen
     * @Version 1.0.0
     * @Description 查询条件构建
     * @Return
     * @Exception
     * @Date 2019/12/1 19:25
     */

    public Example createExample(Album album) {
        //自定义条件搜索
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();//条件构造器
        if (album != null) {
            if (StringUtils.isNotEmpty(album.getTitle())) {
                criteria.andLike("name", "%" + album.getTitle() + "%");
            }
        }
        return example;
    }
}
