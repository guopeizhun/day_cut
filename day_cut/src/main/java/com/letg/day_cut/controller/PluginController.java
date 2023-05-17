package com.letg.day_cut.controller;

import com.letg.day_cut.model.Result;
import com.letg.day_cut.service.PluginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plugin")
public class PluginController {

    @Autowired
    private PluginService pluginService;


    @GetMapping("/loadPlugin")
    public Result loadPlugin() throws Exception {
        return pluginService.loadPlugin();
    }

}
