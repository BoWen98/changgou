package com.bowen.service.goods.service;

import com.bowen.service.goods.api.pojo.Goods;
import com.bowen.service.goods.api.pojo.Spu;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface SpuService {


    void saveGoods(Goods goods);

    /**
     * 批量上架
     * @param ids
     * @return
     */
    int putMany(Long[] ids);

    /***
     * 商品上架
     * @param spuId
     */
    void put(Long spuId);

    /***
     * 商品下架
     * @param spuId
     */
    void pull(Long spuId);


    /***
     * 商品审核
     * @param spuId
     */
    void audit(Long spuId);

    /***
     * 根据SPU的ID查找SPU以及对应的SKU集合
     * @param spuId
     */
    Goods findGoodsById(Long spuId);

    /***
     * 查询所有
     * @return
     */
    List<Spu> findAll();

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    Spu findById(String id);

    /***
     * 新增
     * @param spu
     */
    void add(Spu spu);

    /***
     * 修改
     * @param spu
     */
    void update(Spu spu);

    /***
     * 删除
     * @param id
     */
    void delete(String id);

    /***
     * 多条件搜索
     * @param searchMap
     * @return
     */
    List<Spu> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Spu> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    Page<Spu> findPage(Map<String, Object> searchMap, int page, int size);


}
