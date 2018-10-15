package com.cs.tiny.beans.io;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author benjaminChan
 * @date 2018/10/15 0015 上午 10:57
 */
public class UrlResource implements Resource {

    private final URL url;

    public UrlResource(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws Exception {
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        return urlConnection.getInputStream();
    }
}
