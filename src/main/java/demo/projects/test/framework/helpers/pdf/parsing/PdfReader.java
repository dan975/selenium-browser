package demo.projects.test.framework.helpers.pdf.parsing;

import lombok.SneakyThrows;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Pdf reader helper.
 */
@Component
class PdfReader {

    /**
     * @param pdfDocument Pdf file.
     * @return Pdf file text content.
     */
    @SneakyThrows
    public String getPDFDocumentContent(File pdfDocument) {
        try (PDDocument document = PDDocument.load(pdfDocument)) {
            return new PDFTextStripper().getText(document);
        }
    }
}
