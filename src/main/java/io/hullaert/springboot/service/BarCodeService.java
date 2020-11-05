package io.hullaert.springboot.service;

import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
public interface BarCodeService {

    BitMatrix generateEAN13BarcodeImage(String barCode);

    BitMatrix generateCode39BarcodeImage(String barCode);

    BufferedImage getAsBufferededImage(BitMatrix bitMatrix);

    File getAsPNG(String outputFileName, BitMatrix bitMatrix) throws IOException;
}
