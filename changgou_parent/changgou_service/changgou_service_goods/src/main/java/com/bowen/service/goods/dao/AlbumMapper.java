package com.bowen.service.goods.dao;

import com.bowen.service.goods.api.pojo.Album;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface AlbumMapper extends Mapper<Album> {
}
