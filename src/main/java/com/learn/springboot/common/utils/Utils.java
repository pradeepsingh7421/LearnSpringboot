package com.learn.springboot.common.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

/**
 * Utility
 *
 * @author Pradeep
 */
public class Utils {

    private static final Logger mLogger = LoggerFactory.getLogger(Utils.class);

    /**
     * <b>readFile</b> -Reads File from FilePath
     *
     * String pFilePath - the File path in String
     * 
     * @return - returns data in string
     */
    public static String readFile(final String pFilePath) {
        String lContent = null;
        try {
            final File lFile = ResourceUtils.getFile("classpath:" + pFilePath);
            lContent = new String(Files.readAllBytes(lFile.toPath()));
        } catch (IOException pException) {
            mLogger.warn("File not found", pException);
        }
        return lContent;
    }

}
