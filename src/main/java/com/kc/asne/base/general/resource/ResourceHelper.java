package com.kc.asne.base.general.resource;

import com.kc.asne.asne.util.parser.CustomParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;

public class ResourceHelper {
    public static byte[] getResourceAsBytes(String path) {
        InputStream stream = ResourceHelper.class.getClassLoader().getResourceAsStream(path);
        try {
            assert stream != null;
            byte[] bytes = new byte[stream.available()];
            stream.read(bytes);
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
