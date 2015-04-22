package com.bikesandwheels.tools;

import java.io.*;
import java.net.*;

public class FileUtils {
    public static URL filePathToUrl(String filePath) throws FileNotFoundException, MalformedURLException {
        File file = new File(filePath);
        if (!file.exists())
            throw new FileNotFoundException();
        if (!file.isFile())
            throw new IllegalArgumentException(String.format("%s is not valid file path", filePath));
        return file.toURI().toURL();
    }
}
