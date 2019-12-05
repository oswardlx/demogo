package com.example.util.test;

import com.example.entity.PdfStuInfoModel;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 成绩总表打印基础工具类
 *
 * @author jiangyy
 */
@Service
public class JwglxtXscjzbUtil {
    /**
     * @return com.itextpdf.text.Font
     * @description 添加font名参数
     * @author 刘鑫（1661）
     * @Params [widthRatioArr, order, length, maxSize, fontName]
     * @date 2019/9/17 9:49
     */
    public Font setFontRatioCN(int[] widthRatioArr, int order, int length, int maxSize, BaseFont bfChinese) throws IOException, DocumentException {
        double ratio = getRatio(widthRatioArr, order);
        Font font = setFontRatioCN(ratio, length, maxSize, bfChinese);
        return font;
    }


    /**
     * @创建人:liuxin
     * @描述: 只支持横版或竖版，以及内容的长度进行对字体大小的自适应(限于中文），支持自定义边距，20190524.其他情况之后做 20190604自定义边距只支持竖版
     * @创建日期: 11:42 2019/5/24
     * @参数：widthRatioArr order
     * inputStr
     */
    public Font setFontRatioCN(int[] widthRatioArr, int order, int length, int maxSize, boolean verticalVersion, float sumMarginLeftRight) throws IOException, DocumentException {
        double ratio = getRatio(widthRatioArr, order);
        Font font = setFontRatioCnPro(ratio, length, maxSize, verticalVersion, sumMarginLeftRight);
        return font;
    }

    /**
     * @创建人:liuxin
     * @描述: 通过顺序获得比例
     * @创建日期: 11:44 2019/6/15
     * @参数：widthRatioArr order
     */
    public double getRatio(int[] widthRatioArr, int order) {
        int sumWidth = getIntegerArrSum(widthRatioArr);
        double ratio = (double) widthRatioArr[order] / sumWidth;
        return ratio;
    }

    /**
     * 整型数组求和
     *
     * @param arr
     * @return
     */
    public int getIntegerArrSum(int[] arr) {
        if (arr != null && arr.length > 0) {
            int sumWidth = 0;
            for (int width : arr) {
                sumWidth += width;
            }
            return sumWidth;
        } else {
            return 0;
        }
    }


    /**
     * @return com.itextpdf.text.Font
     * @description 添加font名参数
     * @author 刘鑫（1661）
     * @Params [ratio, length, maxSize]
     * @date 2019/9/17 9:50
     */
    public Font setFontRatioCN(double ratio, int length, int maxSize, BaseFont bfChinese) throws IOException, DocumentException {
        //字体
        //获取左右边距为16的换算常量
        double unit = getUnit16HorizontalVersion();
        //计算以此字体大小，最多能塞进多少字
        int tempLength = (int) (ratio / (maxSize / Math.sqrt(2)) / unit);
        //与需要塞进的字数做对比。若装不下字体大小减1。循环得出刚好的盒子大小。20190603我为什么不直接用需要塞进的字数反推算出字体大小呢？还在这循环。真傻
        while (tempLength < length) {
            maxSize--;
            tempLength = (int) (ratio / (maxSize / Math.sqrt(2)) / unit);
        }
        if (maxSize < 1) {
            maxSize = 1;
        }
        Font cellFont = new Font(bfChinese, maxSize, Font.NORMAL);
        return cellFont;
    }

    /**
     * @创建人:liuxin
     * @描述: 获取当左右边距都为16时（横版）。一个中文字宽度换算成宽占比的换算常数。 一个中文字宽度*unit = 一个中文字的宽占比
     * 字数计算公式 ： 宽占比=中文字数*一个中文字宽度*unit
     * 但是今天仅限于左右边距为16的时候
     * @创建日期: 20:21 2019/6/3
     * @参数：
     */
    private double getUnit16HorizontalVersion() {
        return 0.00186846;
    }

    /**
     * @创建人:liuxin
     * @描述: 获取当左右边距都为16时（横版）。一个中文字宽度换算成宽占比的换算常数。 一个中文字宽度*unit = 一个中文字的宽占比
     * 字数计算公式 ： 宽占比=中文字数*一个中文字宽度*unit
     * 但是今天仅限于左右边距为16的时候
     * @创建日期: 20:21 2019/6/3
     * @参数：
     */
    private double getUnit16VerticalVersion() {
        return 0.0029;
    }

    /**
     * @创建人:liuxin
     * @描述: 获取字号，与纸张大小的换算参数，字号*unit = 在纸上的大小。限于A4
     * @创建日期: 20:21 2019/6/3
     * @参数：
     * @see PageSize
     */
    private double getUnit(boolean verticalVersion) {
        double unit;
        if (verticalVersion) {
            unit = 1.15;
        } else {
            unit = 1.23;
        }
        return unit;
    }

    /**
     * @创建人:liuxin
     * @描述: 获取英文字号，与纸张大小的换算参数，字号*unit = 在纸上的大小。限于A4
     * @创建日期: 20:21 2019/6/3
     * @参数：
     * @see PageSize
     */
    private double getUnitEn(boolean verticalVersion) {
        double unit;
        if (verticalVersion) {
            unit = 0.556;//竖版版比例还为算出
        } else {
            unit = 0.556;
        }
        return unit;
    }

    /**
     * @return int
     * @throws
     * @description 获取自适应字体大小 中文  A4 纸
     * @author lx(1661)
     * @date 2019/6/29 15:31
     * @see com.zfsoft.jwglxt.bygl.util.JwglxtXscjzbUtil#getRatioFontSizePro(double, String, int, Rectangle, float)
     * @deprecated 用see里的这个
     */
    public int getRatioFontSize(double ratio, int length, int maxSize, boolean verticalVersion, float sumMarginLeftRight) {
        double unit;
        unit = getUnit(verticalVersion);
        int resultSize = caculateFontSize(ratio, length, maxSize, verticalVersion, sumMarginLeftRight, unit);
        return resultSize;
    }

    /**
     * @创建人:liuxin
     * @描述: 考虑页边距, a4, 竖版
     * @创建日期: 9:49 2019/6/4
     * @参数：ratio length
     * maxSize
     * verticalVersion
     */
    public Font setFontRatioCnPro(double ratio, int length, int maxSize, boolean verticalVersion, float sumMarginLeftRight) throws IOException, DocumentException {
        //获取左右边距为16的换算常量
        int resultSize = getRatioFontSize(ratio, length, maxSize, verticalVersion, sumMarginLeftRight);
        //字体
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font cellFont = new Font(bfChinese, resultSize, Font.NORMAL);
        return cellFont;
    }

    /**
     * @return com.itextpdf.text.Font
     * @description 考虑页边距, a4, 竖版 添加字体！！
     * @author 刘鑫（1661）
     * @Params [ratio, length, maxSize, verticalVersion, sumMarginLeftRight, bfChinese]
     * @date 2019/9/21 14:46
     */
    public Font setFontRatioCnPro(double ratio, int length, int maxSize, boolean verticalVersion, float sumMarginLeftRight, BaseFont bfChinese) throws IOException, DocumentException {
        //获取左右边距为16的换算常量
        int resultSize = getRatioFontSize(ratio, length, maxSize, verticalVersion, sumMarginLeftRight);
        Font cellFont = new Font(bfChinese, resultSize, Font.NORMAL);
        return cellFont;
    }


    /**
     * @return int
     * @throws
     * @description 获得自适应字体大小Pro ！！！！！！！
     * 建议设置最小字号与行高一起配合，比如当字号最小为6，行高为17时当字过多会换行。
     * 添加字体名称来确定参数使之更加精确
     * @author lx(1661)
     * @date 2019/7/12 11:17
     */
    public int getRatioFontSizePro(double ratio, String str, int maxSize, Rectangle pageSize, float sumMarginLeftRight, String fontName) {
        int length = caculateStrLength(str);
        //com.itextpdf.text.pdf.PdfPCell 里面默认paddingLeft,和paddingRigh 都是2 所以要两个都减掉来精确计算
        int paddingRightLeft = 4;
        double totalWidth = (pageSize.getWidth() - sumMarginLeftRight) * ratio - paddingRightLeft;
        double unit = getUnit(getPageSizeRotation(fontName));
        int fontSize = caculateFontSizePro(totalWidth, length, maxSize, unit);
        return fontSize;
    }

    /**
     * @return com.zfsoft.jwglxt.bygl.util.PageSizeRotation
     * @throws
     * @description 得到页面大小和方向
     * @author lx(1661)
     * @date 2019/7/12 14:14
     */
    public PageSizeRotation getPageSizeRotation(Rectangle pageSize) {
        // TODO: 2019/10/9  待验证纸张方向是否会影响自适应参数
//		float width = pageSize.getWidth();
//		float height = pageSize.getHeight();
//		if (width == 842 && height == 595) {
        return PageSizeRotation.A4H;
//		} else if (width == 595 && height == 842) {
//			return PageSizeRotation.A4V;
//		} else {
//			return PageSizeRotation.A4H;
//		}
    }

    /**
     * @return com.zfsoft.jwglxt.bygl.util.PageSizeRotation
     * @description 通过字体返名称回自适应枚举标识
     * @author 刘鑫（1661）
     * @Params [fontName]
     * @date 2019/10/9 14:24
     */
    public PageSizeRotation getPageSizeRotation(String fontName) {
        PageSizeRotation result = null;
        switch (fontName) {
            case "STSong-Light":
                result = PageSizeRotation.STSongLight;
                break;
            default:
                result = PageSizeRotation.AlibabaPuHuiTiR;
        }
        return result;

    }

    public double caculateTableWidth(Rectangle pageSize, float sumML) {
        double result;
        float realWidth;
        if (pageSize.getRotation() == 90 || pageSize.getRotation() == 270) {
            realWidth = pageSize.getHeight();
        } else {
            realWidth = pageSize.getWidth();
        }
        result = realWidth - sumML;
        return result;
    }

    /**
     * @return int
     * @throws
     * @description 统计长度，数字和字母算1，其他算2 适用于微软雅黑
     * @author lx(1661)
     * @date 2019/7/12 11:25
     */
    public int caculateStrLength(String str) {
        if (str == null || str.length() < 1) {
            return 1;
        } else {
            char[] charArr = str.toCharArray();
            double raitoLength = 0;
            int result;
            for (char c : charArr) {
                boolean b = c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
                if (c >= '0' && c <= '9') {
                    raitoLength++;
                } else if (b) {
                    raitoLength++;
                } else if (c == ' ') {
                    raitoLength++;
                } else {
                    raitoLength += 2;
                }
            }
            result = (int) Math.ceil(raitoLength);
            return result;
        }
    }

    /**
     * @创建人:liuxin
     * @描述: 考虑页边距, a4, 竖版
     * @创建日期: 9:49 2019/6/4
     * @参数：ratio length
     * maxSize
     * verticalVersion
     */
    public Font setFontRatioEnPro(double ratio, int length, int maxSize, boolean verticalVersion, float sumMarginLeftRight) throws IOException, DocumentException {
        //字体
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        //获取左右边距为16的换算常量
        double unit;
        unit = getUnitEn(verticalVersion);
        int resultSize = caculateFontSize(ratio, length, maxSize, verticalVersion, sumMarginLeftRight, unit);
        Font cellFont = new Font(bfChinese, resultSize, Font.NORMAL);
        return cellFont;
    }

    /**
     * @return int
     * @throws
     * @description 计算字体大小
     * @author lx(1661)
     * @date 2019/7/2 15:23
     */
    private int caculateFontSize(double ratio, int length, int maxSize, boolean verticalVersion, float sumMarginLeftRight, double unit) {
        double totalWidth = (verticalVersion ? 595 : 842) - sumMarginLeftRight;
        int resultSize;
        resultSize = (int) Math.floor(ratio * totalWidth / (length * unit));
        if (resultSize > maxSize) {
            resultSize = maxSize;
        }
        return resultSize;
    }

    /**
     * @return int
     * @throws
     * @description 计算字体大小Pro ！！！！！
     * @author lx(1661)
     * @date 2019/7/12 10:24
     */
    private int caculateFontSizePro(double totalWidth, int length, int maxSize, double unit) {
        int resultSize;
//		resultSize = (int)Math.floor(totalWidth/length/unit/(Math.sqrt(2)/4));
//		unit =1.14344;
        resultSize = (int) Math.floor((totalWidth / length / unit) * Math.sqrt(5));
        resultSize = resultSize > maxSize ? maxSize : resultSize;
        return resultSize;
    }

    public PdfPCell getBorderedCell(PdfPCell cell, int border) {
        cell.setBorder(border);
        return cell;
    }//新建需加


    /**
     * @return com.itextpdf.text.pdf.PdfPTable
     * @description 快速生成中文大小自适应table 添加字体参数
     * @author 刘鑫（1661）
     * @Params [widthRatioArr, sumMarginLeftRight, requireBorder, isVertical, pdfStuInfoModels, bfChinese]
     * @date 2019/9/21 14:45
     */
    public PdfPTable getTableAdaptive(int[] widthRatioArr, float sumMarginLeftRight, boolean requireBorder, boolean isVertical, List<PdfStuInfoModel> pdfStuInfoModels, BaseFont bfChinese) throws IOException, DocumentException {
        PdfPTable result = new PdfPTable(widthRatioArr.length);
        result.setWidthPercentage(100f);
        result.setWidths(widthRatioArr);
        PdfPCell PdfPCell;
        String mc;
        Font font;
        for (PdfStuInfoModel pModel : pdfStuInfoModels) {
            mc = pModel.getItemInfo();
            if (pModel.isAdaptive()) {
                font = setFontRatioCnPro(pModel.getRatio(), mc.length(), pModel.getMaxSize(), isVertical, sumMarginLeftRight, bfChinese);
            } else {
                font = setFontRatioCnPro(0.99, 1, pModel.getMaxSize(), isVertical, sumMarginLeftRight, bfChinese);
            }
            PdfPCell = makeCell(mc, pModel.getHorizontalSpan(), pModel.getVerticalSpan(), pModel.getHorizontalAlignmentName(), pModel.getVerticalAlignmentName(), font);
            if (!requireBorder) {
                PdfPCell = getBorderedCell(PdfPCell, 0);
            }
            result.addCell(PdfPCell);
        }
        return result;
    }

    /**
     * 构造一个自定义的cell<br/>
     * 由于使用PdfPCellx无法合并行,所以此方法中一些属性无法使用.
     *
     * @param content 单元格内容
     * @param colspan 合并列
     * @param rowspan 合并行 (deprecated)
     * @param align   横向对齐
     * @param valign  纵向对齐
     * @param font    单元格字体
     * @return 生成的单元格
     */
    public static PdfPCell makeCell(String content, int colspan, int rowspan, int align, int valign, Font font) {
        PdfPCell cell = null;
        Paragraph paragraph = null;
        //使用自定义字体
        paragraph = new Paragraph(content, font);
        cell = new PdfPCell(paragraph);
        //设置colspan,同样的方法可以设置rowspan
        if (colspan > 1) {
            cell.setColspan(colspan);
        }
        if (rowspan > 1) {
            cell.setRowspan(rowspan);
        }
        //设置对齐
        cell.setHorizontalAlignment(align);
        cell.setVerticalAlignment(valign);
        cell.setMinimumHeight(15);
        return cell;
    }

    /**
     * @return void
     * @description 水印加透明度参数
     * @author 刘鑫（1661）
     * @Params [imageFilePath, inputFile, outputFile, absoluteX, absoluteY, percent]
     * @date 2019/9/12 9:40
     */
    public void addSeal(String imageFilePath, String inputFile, String outputFile, float absoluteX, float absoluteY, float percent, float ca) {
        try {
            PdfReader reader = new PdfReader(inputFile);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputFile));
            int total = reader.getNumberOfPages() + 1;
            Image image = Image.getInstance(imageFilePath);
            image.setAbsolutePosition(absoluteX, absoluteY);//absoluteX-到页面左边的距离，absoluteY-到页面下面的距离
            image.setAlignment(Image.MIDDLE);
            image.scalePercent(percent);//依照比例缩放
            PdfContentByte under;
            for (int i = 1; i < total; i++) {
                under = stamper.getOverContent(i);
                // 添加图片
                under.addImage(image);
                PdfGState gs = new PdfGState();
                gs.setFillOpacity(ca);// 设置透明度为0.2
                gs.setStrokeOpacity(ca);// 设置透明度为0.2
                under.setGState(gs);
            }
            stamper.close();
            reader.close();
            executorDeleteFile(inputFile, 10, 5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @return com.itextpdf.text.pdf.PdfPTable
     * @throws
     * @description 快速生成中文大小自适应table，添加可以自定义字体,和高度 ！！！！
     * @author lx(1661)
     * @date 2019/6/29 15:42
     */
    public PdfPTable getTableAdaptivePro(int[] widthRatioArr, float sumMarginLeftRight, int border, Rectangle pageSize, List<PdfStuInfoModel> pdfStuInfoModels) throws IOException, DocumentException {
        PdfPTable result = new PdfPTable(widthRatioArr.length);
        result.setWidthPercentage(100f);
        result.setWidths(widthRatioArr);
        PdfPCell PdfPCell;
        String mc;
        //字体
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        int fontSize = 9;
        Font cellFont = new Font(bfChinese, 9, Font.NORMAL);
        for (PdfStuInfoModel pModel : pdfStuInfoModels) {
            mc = pModel.getItemInfo();
            //处理字体样式和大小自适应的关系
            if (pModel.isAdaptive() && pModel.getFont() != null) {
                fontSize = getRatioFontSizePro(pModel.getRatio(), mc, pModel.getMaxSize(), pageSize, sumMarginLeftRight, bfChinese.getPostscriptFontName());
                if (pModel.getMinSize() > 0 && fontSize <= pModel.getMinSize()) {
                    fontSize = pModel.getMinSize();
                } else if (fontSize <= 6) {
                    fontSize = 6;
                }
                cellFont = new Font(pModel.getFont().getBaseFont(), fontSize, pModel.getFont().getStyle());
            } else if (pModel.isAdaptive() && pModel.getFont() == null) {
                fontSize = getRatioFontSizePro(pModel.getRatio(), mc, pModel.getMaxSize(), pageSize, sumMarginLeftRight, bfChinese.getPostscriptFontName());
                if (fontSize <= 6) {
                    fontSize = 6;
                }
                cellFont = new Font(bfChinese, fontSize, Font.NORMAL);
            } else if (!pModel.isAdaptive() && pModel.getFont() != null) {
                cellFont = pModel.getFont();
            } else if (!pModel.isAdaptive() && pModel.getFont() == null) {
                cellFont = new Font(bfChinese, pModel.getMaxSize(), Font.NORMAL);
            }
            PdfPCell = makeCell(mc, pModel.getHorizontalSpan(), pModel.getVerticalSpan(), pModel.getHorizontalAlignmentName(), pModel.getVerticalAlignmentName(), cellFont);
            //处理行高的关系
            int cacuHeight = caculateHeightByFontSize(fontSize);
            if (pModel.getHeight() <= cacuHeight) {
                PdfPCell.setFixedHeight(cacuHeight);
            } else {
                PdfPCell.setFixedHeight(pModel.getHeight());
            }
            if (pModel.getBorder() > 0) {
                PdfPCell.setBorder(pModel.getBorder());
            }
            PdfPCell.setBorder(border);
            result.addCell(PdfPCell);
        }
        return result;
    }

    /**
     * @return com.itextpdf.text.pdf.PdfPTable
     * @description 添加字体    ！！！！！！
     * @author 刘鑫（1661）
     * @Params [widthRatioArr, sumMarginLeftRight, border, pageSize, pdfStuInfoModels, bfChinese]
     * @date 2019/9/21 14:41
     */
    public PdfPTable getTableAdaptivePro(int[] widthRatioArr, float sumMarginLeftRight, int border, Rectangle pageSize, List<PdfStuInfoModel> pdfStuInfoModels, BaseFont bfChinese) throws IOException, DocumentException {
        PdfPTable result = new PdfPTable(widthRatioArr.length);
        result.setWidthPercentage(100f);
        result.setWidths(widthRatioArr);
        PdfPCell PdfPCell;
        String mc;
        //字体
        int fontSize = 9;
        Font cellFont = new Font(bfChinese, 9, Font.NORMAL);
        for (PdfStuInfoModel pModel : pdfStuInfoModels) {
            mc = pModel.getItemInfo();
            //处理字体样式和大小自适应的关系   自适应且提供固定字体
            if (pModel.isAdaptive() && pModel.getFont() != null) {
                fontSize = getRatioFontSizePro(pModel.getRatio(), mc, pModel.getMaxSize(), pageSize, sumMarginLeftRight, bfChinese.getPostscriptFontName());
                if (pModel.getMinSize() > 0 && fontSize <= pModel.getMinSize()) {
                    fontSize = pModel.getMinSize();
                } else if (fontSize <= 6) {
                    fontSize = 6;
                }
                cellFont = new Font(pModel.getFont().getBaseFont(), fontSize, pModel.getFont().getStyle());
            } else if (pModel.isAdaptive() && pModel.getFont() == null) {
//				自适应且未提供字体
                fontSize = getRatioFontSizePro(pModel.getRatio(), mc, pModel.getMaxSize(), pageSize, sumMarginLeftRight, bfChinese.getPostscriptFontName());
                if (fontSize <= 6) {
                    fontSize = 6;
                }
                cellFont = new Font(bfChinese, fontSize, Font.NORMAL);
            } else if (!pModel.isAdaptive() && pModel.getFont() != null) {
                cellFont = new Font(bfChinese, pModel.getFont().getSize(), pModel.getFont().getStyle());
                ;
            } else if (!pModel.isAdaptive() && pModel.getFont() == null) {
                cellFont = new Font(bfChinese, pModel.getMaxSize(), Font.NORMAL);
            }
            PdfPCell = makeCell(mc, pModel.getHorizontalSpan(), pModel.getVerticalSpan(), pModel.getHorizontalAlignmentName(), pModel.getVerticalAlignmentName(), cellFont);
            //处理行高的关系
            int cacuHeight = caculateHeightByFontSize(fontSize);
            if (pModel.getHeight() <= cacuHeight) {
                PdfPCell.setFixedHeight(cacuHeight);
            } else {
                PdfPCell.setFixedHeight(pModel.getHeight());
            }
            if (pModel.getBorder() > 0) {
                PdfPCell.setBorder(pModel.getBorder());
            }
            PdfPCell.setBorder(border);
            result.addCell(PdfPCell);
        }
        return result;
    }


    /**
     * @return java.util.List<com.zfsoft.util.print.PdfPCell>
     * @description 添加自定义字体
     * @author 刘鑫（1661）
     * @Params [sumMarginLeftRight, requireBorder, isVertical, pdfStuInfoModels, bfChinese]
     * @date 2019/9/21 14:43
     */
    public List<PdfPCell> getCellListAdaptivePro(float sumMarginLeftRight, boolean requireBorder, boolean isVertical, List<PdfStuInfoModel> pdfStuInfoModels, BaseFont bfChinese) throws IOException, DocumentException {
        List<PdfPCell> PdfPCelles = new ArrayList<>();
        PdfPCell PdfPCell;
        String mc;
        //字体
        Font cellFont = new Font(bfChinese, 9, Font.NORMAL);
        for (PdfStuInfoModel pModel : pdfStuInfoModels) {
            int fontSize = 9;
            mc = pModel.getItemInfo();
            if (pModel.isAdaptive()) {
                fontSize = getRatioFontSize(pModel.getRatio(), mc.length(), pModel.getMaxSize(), isVertical, sumMarginLeftRight);
            }
            if (pModel.getFont() != null) {
                cellFont = pModel.getFont();
            } else {
                int fonStyle = 0;
                if (pModel.getFontStyle() > 0) {
                    fonStyle = pModel.getFontStyle();
                }
                cellFont = new Font(bfChinese, fontSize, fonStyle);
            }
            PdfPCell = makeCell(mc, pModel.getHorizontalSpan(), pModel.getVerticalSpan(), pModel.getHorizontalAlignmentName(), pModel.getVerticalAlignmentName(), cellFont);
            if (!requireBorder) {
                PdfPCell = getBorderedCell(PdfPCell, 0);
            }
            PdfPCelles.add(PdfPCell);
        }
        return PdfPCelles;
    }


    /**
     * @return java.util.List<com.zfsoft.util.print.PdfPCell>
     * @throws
     * @description 中英文数字字符混合自适应，加自定义字体，和行高！！！！
     * @author lx(1661)
     * @date 2019/8/14 15:30
     */
    public List<PdfPCell> getCellListAdaptivePro(float sumMarginLeftRight, int border, Rectangle pageSize, List<PdfStuInfoModel> pdfStuInfoModels) throws IOException, DocumentException {

        List<PdfPCell> PdfPCelles = new ArrayList<>();
        PdfPCell PdfPCell;
        String mc;
        //字体
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font cellFont;
        int fontSize = 9;
        for (PdfStuInfoModel pModel : pdfStuInfoModels) {
            mc = pModel.getItemInfo();
            if (pModel.isAdaptive()) {
                fontSize = getRatioFontSizePro(pModel.getRatio(), mc, pModel.getMaxSize(), pageSize, sumMarginLeftRight, bfChinese.getPostscriptFontName());
            }
            if (pModel.getMinSize() > 0 && fontSize <= pModel.getMinSize()) {
                fontSize = pModel.getMinSize();
            } else if (fontSize <= 6) {
                fontSize = 6;
            }
            if (pModel.getFont() != null) {
                cellFont = pModel.getFont();
            } else {
                cellFont = new Font(bfChinese, fontSize, Font.NORMAL);
            }
            PdfPCell = makeCell(mc, pModel.getHorizontalSpan(), pModel.getVerticalSpan(), pModel.getHorizontalAlignmentName(), pModel.getVerticalAlignmentName(), cellFont);
            PdfPCell.setBorder(border);
            if (pModel.getBorder() > 0) {
                PdfPCell.setBorder(pModel.getBorder());
            }
            if (pModel.getHeight() > 0) {
                PdfPCell.setFixedHeight(pModel.getHeight());
            }

            PdfPCelles.add(PdfPCell);
            fontSize = 9;
        }
        return PdfPCelles;
    }


    /**
     * @return java.util.List<com.zfsoft.util.print.PdfPCell>
     * @description 添加自定义字体  ！！！！gogogo
     * @author 刘鑫（1661）
     * @Params [sumMarginLeftRight, border, pageSize, pdfStuInfoModels, bfChinese]
     * @date 2019/9/21 14:40
     */
    public List<PdfPCell> getCellListAdaptivePro(float sumMarginLeftRight, int border, Rectangle pageSize, List<PdfStuInfoModel> pdfStuInfoModels, BaseFont bfChinese) throws IOException, DocumentException {
        List<PdfPCell> PdfPCelles = new ArrayList<>();
        try {
            PdfPCell PdfPCell;
            String mc;
            //字体
            Font cellFont = new Font(bfChinese, 9, Font.NORMAL);
            int fontSize = 9;
            for (PdfStuInfoModel pModel : pdfStuInfoModels) {
                mc = pModel.getItemInfo();
                //处理字体样式和大小自适应的关系   自适应且提供固定字体
                if (pModel.isAdaptive() && pModel.getFont() != null) {
                    fontSize = getRatioFontSizePro(pModel.getRatio(), mc, pModel.getMaxSize(), pageSize, sumMarginLeftRight, bfChinese.getPostscriptFontName());
                    if (pModel.getMinSize() > 0 && fontSize <= pModel.getMinSize()) {
                        fontSize = pModel.getMinSize();
                    } else if (fontSize <= 6) {
                        fontSize = 6;
                    }
                    cellFont = new Font(pModel.getFont().getBaseFont(), fontSize, pModel.getFont().getStyle(), pModel.getFont().getColor());
                } else if (pModel.isAdaptive() && pModel.getFont() == null) {
//				自适应且未提供字体
                    fontSize = getRatioFontSizePro(pModel.getRatio(), mc, pModel.getMaxSize(), pageSize, sumMarginLeftRight, bfChinese.getPostscriptFontName());
                    if (fontSize <= 6) {
                        fontSize = 6;
                    }
                    cellFont = new Font(bfChinese, fontSize, Font.NORMAL);
                } else if (!pModel.isAdaptive() && pModel.getFont() != null) {
                    cellFont = new Font(bfChinese, pModel.getFont().getSize(), pModel.getFont().getStyle(), pModel.getFont().getColor());
                    ;
                } else if (!pModel.isAdaptive() && pModel.getFont() == null) {
                    cellFont = new Font(bfChinese, pModel.getMaxSize(), Font.NORMAL);
                }
//                //是否自适应
//                if (pModel.isAdaptive()) {
//                    fontSize = getRatioFontSizePro(pModel.getRatio(), mc, pModel.getMaxSize(), pageSize, sumMarginLeftRight, bfChinese.getPostscriptFontName());
//                }
//                //限制最小字号
//                if (pModel.getMinSize() > 0 && fontSize <= pModel.getMinSize()) {
//                    fontSize = pModel.getMinSize();
//                } else if (fontSize <= 6) {
//                    fontSize = 6;
//                }
//                //使用传入字体的样式
//                if (pModel.getFont() != null && pModel.isAdaptive()) {
//                    cellFont = new Font(pModel.getFont().getBaseFont(), fontSize, pModel.getFont().getStyle(), pModel.getFont().getColor());
//                } else {
//                    cellFont = new Font(bfChinese, fontSize, Font.NORMAL);
//                }
                PdfPCell = makeCell(mc, pModel.getHorizontalSpan(), pModel.getVerticalSpan(), pModel.getHorizontalAlignmentName(), pModel.getVerticalAlignmentName(), cellFont);
                //统一设置边框
                PdfPCell.setBorder(border);
//			PdfPCell.setPaddingx(10,0,0,0);
                //单独设置边框
                if (pModel.getBorder() > 0) {
                    PdfPCell.setBorder(pModel.getBorder());
                }
                //设置单元格高度
                if (pModel.getHeight() > 0) {
                    PdfPCell.setFixedHeight(pModel.getHeight());
                }
                PdfPCelles.add(PdfPCell);
                fontSize = 9;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PdfPCelles;
    }

    /**
     * @return java.util.List<com.zfsoft.util.print.PdfPCell>
     * @throws
     * @description 中英文数字字符混合自适应，加自定义字体，和行高！！！！  中国明航中文英文专用。他的情况有点 特殊没法满足只好单独写
     * @author lx(1661)
     * @date 2019/8/14 15:30
     */
    public List<PdfPCell> getZgmhCellListAdaptivePro(float sumMarginLeftRight, int border, Rectangle pageSize, List<PdfStuInfoModel> pdfStuInfoModels, BaseFont bfChinese) throws IOException, DocumentException {
        List<PdfPCell> PdfPCelles = new ArrayList<>();
        PdfPCell PdfPCell;
        String mc;
        Font cellFont;
        int fontSize = 9;
        for (PdfStuInfoModel pModel : pdfStuInfoModels) {
            mc = pModel.getItemInfo();
            String mc1;
            String mc2;
            Paragraph paragraph1 = new Paragraph();
            if (mc.indexOf("\n") > 0) {
                mc1 = mc.substring(0, mc.indexOf("\n"));
                mc2 = mc.substring(mc.indexOf("\n"));
                int size1 = getRatioFontSizePro(pModel.getRatio(), mc1, pModel.getMaxSize(), pageSize, sumMarginLeftRight, bfChinese.getPostscriptFontName());
                int size2 = getRatioFontSizePro(pModel.getRatio(), mc2, pModel.getMaxSize(), pageSize, sumMarginLeftRight, bfChinese.getPostscriptFontName());
                Chunk c1 = new Chunk(mc1, new Font(bfChinese, size1, Font.NORMAL));
                Chunk c2 = new Chunk(mc2 + "\n", new Font(bfChinese, size2, Font.NORMAL));
                paragraph1.add(c1);
                paragraph1.add(c2);
            } else {
                if (pModel.isAdaptive()) {
                    fontSize = getRatioFontSizePro(pModel.getRatio(), mc, pModel.getMaxSize(), pageSize, sumMarginLeftRight, bfChinese.getPostscriptFontName());
                }
                mc = pModel.getItemInfo();
                if (pModel.getFont() != null) {
                    cellFont = pModel.getFont();
                } else {
                    cellFont = new Font(bfChinese, fontSize, Font.NORMAL);
                }
                Chunk c1 = new Chunk(mc, cellFont);
                paragraph1.add(c1);
            }
            PdfPCell = makeCellPro(paragraph1, pModel.getHorizontalSpan(), pModel.getVerticalSpan(), pModel.getHorizontalAlignmentName(), pModel.getVerticalAlignmentName());
            PdfPCell.setBorder(border);
            if (pModel.getBorder() > 0) {
                PdfPCell.setBorder(pModel.getBorder());
            }
            if (pModel.getHeight() > 0) {
                PdfPCell.setFixedHeight(pModel.getHeight());
            }
            PdfPCell.setLeading(2, 1);
            PdfPCelles.add(PdfPCell);
            fontSize = 9;
        }
        return PdfPCelles;
    }

    /**
     * @return com.zfsoft.jwglxt.bygl.dao.xscjzbdy.entities.PdfStuInfoModel
     * @description 初始化无自定义字体
     * @author 刘鑫（1661）
     * @Params [info, ratio, maxSize, hSpan, vSpan, hAlign, vAlign, isAdaptive]
     * @Params [文本, 占比, 字体最大字号, 横向合并, 纵向合并, 横向对齐方式,纵向对齐方式, 是否字号自适应]
     * @date 2019/7/2 15:39
     */
    public PdfStuInfoModel getPdfStuInfoModel(String info, double ratio, int maxSize, int hSpan, int vSpan, int hAlign, int vAlign, boolean isAdaptive) {
        PdfStuInfoModel result = new PdfStuInfoModel(info, hAlign, vAlign, hSpan, vSpan, ratio, maxSize, isAdaptive);
        return result;
    }

    /**
     * @return com.zfsoft.jwglxt.bygl.dao.xscjzbdy.entities.PdfStuInfoModel
     * @description 加了高度
     * @author 刘鑫（1661）
     * @Params [info, ratio, maxSize, height, hSpan, vSpan, hAlign, vAlign, isAdaptive]
     * @Params [文本, 占比, 字体最大字号, 单元格高度， 横向合并, 纵向合并, 横向对齐方式,纵向对齐方式, 是否字号自适应]
     * @date 2019/9/29 9:15
     */
    public PdfStuInfoModel getPdfStuInfoModel(String info, double ratio, int maxSize, int height, int hSpan, int vSpan, int hAlign, int vAlign, boolean isAdaptive) {

        PdfStuInfoModel result = new PdfStuInfoModel(info, hAlign, vAlign, hSpan, vSpan, ratio, maxSize, isAdaptive);
        result.setHeight(height);
        return result;
    }

    /**
     * @return com.zfsoft.jwglxt.bygl.dao.xscjzbdy.entities.PdfStuInfoModel
     * @description 初始化有自定义字体
     * @author 刘鑫（1661）
     * @Params [info, ratio, font, hSpan, vSpan, hAlign, vAlign, isAdaptive]
     * @Params [文本, 占比, 固定字体， 横向合并, 纵向合并, 横向对齐方式, 纵向对齐方式, 是否字号自适应]
     * @date 2019/9/29 9:16
     */
    public PdfStuInfoModel getPdfStuInfoModel(String info, double ratio, Font font, int hSpan, int vSpan, int hAlign, int vAlign, boolean isAdaptive) {

        PdfStuInfoModel result = new PdfStuInfoModel(info, hAlign, vAlign, hSpan, vSpan, ratio, font, isAdaptive);
        return result;
    }

    /**
     * @return com.zfsoft.jwglxt.bygl.dao.xscjzbdy.entities.PdfStuInfoModel
     * @throws
     * @description 初始化有自定义字体
     * @author lx(1661)
     * @date 2019/7/2 15:39
     */
    public PdfStuInfoModel getPdfStuInfoModel(String info, double ratio, Font font, int maxSize, int hSpan, int vSpan, int hAlign, int vAlign, boolean isAdaptive) {
        PdfStuInfoModel result = new PdfStuInfoModel(info, hAlign, vAlign, hSpan, vSpan, ratio, font, maxSize, isAdaptive);
        return result;
    }

    /**
     * @return com.zfsoft.jwglxt.bygl.dao.xscjzbdy.entities.PdfStuInfoModel
     * @throws
     * @description 初始化有自定义字体
     * @author lx(1661)
     * @date 2019/7/2 15:39
     */
    public PdfStuInfoModel getPdfStuInfoModel(String info, double ratio, Font font, int maxSize, int height, int hSpan, int vSpan, int hAlign, int vAlign, boolean isAdaptive) {
        PdfStuInfoModel result = new PdfStuInfoModel(info, hAlign, vAlign, hSpan, vSpan, ratio, font, maxSize, height, isAdaptive);
        return result;
    }

    /**
     * @return com.zfsoft.jwglxt.bygl.dao.xscjzbdy.entities.PdfStuInfoModel
     * @description 初始化
     * @author 刘鑫（1661）
     * @Params [cell]
     * @date 2019/9/25 16:35
     */
    public PdfStuInfoModel getPdfStuInfoModel(PdfPCell cell) {
        PdfStuInfoModel result = new PdfStuInfoModel(cell);
        return result;
    }

    /**
     * @return double
     * @throws
     * @description 用来保留小数位数，并四舍五入
     * @author lx(1661)
     * @date 2019/7/3 11:37
     */
    public String formatDecimal(String value, int scale) {
        String result;
        if (!value.matches("-?[0-9]*.[0-9]*") || scale < 0) {
            result = value;
            return result;
        }
        BigDecimal b = new BigDecimal(value);
        double f1 = b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        result = String.valueOf(f1);
        if (scale == 0) {
            result = String.valueOf((int) f1);
        }
        if (result.startsWith(".")) {
            result = "0" + result;
        }
        return result;
    }

    /**
     * @return double
     * @throws
     * @description 从根据枚举类获取宽度转换因数
     * @author lx(1661)
     * @date 2019/7/12 11:52
     */
    public double getUnit(PageSizeRotation psr) {
        double result;
        switch (psr) {
            case A4H:
                result = 1.2;
                break;
            case A4V:
                result = 1.2;
                break;
            case STSongLight:
                result = 1.12;
                break;
            case AlibabaPuHuiTiR:
                //不要怀疑，看看比例是否是相对纸张的
                result = 1.17;
                break;
            default:
                result = 1.17;
        }
        return result;
    }

    /**
     * @return int
     * @throws
     * @description 通过字号计算cell适合的高度，不完美
     * @author lx(1661)
     * @date 2019/7/12 16:47
     */
    public int caculateHeightByFontSize(int fontSize) {
        int result;
        result = (int) Math.ceil(fontSize * 1.79 / Math.sqrt(2)) + 1;
        return result;
    }

    /**
     * @return int
     * @throws
     * @description 通过高度计算字号。不完美
     * @author lx(1661)
     * @date 2019/7/15 9:56
     */
    public int caculageFontSizebyHeight(int height) {
        int result;
        result = (int) Math.floor((height) * Math.sqrt(2) / 1.79) - 2;
        return result;
    }

    /**
     * @return float
     * @throws
     * @description 边距根据厘米转float
     * @author lx(1661)
     * @date 2019/8/13 11:34
     */
    public float getMarginNumByCM(float cent) {
        if (cent > 0) {
            return cent * 595 / 21;
        }
        return 0;
    }

    /**
     * @return com.itextpdf.text.pdf.PdfPTable
     * @description 创建table
     * @author 刘鑫（1661）
     * @Params [widths, widthPercentage, border]
     * @date 2019/9/11 9:31
     */
    public PdfPTable createTable(int[] widths, float widthPercentage, int border) throws DocumentException {
        if (!(widths.length > 0)) {
            throw new RuntimeException("创建表格失败，请检查宽占比参数！");
        }
        PdfPTable result = new PdfPTable(widths.length);
        result.setWidths(widths);
        result.setWidthPercentage(widthPercentage);
        result.getDefaultCell().setBorder(border);
        return result;
    }

    /**
     * @return com.itextpdf.text.pdf.PdfPTable
     * @description 创建table
     * @author 刘鑫（1661）
     * @Params [widths, border]
     * @date 2019/9/11 9:30
     */
    public PdfPTable createTable(int[] widths, int border) throws DocumentException {
        if (!(widths.length > 0)) {
            throw new RuntimeException("创建表格失败，请检查宽占比参数！");
        }
        PdfPTable result = new PdfPTable(widths.length);
        result.setWidths(widths);
        result.setWidthPercentage(100f);
        result.getDefaultCell().setBorder(border);
        return result;
    }

    /**
     * @return com.itextpdf.text.pdf.PdfPTable
     * @description 创建TABle
     * @author 刘鑫（1661）
     * @Params [widths]
     * @date 2019/9/11 9:32
     */
    public PdfPTable createTable(int[] widths) throws DocumentException {
        if (!(widths.length > 0)) {
            throw new RuntimeException("创建表格失败，请检查宽占比参数！");
        }
        PdfPTable result = new PdfPTable(widths.length);
        result.setWidths(widths);
        result.setWidthPercentage(100f);
        result.getDefaultCell().setBorder(PdfPCell.BOX);
        return result;
    }

    /**
     * @return com.zfsoft.util.print.PdfPCell
     * @throws
     * @description 制作格子
     * @author lx(1661)
     * @date 2019/9/3 8:41
     */
    public PdfPCell makeCellPro(Paragraph paragraph, int colspan, int rowspan, int align, int valign) {
        PdfPCell cell = null;
        cell = new PdfPCell(paragraph);
        //设置colspan,同样的方法可以设置rowspan
        if (colspan > 1) {
            cell.setColspan(colspan);
        }
        if (rowspan > 1) {
            cell.setRowspan(rowspan);
        }
        //设置对齐
        cell.setHorizontalAlignment(align);
        cell.setVerticalAlignment(valign);
        cell.setMinimumHeight(15);
        return cell;
    }

    /**
     * @return boolean
     * @description 删除文件
     * @author 刘鑫（1661）
     * @Params [filename, filepath]
     * @date 2019/9/20 13:53
     */
    public boolean deleteFile(String filepath) {
        File file = new File(filepath);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            file.delete();
            return true;
        }
    }

    /**
     * @return void
     * @description 异步定时多次删除文件
     * @author 刘鑫（1661）
     * @Params [filePath, Times, millies]
     * @date 2019/9/20 15:53
     */
    public void executorDeleteFile(String filePath, int Times, long millies) {
        ExecutorService executor = Executors.newCachedThreadPool();
        try {
            String finalPath = filePath;
            executor.submit(new Runnable() {
                public void run() {
                    try {
                        for (int i = 0; i < Times; i++) {
                            Thread.sleep(millies);
                            if (deleteFile(finalPath)) {
                                break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("删除报表文件线程出错！");
                    }
                }
            });
            executor.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除报表文件失败！！");
        }
    }

    /**
     * @return com.itextpdf.text.pdf.PdfPCell
     * @description 获取照片Cell
     * @author 刘鑫（1661）
     * @Params [image, imgHeight, imgWidth, rowSpan, colSpan, hAlign, vAlign, border]
     * @date 2019/10/23 14:18
     */
    public PdfPCell getPhotoCell(Image image, float imgHeight, float imgWidth, int rowSpan, int colSpan, int hAlign, int vAlign, int border) {
        PdfPCell result;
        if (image != null) {
            image.scaleAbsoluteHeight(imgHeight);
            image.scaleAbsoluteWidth(imgWidth);
            result = new PdfPCell(image);
            result.setRowspan(rowSpan);
            result.setColspan(colSpan);
            result.setHorizontalAlignment(hAlign);
            result.setVerticalAlignment(vAlign);
            result.setBorder(border);
            return result;
        } else {
            result = new PdfPCell();
            result.setRowspan(1);
            result.setColspan(1);
            result.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            result.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            result.setBorder(PdfPCell.BOX);
            return result;
        }
    }

    /**
     * @return com.itextpdf.text.pdf.PdfPTable
     * @description 写入数据项+猎头
     * @author 刘鑫（1661）
     * @Params [perPageRowNum, pageColNum, scoreHeight, maxSize, arrays, bfChinese, marginLeftRight, rectangle, allXscjCellList, cellNummap, dataTable, dataArr, pdfPCellArr, height]
     * @date 2019/10/25 11:39
     */
    public PdfPTable writeDataShoot(List<PdfPCell> PdfPCelllts, int perPageRowNum, int pageColNum, int scoreHeight, List<PdfPCell> allXscjCellList, HashMap<String, Integer> cellNummap, PdfPTable dataTable, PdfPTable[] dataArr, PdfPCell[] pdfPCellArr) throws IOException, DocumentException {
        //定位
        int fromNum = cellNummap.get("fromNum");
        //写入列头
        for (PdfPCell pCellx : PdfPCelllts) {
            for (PdfPTable pt : dataArr) {
                pt.addCell(pCellx);
            }
        }
        //写入数据格子
        for (PdfPTable pt : dataArr) {
            for (int x = fromNum; x < allXscjCellList.size(); x++) {
                if (pt.getRows().size() >= 0 && pt.getRows().size() < perPageRowNum) {
                    allXscjCellList.get(x).setFixedHeight(scoreHeight);
                    pt.addCell(allXscjCellList.get(x));
                    continue;
                }
                fromNum = x;
                break;
            }
        }
        //table写入布局
        for (int x = 0; x < pageColNum; x++) {
            pdfPCellArr[x].addElement(dataArr[x]);
            dataTable.addCell(pdfPCellArr[x]);
        }
        cellNummap.put("fromNum", fromNum);
        return dataTable;
    }

    /**
     * @return java.util.List<com.zfsoft.util.print.PdfPCell>
     * @description 传入字符串数组，转单元格list
     * @author 刘鑫（1661）
     * @Params [maxSize, arrays, bfChinese, marginLeftRight, rectangle, height]
     * @date 2019/10/25 11:44
     */
    public List<PdfPCell> getPdfPCelles(String[] arrays, int maxSize, int height, BaseFont bfChinese, float marginLeftRight, Rectangle rectangle) throws IOException, DocumentException {
        List<PdfStuInfoModel> rowModelList = new ArrayList<>();
        for (int arrIndex = 0; arrIndex < arrays.length; arrIndex++) {
            rowModelList.add(getPdfStuInfoModel(arrays[arrIndex], (double) 1, maxSize, height, 1, 1, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, false));
        }
        return getCellListAdaptivePro(marginLeftRight, PdfPCell.BOX, rectangle, rowModelList, bfChinese);
    }

    /**
     * @return java.util.List<java.lang.String>
     * @description 获取chunk的参数表达式提取map的key   input{aaa}dfs{bbbb}   output:aaa,bbb
     * @author 刘鑫（1661）
     * @Params [input]
     * @date 2019/10/30 21:27
     */
    public List<String> getKeyList(String input) {
        if (input == null || input.length() < 1) {
            return null;
        }
        List<String> result = new ArrayList<>();
        char[] inputCharArr = input.toCharArray();
        int startIndex = 0;
        int endIndex = 0;
        boolean openFlag = false;
        for (int x = 0; x < inputCharArr.length; x++) {
            if ('{' == inputCharArr[x]) {
                startIndex = x;
                openFlag = true;
            } else if (openFlag && '}' == inputCharArr[x]) {
                endIndex = x;
                openFlag = false;
                result.add(input.substring(startIndex + 1, endIndex));
            }
        }
        return result;
    }
}
