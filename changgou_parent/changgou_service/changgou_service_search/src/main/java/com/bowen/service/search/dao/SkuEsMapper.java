package com.bowen.service.search.dao;

import com.bowen.service.search.api.pojo.SkuInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Steven
 * @version 1.0
 * @description com.changgou.search.dao
 * @date 2019-9-6
 */
@Repository
public interface SkuEsMapper extends ElasticsearchRepository<SkuInfo, Long> {
}
