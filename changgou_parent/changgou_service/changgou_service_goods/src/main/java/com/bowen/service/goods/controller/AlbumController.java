package com.bowen.service.goods.controller;

import com.bowen.common.utils.Result;
import com.bowen.common.utils.StatusCode;
import com.bowen.service.goods.api.pojo.Album;
import com.bowen.service.goods.api.pojo.Brand;
import com.bowen.service.goods.service.AlbumService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: changgou
 * @Package: com.bowen.service.goods.controller
 * @ClassName: AlbumController
 * @Author: Bowen
 * @Description: 相册控制层
 * @Date: 2019/12/2 15:03
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping
    public Result<List<Album>> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功", albumService.findAll());
    }

    @PostMapping
    public Result<Album> saveBrad(@RequestBody Album album) {
        albumService.saveBrand(album);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    @GetMapping("/{id}")
    public Result<Album> findById(@PathVariable Long id) {
        return new Result<>(true, StatusCode.OK, "查询成功", albumService.findById(id));
    }

    @PostMapping("/{id}")
    public Result<Brand> updateBrad(@RequestBody Album album, @PathVariable Long id) {
        album.setId(id);
        albumService.updateBrand(album);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    @DeleteMapping("/{id}")
    public Result<Album> deleteBrad(@PathVariable Long id) {
        albumService.deleteBrand(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    @PostMapping("/search")
    public Result<Album> findList(@RequestBody Album album) {
        return new Result<>(true, StatusCode.OK, "查询成功", albumService.findList(album));
    }

    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<Album>> findPage(@PathVariable("page") Integer page,
                                            @PathVariable("size") Integer size) {
        return new Result<PageInfo<Album>>(true, StatusCode.OK, "分页成功", albumService.findPage(page, size));
    }

    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<Album>> findPage(@RequestBody Album album, @PathVariable("page") Integer page,
                                            @PathVariable("size") Integer size) {
        return new Result<PageInfo<Album>>(true, StatusCode.OK, "分页成功", albumService.findPage(album, page, size));
    }
}
