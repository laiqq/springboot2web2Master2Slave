package com.jony.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jony.platform.sender.Sender;



/**
 * Created by jony
 */
@Controller
public class MqController {
    @Autowired
    private Sender sender;


    @RequestMapping("send/")
    @ResponseBody
    public  String hello() throws Exception {
        for(int i=0;i<10;i++){
            sender.send(i+1);
        }
          return "ok";
    }
}
