package com.bowen.service.goods.controller;

import com.bowen.common.utils.Result;
import com.bowen.common.utils.StatusCode;
import com.bowen.service.goods.api.pojo.Brand;
import com.bowen.service.goods.service.BrandService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: changgou
 * @Package: com.bowen.service.goods.controller
 * @ClassName: BrangController
 * @Author: Bowen
 * @Description: Brand控制层
 * @Date: 2019/12/1 10:46
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/brand")
public class BrangController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/category/{id}")
    public Result<List<Brand>> findByCategory(@PathVariable("id") Integer categoryid) {
        return new Result<>(true, StatusCode.OK, "查询成功", brandService.findByCategory(categoryid));
    }

    @GetMapping
    public Result<List<Brand>> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功", brandService.findAll());
    }

    @GetMapping("/{id}")
    public Result<Brand> findById(@PathVariable Integer id) {
        return new Result<>(true, StatusCode.OK, "查询成功", brandService.findById(id));
    }

    @PostMapping
    public Result<Brand> saveBrad(@RequestBody Brand brand) {
        brandService.saveBrand(brand);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    @PostMapping("/{id}")
    public Result<Brand> updateBrad(@RequestBody Brand brand, @PathVariable Integer id) {
        brand.setId(id);
        brandService.updateBrand(brand);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }


    @DeleteMapping("/{id}")
    public Result<Brand> deleteBrad(@PathVariable Integer id) {
        brandService.deleteBrand(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    @PostMapping("/search")
    public Result<Brand> findList(@RequestBody Brand brand) {
        return new Result<>(true, StatusCode.OK, "查询成功", brandService.findList(brand));
    }

    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<Brand>> findPage(@PathVariable("page") Integer page,
                                            @PathVariable("size") Integer size) {
        return new Result<PageInfo<Brand>>(true, StatusCode.OK, "分页成功", brandService.findPage(page, size));
    }

    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<Brand>> findPage(@RequestBody Brand brand, @PathVariable("page") Integer page,
                                            @PathVariable("size") Integer size) {
        return new Result<PageInfo<Brand>>(true, StatusCode.OK, "分页成功", brandService.findPage(brand, page, size));
    }


}
