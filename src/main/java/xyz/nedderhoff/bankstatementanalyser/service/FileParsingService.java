package xyz.nedderhoff.bankstatementanalyser.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class FileParsingService {
    private static final Logger logger = LoggerFactory.getLogger(FileParsingService.class);

    public void process(File file) {
        String parsedFile = parsePDF(file);
        logger.debug("Parsed file: {}", parsedFile);

        // TODO process somehow
    }

    private String parsePDF(File file) {
        PDDocument document = null;
        try {
            document = PDDocument.load(file);
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            return pdfTextStripper.getText(document);
        } catch (IOException e) {
            logger.error("Error reading file {}", file);
            throw new RuntimeException(e);
        } finally {
            if (document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                    logger.error("Error closing file {}", file);
                }
            }
        }
    }
}
