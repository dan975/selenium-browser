package demo.projects.test.framework.helpers.image.comparison;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.awt.image.BufferedImage;

/**
 * Buffered image wrapper storing image file's name without file extension as name of image.
 */
@AllArgsConstructor
@Getter
public class NamedBufferedImage {
    private final String fileName;
    private final BufferedImage bufferedImage;
}
