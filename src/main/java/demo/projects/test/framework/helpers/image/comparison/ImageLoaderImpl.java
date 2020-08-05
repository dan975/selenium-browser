package demo.projects.test.framework.helpers.image.comparison;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Image loader implementation.
 */
@Component
public class ImageLoaderImpl extends ImageLoader {

    @Override
    public NamedBufferedImage[] getNotCategorizedImages(String imageDirPath) {
        File imageDirectory = getResourceFile(imageDirPath);

        return getNamedBufferedImagesDirectlyUnderDir(imageDirectory);
    }

    @SneakyThrows
    private File getResourceFile(String imagePath) {
        var resourceFile = getImageDirectory() + imagePath;
        return ResourceUtils.getFile(resourceFile);
    }

    @SneakyThrows
    private BufferedImage getBufferedImage(File imageFile) {
        return ImageIO.read(imageFile);
    }

    @Override
    public Map<String, NamedBufferedImage[]> getCategorizedImages(String imageDirPath) {
        File imageDirectory = getResourceFile(imageDirPath);
        File[] categoryDirs = getAllFilesDirectlyUnderDirectory(imageDirectory);

        return getCategorizedImages(categoryDirs);
    }

    private File[] getAllFilesDirectlyUnderDirectory(File directory) {
        return directory.listFiles();
    }

    private Map<String, NamedBufferedImage[]> getCategorizedImages(File[] categoryDirs) {
        Map<String, NamedBufferedImage[]> res = Collections.synchronizedMap(new HashMap<>());
        for (File categoryDir : categoryDirs) {
            addCategoryAndCategoryImages(res, categoryDir);
        }

        return res;
    }

    private void addCategoryAndCategoryImages(Map<String, NamedBufferedImage[]> res, File categoryDir) {
        NamedBufferedImage[] resEntry = getNamedBufferedImagesDirectlyUnderDir(categoryDir);
        res.put(categoryDir.getName(), resEntry);
    }


    private NamedBufferedImage[] getNamedBufferedImagesDirectlyUnderDir(File categoryDir) {
        File[] imageFiles = categoryDir.listFiles();
        @SuppressWarnings("ConstantConditions")
        NamedBufferedImage[] resEntry = new NamedBufferedImage[imageFiles.length];

        for (int i = 0; i < imageFiles.length; i++) {
            resEntry[i] = getNamedBufferedImage(imageFiles[i]);
        }

        return resEntry;
    }

    private NamedBufferedImage getNamedBufferedImage(File imageFile) {
        BufferedImage bufferedImage = getBufferedImage(imageFile);

        return getNamedBufferedImage(imageFile, bufferedImage);
    }

    private NamedBufferedImage getNamedBufferedImage(File imageFile, BufferedImage bufferedImage) {
        String fileName = getFileName(imageFile);
        return new NamedBufferedImage(fileName, bufferedImage);
    }

    @Override
    public NamedBufferedImage getImage(String imageFilePath) {
        File imageFile = getResourceFile(imageFilePath);

        return getNamedBufferedImage(imageFile);
    }

    private String getFileName(File imageFile) {
        var name = imageFile.getName();
        return name.substring(0, name.lastIndexOf('.'));
    }
}
