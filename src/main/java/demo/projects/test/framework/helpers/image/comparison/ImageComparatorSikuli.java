package demo.projects.test.framework.helpers.image.comparison;

import demo.projects.test.framework.exceptions.ImagesDoNotMatchException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.sikuli.basics.Settings;
import org.sikuli.script.Finder;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.springframework.stereotype.Component;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Sikuli image comparator implementation.
 * <p>
 * Demo note:
 * Sikuli supports finding target image in screenshot and returning image's coordinates,
 * interacting with the element. However in case of absence of target image in passed screenshot,
 * due to the normalization algorithm implemented in Sikuli there is a high chance of false positives.
 * <p>
 * In current test framework only using Sikuli to match the passed images and all retrieval of GUI node elements,
 * interaction with elements is handled by Selenium and Appium driver extending Selenium driver.
 */
@Slf4j
@Component
public class ImageComparatorSikuli extends ImageComparator {
    private static final int IMAGE_NOT_FOUND_COMPARISON_VALUE = -1;
    private static final float MIN_THRESHOLD_VALUE = 0.1f;
    private static final float MAX_THRESHOLD_VALUE = 1;

    static {
        //Set Sikuli min sensitivity to min threshold value.
        //Image match assertion is handled in comparator implemented methods
        Settings.MinSimilarity = MIN_THRESHOLD_VALUE;
    }

    @Override
    @SuppressWarnings("checkstyle:LineLength")
    protected double compareImagesHelper(BufferedImage baseImage, BufferedImage targetImage, double comparisonThreshold) {
        validateThresholdArgumentWithinBounds(comparisonThreshold);

        baseImage = resizeImage(baseImage, targetImage.getWidth(), targetImage.getHeight());

        double comparisonResult = getImageComparison(baseImage, targetImage);
        verifyComparisonResultWithinThreshold(comparisonResult, comparisonThreshold);

        return comparisonResult;
    }

    private void validateThresholdArgumentWithinBounds(double comparisonThreshold) {
        if (comparisonThreshold <= MIN_THRESHOLD_VALUE || comparisonThreshold > MAX_THRESHOLD_VALUE) {
            String messageBase = "comparisonThreshold must be between %f and %f. Passed value: %f";
            String message = String.format(messageBase, MIN_THRESHOLD_VALUE, MAX_THRESHOLD_VALUE, comparisonThreshold);

            throw new IllegalArgumentException(message);
        }
    }

    @SneakyThrows
    private double getImageComparison(BufferedImage baseImage, BufferedImage targetImage) {
        Pattern target = getTargetImagePattern(targetImage);
        Finder base = getBaseImageFinder(baseImage);
        return compareImages(target, base);
    }

    @SneakyThrows
    private Pattern getTargetImagePattern(BufferedImage targetImage) {
        return new Pattern(targetImage);
    }

    private Finder getBaseImageFinder(BufferedImage baseImage) {
        return new Finder(baseImage);
    }

    private BufferedImage resizeImage(BufferedImage image, int newWidth, int newHeight) {
        BufferedImage res = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics2D = res.createGraphics();
        graphics2D.drawImage(image, 0, 0, newWidth, newHeight, null);
        graphics2D.dispose();

        return res;
    }

    private double compareImages(Pattern target, Finder base) {
        base.find(target);
        double score = IMAGE_NOT_FOUND_COMPARISON_VALUE;

        try {
            if (base.hasNext()) {
                Match m = base.next();
                score = m.getScore();
            }
        } finally {
            base.destroy();
        }

        return score;
    }

    private void verifyComparisonResultWithinThreshold(double comparisonResult, double comparisonThreshold) {
        if (comparisonResult < comparisonThreshold) {
            throw new ImagesDoNotMatchException(comparisonResult, comparisonThreshold);
        }
    }
}
