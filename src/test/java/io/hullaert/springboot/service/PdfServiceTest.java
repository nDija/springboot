package io.hullaert.springboot.service;

import com.google.zxing.common.BitMatrix;
import com.itextpdf.text.DocumentException;
import io.hullaert.springboot.TicketField;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PdfServiceTest {

    private PDFService pdfService = new PDFServiceImpl();
    private BarCodeService barCodeService = new BarcodeServiceImpl();
    private String barCode39 = "F0129000116";

    @Test
    public void createPDFTest() throws IOException, DocumentException {
        BitMatrix bitMatrix = barCodeService.generateCode39BarcodeImage(barCode39);
        String tmpdir = System.getProperty("java.io.tmpdir");
        System.out.println("Temp file path: " + tmpdir);
        File file = barCodeService.getAsPNG(tmpdir + barCode39 + ".png", bitMatrix);
        Map<String, String> ticketMap = new HashMap<>();
        ticketMap.put(TicketField.RETIREMENT_DATE, "12/09/2019");
        ticketMap.put(TicketField.CLIENT_NAME, "LISA CHETE");
        ticketMap.put(TicketField.PRODUCT_ID, "1100458");
        ticketMap.put(TicketField.PRODUCT_NAME, "Tablette HUAWEI RE7 Projet BOM M5 10.8'");
        ticketMap.put(TicketField.CODE_BARE_IMG_SRC, tmpdir + file.getName());
        ticketMap.put(TicketField.CODE39, barCode39);
        pdfService.createPDF("templateTicket.html", ticketMap, tmpdir);

    }
}
