package demo.projects.test.framework.helpers.pdf.parsing;

import demo.projects.sut.mapping.test.data.InvoiceContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Scanner;

/**
 * Pdf file parser to test data objects.
 */
@Component
public class PdfParser {
    private static final String TOTAL_TAX_START = "Total Tax";
    private static final String TOTAL_START = "Total";

    @Autowired
    private PdfReader pdfReader;

    /**
     * @param invoiceFile Invoice pdf file.
     * @return Parsed invoice pdf file test data object.
     */
    public InvoiceContent parseInvoice(File invoiceFile) {
        InvoiceContent invoiceContent = new InvoiceContent();

        try (var input = new Scanner(pdfReader.getPDFDocumentContent(invoiceFile))) {
            var totalTaxValue = iterateUntilFound(input, TOTAL_TAX_START)
                    .substring(TOTAL_TAX_START.length())
                    .trim();
            invoiceContent.setTotalTax(totalTaxValue);

            var totalValue = iterateUntilFound(input, TOTAL_START)
                    .substring(TOTAL_START.length())
                    .trim();
            invoiceContent.setTotal(totalValue);
        }

        return invoiceContent;
    }

    private String iterateUntilFound(Scanner input, String lineStartMatch) {
        while (input.hasNextLine()) {
            var line = input.nextLine();
            if (line.startsWith(lineStartMatch)) {
                return line;
            }
        }

        throw new RuntimeException("No match found with input line starting: " + lineStartMatch);
    }
}
