package demo.projects.cucumber.step.def;

import demo.projects.sut.mapping.pages.OrderHistoryPage;
import demo.projects.test.framework.helpers.pdf.parsing.PdfParser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.LinkedList;
import java.util.List;

import static demo.projects.Constants.ONE_SECOND_IN_MILLISECONDS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OrderHistoryPageSteps extends BaseSteps {

    @Value("${pdfDownloadTimeInSeconds}")
    private int pdfDownloadTimeInSeconds;

    @Autowired
    private OrderHistoryPage orderHistoryPage;

    @Value("${downloadDir}")
    private String downloadDir;

    @Autowired
    private PdfParser pdfParser;

//    Step might be flaky when tests executed in parallel since latest pdf might be from other test case.
//    Can create separate directories in download folder for different test cases.
    @SneakyThrows
    @When("User downloads latest invoice pdf and sees purchase total {string}")
    public void userDownloadsLatestInvoicePdfAndSeesPurchaseTotal(String expectedPurchaseTotal) {
        orderHistoryPage.getPastOrders().get(0).getInvoiceDownloadLink().click();

        Thread.sleep(pdfDownloadTimeInSeconds * ONE_SECOND_IN_MILLISECONDS);

        assertPurchaseTotalInPdf(expectedPurchaseTotal);
    }

    @Given("User has at least one older purchase in his order history")
    public void userHasAtLeastOneOlderPurchaseInHisOrderHistory() {
        assertTrue("No older orders found", orderHistoryPage.getPastOrders().size() > 0);
    }

    private void assertPurchaseTotalInPdf(String expectedTotal) {
        File latestPdfFile = getLatestPdfFile();

        attachPdfToReportStep(latestPdfFile);

        var invoiceContent = pdfParser.parseInvoice(latestPdfFile);
        assertEquals(expectedTotal, invoiceContent.getTotal());
    }

    @SneakyThrows
    private void attachPdfToReportStep(File latestPdfFile) {
        try (var is = Files.newInputStream(Paths.get(latestPdfFile.getAbsolutePath()))) {
            Allure.addAttachment("Latest Pdf file", is);
        }
    }

    public File getLatestPdfFile() {
        List<File> downloadedPdfFiles = getDownloadedPdfFiles();
        File latest = downloadedPdfFiles.remove(0);
        FileTime latestTime  = getFileTime(latest);

        for (File pdfFile : downloadedPdfFiles) {
            var currTime = getFileTime(pdfFile);

            if (currTime.compareTo(latestTime) > 0) {
                latest = pdfFile;
                latestTime = currTime;
            }
        }

        return latest;
    }

    @SneakyThrows
    private FileTime getFileTime(File file) {
        var filePath = Paths.get(file.getPath());
        var basicFileAttributes = Files.readAttributes(filePath, BasicFileAttributes.class);

        return basicFileAttributes.creationTime();
    }

    @SuppressWarnings("ConstantConditions")
    private List<File> getDownloadedPdfFiles() {
        List<File> res = new LinkedList<>();

        File pdfDir = new File(downloadDir);
        for (int i = 0; i < pdfDir.listFiles().length; i++) {
            File file = pdfDir.listFiles()[i];
            if (file.getName().endsWith(".pdf")) {
                res.add(file);
            }
        }

        return res;
    }
}
