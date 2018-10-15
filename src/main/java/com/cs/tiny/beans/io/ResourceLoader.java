package com.cs.tiny.beans.io;

import java.net.URL;

/**
 * @author benjaminChan
 * @date 2018/10/15 0015 上午 10:59
 */
public class ResourceLoader {

    public Resource getResources(String location) {
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }
}
