package edu.miu.moviecatalogservice.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Text;

@RestController
@RefreshScope
public class ReadConfigServerController {


    @Value("${appName: default value}")
    private String appName;

    @Value("${desc: default desc}")
    private String desc1;

    @Value("${desc2: default desc}")
    private String desc2;

    @GetMapping("/ReadConfig")
    public String getConfigText(){
        System.out.println(">>>> appName: "+appName);
        System.out.println(">> desc1: "+desc1);
        System.out.println(">> desc2: "+desc2);
        return appName+"  \n "+desc1+"  \n "+desc2;
    }
}
