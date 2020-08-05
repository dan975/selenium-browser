package demo.projects.test.framework.helpers.image.comparison;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

/**
 * Image loading helper.
 */
public abstract class ImageLoader {

    @Value("${imageDirectory}")
    @Getter(AccessLevel.PROTECTED)
    private String imageDirectory;

    /**
     * @param imageDirPath Path to image directory under {@link ImageLoader#imageDirectory}.
     * @return Not categorized images stored directly under the passed directory.
     */
    public abstract NamedBufferedImage[] getNotCategorizedImages(String imageDirPath);

    /**
     * Returns map of categorized images under given path.
     * <p>
     * Categorized directory structure should be as follows:
     * for each category a directory is present.
     * Directly under category directory image files for that category are present.
     * @param imageDirPath Path to image directory under {@link ImageLoader#imageDirectory}.
     * @return Map of categorized images: key - category name, value - category's images.
     */
    public abstract Map<String, NamedBufferedImage[]> getCategorizedImages(String imageDirPath);

    /**
     * @param imageFilePath Image file path under {@link ImageLoader#imageDirectory}.
     * @return Image stored in file.
     */
    public abstract NamedBufferedImage getImage(String imageFilePath);
}
