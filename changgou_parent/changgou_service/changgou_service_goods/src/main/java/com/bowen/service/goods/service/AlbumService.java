package com.bowen.service.goods.service;

import com.bowen.service.goods.api.pojo.Album;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AlbumService {

    public List<Album> findAll();

    public List<Album> findList(Album album);

    public Album findById(Long id);

    public void saveBrand(Album album);

    public void updateBrand(Album album);

    public void deleteBrand(Long id);

    public PageInfo<Album> findPage(Integer page, Integer size);

    public PageInfo<Album> findPage(Album album, Integer page, Integer size);
}
