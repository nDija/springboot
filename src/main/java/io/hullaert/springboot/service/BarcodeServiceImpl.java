package io.hullaert.springboot.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.encoder.SymbolShapeHint;
import com.google.zxing.oned.Code39Writer;
import com.google.zxing.oned.EAN13Writer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;

public class BarcodeServiceImpl implements BarCodeService{

    @Override
    public BitMatrix generateEAN13BarcodeImage(String barcodeText) {
        EAN13Writer barcodeWriter = new EAN13Writer();
        Hashtable hints = new Hashtable<String, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.EAN_13, 300, 150, hints);

        return bitMatrix;
    }

    @Override
    public BitMatrix generateCode39BarcodeImage(String barcodeText) {
        Code39Writer barcodeWriter = new Code39Writer();
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        //hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 10);
        //hints.put(EncodeHintType.PDF417_COMPACT, true);

        return barcodeWriter.encode(barcodeText, BarcodeFormat.CODE_39, 0, 25, hints);
    }

    @Override
    public BufferedImage getAsBufferededImage(BitMatrix bitMatrix) {
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    @Override
    public File getAsPNG(String outputFileName, BitMatrix bitMatrix) throws IOException {
        File pngFile = new File(outputFileName);
        FileOutputStream fileOutputStream = new FileOutputStream(pngFile);
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", fileOutputStream);
        fileOutputStream.close();
        return pngFile;
    }

/**    public void aspose() {
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, "Aspose.BarCode");
// set resolution
        generator.getParameters().setResolution(400);
// generate barcode
        generator.save("generate-barcode.png");
    }**/
}
