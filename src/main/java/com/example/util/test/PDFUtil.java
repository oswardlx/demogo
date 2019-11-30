package com.example.util.test;


import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.security.*;
//import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.*;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.util.List;
import java.util.UUID;

public class PDFUtil {
    public static final String KEYSTORE = "F:\\prosssss\\demogogogogog\\src\\main\\resources\\static\\CA\\p12\\tomatocc.p12";
    public static final char[] PASSWORD = "123456".toCharArray();//keystory密码
    public static final String SRC = "F:\\prosssss\\demogogogogog\\src\\main\\resources\\static\\templates\\gogo1564823044196.pdf";
    public static final String DEST = "F:\\prosssss\\demogogogogog\\target\\classes\\static\\templates\\zzzzzzz.pdf";
    public static final String IMG = "F:\\prosssss\\demogogogogog\\src\\main\\resources\\static\\pic\\fff.png";

    public static void main(String[] args) throws IOException {
//        try {
//            //读取keystore ，获得私钥和证书链
//            KeyStore ks = KeyStore.getInstance("PKCS12");
//            ks.load(new FileInputStream(KEYSTORE), PASSWORD);
//            String alias = (String)ks.aliases().nextElement();
//            PrivateKey pk = (PrivateKey) ks.getKey(alias, PASSWORD);
//            Certificate[] chain = ks.getCertificateChain(alias);
//            //new一个上边自定义的方法对象，调用签名方法
//            PDFUtil app = new PDFUtil();
////      app.sign(SRC, String.format(DEST, 1), chain, pk, DigestAlgorithms.SHA1, provider.getName(), CryptoStandard.CMS, "Test 1", "Ghent");
////      app.sign(SRC, String.format(DEST, 2), chain, pk, "SM3", provider.getName(), CryptoStandard.CADES, "Test 2", "Ghent");
//            app.sign(SRC, String.format(DEST, 3), chain, pk, DigestAlgorithms.SHA1, null, MakeSignature.CryptoStandard.CMS, "Test 3", "Ghent");
////      app.sign(SRC, String.format(DEST, 4), chain, pk, DigestAlgorithms.RIPEMD160, provider.getName(), CryptoStandard.CADES, "Test 4", "Ghent");
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            JOptionPane.showMessageDialog(null, e.getMessage());
//            e.printStackTrace();
//        }
//        byte[] fileData = sign("123456", KEYSTORE, //
//                SRC,//
//                IMG, 100, 290);
//        FileOutputStream f = new FileOutputStream(new File(DEST));
//        f.write(fileData);
//        f.close();
    }


    public void sign1(String src  //需要签章的pdf文件路径
            , String dest  // 签完章的pdf文件路径
            , Certificate[] chain //证书链
            , PrivateKey pk //签名私钥
            , String digestAlgorithm  //摘要算法名称，例如SHA-1
            , String provider  // 密钥算法提供者，可以为null
            , MakeSignature.CryptoStandard subfilter //数字签名格式，itext有2种
            , String reason  //签名的原因，显示在pdf签名属性中，随便填
            , String location) //签名的地点，显示在pdf签名属性中，随便填
            throws GeneralSecurityException, IOException, DocumentException {
        //下边的步骤都是固定的，照着写就行了，没啥要解释的
        // Creating the reader and the stamper，开始pdfreader
        PdfReader reader = new PdfReader(src);
        //目标文件输出流
        FileOutputStream os = new FileOutputStream(dest);
        //创建签章工具PdfStamper ，最后一个boolean参数
        //false的话，pdf文件只允许被签名一次，多次签名，最后一次有效
        //true的话，pdf可以被追加签名，验签工具可以识别出每次签名之后文档是否被修改
        PdfStamper stamper = PdfStamper.createSignature(reader, os, '\0', null, true);
        // 获取数字签章属性对象，设定数字签章的属性
        PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
        appearance.setReason(reason);
        appearance.setLocation(location);
        //设置签名的位置，页码，签名域名称，多次追加签名的时候，签名预名称不能一样
        //签名的位置，是图章相对于pdf页面的位置坐标，原点为pdf页面左下角
        //四个参数的分别是，图章左下角x，图章左下角y，图章右上角x，图章右上角y
        appearance.setVisibleSignature(new Rectangle(200, 200, 300, 300), 1, "sig1");
        //读取图章图片，这个image是itext包的image
        Image image = Image.getInstance("F:\\prosssss\\demogogogogog\\src\\main\\resources\\static\\pic\\fff.png");
        appearance.setSignatureGraphic(image);
        appearance.setCertificationLevel(PdfSignatureAppearance.NOT_CERTIFIED);
        //设置图章的显示方式，如下选择的是只显示图章（还有其他的模式，可以图章和签名描述一同显示）
        appearance.setRenderingMode(PdfSignatureAppearance.RenderingMode.GRAPHIC);

        // 这里的itext提供了2个用于签名的接口，可以自己实现，后边着重说这个实现
        // 摘要算法
        ExternalDigest digest = new BouncyCastleDigest();
        // 签名算法
        ExternalSignature signature = new PrivateKeySignature(pk, digestAlgorithm, null);
        // 调用itext签名方法完成pdf签章
        MakeSignature.signDetached(appearance, digest, signature, chain, null, null, null, 0, subfilter);
    }



//    public static byte[] sign(String password, String keyStorePath, String signPdfSrc, String signImage,   float x, float y) {
//        File signPdfSrcFile = new File(signPdfSrc);
//        PdfReader reader = null;
//        ByteArrayOutputStream signPDFData = null;
//        PdfStamper stp = null;
//        FileInputStream fos = null;
//        try {
//            //  加入算法提供者
//            BouncyCastleProvider provider = new BouncyCastleProvider();
//            Security.addProvider(provider);
//            KeyStore ks = KeyStore.getInstance("PKCS12", new BouncyCastleProvider());
//            fos = new FileInputStream(keyStorePath);
//
//            // 私钥密码 为Pkcs生成证书是的私钥密码 123456
//            ks.load(fos, password.toCharArray());
//            String alias = (String) ks.aliases().nextElement();
//            // 获取私钥
//            PrivateKey key = (PrivateKey) ks.getKey(alias, password.toCharArray());
//            // 获取证书链
//            Certificate[] chain = ks.getCertificateChain(alias);
//            reader = new PdfReader(signPdfSrc);
//            signPDFData = new ByteArrayOutputStream();
//            // 临时pdf文件
//            File temp = new File(signPdfSrcFile.getParent(), System.currentTimeMillis() + ".pdf");
//            stp = PdfStamper.createSignature(reader, signPDFData, '\0', temp, true);
//            stp.setFullCompression();
//            PdfSignatureAppearance sap = stp.getSignatureAppearance();
//            sap.setReason("数字签名，不可改变");
//            // 使用png格式透明图片
//            Image image = Image.getInstance(signImage);
//            sap.setImageScale(0);
//            sap.setSignatureGraphic(image);
//            sap.setRenderingMode(PdfSignatureAppearance.RenderingMode.GRAPHIC);
//            // 是对应x轴和y轴坐标
//            sap.setVisibleSignature( new Rectangle(x, y, x + 185, y + 68), 1,  UUID.randomUUID().toString().replaceAll("-", ""));
//            stp.getWriter().setCompressionLevel(5);
//
//            ExternalDigest digest = new BouncyCastleDigest();
//            ExternalSignature signature = new PrivateKeySignature(key, DigestAlgorithms.SHA512, provider.getName());
//            MakeSignature.signDetached(sap, digest, signature, chain, null, null, null, 0, MakeSignature.CryptoStandard.CADES);
//            stp.close();
//            reader.close();
//            return signPDFData.toByteArray();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (signPDFData != null) {
//                try {
//                    signPDFData.close();
//                } catch (IOException e) {
//                }
//            }
//
//            if (fos != null) {
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                }
//            }
//        }
//        return null;
//    }

    /**
     * 加水印（字符串）
     *
     * @param inputFile     需要加水印的PDF路径
     * @param outputFile    输出生成PDF的路径
     * @param waterMarkName 水印字符
     */
    public static void stringWaterMark(String inputFile, String waterMarkName) {
        try {
            String[] spe = DataUtil.separatePath(inputFile);
            String outputFile = spe[0] + "_WM." + spe[1];

            PdfReader reader = new PdfReader(inputFile);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputFile));


//添加中文字体
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);


            int total = reader.getNumberOfPages() + 1;


            PdfContentByte under;
            int j = waterMarkName.length();
            char c = 0;
            int rise = 0;
//给每一页加水印
            for (int i = 1; i < total; i++) {
                rise = 400;
                under = stamper.getUnderContent(i);
                under.beginText();
                under.setFontAndSize(bfChinese, 30);
                under.setTextMatrix(200, 120);
                for (int k = 0; k < j; k++) {
                    under.setTextRise(rise);
                    c = waterMarkName.charAt(k);
                    under.showText(c + "");
                }


// 添加水印文字
                under.endText();
            }
            stamper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加水印（图片）
     *
     * @param inputFile  需要加水印的PDF路径
     * @param outputFile 输出生成PDF的路径
     * @param imageFile  水印图片路径
     */
    public static void imageWaterMark(String inputFile, String imageFile) {
        try {
            String[] spe = DataUtil.separatePath(inputFile);
            String outputFile = spe[0] + "_WM." + spe[1];

            PdfReader reader = new PdfReader(inputFile);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputFile));

            int total = reader.getNumberOfPages() + 1;

            Image image = Image.getInstance(imageFile);
            image.setAbsolutePosition(-100, 0);//坐标
            image.scaleAbsolute(800, 1000);//自定义大小
            //image.setRotation(-20);//旋转 弧度
            //image.setRotationDegrees(-45);//旋转 角度
            //image.scalePercent(50);//依照比例缩放

            PdfGState gs = new PdfGState();
            gs.setFillOpacity(0.2f);// 设置透明度为0.2


            PdfContentByte under;
            //给每一页加水印
            for (int i = 1; i < total; i++) {
                under = stamper.getUnderContent(i);
                under.beginText();
                // 添加水印图片
                under.addImage(image);
                under.setGState(gs);
            }
            stamper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置左边距
     *
     * @param str
     * @param i
     * @return
     */
    public static String leftPad(String str, int i) {
        int addSpaceNo = i - str.length();
        String space = "";
        for (int k = 0; k < addSpaceNo; k++) {
            space = " " + space;
        }
        ;
        String result = space + str;
        return result;
    }

    /**
     * 设置模拟数据
     *
     * @param list
     * @param num
     */
    public static void add(List<String> list, int num) {
        for (int i = 0; i < num; i++) {
            list.add("test" + i);
        }
    }

    /**
     * 设置间距
     *
     * @param tmp
     * @return
     */
    public static String printBlank(int tmp) {
        String space = "";
        for (int m = 0; m < tmp; m++) {
            space = space + " ";
        }
        return space;
    }

}
