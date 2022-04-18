package com.wx.cmd;

import java.io.*;
import java.nio.file.Files;
import java.util.Properties;

public class InitCmd {
    private final Properties returnProperties = new Properties();

    public InitCmd() {
        InputStream is = InitCmd.class.getClassLoader().getResourceAsStream("shorcut.properties");
        try {
            returnProperties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public InitCmd(String filePath) {
        try (InputStream fis = Files.newInputStream(new File(filePath).toPath())) {
            returnProperties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Properties getReturnProperties() {
        return returnProperties;
    }
}
