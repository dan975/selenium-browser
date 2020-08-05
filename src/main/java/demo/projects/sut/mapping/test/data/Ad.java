package demo.projects.sut.mapping.test.data;

import com.opencsv.bean.CsvBindByPosition;
import demo.projects.test.framework.helpers.image.comparison.NamedBufferedImage;
import demo.projects.test.framework.helpers.test.data.loader.TestDataWithNotCategorizedImages;
import lombok.Getter;

import java.awt.image.BufferedImage;

/**
 * Expected ad image and redirect URL content substring.
 */
@SuppressWarnings("checkstyle:MagicNumber")
public class Ad implements TestDataWithNotCategorizedImages {

    /**
     * Expected ad image.
     */
    @Getter
    private BufferedImage image;

    /**
     * Image path for ad.
     */
    @CsvBindByPosition(position = 0)
    private String imagePath;

    /**
     * Expected Redirect link content for ad.
     */
    @Getter
    @CsvBindByPosition(position = 1)
    private String redirectLink;

    @Override
    public void setNotCategorizedImagesForObject(NamedBufferedImage[] namedImages) {
        image = namedImages[0].getBufferedImage();
    }

    @Override
    public String getImagePath() {
        return imagePath;
    }

    @Override
    public int getInternalId() {
        throw new RuntimeException("Not implemented for test data");
    }
}
