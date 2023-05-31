package com.letg.day_cut.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.letg.day_cut.model.PageInfo;
import org.springframework.util.CollectionUtils;


import java.util.List;
import java.util.Map;
import java.util.Set;

public class Query<M extends BaseMapper<O>, O> {


    private ServiceImpl<M, O> service;
    private Map<String, Object> params;
    private static final String PAGE = "page";
    private static final String SIZE = "size";
    private static final String ORDERBY = "orderBy";
    private static final String ORDERTYPE = "orderType";

    public Query(ServiceImpl<M, O> service, Map<String, Object> params) {
        this.service = service;
        this.params = params;
    }

    private Query() {
    }

    public PageInfo selectPage() {
        Object page = params.get(PAGE);
        Object size = params.get(SIZE);
        Object orderBy = params.get(ORDERBY);
        Object orderType = params.get(ORDERTYPE);
        Page<O> pageObj = new Page<O>((Long) page, (Long) size);
        //移除page相关参数
        removeExcludeParams(params);

        QueryWrapper<O> wr = new QueryWrapper<>();
        wr.orderBy(false, orderType.equals("asc"), orderBy.toString());

        //封装参数
        bulidWrapper(params,wr);

        Page<O> pageRes = service.page(pageObj, wr);
        return coverPageToInfo(pageRes);
    }

    private void removeExcludeParams(Map<String, Object> params) {
        params.remove(PAGE);
        params.remove(SIZE);
        params.remove(ORDERBY);
        params.remove(ORDERTYPE);
    }

    private PageInfo coverPageToInfo(Page page) {
        List records = page.getRecords();
        long total = page.getTotal();
        PageInfo pageInfo = new PageInfo();
        pageInfo.setData(records);
        pageInfo.setTotal(total);
        return pageInfo;
    }


    public O selectOne() {
        QueryWrapper<O> wr = bulidWrapper(params,null);
        return service.getOne(wr);
    }

    public List<O> selectList() {
        QueryWrapper<O> wr = bulidWrapper(params,null);
        return service.list(wr);
    }

    /**
     * 封装wrapper
     *
     * @param params
     * @return
     */
    private QueryWrapper<O> bulidWrapper(Map<String, Object> params, QueryWrapper<O> wrapper) {
        QueryWrapper<O> wr = null == wrapper ? new QueryWrapper<O>() : wrapper;
        //其他参数
        if (!CollectionUtils.isEmpty(params)) {
            Set<String> keySet = params.keySet();
            for (String key : keySet) {
                wr.eq(key, params.get(key));
            }
        }
        return wr;
    }
}
