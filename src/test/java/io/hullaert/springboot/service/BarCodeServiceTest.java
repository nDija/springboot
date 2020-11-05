package io.hullaert.springboot.service;

import com.google.zxing.common.BitMatrix;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class BarCodeServiceTest {

    private BarCodeService barCodeService = new BarcodeServiceImpl();
    private final String barCode = "401234567890";
    private final String barCode39 = "F0129000116";

    @Test
    void getAsPNGTest() throws IOException {
        BitMatrix bitMatrix = barCodeService.generateEAN13BarcodeImage(barCode);
        File file = barCodeService.getAsPNG("test.png", bitMatrix);
        assertTrue(file.exists());
    }

    @Test
    void getAsBufferededImageTest() {
        BitMatrix bitMatrix = barCodeService.generateEAN13BarcodeImage(barCode);
        BufferedImage bufferedImage = barCodeService.getAsBufferededImage(bitMatrix);
        assertNotNull(bufferedImage);
    }
    @Test
    void getAsPNGCode39Test() throws IOException {
        String tmpdir = System.getProperty("java.io.tmpdir");
        System.out.println("Temp file path: " + tmpdir);
        BitMatrix bitMatrix = barCodeService.generateCode39BarcodeImage(barCode39);
        File file = barCodeService.getAsPNG("testCode39.png", bitMatrix);
        assertTrue(file.exists());
    }

    @Test
    void getAsBufferededImageCode39Test() {
        BitMatrix bitMatrix = barCodeService.generateCode39BarcodeImage(barCode39);
        BufferedImage bufferedImage = barCodeService.getAsBufferededImage(bitMatrix);
        assertNotNull(bufferedImage);
    }

}
