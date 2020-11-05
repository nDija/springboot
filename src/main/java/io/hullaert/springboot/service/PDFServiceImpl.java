package io.hullaert.springboot.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import io.hullaert.springboot.TicketField;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class PDFServiceImpl implements PDFService {

    @Override
    public File createPDF(String template, Map<String, String> data, String outputDir) throws IOException, DocumentException {
        File html = replaceFields(template, data, outputDir);
        Rectangle pageSize = new Rectangle(200f, 200f);
        Document document = new Document(pageSize, 10f, 0f, 20f, 0f);

        PdfWriter writer = PdfWriter.getInstance(document,
                new FileOutputStream(outputDir + "ticket_" + data.get(TicketField.CODE39) + ".pdf"));
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream(outputDir + html.getName()));
        document.close();
        return new File("test");
    }

    private File replaceFields(String template, Map<String, String> data, String outputDir) throws IOException {
        Path filePath = Paths.get(template);
        Path nFilePath = Paths.get(outputDir + "ticket_" + data.get(TicketField.CODE39) + ".html");
        Stream<String> lines = Files.lines(filePath , StandardCharsets.UTF_8);
        List<String> replacedLine = lines.map(line -> replaceWords(line, data)).collect(Collectors.toList());
        Files.write(nFilePath, replacedLine, StandardCharsets.UTF_8);
        lines.close();
        nFilePath.getFileName();
        return nFilePath.getFileName().toFile();
    }

    public static String replaceWords(String str, Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {

            if (str.contains(entry.getKey())) {
                str = str.replace(entry.getKey(), entry.getValue());
            }
        }
        return str;
    }
}
