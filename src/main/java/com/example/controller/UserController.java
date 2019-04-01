package com.example.controller;

import com.example.entity.Man;
import com.example.service.ManService;
import com.example.service.PdfExportServiceImpl;
import com.example.service.UserService;
import com.example.util.map.PdfView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/testBoot")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ManService manService;

    @Autowired
    private PdfExportServiceImpl exportService;

    @RequestMapping("getUser/{id}")
    public String GetUser(@PathVariable int id){
        return userService.Sel(id).toString();
    }

    @RequestMapping("getPdf")
    public String GetPdf(){

        return null;
    }

    @RequestMapping("/export/pdf")
    public ModelAndView exportPdf(){
        List<Man> manList = manService.getManList();
        Map<String,Object> map = new HashMap<>();
        map.put("manList",manList);
        View view = new PdfView(exportService.make(map,));
        ModelAndView mv = new ModelAndView();
        mv.setView(view);
        return mv;
    }


}
