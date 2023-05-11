package com.letg.day_cut.service.impl;

import com.letg.day_cut.service.SseService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:letg(pz)
 * @Date: 2023/5/4 11:51
 * @Description: 订阅消息服务
 */


public class SseServiceImpl implements SseService {
    private List<SseEmitter> emitters = new ArrayList<>();


    @Override
    public void handleSSE() {
        // 设置超时时间
        SseEmitter emitter = new SseEmitter(5000L);
        emitters.add(emitter);


        // 设置 SSE 离线处理器，用于移除已失效的 emitter
        emitter.onCompletion(() -> {
            synchronized (emitters) {
                emitters.remove(emitter);
            }
        });

    }

   @Async
    @Scheduled(fixedDelay = 1000L)
    public void pushDataToClient() {
        synchronized (emitters) {
            for (SseEmitter emitter : emitters) {
                try {
                    emitter.send(new SseEmitter
                            .SseEventBuilder()
                            .data("SSE Example Data")
                            .id("1")
                            .event("message")
                            .build());
                } catch (IOException e) {
                    emitters.remove(emitter);
                }
            }
        }
    }
}
