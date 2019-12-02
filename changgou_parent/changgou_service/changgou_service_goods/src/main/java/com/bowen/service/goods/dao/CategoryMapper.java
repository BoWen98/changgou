package com.bowen.service.goods.dao;


import com.bowen.service.goods.api.pojo.Category;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CategoryMapper extends Mapper<Category> {

    @Select("SELECT tb.* FROM tb_brand tb,tb_category_brand tcb WHERE tb.id = tcb.brand_id AND tcb.category_id = #{pid}")
    public List<Category> findByParentId(Integer pid);

}
