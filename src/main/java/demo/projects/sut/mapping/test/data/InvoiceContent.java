package demo.projects.sut.mapping.test.data;

import lombok.Getter;
import lombok.Setter;

/**
 * Parsed Pdf Invoice object.
 */
@Getter
@Setter
public class InvoiceContent {
    /**
     * Total tax.
     */
    private String totalTax;

    /**
     * Total purchase price.
     */
    private String total;
}
