package com.letg.day_cut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.letg.day_cut.constant.UserConstant;
import com.letg.day_cut.model.Plugin;
import com.letg.day_cut.model.Result;
import com.letg.day_cut.model.vo.PluginVO;
import com.letg.day_cut.service.PluginService;
import com.letg.day_cut.mapper.PluginMapper;
import com.letg.day_cut.util.ReflectUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【t_plugin(设置)】的数据库操作Service实现
* @createDate 2023-05-15 10:45:35
*/
@Service
public class PluginServiceImpl extends ServiceImpl<PluginMapper, Plugin>
    implements PluginService{

    @Override
    public Result loadPlugin() throws Exception {
        Integer uid = UserConstant.getUid();
        LambdaQueryWrapper<Plugin> wr = new LambdaQueryWrapper<>();
        wr.eq(Plugin::getUid,uid);
        Plugin plugin = baseMapper.selectOne(wr);
        PluginVO pluginVO = new PluginVO();
        BeanUtils.copyProperties(plugin,pluginVO);
        ReflectUtil.codeToValue(pluginVO,plugin);
        return Result.ok().data(pluginVO);
    }
}




