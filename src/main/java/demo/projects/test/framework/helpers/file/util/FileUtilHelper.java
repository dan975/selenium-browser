package demo.projects.test.framework.helpers.file.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * File I/O helper utility class.
 */
@Slf4j
public final class FileUtilHelper {

    /**
     * Method creates directories for passed file if they don't exist.
     * @param filePath Path to file.
     */
    public static void createDirectoriesForFile(String filePath) {
        log.debug("checking if file requires directories to be created, file path: {}", filePath);
        int index = Math.max(filePath.lastIndexOf("/"), filePath.lastIndexOf("\\"));

        if (index != -1) {
            log.debug("file requires directories");
            String dirsStr = filePath.substring(0, index);
            var directory = new File(dirsStr);
            createDirectoriesIfNotPresent(directory);
        } else {
            log.debug("file does not require directory creation");
        }
    }

    /**
     * Creates directories if they don't exist.
     * @param directory Path to directory.
     */
    public static void createDirectoriesIfNotPresent(File directory) {
        log.debug("Checking if directories exist, for directory: {}", directory.getAbsolutePath());
        if (!directory.exists()) {
            boolean mkdirs = directory.mkdirs();
            if (!mkdirs) {
                log.warn("failed to create directories for directory: {}", directory);
            } else {
                log.debug("created directories successfully");
            }
        }
    }

    private FileUtilHelper() {
    }
}
