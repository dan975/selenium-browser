package demo.projects.sut.mapping.test.data.supporting;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Class encapsulates image comparison thresholds that if met when passed to
 * {@link demo.projects.test.framework.helpers.image.comparison.ImageComparator} determine,
 * if element is displayed as expected.
 */
@Component
@Getter
public class ImageComparisonThresholds {

    @Value("${primaryImageThreshold}")
    private double productLargeImage;

    @Value("${adComparisonThreshold}")
    private double ad;

    @Value("${siteLogoThreshold}")
    private double siteLogo;
}
