package com.letg.day_cut.service;

import com.letg.day_cut.model.Plugin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.letg.day_cut.model.Result;

/**
* @author Administrator
* @description 针对表【t_plugin(设置)】的数据库操作Service
* @createDate 2023-05-15 10:45:36
*/
public interface PluginService extends IService<Plugin> {

    Result loadPlugin() throws Exception;
}
