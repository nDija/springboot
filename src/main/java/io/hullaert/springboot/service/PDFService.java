package io.hullaert.springboot.service;

import com.itextpdf.text.DocumentException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public interface PDFService {

    File createPDF(String template, Map<String, String> data, String outputDir) throws IOException, DocumentException;
}
