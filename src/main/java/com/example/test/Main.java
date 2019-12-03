package com.example.test;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
//        Man man = null;
//        System.out.println(test2(man.getAge()));
//        System.out.println(isBlank2(null));
//        StringBuilder sBuilder = new StringBuilder("");

//        Map<String,String>  param = new HashMap<>();
//        param.put("g1","g1Param");
//        param.put("g2","g2Param");
//        param.put("g3","g3Param");
//        param.put("g4","g4Param");
//        String ggog = "{g1}+{g2}+{g3}+{g4}";
//        List<String> gggg = getKeyList(ggog);
////        String[] g1 = ggog.split("\\{|\\}");
//        for(String g : gggg){
//            ggog = ggog.replace("{"+g+"}",param.get(g));
////            System.out.println(param.get(g));
//        }
//
//        System.out.println(ggog);
//        System.out.println(9*6/3);
//        System.out.println(sBuilder.substring(0, 0));
//        String gg = "60.3.3";
//        System.out.println(isInteger(gg));
//        File loala
//        1,2,3,4,2,6
//        int[] arr1 = new int[]{1,1,2,3,1,4,3};
//        int[] arr2 = new int[]{1,2,1,2};
//
////        7+   arr1.sum
//
//
//        int index  = 0 ;
//        int lastIndex = 0;
//        int sum;
//        List<Integer> i = new ArrayList<>();
//        for(int x : arr1){
//            sum = 0;
//            for(;index<lastIndex+x;index++){
//                sum += arr2[index%arr2.length];
//            }
//            lastIndex = index;
//            i.add(sum);
//        }
//
//        for(Integer  it : i){
//            System.out.println(it);
//        }
//        Scanner scan = new Scanner(System.in);
//        String gg = scan.nextLine();
//        gg = gg.replace("\\n","\n");
//        System.out.println(gg);
//        String filePath = "/templete/scorePrint/score_518021910234_2019-11-25_09_25_33.pdf";
//        String filePathTemp = filePath.substring(filePath.indexOf(File.separator,2)+1, filePath.length());
//        filePathTemp = filePathTemp.substring(filePathTemp.indexOf("templete"), filePathTemp.length());
        List<String> gg  = new ArrayList<>();
        gg.add("1");
        gg.add("2");
        gg.add("3");
        gg.add("4");
        gg.add("5");
        gg.add("6");
        gg.add("7");
        gg.add("8");
        gg.add("9");
        gg.add("0");
        gg.remove(4);
//        gg.set(3,null);
        System.out.println(gg.size());
//        String weatherUrl = "http://localhost:8089/testBoot/";
////        String userAgent = UserAgentUtil.getUserAgents();
//        Connection.Response hh= Jsoup.connect(weatherUrl).execute();
//        System.out.println(hh.body());
//        String str = "zfsoft123&amp;";
//        String str2 = "zfsoft123$";
//        String str43 = "fsoft12&amp;&lt;a href='http://www.qq.com'&gt;QQ&lt;/a&gt;&lt;script&gt";
//        System.out.println(StringEscapeUtils.unescapeHtml4(str43));
//        System.out.println(StringEscapeUtils.unescapeHtml4(str));
//        System.out.println(StringEscapeUtils.escapeHtml3(str43));
String str = null;
        System.out.println(str.substring(0,str.contains("-")?str.indexOf("-"):str.length()));

//        String str = "zfsoft123&amp;";
//        String str2 = "zfsoft123$";
//        String str43 = "fsoft12&amp;&lt;a href='http://www.qq.com'&gt;QQ&lt;/a&gt;&lt;script&gt";
//        System.out.println(StringEscapeUtils.unescapeHtml4(str43));
//        System.out.println(StringEscapeUtils.unescapeHtml4(str));
        Scanner scan = new Scanner(System.in);
        String gogo = scan.nextLine();
        String ggf =  gogo.replace("{\\n}","\n");
//        System.out.println(StringEscapeUtils.escapeHtml3(ggf));
    }
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[\\-|\\+]?\\d+(\\.\\d+)?$");
        return pattern.matcher(str).matches();
    }
    public static List<String> getKeyList (String input ){
        if(input== null || input.length()<1){
            return null;
        }
        List<String> result = new ArrayList<>();
        char[] inputCharArr = input.toCharArray();
        int startIndex = 0;
        int endIndex = 0;
        boolean openFlag = false;
        for(int x = 0; x<inputCharArr.length;x++){
            if('{' == inputCharArr[x]){
                startIndex = x;
                openFlag = true;
            }else if(openFlag && '}' == inputCharArr[x]){
                endIndex = x;
                openFlag = false;
                result.add(input.substring(startIndex+1,endIndex));
            }
        }
        return result;
    }

    public static String test2(String str) {
        return getSafeStr(str);
    }

    public static String getSafeStr(String str) {
        return isBlank(str) ? "" : str;
    }

    public static boolean isBlank(final String str) {
        return str == null || str.trim().isEmpty();
    }


//    public String xgMm() throws Exception {
//        if ("save".equalsIgnoreCase(this.getRequest().getParameter("doType"))) {
//            boolean result = false;
//            try {
//                this.model.setYhm(this.getUser().getYhm());
//                model.setMm(StringEscapeUtils.unescapeHtml3(model.getMm()));
//                model.setMm(StringEscapeUtils.unescapeHtml4(model.getMm()));
//                result = this.getYhglService().checkYhMm(this.model.getYhm(), this.model.getYmm());
//                if (result) {
//                    this.getYhglService().xgMm(this.model);
//                    this.getValueStack().set("data", this.getText("I99003") + "请重新登录!");
//                } else {
//                    this.getValueStack().set("data", "请确认用户名或密码是否正确。");
//                }
//            } catch (Exception var3) {
//                this.logStackException(var3);
//                this.getValueStack().set("data", this.getText("I99004"));
//            }
//
//
//            return "data";
//        } else {
//            return "success";
//        }
//    }


    public static boolean isBlank2(String str){
        if(str.isEmpty()){
            return true;
        }
        return false;
    }
    public static String formatDecimal(String value, int scale) {
        String result;
        if (!value.matches("-?[0-9]*.[0-9]*")|| scale < 0) {
            result = value;
            return result;
        }
        BigDecimal b = new BigDecimal(value);
        double f1 = b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        result = String.valueOf(f1);
        if (scale == 0) {
            result = String.valueOf((int) f1);
        }
        if(result.startsWith(".")){
            result = "0" + result;
        }
        return result;
    }
}
