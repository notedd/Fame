package com.zbw.fame.controller.admin;

import com.zbw.fame.model.domain.Meta;
import com.zbw.fame.model.dto.MetaInfo;
import com.zbw.fame.service.MetaService;
import com.zbw.fame.util.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 属性(标签和分类)管理 Controller
 *
 * @author zzzzbw
 * @since 2017/8/28 23:16
 */
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public abstract class AbstractMetaController<META extends Meta> {

    private final MetaService<META> metaService;


    /**
     * 获取所有属性
     *
     * @return {@see List<MetaDto>}
     */
    @GetMapping
    public RestResponse<List<MetaInfo>> getAll() {
        return RestResponse.ok(metaService.getAdminMetaInfos());
    }

    /**
     * 根据name删除分类
     *
     * @param name 属性名
     * @return {@link RestResponse#ok()}
     */
    @DeleteMapping("{name}")
    public RestResponse<RestResponse.Empty> delete(@PathVariable String name) {
        metaService.delete(name);
        return RestResponse.ok();
    }

    /**
     * 添加一个分类
     *
     * @param name 属性名
     * @return {@link RestResponse#ok()}
     */
    @PostMapping("{name}")
    public RestResponse<RestResponse.Empty> save(@PathVariable String name) {
        metaService.save(name);
        return RestResponse.ok();
    }

    /**
     * 根据id修改分类
     *
     * @param id   属性id
     * @param name 新属性名
     * @return {@link RestResponse#ok()}
     */
    @PutMapping("{id}/{name}")
    public RestResponse<RestResponse.Empty> update(@PathVariable Integer id, @PathVariable String name) {
        metaService.update(id, name);
        return RestResponse.ok();
    }
}
