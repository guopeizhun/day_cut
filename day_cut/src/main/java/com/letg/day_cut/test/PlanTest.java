package com.letg.day_cut.test;

import com.letg.day_cut.model.Task;
import com.letg.day_cut.model.vo.PlanVO;
import com.letg.day_cut.service.PlanService;
import javafx.scene.control.Tab;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanTest {

    @Autowired
    private PlanService service;

    @Test
    public void formulatePlan(){
        ArrayList<Task> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Task task1 = new Task();
            task1.setDetail("123");
            task1.setPid(i);
            task1.setTimeEnd(new Date());
            task1.setTimeStart(new Date());
            task1.setOrderNumber(1);
            list.add(task1);
        }
        PlanVO planVO = new PlanVO();
        planVO.setTaskList(list);
        service.formulate(planVO);
    }

}
