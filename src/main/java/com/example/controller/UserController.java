package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.service.UserService;
import com.example.util.test.PrePdf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public String GetUser(@PathVariable int id) {
        System.out.println(id);
        return userService.Sel(id).toString();
    }

    @RequestMapping("getPdf")
    public String GetPdf() {

        return null;
    }

    @RequestMapping("/")
    public String gogo() {
        return "Hello bro";
    }

    @RequestMapping("/export/pdf")
    public void exportPdf(HttpServletResponse response) throws Exception {
        OutputStream outputStream = response.getOutputStream();
        prePdf.createPDF(outputStream);
    }

    @CrossOrigin
    @RequestMapping(value = "/export/pdf2", method = RequestMethod.POST)
    public String exportPdf2(HttpServletRequest request, HttpServletResponse response, @RequestParam String nodeInfo) throws Exception {
        long startTime = System.currentTimeMillis();
        OutputStream outputStream = response.getOutputStream();
        JSONObject obj = JSON.parseObject(nodeInfo);
        String result = prePdf.decompling(obj);
        long endTime = System.currentTimeMillis();
        System.out.println("total cost: " + (endTime - startTime) + " (ms)");
        return result;
    }

    @RequestMapping("/export/pdf3")
    public void exportPdf3(HttpServletResponse response) throws Exception {
//        response.reset();
//        response.setContentType("application/pdf");
//        try {
//            File file = new File(dest);
//            FileInputStream fileInputStream = new FileInputStream(file);
//            OutputStream outputStream = response.getOutputStream();
//            IOUtils.write(IOUtils.toByteArray(fileInputStream), outputStream);
//            response.setHeader("Content-Disposition",
//                    "inline; filename= file");
//            outputStream.flush();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        OutputStream outputStream = response.getOutputStream();
        prePdf.tableTest(outputStream);
    }


}
