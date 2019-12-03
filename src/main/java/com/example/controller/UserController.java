package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.service.UserService;
import com.example.util.test.PrePdf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.soap.MimeHeaders;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

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

    @RequestMapping("/cookies")
    public String gogo2(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        Cookie[] cookies = req.getCookies();
        for(Cookie cookie :cookies){
            sb.append(cookie.getName()+":"+cookie.getValue()+"\r");
        }
        return sb.toString();
    }
    @RequestMapping("/attributes2")
    public String gogo3(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        Enumeration enumeration = req.getAttributeNames();
        if(enumeration.hasMoreElements()){
            sb.append(enumeration.nextElement().toString()+"\r");
        }
        return sb.toString();
    }
    @RequestMapping("/headers")
    public String headers(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        Cookie[] cookies = req.getCookies();
        HttpSession session = req.getSession();
//        session.setAttribute("gg","333");
//        Enumeration enumeration = req.getAttributeNames();
        Enumeration enumeration = req.getHeaderNames();
        sb.append("获取headernames和值<br>");
//        sb.append("<br>");
        while (enumeration.hasMoreElements()){
            String name = enumeration.nextElement().toString();
            sb.append(name+"  :   "+req .getHeader(name)+"<br>");
        }
        enumeration = req.getParameterNames();
        sb.append("获取参数名和值"+"<br>");
        while (enumeration.hasMoreElements()){
            String paramName = enumeration.nextElement().toString();
            sb.append(paramName+"   :   "+req.getParameter(paramName)+"<br>");
        }
//        Enumeration enumeration = session.getAttributeNames();
//        Locale locale = req.getLocale();
//        sb.append(locale.toString());
//        String authType = req.getAuthType();
//        sb.append(authType);
        String characterEncoding = req.getCharacterEncoding();
        sb.append("获取请求主体字符编码名称    :   "+characterEncoding+"<br>");
        String contentType = req.getContentType();
        sb.append("获取消息内容类型  :   "+contentType+"<br>");
        String contextPath = req.getContextPath();
        sb.append("获取ContextPath   :   "+contextPath+"<br>");
        String method = req.getMethod();
        sb.append("获取Http方法名  :   "+method+"<br>");
        String pathInfo = req.getPathInfo();
        sb.append("获取路径信息PatbInfo :   "+pathInfo+"<br>");
        String protocol = req.getProtocol();
        sb.append("获取协议名称和版本 :   "+protocol+"<br>");
        String queryString = req.getQueryString();
        sb.append("获取queryString请求字符串 :   "+queryString+"<br>");
        String remoteAddr = req.getRemoteAddr();
        sb.append("获取remoteAddr客户端ip地址 :   "+remoteAddr+"<br>");
        String remoteHost = req.getRemoteHost();
        sb.append("获取remoteHost完全限定名称 :   "+remoteHost+"<br>");
        String remoteUser = req.getRemoteUser();
        sb.append("获取remoteUser :   "+remoteUser+"<br>");
        String requestURI = req.getRequestURI();
        sb.append("获取requestURI :   "+requestURI+"<br>");
        String requestSessionId = req.getRequestedSessionId();
        sb.append("获取requestSessionId :   "+requestSessionId+"<br>");
        Boolean isSecure = req.isSecure();
        sb.append("获取isSecure   https :   "+isSecure+"<br>");
        int ServerPort = req.getServerPort();
        sb.append("获取ServerPort :   "+ServerPort+"<br>");
        Map<String,String[]> paramMap = req.getParameterMap();
        Iterator iterator = paramMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            sb.append(entry.getKey() + "  :  " + entry.getValue()+ "<br>");
        }
        sb.append("获取Cookies :   <br>");
        for(Cookie cookie : cookies){
            sb.append(cookie.getName()+"  :  "+cookie.getValue()+"<br>");
        }
//        Multipurpose Internet Mail Extensions

        return sb.toString();
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
//                    "inline; file∂name= file");
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
