package com.bowen.service.goods.controller;

import com.bowen.common.utils.PageResult;
import com.bowen.common.utils.Result;
import com.bowen.common.utils.StatusCode;
import com.bowen.service.goods.api.pojo.Goods;
import com.bowen.service.goods.api.pojo.Spu;
import com.bowen.service.goods.service.SpuService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/spu")
public class SpuController {


    @Autowired
    private SpuService spuService;

    /**
     *  批量上架
     * @param ids
     * @return
     */
    @PutMapping("/put/many")
    public Result putMany(@RequestBody Long[] ids){
        int count = spuService.putMany(ids);
        return new Result(true,StatusCode.OK,"上架"+count+"个商品");
    }


    @PutMapping("put/{id}")
    public Result put(@PathVariable Long id) {
        spuService.put(id);
        return new Result(true, StatusCode.OK, "上架成功");
    }

    @PutMapping("pull/{id}")
    public Result pull(@PathVariable Long id) {
        spuService.pull(id);
        return new Result(true, StatusCode.OK, "下架成功");
    }

    @PutMapping("audit/{id}")
    public Result audit(@PathVariable Long id) {
        spuService.audit(id);
        return new Result(true, StatusCode.OK, "审核成功");
    }

    @GetMapping("goods/{id}")
    public Result<Goods> findGoodsById(@PathVariable Long spuId) {
        Goods goods = spuService.findGoodsById(spuId);
        return new Result<Goods>(true, StatusCode.OK, "查询成功", goods);
    }

    /**
     * 查询全部数据
     *
     * @return
     */
    @GetMapping
    public Result findAll() {
        List<Spu> spuList = spuService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", spuList);
    }

    /***
     * 根据ID查询数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        Spu spu = spuService.findById(id);
        return new Result(true, StatusCode.OK, "查询成功", spu);
    }


    /***
     * 新增数据
     * @param spu
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Spu spu) {
        spuService.add(spu);
        return new Result(true, StatusCode.OK, "添加成功");
    }


    /***
     * 修改数据
     * @param spu
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Spu spu, @PathVariable Long id) {
        spu.setId(id);
        spuService.update(spu);
        return new Result(true, StatusCode.OK, "修改成功");
    }


    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        spuService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /***
     * 多条件搜索品牌数据
     * @param searchMap
     * @return
     */
    @GetMapping(value = "/search")
    public Result findList(@RequestParam Map searchMap) {
        List<Spu> list = spuService.findList(searchMap);
        return new Result(true, StatusCode.OK, "查询成功", list);
    }


    /***
     * 分页搜索实现
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result findPage(@RequestParam Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Spu> pageList = spuService.findPage(searchMap, page, size);
        PageResult pageResult = new PageResult(pageList.getTotal(), pageList.getResult());
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }


}
