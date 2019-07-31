package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.service.UserService;
import com.example.util.test.PrePdf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

@RestController
@RequestMapping("/testBoot")
public class UserController {
    @Autowired
    private UserService userService;


    @Autowired
    private PrePdf prePdf;



    @RequestMapping("getUser/{id}")
    public String GetUser(@PathVariable int id){
        return userService.Sel(id).toString();
    }

    @RequestMapping("getPdf")
    public String GetPdf(){

        return null;
    }
    @RequestMapping("/")
    public String gogo(){
        return "Hello bro";
    }

    @RequestMapping("/export/pdf")
    public void exportPdf(HttpServletResponse response) throws Exception {
        OutputStream outputStream = response.getOutputStream();
        prePdf.createPDF(outputStream);
    }
    @RequestMapping(value = "/export/pdf2",method = RequestMethod.POST)
    public String exportPdf2(HttpServletResponse response, @RequestParam String nodeInfo) throws Exception {
        OutputStream outputStream = response.getOutputStream();
        JSONArray nodeJsonObj = JSONArray.parseArray(nodeInfo);
        prePdf.decompling(outputStream,nodeJsonObj);
        return nodeInfo;
    }


}
