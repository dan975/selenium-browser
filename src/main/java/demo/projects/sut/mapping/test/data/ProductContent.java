package demo.projects.sut.mapping.test.data;

import com.opencsv.bean.CsvBindByPosition;
import demo.projects.sut.mapping.test.data.supporting.TestDataBase;
import demo.projects.test.framework.driver.abstraction.WebElementWrapper;
import demo.projects.test.framework.helpers.image.comparison.NamedBufferedImage;
import demo.projects.test.framework.helpers.test.data.loader.TestDataWithCategorizedImages;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Expected product content object.
 */
@Slf4j
@ToString
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("checkstyle:MagicNumber")
public class ProductContent extends TestDataBase implements TestDataWithCategorizedImages {
    private static final String MAIN_IMAGE_KEY_DELIMITER = "_";
    private static final int PRODUCT_NOT_ON_SALE_PERCENTAGE = -1;

    /**
     * Internal product content id.
     */
    @CsvBindByPosition(position = 0)
    private int internalId;

    /**
     * Expected title.
     */
    @Getter
    @CsvBindByPosition(position = 1)
    private String title;

    /**
     * Regular product price.
     */
    @Getter
    @CsvBindByPosition(position = 2)
    private int regularPriceInCents;

    /**
     * Expected sale price.
     */
    @Getter
    @CsvBindByPosition(position = 3)
    private int salePriceInCents;

    /**
     * Expected sale, discount percentage.
     */
    @Getter
    @CsvBindByPosition(position = 4)
    private int salePercentage;

    /**
     * All categorized by color product and named by thumbnail id image path.
     */
    @CsvBindByPosition(position = 5)
    private String imagePath;

    /**
     * Expected main product image key expressed as
     * color + {@link ProductContent#MAIN_IMAGE_KEY_DELIMITER} + thumbnail id.
     */
    @CsvBindByPosition(position = 6)
    private String mainImage;

    /**
     * Expected product description.
     */
    @Getter
    @CsvBindByPosition(position = 7)
    private String description;

    /**
     * Color categorized images.
     * Key - color.
     * Value - all product images for given color.
     */
    private Map<String, ProductImage[]> colorImages;

    /**
     * Returns expected price in cents.
     * If product is on sale - sale price, if not on sale - regular price.
     * @return Expected product price in cents.
     */
    public int getPriceInCents() {
        return isProductOnSale() ? getSalePriceInCents() : getRegularPriceInCents();
    }

    /**
     * @return True - if expected for product to be on sale, false - if not on sale.
     */
    public boolean isProductOnSale() {
        return salePercentage != PRODUCT_NOT_ON_SALE_PERCENTAGE;
    }

    /**
     * @return Regular product price.
     */
    public double getRegularPrice() {
        return convertPriceFromCents(regularPriceInCents);
    }

    /**
     * @return Product on sale price.
     */
    public double getSalePrice() {
        return convertPriceFromCents(salePriceInCents);
    }

    /**
     * @param color Color for which to get expected images.
     * @return All product images for chosen color.
     */
    public ProductImage[] getImagesForColor(String color) {
        log.info("Get images for color: {}", color);
        return colorImages.get(color.toLowerCase());
    }

    /**
     * @return Expected main product image
     */
    public ProductImage getMainImage() {
        String[] colorCategoryKeyPair = mainImage.split(MAIN_IMAGE_KEY_DELIMITER);

        for (ProductImage productImage : colorImages.get(colorCategoryKeyPair[0])) {
            if (productImage.isMatchingImageId(colorCategoryKeyPair[1])) {
                return productImage;
            }
        }

        throw new RuntimeException("No image matching the mainImage key found, mainImage key = " + mainImage);
    }

    /**
     * Returns expected image for passed product thumbnail id.
     * Meant to be used when asserting the expected product image is displayed
     * when hovering on different thumbnails in ProductQuickViewPage
     * or in other test scenarios, where different than main image
     * is expected to be displayed.
     * @param thumbnailId Thumbnail "id" attribute value.
     * @return Expected image for thumbnail id.
     */
    public ProductImage getProductImageByThumbnailId(String thumbnailId) {
        for (ProductImage productImage : getAllProductImages()) {
            if (productImage.isMatchingImageThumbnailId(thumbnailId)) {
                return productImage;
            }
        }

        throw new RuntimeException("No image found matching passed thumbnail id = " + thumbnailId);
    }

    /**
     * @return Returns all product images.
     */
    public List<ProductImage> getAllProductImages() {
        List<ProductImage> res = new LinkedList<>();
        for (ProductImage[] value : colorImages.values()) {
            res.addAll(Arrays.asList(value));
        }

        return res;
    }

    @Override
    public String getImagePath() {
        return imagePath;
    }

    @Override
    public void setCategorizedImagesForObject(Map<String, NamedBufferedImage[]> categorizedImages) {
        this.colorImages = categorizedImages.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> Arrays.stream(e.getValue())
                                .map(ProductImage::new)
                                .toArray(ProductImage[]::new)
                ));
    }

    @Override
    public int getInternalId() {
        return internalId;
    }

    /**
     * Product image class containing methods for matching thumbnail id to image id.
     */
    @AllArgsConstructor
    public static class ProductImage {
        private static final String THUMBNAIL_ID_START = "thumbnail_";

        private final NamedBufferedImage image;

        /**
         * @return Expected product image.
         */
        public BufferedImage getImage() {
            return image.getBufferedImage();
        }

        /**
         * Method returns thumbnail {@link WebElementWrapper} with matching thumbnail "id" attribute value.
         * @param thumbnails all products thumbnail elements retrieved by call to
         *                   ProductQuickViewPage.getThumbnails() method or other where applicable.
         * @return Thumbnail matching element.
         */
        public WebElementWrapper getMatchingThumbnail(List<WebElementWrapper> thumbnails) {
            for (WebElementWrapper thumbnail : thumbnails) {
                String id = thumbnail.getAttribute("id");

                if (isMatchingImageThumbnailId(id)) {
                    return thumbnail;
                }
            }

            throw new RuntimeException("No Thumbnail found matching product image");
        }

        /**
         * @param thumbnailId Thumbnail "id" attribute value.
         * @return True - if image id matches thumbnail id, false - if thumbnail id does not match image id.
         */
        public boolean isMatchingImageThumbnailId(String thumbnailId) {
            String imageId = getImageIdFromThumbnailId(thumbnailId);
            return isMatchingImageId(imageId);
        }

        private boolean isMatchingImageId(String imageId) {
            return image.getFileName().equals(imageId);
        }

        private String getImageIdFromThumbnailId(String thumbnailId) {
            return thumbnailId.substring(THUMBNAIL_ID_START.length());
        }
    }

}
