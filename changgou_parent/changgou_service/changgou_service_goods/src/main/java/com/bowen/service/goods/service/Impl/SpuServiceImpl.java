package com.bowen.service.goods.service.Impl;


import com.alibaba.fastjson.JSON;
import com.bowen.common.utils.IdWorker;
import com.bowen.service.goods.api.pojo.*;
import com.bowen.service.goods.dao.BrandMapper;
import com.bowen.service.goods.dao.CategoryMapper;
import com.bowen.service.goods.dao.SkuMapper;
import com.bowen.service.goods.dao.SpuMapper;
import com.bowen.service.goods.service.SpuService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public int putMany(Long[] ids) {
        //构建修改结果
        Spu spu = new Spu();
        spu.setIsMarketable("1");  //上架
        //修改的条件
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        //修改范围
        //把数组转换成List
        List<Long> longs = Arrays.asList(ids);
        criteria.andIn("id", longs);

        criteria.andEqualTo("isMarketable", "0"); //下架商品才能上架
        criteria.andEqualTo("status", "1"); //审核通过的
        criteria.andEqualTo("isDelete", "0"); //非删除的
        //updateByExampleSelective（修改的结果，修改的条件）
        int count = spuMapper.updateByExampleSelective(spu, example);
        return count;
    }

    @Override
    public void put(Long spuId) {
        Spu bePut = spuMapper.selectByPrimaryKey(spuId);
        if ("1".equals(bePut.getIsDelete())) {
            throw new RuntimeException("已删除的商品，无法上架!");
        }
        if (!"1".equals(bePut.getStatus())) {
            throw new RuntimeException("未审核通过的商品，无法上架!");
        }
        bePut.setIsMarketable("1");
        spuMapper.updateByPrimaryKeySelective(bePut);
    }

    @Override
    public void pull(Long spuId) {
        Spu bePull = spuMapper.selectByPrimaryKey(spuId);
        if ("1".equals(bePull.getIsDelete())) {
            throw new RuntimeException("已删除的商品，无法下架!");
        }
        if (!"1".equals(bePull.getStatus())) {
            throw new RuntimeException("未审核通过的商品，无法下架!");
        }
        bePull.setIsMarketable("0");
        spuMapper.updateByPrimaryKeySelective(bePull);
    }

    @Override
    public void audit(Long spuId) {
        Spu beUpdate = spuMapper.selectByPrimaryKey(spuId);
        if ("1".equals(beUpdate.getIsDelete())) {
            throw new RuntimeException("已删除的商品，无法审核!");
        }
        beUpdate.setStatus("1");  //审核通过
        beUpdate.setIsMarketable("1");  //上架
        spuMapper.updateByPrimaryKeySelective(beUpdate);
    }

    @Override
    public void saveGoods(Goods goods) {
        //保存spu信息
        Spu spu = goods.getSpu();
        //识别是新增还是修改操作
        if (spu.getId() == null) {
            spu.setId(idWorker.nextId());  //生成spuId
            spuMapper.insertSelective(spu);
        } else {  //修改操作
            //先修改spu信息
            spuMapper.updateByPrimaryKeySelective(spu);

            //先删除目前的sku列表
            Sku where = new Sku();
            where.setSpuId(spu.getId());
            skuMapper.delete(where);
        }
        //查询商品分类与品牌信息
        Category category = categoryMapper.selectByPrimaryKey(spu.getCategory3Id());
        Brand brand = brandMapper.selectByPrimaryKey(spu.getBrandId());

        //遍历保存sku列表
        for (Sku sku : goods.getSkuList()) {
            sku.setId(idWorker.nextId());  //skuID
            //拼接SKU名称
            String name = goods.getSpu().getName();
            Map<String, String> specMap = JSON.parseObject(sku.getSpec(), Map.class);  //把spec字符转Map
            for (String value : specMap.values()) {
                name += " " + value;
            }
            sku.setName(name);
            sku.setCreateTime(new Date());  //创建时间
            sku.setUpdateTime(sku.getCreateTime());  //更新时间
            sku.setSpuId(spu.getId());  //spuId
            //分类
            sku.setCategoryId(spu.getCategory3Id());  //商品分类

            sku.setCategoryName(category.getName());
            //品牌信息
            sku.setBrandName(brand.getName());
            //保存sku
            skuMapper.insertSelective(sku);
        }

    }

    @Override
    public Goods findGoodsById(Long spuId) {
        Goods goods = new Goods();
        //查询spu信息
        Spu spu = spuMapper.selectByPrimaryKey(spuId);

        goods.setSpu(spu);
        //查询sku列表
        Sku where = new Sku();
        where.setSpuId(spuId);
        List<Sku> skuList = skuMapper.select(where);
        goods.setSkuList(skuList);
        return goods;
    }

    /**
     * 查询全部列表
     *
     * @return
     */
    @Override
    public List<Spu> findAll() {
        return spuMapper.selectAll();
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Override
    public Spu findById(String id) {
        return spuMapper.selectByPrimaryKey(id);
    }


    /**
     * 增加
     *
     * @param spu
     */
    @Override
    public void add(Spu spu) {
        spuMapper.insert(spu);
    }


    /**
     * 修改
     *
     * @param spu
     */
    @Override
    public void update(Spu spu) {
        spuMapper.updateByPrimaryKey(spu);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(String id) {
        spuMapper.deleteByPrimaryKey(id);
    }


    /**
     * 条件查询
     *
     * @param searchMap
     * @return
     */
    @Override
    public List<Spu> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return spuMapper.selectByExample(example);
    }

    /**
     * 分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Spu> findPage(int page, int size) {
        PageHelper.startPage(page, size);
        return (Page<Spu>) spuMapper.selectAll();
    }

    /**
     * 条件+分页查询
     *
     * @param searchMap 查询条件
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @Override
    public Page<Spu> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page, size);
        Example example = createExample(searchMap);
        return (Page<Spu>) spuMapper.selectByExample(example);
    }

    /**
     * 构建查询对象
     *
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap) {
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap != null) {
            // 主键
            if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                criteria.andEqualTo("id", searchMap.get("id"));
            }
            // 货号
            if (searchMap.get("sn") != null && !"".equals(searchMap.get("sn"))) {
                criteria.andEqualTo("sn", searchMap.get("sn"));
            }
            // SPU名
            if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                criteria.andLike("name", "%" + searchMap.get("name") + "%");
            }
            // 副标题
            if (searchMap.get("caption") != null && !"".equals(searchMap.get("caption"))) {
                criteria.andLike("caption", "%" + searchMap.get("caption") + "%");
            }
            // 图片
            if (searchMap.get("image") != null && !"".equals(searchMap.get("image"))) {
                criteria.andLike("image", "%" + searchMap.get("image") + "%");
            }
            // 图片列表
            if (searchMap.get("images") != null && !"".equals(searchMap.get("images"))) {
                criteria.andLike("images", "%" + searchMap.get("images") + "%");
            }
            // 售后服务
            if (searchMap.get("saleService") != null && !"".equals(searchMap.get("saleService"))) {
                criteria.andLike("saleService", "%" + searchMap.get("saleService") + "%");
            }
            // 介绍
            if (searchMap.get("introduction") != null && !"".equals(searchMap.get("introduction"))) {
                criteria.andLike("introduction", "%" + searchMap.get("introduction") + "%");
            }
            // 规格列表
            if (searchMap.get("specItems") != null && !"".equals(searchMap.get("specItems"))) {
                criteria.andLike("specItems", "%" + searchMap.get("specItems") + "%");
            }
            // 参数列表
            if (searchMap.get("paraItems") != null && !"".equals(searchMap.get("paraItems"))) {
                criteria.andLike("paraItems", "%" + searchMap.get("paraItems") + "%");
            }
            // 是否上架
            if (searchMap.get("isMarketable") != null && !"".equals(searchMap.get("isMarketable"))) {
                criteria.andEqualTo("isMarketable", searchMap.get("isMarketable"));
            }
            // 是否启用规格
            if (searchMap.get("isEnableSpec") != null && !"".equals(searchMap.get("isEnableSpec"))) {
                criteria.andEqualTo("isEnableSpec", searchMap.get("isEnableSpec"));
            }
            // 是否删除
            if (searchMap.get("isDelete") != null && !"".equals(searchMap.get("isDelete"))) {
                criteria.andEqualTo("isDelete", searchMap.get("isDelete"));
            }
            // 审核状态
            if (searchMap.get("status") != null && !"".equals(searchMap.get("status"))) {
                criteria.andEqualTo("status", searchMap.get("status"));
            }

            // 品牌ID
            if (searchMap.get("brandId") != null) {
                criteria.andEqualTo("brandId", searchMap.get("brandId"));
            }
            // 一级分类
            if (searchMap.get("category1Id") != null) {
                criteria.andEqualTo("category1Id", searchMap.get("category1Id"));
            }
            // 二级分类
            if (searchMap.get("category2Id") != null) {
                criteria.andEqualTo("category2Id", searchMap.get("category2Id"));
            }
            // 三级分类
            if (searchMap.get("category3Id") != null) {
                criteria.andEqualTo("category3Id", searchMap.get("category3Id"));
            }
            // 模板ID
            if (searchMap.get("templateId") != null) {
                criteria.andEqualTo("templateId", searchMap.get("templateId"));
            }
            // 运费模板id
            if (searchMap.get("freightId") != null) {
                criteria.andEqualTo("freightId", searchMap.get("freightId"));
            }
            // 销量
            if (searchMap.get("saleNum") != null) {
                criteria.andEqualTo("saleNum", searchMap.get("saleNum"));
            }
            // 评论数
            if (searchMap.get("commentNum") != null) {
                criteria.andEqualTo("commentNum", searchMap.get("commentNum"));
            }

        }
        return example;
    }
}
