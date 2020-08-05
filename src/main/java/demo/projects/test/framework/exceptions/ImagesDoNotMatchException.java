package demo.projects.test.framework.exceptions;

/**
 * Exception for failed comparison between images when comparing by
 * {@link demo.projects.test.framework.helpers.image.comparison.ImageComparator}.
 */
public class ImagesDoNotMatchException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Passed images do not match. "
            + "Comparison result = %f, comparison threshold = %f";

    /**
     * Returns Exception with default message.
     * @param comparisonResult Failed comparison result score.
     * @param comparisonThreshold Comparison threshold.
     */
    public ImagesDoNotMatchException(double comparisonResult, double comparisonThreshold) {
        super(String.format(DEFAULT_MESSAGE, comparisonResult, comparisonThreshold));
    }
}
