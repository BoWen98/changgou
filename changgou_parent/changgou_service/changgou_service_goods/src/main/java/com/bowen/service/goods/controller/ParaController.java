package com.bowen.service.goods.controller;

import com.bowen.common.utils.PageResult;
import com.bowen.common.utils.Result;
import com.bowen.common.utils.StatusCode;
import com.bowen.service.goods.api.pojo.Para;
import com.bowen.service.goods.service.ParaService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/para")
public class ParaController {


    @Autowired
    private ParaService paraService;


    /*
     *根据分类ID查询
     */
    @GetMapping("/category/{id}")
    public Result<List<Para>> findByCategory(@PathVariable("id") Integer categoryId) {
        return new Result(true, StatusCode.OK, "查规格集合成功", paraService.findByCategory(categoryId));
    }

    /**
     * 查询全部数据
     *
     * @return
     */
    @GetMapping
    public Result findAll() {
        List<Para> paraList = paraService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", paraList);
    }

    /***
     * 根据ID查询数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        Para para = paraService.findById(id);
        return new Result(true, StatusCode.OK, "查询成功", para);
    }


    /***
     * 新增数据
     * @param para
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Para para) {
        paraService.add(para);
        return new Result(true, StatusCode.OK, "添加成功");
    }


    /***
     * 修改数据
     * @param para
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Para para, @PathVariable Integer id) {
        para.setId(id);
        paraService.update(para);
        return new Result(true, StatusCode.OK, "修改成功");
    }


    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        paraService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /***
     * 多条件搜索品牌数据
     * @param searchMap
     * @return
     */
    @GetMapping(value = "/search")
    public Result findList(@RequestParam Map searchMap) {
        List<Para> list = paraService.findList(searchMap);
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
        Page<Para> pageList = paraService.findPage(searchMap, page, size);
        PageResult pageResult = new PageResult(pageList.getTotal(), pageList.getResult());
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }


}
