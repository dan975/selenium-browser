package demo.projects.test.framework.helpers.image.comparison;

import demo.projects.test.framework.helpers.file.util.FileUtilHelper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Abstract class defining Image comparator implementations.
 */
@Slf4j
public abstract class ImageComparator {

    private static final String DEBUG_IMAGE_PATH = "target/debug/";
    private static int debugImageCounter = 0;

    @Value("${imageDirectory}")
    @Getter(AccessLevel.PROTECTED)
    private String imageDirectory;

    @Value("${enableImageDebug}")
    private boolean isDebugImagesEnabled;

    @Value("${enableImageComparison}")
    private boolean isImageComparisonEnabled;

    /**
     * Overloaded {@link ImageComparator#compareImages(BufferedImage, BufferedImage, double)} method loads images from
     * passed file paths and then calls former overloaded comparison method.
     * @param basePath Expected image's path.
     * @param targetPath Actual image's path.
     * @param comparisonThreshold Minimum comparison score to assert that images matched.
     * @return Comparison score, if images matched.
     */
    @SneakyThrows
    public double compareImages(String basePath, String targetPath, double comparisonThreshold) {
        var targetImage = getBufferedImage(targetPath);
        var baseImage = getBufferedImage(basePath);

        return compareImages(baseImage, targetImage, comparisonThreshold);
    }

    /**
     * Method compares images and returns their comparison score, if images matched,
     * if images do not match throws ImagesDoNotMatchException exception.
     * In case of image dimension difference resizes images and then compares.
     * <p>
     * At the time of writing test framework has single implementation of comparator using Sikuli.
     * When comparing resized images of different resolutions, the comparison score drops.
     * Target image resolution cannot be too different from base image.
     * As an example in current SUT test suite,
     * comparison between product thumbnail image and stored full size product image is unreliable.
     * @param baseImage Expected image.
     * @param targetImage Actual image.
     * @param comparisonThreshold Minimum comparison score to assert that images matched.
     * @return Comparison score, if images matched.
     */
    public double compareImages(BufferedImage baseImage, BufferedImage targetImage, double comparisonThreshold) {
        if (!isImageComparisonEnabled) {
            log.debug("Image comparison is turned off");
            return 1;
        }

        if (isDebugImagesEnabled) {
            storeDebugImages(baseImage, targetImage);
        }

        log.debug("Comparing images.");
        var res = compareImagesHelper(baseImage, targetImage, comparisonThreshold);
        log.info("Images matched. Comparison result = {}, comparison threshold = {}.", res, comparisonThreshold);
        return res;
    }

    /**
     * Abstract method to get comparison score in {@link ImageComparator#compareImages(String, String, double)} method.
     * Implementations should throw ImagesDoNotMatchException exception
     * when comparison score is less than comparison threshold.
     * @param baseImage Expected image.
     * @param targetImage Actual image.
     * @param comparisonThreshold Minimum comparison score to assert that images matched.
     * @return Comparison score, if images matched.
     */
    @SuppressWarnings("checkstyle:LineLength")
    protected abstract double compareImagesHelper(BufferedImage baseImage, BufferedImage targetImage, double comparisonThreshold);

    protected BufferedImage getBufferedImage(String baseImagePath) throws IOException {
        return ImageIO.read(new File(getImageDirectory() + baseImagePath));
    }

    @SneakyThrows
    private static synchronized void storeDebugImages(BufferedImage base, BufferedImage target) {
        storeDebugImage(base, debugImageCounter + "_base");
        storeDebugImage(target, debugImageCounter + "_target");
        debugImageCounter++;
    }

    @SneakyThrows
    private static void storeDebugImage(BufferedImage image, String imageName) {
        File baseFile = getDebugImageFile(imageName);

        ImageIO.write(image, "png", baseFile);
    }

    private static File getDebugImageFile(String imageName) throws IOException {
        String filePath = DEBUG_IMAGE_PATH + imageName + ".png";
        FileUtilHelper.createDirectoriesForFile(filePath);

        File baseFile = new File(filePath);
        if (!baseFile.exists()) {
            boolean isDebugFileCreated = baseFile.createNewFile();
            if (!isDebugFileCreated) {
                log.warn("failed to create debug image file: {}.", baseFile.getAbsolutePath());
            }
        }
        log.debug("created debug image file: {}", baseFile.getAbsolutePath());

        return baseFile;
    }
}
