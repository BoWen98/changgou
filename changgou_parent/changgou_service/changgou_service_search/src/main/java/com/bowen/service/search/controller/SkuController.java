package com.bowen.service.search.controller;


import com.bowen.common.utils.Result;
import com.bowen.common.utils.StatusCode;
import com.bowen.service.search.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "search")
@CrossOrigin
public class SkuController {

    @Autowired
    private SkuService skuService;

    /**
     * 导入数据
     *
     * @return
     */
    @GetMapping("import")
    public Result importSku() {
        skuService.importSku();
        return new Result(true, StatusCode.OK, "导入数据到索引库中成功！");
    }

    /**
     * 搜索商品
     *
     * @return
     */
    @GetMapping
    public Map search(@RequestParam(required = false) Map searchMap) {
        return skuService.search(searchMap);
    }

}
