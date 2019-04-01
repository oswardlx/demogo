package com.example.util.map;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 继承AbstractPdfView类，实现抽象方法buildPdfDocument
 */
public class PdfView extends AbstractPdfView {


    private PdfExportService pdfExportService = null;

    /**
     * 创建对象时载入导出服务接口
     * @param pdfExportService
     */
    public PdfView(PdfExportService pdfExportService){
        this.pdfExportService = pdfExportService;
    }

    /**
     * 调用接口实现导出
     * @param map
     * @param document
     * @param pdfWriter
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws Exception
     */
    @Override
    protected void buildPdfDocument(Map<String, Object> map, Document document, PdfWriter pdfWriter,
                                    HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        pdfExportService.make(map,document,pdfWriter,httpServletRequest,httpServletResponse);

    }
}
