package com.example.controller;

import com.example.service.UserService;
import com.example.util.test.PrePdf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @RequestMapping("/export/pdf")
    public void exportPdf(HttpServletResponse response) throws Exception {
        OutputStream outputStream = response.getOutputStream();
        prePdf.createPDF(outputStream);
    }


}
